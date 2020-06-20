package com.business.unknow.services.services.builder;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.mapper.EmpresaMapper;
import com.business.unknow.services.mapper.factura.FacturaMapper;
import com.business.unknow.services.repositories.EmpresaRepository;
import com.business.unknow.services.repositories.PagoRepository;
import com.business.unknow.services.repositories.facturas.FacturaRepository;
import com.business.unknow.services.services.FacturaService;

@Service
public class TimbradoBuilderService extends AbstractBuilderService {

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
		//TODO refactorizar logica de cancelacion, las facturas ya no estaran ligadas a un padre.
//		Optional<Factura> folioPadreEntity = repository.findByFolio(facturaDto.getFolioPadre());
//		FacturaDto factura=facturaService.getFacturaByFolio(folio);
//		EmpresaDto empresaDto = empresaMapper
//				.getEmpresaDtoFromEntity(empresaRepository.findByRfc(facturaDto.getRfcEmisor())
//						.orElseThrow(() -> new InvoiceManagerException("La empresa no existe",
//								String.format("La empresa con el rfc no existe %s", facturaDto.getRfcEmisor()),
//								HttpStatus.SC_NOT_FOUND)));
//		getEmpresaFiles(empresaDto, facturaDto);
//		return new FacturaContextBuilder().setFacturaDto(factura)
//				.setPagos(mapper.getPagosDtoFromEntity(pagoRepository.findByFolio(folio))).setEmpresaDto(empresaDto)
//				.setFacturaPadreDto(
//						folioPadreEntity.isPresent() ? mapper.getFacturaDtoFromEntity(folioPadreEntity.get()) : null)
//				.setTipoFactura(factura.getCfdi().getMetodoPago())
//				.setTipoDocumento(factura.getTipoDocumento())
//				.build();
		return null;
	}

	public FacturaContext buildFacturaContextTimbrado(FacturaDto facturaDto, String folio)
			throws InvoiceManagerException {
		
		// TODO Rehacer logica de timbrado, el concepto de folio padre dejo de existir
//		FacturaDto currentFacturaDto = facturaService.getFacturaByFolio(folio);
//		currentFacturaDto.setPackFacturacion(facturaDto.getPackFacturacion());
//		FacturaDto facturaPadre = (TipoDocumentoEnum.COMPLEMENTO.getDescripcion().equals(currentFacturaDto.getTipoDocumento())) ?
//				facturaService.getFacturaByFolio(currentFacturaDto.getFolioPadre()):null;
//		validatePackFacturacion(currentFacturaDto, facturaPadre);
//		EmpresaDto empresaDto = empresaMapper
//				.getEmpresaDtoFromEntity(empresaRepository.findByRfc(currentFacturaDto.getRfcEmisor())
//						.orElseThrow(() -> new InvoiceManagerException("Empresa not found",
//								String.format("La empresa con el rfc no existe", currentFacturaDto.getRfcEmisor()),
//								HttpStatus.SC_NOT_FOUND)));
//		Optional<Pago> pagoCredito = pagoRepository.findByFolioAndFormaPagoAndComentarioPago(
//				currentFacturaDto.getFolioPadre(), FacturaComplemento.FORMA_PAGO, FacturaComplemento.PAGO_COMENTARIO);
//		Optional<Pago> currentPago = pagoRepository.findByFolio(folio).stream()
//				.filter(a -> !a.getFormaPago().equals(FormaPagoEnum.CREDITO.getDescripcion())).findFirst();
//		getEmpresaFiles(empresaDto, currentFacturaDto);
//		return new FacturaContextBuilder().setFacturaDto(currentFacturaDto)
//				.setPagos(mapper.getPagosDtoFromEntity(pagoRepository.findByFolioPadre((facturaPadre!=null)?facturaPadre.getFolio():folio)))
//				.setCfdi(currentFacturaDto.getCfdi()).setEmpresaDto(empresaDto)
//				.setCurrentPago(currentPago.isPresent() ? mapper.getPagoDtoFromEntity(currentPago.get()) : null)
//				.setPagoCredito(pagoCredito.isPresent() ? mapper.getPagoDtoFromEntity(pagoCredito.get()) : null)
//				.setFacturaPadreDto(facturaPadre).setTipoFactura(currentFacturaDto.getCfdi().getMetodoPago())
//				.setTipoDocumento(currentFacturaDto.getTipoDocumento())
//				.setCtdadComplementos(repository
//						.findByFolioPadre(currentFacturaDto.getFolioPadre() != null ? currentFacturaDto.getFolioPadre()
//								: currentFacturaDto.getFolio())
//						.size())
//				.build();
		
		return null;
	}

	private void validatePackFacturacion(FacturaDto currentFacturaDto, FacturaDto facturaPadre)
			throws InvoiceManagerException {
		if (facturaPadre!=null && !facturaPadre.getPackFacturacion().equals(currentFacturaDto.getPackFacturacion())) {
			throw new InvoiceManagerException("El pack del complemento debe ser el mismo",
					String.format("El pack de facturacion del complemento %s no es el correcto",
							currentFacturaDto.getPackFacturacion()),
					HttpStatus.SC_BAD_REQUEST);
		}
	}
}
