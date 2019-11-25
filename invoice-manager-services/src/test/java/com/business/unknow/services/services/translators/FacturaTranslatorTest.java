package com.business.unknow.services.services.translators;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.business.unknow.commons.builder.FacturaContextBuilder;
import com.business.unknow.model.EmpresaDto;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.model.factura.FacturaDto;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class FacturaTranslatorTest {

	@Autowired
	private FacturaTranslator service;

	private ObjectMapper objectMapper = new ObjectMapper();

//	@Test
//	public void translateFactura()
//			throws InvoiceManagerException, JsonParseException, JsonMappingException, IOException {
//		FacturaDto dto = objectMapper.readValue(new File("src/test/resources/facturas/factura.json"), FacturaDto.class);
//		EmpresaDto empresaDto = objectMapper.readValue(new File("src/test/resources/empresas/empresa.json"), EmpresaDto.class);
//		FacturaContextBuilder fcb = new FacturaContextBuilder().setFacturaDto(dto).setEmpresaDto(empresaDto);
//		FacturaContext context = fcb.build();
//		FacturaContext output=service.translateFactura(context);
//		assertNotNull(output);
//	}

}
