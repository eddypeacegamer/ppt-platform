package com.business.unknow.services.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.business.unknow.model.ClientDto;
import com.business.unknow.services.entities.Client;
import com.business.unknow.services.mapper.ClientMapper;
import com.business.unknow.services.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	@Autowired
	private ClientMapper mapper;

	public Page<ClientDto> getAllClientsByPromotor(String promotor, int page, int size) {
		Page<Client> result = repository.findAllByPromotor(promotor, PageRequest.of(page, size));
		return new PageImpl<>(mapper.getClientDtosFromEntities(result.getContent()), result.getPageable(),
				result.getTotalElements());
	}

	public Page<ClientDto> getClients(Optional<String> rfc, Optional<String> razonSocial,int page, int size) {
		Page<Client> result;
		
		if(!razonSocial.isPresent() && !rfc.isPresent()) {
			result = repository.findAll(PageRequest.of(page, size));
		}else if(rfc.isPresent()) {
			result = repository.findByRfcContaining(rfc.get(), PageRequest.of(page, size));
		}else {
			result = repository.findByRazonSocialContaining(razonSocial.get(), PageRequest.of(page, size));
		}
		return new PageImpl<>(mapper.getClientDtosFromEntities(result.getContent()), result.getPageable(),
				result.getTotalElements());
	}

	public ClientDto getClientByRfc(String rfc){
		Optional<Client> client = repository.findByRfc(rfc);
		if (client.isPresent()) {
			return mapper.getClientDtoFromEntity(client.get());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("El cliente con el rfc %s no existe", rfc));
		}
	}

	public ClientDto getClientByPromotorAndRfc(String promotor, String rfc) {
		Optional<Client> client = repository.findByEmpresaAndRfc(promotor, rfc);
		if (client.isPresent()) {
			return mapper.getClientDtoFromEntity(client.get());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Cliente con rfc %s no encontrado para %s",rfc, promotor));
		}
	}
	
	public ClientDto insertNewClient(ClientDto cliente) {
		return mapper.getClientDtoFromEntity(repository.save(mapper.getEntityFromClientDto(cliente)));
	}
	
	public ClientDto updateClientInfo(ClientDto client, String rfc) {
		Client dbClient = repository.findByRfc(rfc).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("El cliente con el rfc %s no existe", rfc)));
		dbClient.setActivo(client.getActivo());
		dbClient.setCalle(client.getCalle());
		dbClient.setCodigoPostal(client.getCodigoPostal());
		dbClient.setColonia(client.getColonia());
		dbClient.setCoo(client.getCoo());
		dbClient.setEmail(client.getEmail());
		dbClient.setEstado(client.getEstado());
		dbClient.setMunicipio(client.getMunicipio());
		dbClient.setName(client.getName());
		dbClient.setNoExterior(client.getNoExterior());
		dbClient.setNoInterior(client.getNoInterior());
		dbClient.setPais(client.getPais());
		dbClient.setRazonSocial(client.getRazonSocial());
		//TODO set promotor
		
		return mapper.getClientDtoFromEntity(repository.save(dbClient));
	}

}
