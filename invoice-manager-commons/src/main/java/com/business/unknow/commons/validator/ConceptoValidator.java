package com.business.unknow.commons.validator;

import com.business.unknow.Constants;
import com.business.unknow.model.dto.cfdi.CfdiDto;
import com.business.unknow.model.dto.cfdi.ConceptoDto;
import com.business.unknow.model.error.InvoiceManagerException;

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
			throw new InvoiceManagerException("La Factura no puede tener 0 conceptos","Una  vez creada no es posible tener una factura sin conceptos",
					Constants.BAD_REQUEST);
		}
	}
}
