package com.business.unknow.services.services.evaluations;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.business.unknow.Constants;
import com.business.unknow.commons.validator.ConceptoValidator;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.model.factura.FacturaDto;
import com.business.unknow.model.factura.cfdi.components.CfdiDto;
import com.business.unknow.model.factura.cfdi.components.ConceptoDto;
import com.business.unknow.model.factura.cfdi.components.ImpuestoDto;
import com.business.unknow.services.entities.cfdi.Concepto;
import com.business.unknow.services.entities.cfdi.Impuesto;

@Service
public class ConceptoEvaluatorService extends AbstractEvaluatorService {

	ConceptoValidator conceptoValidator= new ConceptoValidator();
	
	public ConceptoDto validateConceptoCreation(ConceptoDto conceptoDto, String folio) throws InvoiceManagerException {
		conceptoValidator.validatePostConcepto(conceptoDto);
		FacturaDto facturaDto = mapper.getFacturaDtoFromEntity(repository.findByFolio(folio)
				.orElseThrow(() -> new InvoiceManagerException("La factura no exite",
						String.format("La factura con el folio %s no existe", folio), HttpStatus.NOT_FOUND.value())));
		CfdiDto cfdiDto = cfdiMapper.getCfdiDtoFromEntity(cfdiRepository.findByFolio(folio)
				.orElseThrow(() -> new InvoiceManagerException("Error al obtener el Cfdi",
						String.format("El cfdi con el folio %s no existe", folio), HttpStatus.NOT_FOUND.value())));
		if (!cfdiDto.getConceptos().contains(conceptoDto)) {
			Concepto concepto = conceptoMapper.getEntityFromConceptoDto(conceptoDto);
			concepto.setCfdi(cfdiMapper.getEntityFromCfdiDto(cfdiDto));
			Concepto currentConcepto = conceptoRepository.save(concepto);
			for(Impuesto impuesto:concepto.getImpuestos()) {
				impuesto.setConcepto(currentConcepto);
				impuestoRepository.save(impuesto);
			}
			ConceptoDto currentConceptoDto= conceptoMapper.getConceptoDtoFromEntity(concepto);
			cfdiDto.getConceptos().add(currentConceptoDto);
			calulateFacturaTotalAndSubtotal(cfdiDto, facturaDto);
			repository.save(mapper.getEntityFromFacturaDto(facturaDto));
			return currentConceptoDto;
		} else {
			throw new InvoiceManagerException(
					"El concepto ya existe", String.format("El concepto %s con la descripcion %s ya existe.",
							conceptoDto.getClaveProdServ(), conceptoDto.getDescripcion()),
					HttpStatus.NOT_FOUND.value());
		}
	}
	
	public void validateConceptoDelete(int id, String folio) throws InvoiceManagerException {
		FacturaDto facturaDto = mapper.getFacturaDtoFromEntity(
				repository.findByFolio(folio).orElseThrow(() -> new InvoiceManagerException("La factura no exite en el sistema",
						String.format("La factura con el folio %s no existe", folio), HttpStatus.NOT_FOUND.value())));
		CfdiDto cfdiDto = cfdiMapper.getCfdiDtoFromEntity(cfdiRepository.findByFolio(folio)
				.orElseThrow(() -> new InvoiceManagerException("Error al obtener  informacion del CFDI",
						String.format("El cfdi con el folio %s no existe", folio), HttpStatus.NOT_FOUND.value())));
		conceptoValidator.validateDeleteConcepto(cfdiDto);
		Concepto concepto = conceptoRepository.findById(id)
				.orElseThrow(() -> new InvoiceManagerException("El concepto no exite",
						String.format("El concepto con el id %d no existe", id), HttpStatus.NOT_FOUND.value()));
		double impuestos=0.0;
		for(Impuesto impuesto:concepto.getImpuestos()) {
			impuestos+=impuesto.getImporte();
			impuestoRepository.delete(impuesto);
		}
		facturaDto.setTotal(numberHelper.assignPrecision(facturaDto.getTotal()-concepto.getImporte()-impuestos, Constants.DEFAULT_SCALE));
		facturaDto.setSubtotal(numberHelper.assignPrecision(facturaDto.getSubtotal()-concepto.getImporte(), Constants.DEFAULT_SCALE));
		conceptoRepository.delete(concepto);
		repository.save(mapper.getEntityFromFacturaDto(facturaDto));
	}

	private void calulateFacturaTotalAndSubtotal(CfdiDto cfdiDto, FacturaDto facturaDto) {
		Double subtotal = 0.0;
		Double impuestos = 0.0;
		for (ConceptoDto conceptoDto : cfdiDto.getConceptos()) {
			subtotal += conceptoDto.getImporte();
			for (ImpuestoDto impuestoDto : conceptoDto.getImpuestos()) {
				impuestos += impuestoDto.getImporte();
			}
		}
		facturaDto.setSubtotal(numberHelper.assignPrecision(subtotal, Constants.DEFAULT_SCALE));
		facturaDto.setTotal(numberHelper.assignPrecision(subtotal + impuestos, Constants.DEFAULT_SCALE));
	}
}
