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
import com.business.unknow.services.entities.Contribuyente;
import com.business.unknow.services.mapper.ClientMapper;
import com.business.unknow.services.repositories.ClientRepository;
import com.business.unknow.services.repositories.ContribuyenteRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	@Autowired
	private ContribuyenteRepository contribuyenteRepository;

	@Autowired
	private ClientMapper mapper;

	public Page<ClientDto> getClientsByParametros(Optional<String> rfc, Optional<String> razonSocial, int page, int size) {
		Page<Client> result;
		if (!razonSocial.isPresent() && !rfc.isPresent()) {
			result = repository.findAll(PageRequest.of(page, size));
		} else if (rfc.isPresent()) {
			result = repository.findByRfcIgnoreCaseContaining(rfc.get(), PageRequest.of(page, size));
		} else {
			Optional<Contribuyente> contribuyente = contribuyenteRepository.findByRazonSocial(razonSocial.get());
			if (contribuyente.isPresent()) {
				result = repository.findByRfcIgnoreCaseContaining(contribuyente.get().getRfc(),
						PageRequest.of(page, size));
			} else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("La razon social %s del cliente no exite ", razonSocial.get()));
			}
		}
		return new PageImpl<>(mapper.getClientDtosFromEntities(result.getContent()), result.getPageable(),
				result.getTotalElements());
	}

	public ClientDto insertNewClient(ClientDto cliente) {
		return mapper.getClientDtoFromEntity(repository.save(mapper.getEntityFromClientDto(cliente)));
	}

	public ClientDto updateClientInfo(ClientDto client, String rfc) {
		Client dbClient = repository.findByRfc(rfc).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				String.format("El cliente con el rfc %s no existe", rfc)));
		dbClient.setActivo(client.getActivo());
		return mapper.getClientDtoFromEntity(repository.save(dbClient));
	}

}
