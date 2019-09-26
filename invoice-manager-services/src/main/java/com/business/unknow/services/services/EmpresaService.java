package com.business.unknow.services.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.business.unknow.model.EmpresaDto;
import com.business.unknow.services.entities.Contribuyente;
import com.business.unknow.services.entities.Empresa;
import com.business.unknow.services.mapper.EmpresaMapper;
import com.business.unknow.services.repositories.ContribuyenteRepository;
import com.business.unknow.services.repositories.EmpresaRepository;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository repository;

	@Autowired
	private ContribuyenteRepository contribuyenteRepository;

	@Autowired
	private EmpresaMapper mapper;

	public Page<EmpresaDto> getEmpresasByParametros(Optional<String> rfc, Optional<String> razonSocial, int page, int size) {
		Page<Empresa> result;
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
						String.format("La razon social %s de la empresa no exite ", razonSocial.get()));
			}
		}
		return new PageImpl<>(mapper.getEmpresaDtosFromEntities(result.getContent()), result.getPageable(),
				result.getTotalElements());
	}

	public EmpresaDto insertNewEmpresa(EmpresaDto empresa) {
		return mapper.getEmpresaDtoFromEntity(repository.save(mapper.getEntityFromEmpresaDto(empresa)));
	}

	public EmpresaDto updateEmpresaInfo(EmpresaDto EmpresaDto, String rfc) {
		Empresa empresa = repository.findByRfc(rfc).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				String.format("El empresa con el rfc %s no existe", rfc)));
		empresa.setReferencia(empresa.getReferencia());
		empresa.setWeb(empresa.getWeb());
		empresa.setSucursal(empresa.getSucursal());
		empresa.setLogotipo(empresa.getLogotipo());
		empresa.setLlavePrivada(empresa.getLlavePrivada());
		empresa.setCertificado(empresa.getCertificado());
		empresa.setPw(empresa.getPw());
		empresa.setActivo(empresa.getActivo());
		return mapper.getEmpresaDtoFromEntity(repository.save(empresa));
	}

}
