package com.business.unknow.services.services.builder;

import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.unknow.commons.builder.FacturaContextBuilder;
import com.business.unknow.enums.TipoDocumentoEnum;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.pagos.PagoDto;
import com.business.unknow.model.dto.services.EmpresaDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.mapper.EmpresaMapper;
import com.business.unknow.services.repositories.EmpresaRepository;
import com.business.unknow.services.services.FacturaService;
import com.business.unknow.services.services.PagoService;

@Service
public class TimbradoBuilderService extends AbstractBuilderService {

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private EmpresaMapper empresaMapper;

	@Autowired
	private FacturaService facturaService;
	
	@Autowired
	private PagoService pagosService;

	public FacturaContext buildFacturaContextCancelado(FacturaDto facturaDto, String folio)
			throws InvoiceManagerException {
		FacturaDto factura = facturaService.getFacturaByFolio(folio);
		EmpresaDto empresaDto = empresaMapper
				.getEmpresaDtoFromEntity(empresaRepository.findByRfc(facturaDto.getRfcEmisor())
						.orElseThrow(() -> new InvoiceManagerException("La empresa no existe",
								String.format("La empresa con el rfc no existe %s", facturaDto.getRfcEmisor()),
								HttpStatus.SC_NOT_FOUND)));
		getEmpresaFiles(empresaDto, facturaDto);
		return new FacturaContextBuilder().setFacturaDto(factura).setEmpresaDto(empresaDto)
				.setTipoDocumento(factura.getTipoDocumento()).build();
	}

	public FacturaContext buildFacturaContextTimbrado(FacturaDto facturaDto, String folio)
			throws InvoiceManagerException {
		FacturaDto currentFacturaDto = facturaService.getFacturaByFolio(folio);
		currentFacturaDto.setPackFacturacion(facturaDto.getPackFacturacion());
		EmpresaDto empresaDto = empresaMapper
				.getEmpresaDtoFromEntity(empresaRepository.findByRfc(currentFacturaDto.getRfcEmisor())
						.orElseThrow(() -> new InvoiceManagerException("Empresa not found",
								String.format("La empresa con el rfc no existe", currentFacturaDto.getRfcEmisor()),
								HttpStatus.SC_NOT_FOUND)));
		getEmpresaFiles(empresaDto, currentFacturaDto);
		List<PagoDto> pagosFactura= null;
		if(TipoDocumentoEnum.FACTURA.getDescripcion().equals(facturaDto.getTipoDocumento())) {
			pagosFactura = pagosService.findPagosByFolio(folio);
		}
		
		
		return new FacturaContextBuilder().setFacturaDto(currentFacturaDto)
				.setPagos(pagosFactura)
				.setTipoDocumento(currentFacturaDto.getTipoDocumento())
				.setEmpresaDto(empresaDto).build();
	}

}
