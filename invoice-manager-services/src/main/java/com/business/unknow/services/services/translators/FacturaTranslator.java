package com.business.unknow.services.services.translators;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.unknow.Constants;
import com.business.unknow.Constants.FacturaComplemento;
import com.business.unknow.commons.factura.CdfiHelper;
import com.business.unknow.commons.util.DateHelper;
import com.business.unknow.commons.util.FacturaHelper;
import com.business.unknow.commons.util.NumberHelper;
import com.business.unknow.model.PagoDto;
import com.business.unknow.model.cfdi.Cfdi;
import com.business.unknow.model.cfdi.Complemento;
import com.business.unknow.model.cfdi.ComplementoPago;
import com.business.unknow.model.cfdi.Concepto;
import com.business.unknow.model.cfdi.Translado;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.error.InvoiceCommonException;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.model.factura.cfdi.components.CfdiDto;
import com.business.unknow.model.factura.cfdi.components.ConceptoDto;
import com.business.unknow.services.mapper.FacturaCfdiTranslatorMapper;

@Service
public class FacturaTranslator {

	@Autowired
	private CdfiHelper cdfiHelper;

	@Autowired
	private FacturaHelper facturaHelper;

	@Autowired
	private DateHelper dateHelper;

	@Autowired
	private NumberHelper numberHelper;

	@Autowired
	private FacturaCfdiTranslatorMapper facturaCfdiTranslatorMapper;

	public FacturaContext translateFactura(FacturaContext context) throws InvoiceManagerException {
		try {
			Cfdi cfdi = facturaCfdiTranslatorMapper.cdfiRootInfo(context.getFacturaDto(), context.getEmpresaDto());
			Double totalImpuestos = 0.0;
			List<Translado> impuestos = new ArrayList<>();
			for (ConceptoDto conceptoDto : context.getFacturaDto().getCfdi().getConceptos()) {
				Concepto concepto = facturaCfdiTranslatorMapper.cfdiConcepto(conceptoDto);
				cfdi.getConceptos().add(concepto);
				if (!conceptoDto.getImpuestos().isEmpty()) {
					totalImpuestos = calculaImpuestos(impuestos, concepto, totalImpuestos);
				}
			}
			if (!impuestos.isEmpty()) {
				cfdi.getImpuestos().setTotalImpuestosTrasladados(
						numberHelper.assignPrecision(totalImpuestos, Constants.DEFAULT_SCALE));
				cfdi.getImpuestos().setTranslados(impuestos);
			}
			context.setCfdi(cfdi);
			facturaToXmlSigned(context);
			return context;
		} catch (InvoiceCommonException e) {
			e.printStackTrace();
			throw new InvoiceManagerException("Error generating the xml", e.getMessage(), HttpStatus.SC_CONFLICT);
		}
	}

	public FacturaContext translateComplemento(FacturaContext context) throws InvoiceManagerException {
		try {
			context.getFacturaDto().setCfdi(new CfdiDto());
			context.getFacturaDto().getCfdi().setFolio(context.getFacturaDto().getFolio());

			Cfdi cfdi = facturaCfdiTranslatorMapper.complementoRootInfo(context.getFacturaDto(),
					context.getEmpresaDto());
			context.getFacturaDto().getCfdi().setSerie("");
			context.getFacturaDto().getCfdi().setVersion(cfdi.getVersion());
			cfdi.setImpuestos(null);
			cfdi.getConceptos().add(facturaCfdiTranslatorMapper.complementoConcepto(new ConceptoDto()));
			Complemento complemento = new Complemento();
			for (PagoDto pagoDto : context.getPagos()) {
				ComplementoPago complementoComponente = facturaCfdiTranslatorMapper
						.complementoComponente(context.getFacturaDto(), pagoDto);
				complementoComponente.getComplementoDocRelacionado().setNumParcialidad(context.getCtdadComplementos());
				complementoComponente.getComplementoDocRelacionado()
						.setIdDocumento(context.getFacturaPadreDto().getUuid());
				complementoComponente.getComplementoDocRelacionado().setImpSaldoAnt(String.format("%.2f",
						context.getPagoCredito().getMonto() + Double.valueOf(complementoComponente.getMonto())));
				complementoComponente.getComplementoDocRelacionado()
						.setImpSaldoInsoluto(String.format("%.2f", numberHelper.assignPrecision(context.getPagoCredito().getMonto(), Constants.DEFAULT_SCALE)));
				complemento.getComplemntoPago().getComplementoPagos().add(complementoComponente);
			}
			cfdi.setComplemento(complemento);
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
		context.setXml(cdfiHelper.signXML(xml,
				dateHelper.isMyDateAfterDaysInPast(context.getFacturaDto().getFechaActualizacion(), 3)
						? context.getFacturaDto().getFechaActualizacion()
						: new Date(),
				context.getEmpresaDto()));
	}

	public void facturaToXmlSigned(FacturaContext context) throws InvoiceCommonException {
		String xml = facturaHelper.facturaCfdiToXml(context.getCfdi());
		System.out.println(xml);
		context.setXml(cdfiHelper.signXML(xml,
				dateHelper.isMyDateAfterDaysInPast(context.getFacturaDto().getFechaActualizacion(), 3)
						? context.getFacturaDto().getFechaActualizacion()
						: new Date(),
				context.getEmpresaDto()));
		System.out.println(context.getXml());
	}

	public Double calculaImpuestos(List<Translado> impuestos, Concepto concepto, Double totalImpuestos) {
		for (Translado translado : concepto.getImpuestos().getTranslados()) {
			Optional<Translado> tempTranslado = impuestos.stream()
					.filter(a -> a.getImpuesto().equals(translado.getImpuesto())).findFirst();
			if (tempTranslado.isPresent()) {
				tempTranslado.get().setImporte(numberHelper.assignPrecision(tempTranslado.get().getImporte() + translado.getImporte(),Constants.DEFAULT_SCALE));
			} else {
				impuestos.add(
						new Translado(translado.getImpuesto(), translado.getTipoFactor(), translado.getTasaOCuota(),
								numberHelper.assignPrecision(translado.getImporte(), Constants.DEFAULT_SCALE)));
			}
			totalImpuestos += translado.getImporte();
		}
		return totalImpuestos;
	}
}
