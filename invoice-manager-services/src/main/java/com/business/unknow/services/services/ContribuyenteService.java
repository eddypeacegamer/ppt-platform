package com.business.unknow.services.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.business.unknow.model.ContribuyenteDto;
import com.business.unknow.services.entities.Contribuyente;
import com.business.unknow.services.mapper.ContribuyenteMapper;
import com.business.unknow.services.repositories.ContribuyenteRepository;

@Service
public class ContribuyenteService {

	@Autowired
	private ContribuyenteRepository repository;

	@Autowired
	private ContribuyenteMapper mapper;

	public ContribuyenteDto getContribuyenteByRfc(String rfc) {
		Optional<Contribuyente> client = repository.findByRfc(rfc);
		if (client.isPresent()) {
			return mapper.getContribuyenteToFromEntity(client.get());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					String.format("El Contribuyente con el rfc %s no existe", rfc));
		}
	}

	public ContribuyenteDto insertNewContribuyente(ContribuyenteDto contribuyente) {
		return mapper
				.getContribuyenteToFromEntity(repository.save(mapper.getEntityFromContribuyenteDto(contribuyente)));
	}

	public ContribuyenteDto updateContribuyenteInfo(ContribuyenteDto contribuyenteDto, String rfc) {
		Contribuyente contribuyente = repository.findByRfc(rfc)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("El cliente con el rfc %s no existe", rfc)));
		contribuyente.setGiro(contribuyenteDto.getGiro());
		contribuyente.setNombre(contribuyenteDto.getNombre());
		contribuyente.setCurp(contribuyenteDto.getCurp());
		contribuyente.setRazonSocial(contribuyenteDto.getRazonSocial());
		contribuyente.setCalle(contribuyenteDto.getCalle());
		contribuyente.setNoExterior(contribuyenteDto.getNoExterior());
		contribuyente.setNoInterior(contribuyenteDto.getNoInterior());
		contribuyente.setMunicipio(contribuyenteDto.getMunicipio());
		contribuyente.setLocalidad(contribuyenteDto.getLocalidad());
		contribuyente.setEstado(contribuyenteDto.getEstado());
		contribuyente.setPais(contribuyenteDto.getPais());
		contribuyente.setCp(contribuyenteDto.getCp());
		contribuyente.setCorreo(contribuyenteDto.getCorreo());
		contribuyente.setTelefono(contribuyenteDto.getTelefono());
		return mapper.getContribuyenteToFromEntity(repository.save(contribuyente));
	}
}
