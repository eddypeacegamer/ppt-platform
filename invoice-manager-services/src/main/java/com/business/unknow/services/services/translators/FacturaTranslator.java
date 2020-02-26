package com.business.unknow.services.services.translators;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.unknow.Constants.FacturaComplemento;
import com.business.unknow.commons.factura.CdfiHelper;
import com.business.unknow.commons.factura.SignHelper;
import com.business.unknow.commons.util.DateHelper;
import com.business.unknow.commons.util.FacturaHelper;
import com.business.unknow.model.cfdi.Cfdi;
import com.business.unknow.model.cfdi.Complemento;
import com.business.unknow.model.cfdi.ComplementoPagos;
import com.business.unknow.model.cfdi.Concepto;
import com.business.unknow.model.cfdi.Translado;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.dto.cfdi.CfdiPagoDto;
import com.business.unknow.model.dto.cfdi.ComplementoDto;
import com.business.unknow.model.dto.cfdi.ConceptoDto;
import com.business.unknow.model.error.InvoiceCommonException;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.mapper.factura.FacturaCfdiTranslatorMapper;

@Service
public class FacturaTranslator {

	@Autowired
	private CdfiHelper cdfiHelper;

	@Autowired
	private FacturaHelper facturaHelper;

	@Autowired
	private DateHelper dateHelper;


	@Autowired
	private FacturaCfdiTranslatorMapper facturaCfdiTranslatorMapper;

	@Autowired
	private SignHelper signHelper;

	public FacturaContext translateFactura(FacturaContext context) throws InvoiceManagerException {
		try {
			Cfdi cfdi = facturaCfdiTranslatorMapper.cdfiRootInfo(context.getFacturaDto(), context.getEmpresaDto());
			BigDecimal totalImpuestos =new BigDecimal(0);
			List<Translado> impuestos = new ArrayList<>();
			for (ConceptoDto conceptoDto : context.getFacturaDto().getCfdi().getConceptos()) {
				Concepto concepto = facturaCfdiTranslatorMapper.cfdiConcepto(conceptoDto);
				cfdi.getConceptos().add(concepto);
				if (!conceptoDto.getImpuestos().isEmpty()) {
					totalImpuestos = calculaImpuestos(impuestos, concepto, totalImpuestos);
				}
			}
			if (!impuestos.isEmpty()) {
				cfdi.getImpuestos().setTranslados(impuestos);
			}
			cfdi.getImpuestos().setTotalImpuestosTrasladados(totalImpuestos.setScale(2, RoundingMode.HALF_UP));
			context.setCfdi(cfdi);
			facturaToXmlSigned(context);
			System.out.println(context.getXml());
			return context;
		} catch (InvoiceCommonException e) {
			e.printStackTrace();
			throw new InvoiceManagerException("Error generating the xml", e.getMessage(), HttpStatus.SC_CONFLICT);
		}
	}

	public FacturaContext translateComplemento(FacturaContext context) throws InvoiceManagerException {
		try {
			Cfdi cfdi = facturaCfdiTranslatorMapper.complementoRootInfo(context.getFacturaDto().getCfdi(),
					context.getEmpresaDto());
			for (ConceptoDto concepto : context.getFacturaDto().getCfdi().getConceptos()) {
				cfdi.getConceptos().add(facturaCfdiTranslatorMapper.complementoConcepto(concepto));
			}
			Complemento complemento = new Complemento();
			ComplementoPagos complementoPagos = new ComplementoPagos();
			complemento.setComplemntoPago(complementoPagos);
			for (CfdiPagoDto cfdiPago : context.getFacturaDto().getCfdi().getComplemento().getPagos()) {
				complementoPagos.getComplementoPagos().add(facturaCfdiTranslatorMapper.complementoComponente(cfdiPago));
			}
			cfdi.setComplemento(complemento);
			cfdi.setImpuestos(null);
			context.setCfdi(cfdi);
			complementoToXmlSigned(context);
			return context;
		} catch (InvoiceCommonException e) {
			throw new InvoiceManagerException("Error generating the xml", e.getMessage(), HttpStatus.SC_CONFLICT);
		}
	}

	public void complementoToXmlSigned(FacturaContext context) throws InvoiceCommonException {
		String xml = facturaHelper.facturaCfdiToXml(context.getCfdi());
		System.out.println(xml);
		xml = xml.replace(FacturaComplemento.TOTAL, FacturaComplemento.TOTAL_FINAL);
		xml = xml.replace(FacturaComplemento.SUB_TOTAL, FacturaComplemento.SUB_TOTAL_FINAL);
		xml = cdfiHelper.changeDate(xml,
				dateHelper.isMyDateAfterDaysInPast(context.getFacturaDto().getFechaActualizacion(), 3)
						? context.getFacturaDto().getFechaActualizacion()
						: new Date());
		String cadenaOriginal = signHelper.getCadena(xml);
		String sello = signHelper.getSign(cadenaOriginal, context.getEmpresaDto().getPwSat(),
				context.getEmpresaDto().getLlavePrivada());
		context.setXml(cdfiHelper.putsSign(xml, sello));
		context.getFacturaDto().getCfdi().getComplemento().getTimbreFiscal().setCadenaOriginal(cadenaOriginal);
	}

	public void facturaToXmlSigned(FacturaContext context) throws InvoiceCommonException {
		String xml = facturaHelper.facturaCfdiToXml(context.getCfdi());
		xml = cdfiHelper.changeDate(xml,
				dateHelper.isMyDateAfterDaysInPast(context.getFacturaDto().getFechaActualizacion(), 3)
						? context.getFacturaDto().getFechaActualizacion()
						: new Date());
		String cadenaOriginal = signHelper.getCadena(xml);
		String sello = signHelper.getSign(cadenaOriginal, context.getEmpresaDto().getPwSat(),
				context.getEmpresaDto().getLlavePrivada());
		context.setXml(cdfiHelper.putsSign(xml, sello).replace("standalone=\"no\"", ""));
		context.getFacturaDto().getCfdi().setComplemento(new ComplementoDto());
		context.getFacturaDto().getCfdi().getComplemento().getTimbreFiscal().setCadenaOriginal(cadenaOriginal);
	}

	public BigDecimal calculaImpuestos(List<Translado> impuestos, Concepto concepto, BigDecimal totalImpuestos) {
		for (Translado translado : concepto.getImpuestos().getTranslados()) {
			Optional<Translado> tempTranslado = impuestos.stream()
					.filter(a -> a.getImpuesto().equals(translado.getImpuesto())).findFirst();
			if (tempTranslado.isPresent()) {
				tempTranslado.get().setImporte(tempTranslado.get().getImporte().add(translado.getImporte()));
			} else {
				impuestos.add(
						new Translado(translado.getImpuesto(), translado.getTipoFactor(), translado.getTasaOCuota(),
								translado.getImporte()));
			}
			totalImpuestos=totalImpuestos.add(translado.getImporte());
		}
		return totalImpuestos;
	}
	
}
