package com.business.unknow.commons.validator;

import com.business.unknow.Constants;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.model.factura.cfdi.components.CfdiDto;
import com.business.unknow.model.factura.cfdi.components.ConceptoDto;

public class ConceptoValidator extends AbstractValidator {

	public void validatePostConcepto(ConceptoDto dto) throws InvoiceManagerException {
		checkNotNull(dto.getClaveProdServ(), "Clave Producto servicio");
		checkNotNull(dto.getCantidad(), "Cantidad");
		checkNotNull(dto.getClaveUnidad(), "Clave Unidad");
		checkNotNull(dto.getUnidad(), "Unidad");
		checkNotNull(dto.getDescripcion(), "Descripcion");
		checkNotNull(dto.getValorUnitario(), "getValor Unitario");
		checkNotNull(dto.getImporte(), "Importe");
	}

	public void validateDeleteConcepto(CfdiDto dto) throws InvoiceManagerException {
		if (dto.getConceptos().size() == 1) {
			throw new InvoiceManagerException("No se puede borrar el Concepto", "La Factura no puede tener 0 conceptos",
					Constants.BAD_REQUEST);
		}
	}
}
