package com.business.unknow.services.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.business.unknow.Constants;
import com.business.unknow.client.swsapiens.util.SwSapiensClientException;
import com.business.unknow.client.swsapiens.util.SwSapiensConfig;
import com.business.unknow.commons.util.ContactoHelper;
import com.business.unknow.commons.validator.ClienteValidator;
import com.business.unknow.model.dto.services.ClientDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.entities.Client;
import com.business.unknow.services.entities.Contribuyente;
import com.business.unknow.services.mapper.ClientMapper;
import com.business.unknow.services.repositories.ClientRepository;
import com.business.unknow.services.repositories.ContribuyenteRepository;
import com.business.unknow.services.services.executor.SwSapinsExecutorService;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	@Autowired
	private ContribuyenteRepository contribuyenteRepository;

	@Autowired
	private ClientMapper mapper;

	@Autowired
	private SwSapinsExecutorService swSapinsExecutorService;

	@Autowired
	private CatalogsService catalogsService;

	private ClienteValidator clientValidator = new ClienteValidator();

	private ContactoHelper contactoHelper = new ContactoHelper();

	public Page<ClientDto> getClientsByParametros(Optional<String> promotor, String status, String rfc,
			String razonSocial, int page, int size) {
		Page<Client> result;

		if (promotor.isPresent()) {
			result = repository.findClientsFromPromotorByParms(promotor.get(), String.format("%%%s%%", status),
					String.format("%%%s%%", rfc), String.format("%%%s%%", razonSocial),
					PageRequest.of(page, size, Sort.by("fechaActualizacion").descending()));
		} else {
			result = repository.findClientsByParms(String.format("%%%s%%", status), String.format("%%%s%%", rfc),
					String.format("%%%s%%", razonSocial),
					PageRequest.of(page, size, Sort.by("fechaActualizacion").descending()));
		}
		return new PageImpl<>(mapper.getClientDtosFromEntities(result.getContent()), result.getPageable(),
				result.getTotalElements());
	}

	public ClientDto getClientByRFC(String rfc) {
		Client client = repository.findByRfc(rfc).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				String.format("No existe cliente con  rfc %s", rfc)));
		return mapper.getClientDtoFromEntity(client);
	}

	public List<ClientDto> getClientsByPromotor(String promotor) {
		return mapper.getClientDtosFromEntities(repository.findByCorreoPromotor(promotor));
	}

	public ClientDto insertNewClient(ClientDto cliente) throws InvoiceManagerException {
		try {
			clientValidator.validatePostCliente(cliente);
			catalogsService.getCodigosPostaleByCode(cliente.getInformacionFiscal().getCp());
			Optional<Contribuyente> entity = contribuyenteRepository.findByRfc(cliente.getInformacionFiscal().getRfc());
			if (entity.isPresent()) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String
						.format("El rfc  %s ya esta creado en el sistema", cliente.getInformacionFiscal().getRfc()));
			}
			SwSapiensConfig config = swSapinsExecutorService
					.validateRfc(cliente.getInformacionFiscal().getRfc().toUpperCase());
			if (!config.getStatus().equals(Constants.SUCCESS)) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
						String.format("El RFC %s no es valido para facturar", cliente.getInformacionFiscal().getRfc()));
			}
			cliente.setCorreoContacto(contactoHelper.translateContacto(cliente.getInformacionFiscal().getRfc(),
					cliente.getCorreoPromotor(), cliente.getPorcentajeContacto()));
			cliente.setActivo(false);
			cliente.getInformacionFiscal().setRfc(cliente.getInformacionFiscal().getRfc().toUpperCase());
			return mapper.getClientDtoFromEntity(repository.save(mapper.getEntityFromClientDto(cliente)));
		} catch (SwSapiensClientException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					String.format("El RFC %s no esta dado de alta en el SAT", cliente.getInformacionFiscal().getRfc()));
		}
	}

	public ClientDto updateClientInfo(ClientDto client, String rfc) throws InvoiceManagerException {
		clientValidator.validatePostCliente(client);
		client.setCorreoContacto(
				contactoHelper.translateContacto(client.getInformacionFiscal().getRfc(),
						client.getCorreoPromotor(), client.getPorcentajeContacto()));
		if (repository.findByRfc(rfc).isPresent()) {
			return mapper.getClientDtoFromEntity(repository.save(mapper.getEntityFromClientDto(client)));
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					String.format("El cliente con el rfc %s no existe", rfc));
		}
	}

	public void deleteClientInfo(String rfc) {
		Client dbClient = repository.findByRfc(rfc).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				String.format("El cliente con el rfc %s no existe", rfc)));
		repository.delete(dbClient);
	}

}
