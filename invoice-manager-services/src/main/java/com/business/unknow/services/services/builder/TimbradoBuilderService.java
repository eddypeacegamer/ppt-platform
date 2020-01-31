package com.business.unknow.services.services.builder;

import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.unknow.Constants.FacturaComplemento;
import com.business.unknow.commons.builder.FacturaContextBuilder;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.services.EmpresaDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.entities.Pago;
import com.business.unknow.services.entities.cfdi.Cfdi;
import com.business.unknow.services.entities.factura.Factura;
import com.business.unknow.services.mapper.EmpresaMapper;
import com.business.unknow.services.mapper.factura.CfdiMapper;
import com.business.unknow.services.mapper.factura.FacturaMapper;
import com.business.unknow.services.repositories.EmpresaRepository;
import com.business.unknow.services.repositories.PagoRepository;
import com.business.unknow.services.repositories.facturas.CfdiRepository;
import com.business.unknow.services.repositories.facturas.FacturaRepository;

@Service
public class TimbradoBuilderService extends AbstractBuilderService{

	@Autowired
	private FacturaRepository repository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private PagoRepository pagoRepository;
	
	@Autowired
	private CfdiRepository cfdiRepository;

	@Autowired
	private FacturaMapper mapper;
	
	@Autowired
	private EmpresaMapper empresaMapper;
	
	@Autowired
	private CfdiMapper cfdiMapper;
	
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
		Optional<Factura> folioPadreEntity = repository.findByFolio(facturaDto.getFolioPadre());
		Factura folioEnity = repository.findByFolio(folio)
				.orElseThrow(() -> new InvoiceManagerException("Folio not found",
						String.format("Folio with the name %s not found", facturaDto.getFolio()),
						HttpStatus.SC_NOT_FOUND));
		validatePackFacturacion(facturaDto, folioPadreEntity);
		Optional<Cfdi> cfdi = cfdiRepository.findByFolio(folio);
		EmpresaDto empresaDto = empresaMapper
				.getEmpresaDtoFromEntity(empresaRepository.findByRfc(facturaDto.getRfcEmisor())
						.orElseThrow(() -> new InvoiceManagerException("Empresa not found",
								String.format("La empresa con el rfc no existe", facturaDto.getRfcEmisor()),
								HttpStatus.SC_NOT_FOUND)));
		Optional<Pago> pagoCredito = pagoRepository.findByFolioAndFormaPagoAndComentarioPago(facturaDto.getFolioPadre(),
				FacturaComplemento.FORMA_PAGO, FacturaComplemento.PAGO_COMENTARIO);
		FacturaDto currentFacturaDto = mapper.getFacturaDtoFromEntity(folioEnity);
		facturaDto.setCfdi(cfdiMapper.getCfdiDtoFromEntity(cfdi.get()));
		getEmpresaFiles(empresaDto, currentFacturaDto);
		currentFacturaDto.setPackFacturacion(facturaDto.getPackFacturacion());
		return new FacturaContextBuilder().setFacturaDto(currentFacturaDto)
				.setPagos(mapper.getPagosDtoFromEntity(pagoRepository.findByFolio(folio)))
				.setCfdi(cfdi.isPresent() ? cfdiMapper.getCfdiDtoFromEntity(cfdi.get()) : null)
				.setEmpresaDto(empresaDto)
				.setPagoCredito(pagoCredito.isPresent() ? mapper.getPagoDtoFromEntity(pagoCredito.get()) : null)
				.setFacturaPadreDto(
						folioPadreEntity.isPresent() ? mapper.getFacturaDtoFromEntity(folioPadreEntity.get()) : null)
				.setTipoFactura(facturaDto.getCfdi().getMetodoPago()).setTipoDocumento(facturaDto.getTipoDocumento())
				.setCtdadComplementos(repository
						.findByFolioPadre(
								facturaDto.getFolioPadre() != null ? facturaDto.getFolioPadre() : facturaDto.getFolio())
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
