package com.business.unknow.services.services.builder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.unknow.Constants;
import com.business.unknow.Constants.ComplementoPpdDefaults;
import com.business.unknow.commons.builder.CfdiComplementoPagoBuilder;
import com.business.unknow.commons.builder.CfdiDtoBuilder;
import com.business.unknow.commons.builder.ConceptoDtoBuilder;
import com.business.unknow.commons.builder.FacturaBuilder;
import com.business.unknow.commons.builder.FacturaContextBuilder;
import com.business.unknow.enums.FormaPagoEnum;
import com.business.unknow.enums.TipoDocumentoEnum;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.cfdi.CfdiDto;
import com.business.unknow.model.dto.cfdi.CfdiPagoDto;
import com.business.unknow.model.dto.cfdi.ComplementoDto;
import com.business.unknow.model.dto.cfdi.ConceptoDto;
import com.business.unknow.model.dto.cfdi.EmisorDto;
import com.business.unknow.model.dto.cfdi.ReceptorDto;
import com.business.unknow.model.dto.services.EmpresaDto;
import com.business.unknow.model.dto.services.PagoDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.entities.Contribuyente;
import com.business.unknow.services.entities.Empresa;
import com.business.unknow.services.entities.Pago;
import com.business.unknow.services.entities.factura.Factura;
import com.business.unknow.services.mapper.ContribuyenteMapper;
import com.business.unknow.services.mapper.EmpresaMapper;
import com.business.unknow.services.mapper.PagoMapper;
import com.business.unknow.services.mapper.factura.FacturaMapper;
import com.business.unknow.services.repositories.ContribuyenteRepository;
import com.business.unknow.services.repositories.EmpresaRepository;
import com.business.unknow.services.repositories.PagoRepository;
import com.business.unknow.services.repositories.facturas.FacturaRepository;

@Service
public class FacturaBuilderService extends AbstractBuilderService {

	@Autowired
	private FacturaRepository repository;

	@Autowired
	private PagoRepository pagoRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private ContribuyenteRepository contribuyenteRepository;

	@Autowired
	private FacturaMapper mapper;

	@Autowired
	private PagoMapper pagoMapper;

	@Autowired
	private EmpresaMapper empresaMapper;

	@Autowired
	private ContribuyenteMapper contribuyenteMapper;

	public FacturaContext buildFacturaContextPagoPpdCreation(PagoDto pagoDto, FacturaDto facturaPadreDto, String folio)
			throws InvoiceManagerException {
		List<Pago> pagos = pagoRepository.findByFolioPadre(facturaPadreDto.getFolio());
		Pago pagoPadre = pagos.stream().filter(p -> p.getFolio().equals(folio)).findFirst()
				.orElseThrow(() -> new InvoiceManagerException("Pago a credito no encontrado",
						String.format("Verificar consitencia de pagos del folio %s", folio), HttpStatus.SC_NOT_FOUND));
		EmpresaDto empresaDto = empresaMapper
				.getEmpresaDtoFromEntity(empresaRepository.findByRfc(facturaPadreDto.getRfcEmisor())
						.orElseThrow(() -> new InvoiceManagerException("Pago a credito no encontrado",
								String.format("No existe El emisor %s", facturaPadreDto.getRfcEmisor()),
								HttpStatus.SC_NOT_FOUND)));
		getEmpresaFiles(empresaDto, facturaPadreDto);
		return new FacturaContextBuilder().setPagos(pagoMapper.getPagosDtoFromEntities(pagos)).setEmpresaDto(empresaDto)
				.setFacturaPadreDto(facturaPadreDto).setPagoCredito(pagoMapper.getPagoDtoFromEntity(pagoPadre))
				.setCtdadComplementos(repository.findComplementosByFolioPadre(facturaPadreDto.getFolio()).size()).setCurrentPago(pagoDto).build();
	}

	public FacturaContext buildFacturaContextPagoPueCreation(String folio, PagoDto pagoDto) {
		List<Pago> pagos = pagoRepository.findByFolio(folio);
		Optional<Factura> factura = repository.findByFolio(folio);
		Optional<Pago> pagoCredito = pagos.stream()
				.filter(p -> p.getFormaPago().equals(FormaPagoEnum.CREDITO.getPagoValue())).findFirst();
		return new FacturaContextBuilder().setPagos(Arrays.asList(pagoDto))
				.setPagos(pagoMapper.getPagosDtoFromEntities(pagos)).setCurrentPago(pagoDto)
				.setFacturaDto(factura.isPresent() ? mapper.getFacturaDtoFromEntity(factura.get()) : null)
				.setPagoCredito(pagoCredito.isPresent() ? pagoMapper.getPagoDtoFromEntity(pagoCredito.get()) : null)
				.build();
	}

	public FacturaDto buildFacturaDtoPagoPpdCreation(FacturaDto facturaPadre, PagoDto pagoActual) {
		return new FacturaBuilder().setFolioPadre(facturaPadre.getFolio())
				.setPackFacturacion(facturaPadre.getPackFacturacion())
				.setCfdi(facturaPadre.getCfdi())
				.setLineaEmisor(facturaPadre.getLineaEmisor())
				.setRfcEmisor(facturaPadre.getRfcEmisor())
				.setMetodoPago(ComplementoPpdDefaults.METODO_PAGO)
				.setRfcRemitente(facturaPadre.getRfcRemitente())
				.setLineaRemitente(facturaPadre.getLineaRemitente())
				.setRazonSocialEmisor(facturaPadre.getRazonSocialEmisor())
				.setRazonSocialRemitente(facturaPadre.getRazonSocialRemitente())
				.setSolicitante(facturaPadre.getSolicitante())
				.setTipoDocumento(TipoDocumentoEnum.COMPLEMENTO.getDescripcion())
				.setFormaPago(FormaPagoEnum.findByPagoValue(pagoActual.getFormaPago()).getClave())
				.build();
	}

	public CfdiDto buildFacturaComplementoCreation(FacturaContext facturaContext) {
		CfdiDtoBuilder cfdiBuilder = new CfdiDtoBuilder().setVersion(ComplementoPpdDefaults.VERSION_CFDI)
				.setLugarExpedicion(facturaContext.getEmpresaDto().getInformacionFiscal().getCp())
				.setMoneda(ComplementoPpdDefaults.MONEDA)
				.setNoCertificado(facturaContext.getEmpresaDto().getNoCertificado())
				.setSerie(ComplementoPpdDefaults.SERIE).setSubtotal(new BigDecimal(ComplementoPpdDefaults.SUB_TOTAL))
				.setTotal(new BigDecimal(ComplementoPpdDefaults.TOTAL)).setComplemento(new ComplementoDto())
				.setTipoDeComprobante(ComplementoPpdDefaults.COMPROBANTE)
				.setEmisor(new EmisorDto(facturaContext.getFacturaPadreDto().getRfcEmisor(),
						facturaContext.getFacturaPadreDto().getRazonSocialEmisor(),
						facturaContext.getFacturaPadreDto().getCfdi().getEmisor().getRegimenFiscal()))
				.setReceptor(new ReceptorDto(facturaContext.getFacturaPadreDto().getRfcRemitente(),
						facturaContext.getFacturaPadreDto().getRazonSocialRemitente(), ComplementoPpdDefaults.USO_CFDI))
				.setConceptos(buildFacturaComplementoConceptos())
				.setPagos(buildFacturaComplementoPagos(facturaContext));
		return cfdiBuilder.build();
	}

	public List<ConceptoDto> buildFacturaComplementoConceptos() {
		List<ConceptoDto> conceptos = new ArrayList<ConceptoDto>();
		ConceptoDtoBuilder conceptoBuilder = new ConceptoDtoBuilder().setCantidad(new BigDecimal(ComplementoPpdDefaults.CANTIDAD))
				.setClaveProdServ(ComplementoPpdDefaults.CLAVE_PROD).setClaveUnidad(ComplementoPpdDefaults.CLAVE)
				.setDescripcion(ComplementoPpdDefaults.DESCRIPCION)
				.setImporte(new BigDecimal(ComplementoPpdDefaults.IMPORTE))
				.setValorUnitario(new BigDecimal(ComplementoPpdDefaults.VALOR_UNITARIO));
		conceptos.add(conceptoBuilder.build());
		return conceptos;
	}

	public List<CfdiPagoDto> buildFacturaComplementoPagos(FacturaContext facturaContext) {
		List<CfdiPagoDto> pagos = new ArrayList<CfdiPagoDto>();
		CfdiComplementoPagoBuilder cfdiComplementoPagoBuilder = new CfdiComplementoPagoBuilder()
				.setVersion(ComplementoPpdDefaults.VERSION).setFechaPago(facturaContext.getCurrentPago().getFechaPago())
				.setFormaPago(FormaPagoEnum.findByPagoValue(facturaContext.getCurrentPago().getFormaPago()).getClave())
				.setMoneda(facturaContext.getCurrentPago().getMoneda())
				.setMonto(facturaContext.getCurrentPago().getMonto())
				.setFolio(facturaContext.getFacturaPadreDto().getFolio())
				.setIdDocumento(facturaContext.getFacturaPadreDto().getUuid())
				.setImportePagado(facturaContext.getCurrentPago().getMonto())
				.setMonedaDr(facturaContext.getCurrentPago().getMoneda())
				.setMetodoPago(ComplementoPpdDefaults.METODO_PAGO).setSerie(ComplementoPpdDefaults.SERIE_PAGO)
				.setNumeroParcialidad(facturaContext.getCtdadComplementos()+1)
				.setImporteSaldoAnterior(facturaContext.getPagoCredito().getMonto())
				.setImporteSaldoInsoluto(facturaContext.getPagoCredito().getMonto()
						.subtract(facturaContext.getCurrentPago().getMonto()));
		pagos.add(cfdiComplementoPagoBuilder.build());
		return pagos;
	}

	public FacturaContext buildFacturaContextCreateFactura(FacturaDto facturaDto) throws InvoiceManagerException {
		Empresa empresa = empresaRepository.findByRfc(facturaDto.getRfcEmisor())
				.orElseThrow(() -> new InvoiceManagerException("Emisor de factura no existen en el sistema",
						String.format("No se encuentra el RFC %s en el sistema", facturaDto.getRfcEmisor()),
						Constants.BAD_REQUEST));
		Contribuyente contribuyente = contribuyenteRepository.findByRfc(facturaDto.getRfcRemitente())
				.orElseThrow(() -> new InvoiceManagerException("Error al crear factura", "El receptor no exite",
						Constants.BAD_REQUEST));
		return new FacturaContextBuilder().setFacturaDto(facturaDto)
				.setEmpresaDto(empresaMapper.getEmpresaDtoFromEntity(empresa))
				.setContribuyenteDto(contribuyenteMapper.getContribuyenteToFromEntity(contribuyente)).build();
	}

}
