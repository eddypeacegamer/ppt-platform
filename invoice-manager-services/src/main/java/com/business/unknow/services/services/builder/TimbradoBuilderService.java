package com.business.unknow.services.services.builder;

import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.unknow.Constants.FacturaComplemento;
import com.business.unknow.commons.builder.FacturaContextBuilder;
import com.business.unknow.enums.FormaPagoEnum;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.services.EmpresaDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.entities.Pago;
import com.business.unknow.services.entities.factura.Factura;
import com.business.unknow.services.mapper.EmpresaMapper;
import com.business.unknow.services.mapper.factura.FacturaMapper;
import com.business.unknow.services.repositories.EmpresaRepository;
import com.business.unknow.services.repositories.PagoRepository;
import com.business.unknow.services.repositories.facturas.FacturaRepository;
import com.business.unknow.services.services.FacturaService;

@Service
public class TimbradoBuilderService extends AbstractBuilderService{

	@Autowired
	private FacturaRepository repository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private PagoRepository pagoRepository;
	
	@Autowired
	private FacturaMapper mapper;
	
	@Autowired
	private EmpresaMapper empresaMapper;
	
	@Autowired
	private FacturaService facturaService;
	
	public FacturaContext buildFacturaContextCancelado(FacturaDto facturaDto, String folio)
			throws InvoiceManagerException {
		Optional<Factura> folioPadreEntity = repository.findByFolio(facturaDto.getFolioPadre());
		Factura folioEnity = repository.findByFolio(folio)
				.orElseThrow(() -> new InvoiceManagerException("Folio not found",
						String.format("Folio with the name %s not found", facturaDto.getFolio()),
						HttpStatus.SC_NOT_FOUND));
		EmpresaDto empresaDto = empresaMapper
				.getEmpresaDtoFromEntity(empresaRepository.findByRfc(facturaDto.getRfcEmisor())
						.orElseThrow(() -> new InvoiceManagerException("La empresa no existe",
								String.format("La empresa con el rfc no existe %s", facturaDto.getRfcEmisor()),
								HttpStatus.SC_NOT_FOUND)));
		return new FacturaContextBuilder().setFacturaDto(mapper.getFacturaDtoFromEntity(folioEnity))
				.setPagos(mapper.getPagosDtoFromEntity(pagoRepository.findByFolio(folio))).setEmpresaDto(empresaDto)
				.setFacturaPadreDto(
						folioPadreEntity.isPresent() ? mapper.getFacturaDtoFromEntity(folioPadreEntity.get()) : null)
				.setTipoFactura(facturaDto.getCfdi().getMetodoPago()).setTipoDocumento(facturaDto.getTipoDocumento())
				.build();
	}
	
	public FacturaContext buildFacturaContextTimbrado(FacturaDto facturaDto, String folio)
			throws InvoiceManagerException {
		FacturaDto currentFacturaDto=facturaService.getFacturaByFolio(facturaDto.getFolio());
		currentFacturaDto.setPackFacturacion(facturaDto.getPackFacturacion());
		Optional<Factura> folioPadreEntity = repository.findByFolio(currentFacturaDto.getFolioPadre());
		validatePackFacturacion(currentFacturaDto, folioPadreEntity);
		EmpresaDto empresaDto = empresaMapper
				.getEmpresaDtoFromEntity(empresaRepository.findByRfc(currentFacturaDto.getRfcEmisor())
						.orElseThrow(() -> new InvoiceManagerException("Empresa not found",
								String.format("La empresa con el rfc no existe", currentFacturaDto.getRfcEmisor()),
								HttpStatus.SC_NOT_FOUND)));
		Optional<Pago> pagoCredito = pagoRepository.findByFolioAndFormaPagoAndComentarioPago(currentFacturaDto.getFolioPadre(),
				FacturaComplemento.FORMA_PAGO, FacturaComplemento.PAGO_COMENTARIO);
		Optional<Pago> currentPago= pagoRepository.findByFolio(folio).stream().filter(a->!a.getFormaPago().equals(FormaPagoEnum.CREDITO.getDescripcion())).findFirst();
		getEmpresaFiles(empresaDto, currentFacturaDto);
		return new FacturaContextBuilder().setFacturaDto(currentFacturaDto)
				.setPagos(mapper.getPagosDtoFromEntity(pagoRepository.findByFolio(folio)))
				.setCfdi(currentFacturaDto.getCfdi())
				.setEmpresaDto(empresaDto)
				.setCurrentPago(currentPago.isPresent() ? mapper.getPagoDtoFromEntity(currentPago.get()) : null)
				.setPagoCredito(pagoCredito.isPresent() ? mapper.getPagoDtoFromEntity(pagoCredito.get()) : null)
				.setFacturaPadreDto(
						folioPadreEntity.isPresent() ? mapper.getFacturaDtoFromEntity(folioPadreEntity.get()) : null)
				.setTipoFactura(currentFacturaDto.getCfdi().getMetodoPago()).setTipoDocumento(currentFacturaDto.getTipoDocumento())
				.setCtdadComplementos(repository
						.findByFolioPadre(
								currentFacturaDto.getFolioPadre() != null ? currentFacturaDto.getFolioPadre() : currentFacturaDto.getFolio())
						.size())
				.build();
	}
	
	private void validatePackFacturacion(FacturaDto currentFacturaDto, Optional<Factura> facturaPadre)
			throws InvoiceManagerException {
		if (facturaPadre.isPresent()
				&& !facturaPadre.get().getPackFacturacion().equals(currentFacturaDto.getPackFacturacion())) {
			throw new InvoiceManagerException("El pack del complemento debe ser el mismo",
					String.format("El pack de facturacion del complemento %s no es el correcto",
							currentFacturaDto.getPackFacturacion()),
					HttpStatus.SC_BAD_REQUEST);
		}
	}
}
