package com.business.unknow.services.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.business.unknow.Constants;
import com.business.unknow.client.model.swsapiens.SwSapiensClientException;
import com.business.unknow.client.model.swsapiens.SwSapiensConfig;
import com.business.unknow.model.ClientDto;
import com.business.unknow.services.client.SwSapiensClient;
import com.business.unknow.services.entities.Client;
import com.business.unknow.services.mapper.ClientMapper;
import com.business.unknow.services.mapper.ContribuyenteMapper;
import com.business.unknow.services.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	@Autowired
	private ContribuyenteMapper contribuyenteMapper;

	@Autowired
	private SwSapiensClient swSapiensClient;

	@Autowired
	private ClientMapper mapper;

	public Page<ClientDto> getClientsByParametros(Optional<String> rfc, Optional<String> razonSocial, int page,
			int size) {
		Page<Client> result;
		if (!razonSocial.isPresent() && !rfc.isPresent()) {
			result = repository.findAll(PageRequest.of(page, size));
		} else if (rfc.isPresent()) {
			result = repository.findByRfcIgnoreCaseContaining(String.format("%%%s%%", rfc.get()),
					PageRequest.of(page, size));
		} else {
			result = repository.findByRazonSocialIgnoreCaseContaining(String.format("%%%s%%", razonSocial.get()),
					PageRequest.of(page, size));
		}
		return new PageImpl<>(mapper.getClientDtosFromEntities(result.getContent()), result.getPageable(),
				result.getTotalElements());
	}

	public ClientDto getClientByRFC(String rfc) {
		Client client = repository.findByRfc(rfc).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				String.format("No existe cliente con  rfc %s", rfc)));
		return mapper.getClientDtoFromEntity(client);
	}

	public ClientDto insertNewClient(ClientDto cliente, boolean validation) {
		try {
			Optional<Client> entity = repository.findByRazonSocial(cliente.getInformacionFiscal().getRazonSocial());
			if (entity.isPresent()) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
						String.format("La razon social  %s ya esta creada en el sistema",
								cliente.getInformacionFiscal().getRazonSocial()));
			} else if (validation) {
				SwSapiensConfig config = swSapiensClient.getSwSapiensClient()
						.validateRfc(cliente.getInformacionFiscal().getRfc());
				if (!config.getStatus().equals(Constants.SUCCESS)) {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String
							.format("El rfc %s no es valido para facturar", cliente.getInformacionFiscal().getRfc()));
				}
			}
			cliente.setActivo(false);
			cliente.setFechaActualizacion(new Date());
			cliente.setFechaCreacion(new Date());
			cliente.getInformacionFiscal().setRfc(cliente.getInformacionFiscal().getRfc().toUpperCase());
			return mapper.getClientDtoFromEntity(repository.save(mapper.getEntityFromClientDto(cliente)));
		} catch (SwSapiensClientException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					String.format("El rfc %s no esta dado de alta en el SAt", cliente.getInformacionFiscal().getRfc()));
		}
	}

	public ClientDto updateClientInfo(ClientDto client, String rfc) {
		Client dbClient = repository.findByRfc(rfc).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				String.format("El cliente con el rfc %s no existe", rfc)));
		dbClient.setActivo(client.getActivo());
		dbClient.setInformacionFiscal(contribuyenteMapper.getEntityFromContribuyenteDto(client.getInformacionFiscal()));
		return mapper.getClientDtoFromEntity(repository.save(dbClient));
	}

	public void deleteClientInfo(String rfc) {
		Client dbClient = repository.findByRfc(rfc).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				String.format("El cliente con el rfc %s no existe", rfc)));
		repository.delete(dbClient);
	}

}
