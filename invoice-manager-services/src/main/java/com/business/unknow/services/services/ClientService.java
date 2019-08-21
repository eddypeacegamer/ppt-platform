package com.business.unknow.services.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.business.unknow.model.ClientDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.entities.Client;
import com.business.unknow.services.mapper.ClientMapper;
import com.business.unknow.services.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	@Autowired
	private ClientMapper mapper;


	public ClientDto getClientByRfc(String rfc) throws InvoiceManagerException {
			Optional<Client> client = repository.findByRfc(rfc);
			if (client.isPresent()) {
				return mapper.getClientDtoFromentity(client.get());
			} else {
				throw new InvoiceManagerException("Client not found",
						String.format("The client with the rfc %s does not exist", rfc),
						HttpStatus.NOT_FOUND.value());
			}
	}

}
