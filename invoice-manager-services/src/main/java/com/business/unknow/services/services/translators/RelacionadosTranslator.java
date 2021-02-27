package com.business.unknow.services.services.translators;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.business.unknow.Constants.FacturaSustitucionConstants;
import com.business.unknow.commons.builder.ConceptoDtoBuilder;
import com.business.unknow.enums.FacturaStatusEnum;
import com.business.unknow.enums.LineaEmpresaEnum;
import com.business.unknow.enums.MetodosPagoEnum;
import com.business.unknow.enums.TipoDocumentoEnum;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.cfdi.CfdiPagoDto;
import com.business.unknow.model.dto.cfdi.ConceptoDto;
import com.business.unknow.model.dto.cfdi.ImpuestoDto;
import com.business.unknow.model.dto.cfdi.RelacionadoDto;
import com.business.unknow.model.dto.cfdi.RetencionDto;

@Service
public class RelacionadosTranslator {

	public FacturaDto sustitucionComplemento(FacturaDto facturaDto) {
		if (!facturaDto.getStatusFactura().equals(FacturaStatusEnum.CANCELADA.getValor())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					String.format("La factura con el pre-folio %s no esta cancelada y no se puede sustituir",
							facturaDto.getPreFolio()));
		}
		updateBaseInfoSustitucion(facturaDto);
		facturaDto.setValidacionTeso(true);
		return facturaDto;
	}

	public FacturaDto sustitucionFactura(FacturaDto facturaDto) {
		if (!facturaDto.getStatusFactura().equals(FacturaStatusEnum.CANCELADA.getValor())) {
			throw new ResponseStatusException(HttpStatus.CONFLICT,
					String.format("La factura con el pre-folio %s no esta cancelada y no se puede sustituir",
							facturaDto.getPreFolio()));
		}
		updateBaseInfoSustitucion(facturaDto);
		return facturaDto;
	}

	private FacturaDto updateBaseInfoSustitucion(FacturaDto facturaDto) {
		facturaDto.setCadenaOriginalTimbrado(null);
		facturaDto.setFechaTimbrado(null);
		facturaDto.setFolio(null);
		facturaDto.setValidacionOper(false);
		facturaDto.setValidacionTeso(false);
		facturaDto.setNotas("");
		facturaDto.setPreFolio("");
		facturaDto.setSelloCfd(null);
		if (facturaDto.getLineaEmisor().equals(LineaEmpresaEnum.A.name())) {
			if (facturaDto.getMetodoPago().equals(MetodosPagoEnum.PPD.name())) {
				facturaDto.setStatusFactura(FacturaStatusEnum.VALIDACION_OPERACIONES.getValor());
			}else {
				facturaDto.setStatusFactura(FacturaStatusEnum.VALIDACION_TESORERIA.getValor());
			}
			
		} else {
			facturaDto.setStatusFactura(FacturaStatusEnum.POR_TIMBRAR_CONTABILIDAD.getValor());
		}
		facturaDto.setId(0);
		if (facturaDto.getCfdi() != null) {
			facturaDto.getCfdi().setId(null);
			if (facturaDto.getCfdi().getEmisor() != null) {
				facturaDto.getCfdi().getEmisor().setId(null);
			}
			if (facturaDto.getCfdi().getReceptor() != null) {
				facturaDto.getCfdi().getReceptor().setId(null);
			}
			if (facturaDto.getCfdi().getConceptos() != null) {
				for (ConceptoDto conceptoDto : facturaDto.getCfdi().getConceptos()) {
					conceptoDto.setId(null);

					if (conceptoDto.getImpuestos() != null) {
						for (ImpuestoDto impuestoDto : conceptoDto.getImpuestos()) {
							impuestoDto.setId(null);
						}
					}
					if (conceptoDto.getRetenciones() != null) {
						for (RetencionDto retencionDto : conceptoDto.getRetenciones()) {
							retencionDto.setId(null);
						}
					}
				}
			}
			if (facturaDto.getCfdi().getComplemento() != null
					&& facturaDto.getCfdi().getComplemento().getPagos() != null) {
				for (CfdiPagoDto cfdiPagoDto : facturaDto.getCfdi().getComplemento().getPagos()) {
					cfdiPagoDto.setId(0);
				}
			}

			RelacionadoDto relacionadoDto = new RelacionadoDto();
			relacionadoDto.setRelacion(facturaDto.getUuid());
			relacionadoDto.setTipoRelacion("04");
			facturaDto.getCfdi().setRelacionado(relacionadoDto);
			facturaDto.setUuid(null);
		}
		return facturaDto;
	}

	public FacturaDto notaCreditoFactura(FacturaDto facturaDto) {
		if (facturaDto.getStatusFactura().equals(FacturaStatusEnum.TIMBRADA.getValor())) {
			updateBaseInfoNotaCredito(facturaDto);
			return facturaDto;
		} else {
			throw new ResponseStatusException(HttpStatus.CONFLICT,
					String.format("La factura con el pre-folio %s no esta timbrada y no puede tener nota de credito",
							facturaDto.getPreFolio()));
		}

	}

	private FacturaDto updateBaseInfoNotaCredito(FacturaDto facturaDto) {
		facturaDto.setCadenaOriginalTimbrado(null);
		facturaDto.setFechaTimbrado(null);
		facturaDto.setFolio(null);
		facturaDto.setTipoDocumento(TipoDocumentoEnum.NOTA_CREDITO.getDescripcion());
		facturaDto.setTotal(BigDecimal.ZERO);
		facturaDto.setSaldoPendiente(BigDecimal.ZERO);
		facturaDto.setValidacionOper(false);
		facturaDto.setValidacionTeso(true);
		facturaDto.setNotas("");
		facturaDto.setPreFolio("");
		facturaDto.setSelloCfd(null);
		facturaDto.setStatusFactura(1);
		facturaDto.setId(0);
		if (facturaDto.getCfdi() != null) {
			facturaDto.getCfdi().setId(null);
			facturaDto.getCfdi().setImpuestosRetenidos(BigDecimal.ZERO);
			facturaDto.getCfdi().setImpuestosTrasladados(BigDecimal.ZERO);
			facturaDto.getCfdi().setSubtotal(BigDecimal.ZERO);
			facturaDto.getCfdi().setTotal(BigDecimal.ZERO);
			facturaDto.getCfdi().setTipoDeComprobante("E");
			if (facturaDto.getCfdi().getEmisor() != null) {
				facturaDto.getCfdi().getEmisor().setId(null);
			}
			if (facturaDto.getCfdi().getReceptor() != null) {
				facturaDto.getCfdi().getReceptor().setId(null);
				facturaDto.getCfdi().getReceptor().setUsoCfdi(FacturaSustitucionConstants.NOTA_CREDITO_USO_CFDI);
			}
			ConceptoDtoBuilder concepto = new ConceptoDtoBuilder();
			concepto.setCantidad(new BigDecimal(1))
					.setClaveProdServ(FacturaSustitucionConstants.NOTA_CREDITO_CLAVE_CONCEPTO)
					.setDescripcion(FacturaSustitucionConstants.NOTA_CREDITO_DESC_CONCEPTO)
					.setClaveUnidad(FacturaSustitucionConstants.NOTA_CREDITO_CLAVE_UNIDAD)
					.setDescripcionCUPS(FacturaSustitucionConstants.NOTA_CREDITO_CLAVE_CONCEPTO_DESC)
					.setValorUnitario(BigDecimal.ZERO).setImporte(BigDecimal.ZERO).setDescuento(BigDecimal.ZERO);
			facturaDto.getCfdi().setConceptos(new ArrayList<>());
			facturaDto.getCfdi().getConceptos().add(concepto.build());
			facturaDto.getCfdi().setComplemento(null);
		}
		RelacionadoDto relacionadoDto = new RelacionadoDto();
		relacionadoDto.setRelacion(facturaDto.getUuid());
		relacionadoDto.setTipoRelacion("01");
		facturaDto.getCfdi().setRelacionado(relacionadoDto);
		facturaDto.setUuid(null);
		return facturaDto;
	}

}
