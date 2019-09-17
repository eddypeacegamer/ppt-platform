package com.business.unknow.services.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.business.unknow.model.ClientDto;
import com.business.unknow.model.EmpresaDto;
import com.business.unknow.model.FacturaDto;
import com.business.unknow.model.PromotorDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.entities.Factura;
import com.business.unknow.services.mapper.FacturaMapper;
import com.business.unknow.services.repositories.FacturaRepository;

@Service
public class FacturaService {

	@Autowired
	private FacturaRepository repository;

	@Autowired
	private PromotorService promotorService;

	@Autowired
	private ClientService clientService;

	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private FacturaMapper mapper;

	public Page<FacturaDto> getAllFacturas(int page, int size) {
		Page<Factura> result = repository.findAll(PageRequest.of(page, size));
		return new PageImpl<FacturaDto>(mapper.getFacturaDtosFromEntities(result.getContent()), result.getPageable(),
				result.getTotalElements());
	}

	public Page<FacturaDto> getAllFacturasByPromotor(String promotor, int page, int size) throws InvoiceManagerException {
		PromotorDto promotorDto = promotorService.getPromotorsByName(promotor);
		Page<Factura> result = repository.findAllByPromotor(promotorDto.getName(), PageRequest.of(page, size));
		return new PageImpl<FacturaDto>(mapper.getFacturaDtosFromEntities(result.getContent()), result.getPageable(),
				result.getTotalElements());
	}

	private Page<FacturaDto> getAllFacturasByPromotorAndClient(String promotor, String rfc, int page, int size)
			throws InvoiceManagerException {
		PromotorDto promotorDto = promotorService.getPromotorsByName(promotor);
		ClientDto clienteDto = clientService.getClientByPromotorAndRfc(promotorDto.getName(), rfc);
		Page<Factura> result = repository.findAllByPromotorAndRfc(promotorDto.getName(), clienteDto.getRfc(),
				PageRequest.of(page, size));
		return new PageImpl<FacturaDto>(mapper.getFacturaDtosFromEntities(result.getContent()), result.getPageable(),
				result.getTotalElements());
	}
	
	
	private Page<FacturaDto> getAllFacturasByPromotorAndClientAndEmpresa(String promotor, String rfc,String empresa, int page, int size)
			throws InvoiceManagerException {
		PromotorDto promotorDto = promotorService.getPromotorsByName(promotor);
		ClientDto clienteDto = clientService.getClientByPromotorAndRfc(promotorDto.getName(), rfc);
		EmpresaDto empresaDto=empresaService.getEmpresaByName(empresa);
		Page<Factura> result = repository.findAllByPromotorAndRfcAndEmpresa(promotorDto.getName(), clienteDto.getRfc(),empresaDto.getName(),
				PageRequest.of(page, size));
		return new PageImpl<FacturaDto>(mapper.getFacturaDtosFromEntities(result.getContent()), result.getPageable(),
				result.getTotalElements());
	}
	
	public Page<FacturaDto> getAllFacturasByPromotorAndClientAndEmpresas(String promotor, String rfc,String empresa, int page, int size)
			throws InvoiceManagerException {
		if(empresa!=null) {
			return getAllFacturasByPromotorAndClientAndEmpresa(promotor, rfc,empresa, page, size);
		}else {
			return getAllFacturasByPromotorAndClient(promotor, rfc, page, size);
		}
	}

	public FacturaDto getFacturaByFolio(String folio) throws InvoiceManagerException {
		Optional<Factura> entity = repository.findByFolio(folio);
		if (entity.isPresent()) {
			return mapper.getFacturaDtoFromEntity(entity.get());
		} else {
			throw new InvoiceManagerException("Factura not found",
					String.format("La factura con el folio %s no existe", folio), HttpStatus.NOT_FOUND.value());
		}

	}
}
