package com.business.unknow.services.services.translators;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.services.mapper.FacturaMapper;
import com.business.unknow.services.repositories.facturas.FacturaRepository;

@Service
public class TimbradoService {

//	@Autowired
//	private SwSapiensClient swSapiensClient;

	@Autowired
	private FacturaRepository repository;

	@Autowired
	private FacturaMapper mapper;

	public FacturaContext timbrarFactura(FacturaContext context) {
		context.getFacturaDto().setUuid(generateRandomUuid(14));
		context.getFacturaDto().setFechaTimbrado(new Date());
		context.getFacturaDto().setStatusFactura(3);
		repository.save(mapper.getEntityFromFacturaDto(context.getFacturaDto()));
		return context;
	}

	public FacturaContext timbraComplemento(FacturaContext context) {
		context.getFacturaDto().setUuid(generateRandomUuid(14));
		context.getFacturaDto().setFechaTimbrado(new Date());
		context.getFacturaDto().setStatusFactura(3);
		repository.save(mapper.getEntityFromFacturaDto(context.getFacturaDto()));
		return context;
	}

	public FacturaContext cancelarFactura(FacturaContext context) {
		context.getFacturaDto().setStatusFactura(6);
		repository.save(mapper.getEntityFromFacturaDto(context.getFacturaDto()));
		return context;
	}

	private String generateRandomUuid(int count) {
		String cadenaAplha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * cadenaAplha.length());
			builder.append(cadenaAplha.charAt(character));
		}
		return builder.toString();
	}

}
