package com.business.unknow.services.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.business.unknow.model.EmpresaDto;
import com.business.unknow.model.cfdi.Cfdi;
import com.business.unknow.model.factura.FacturaDto;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class FacturaCfdiTranslatorMapperTest {

	@Autowired
	private FacturaCfdiTranslatorMapper mapper;
	private ObjectMapper objectMapper = new ObjectMapper();

//	@Test
//	public void cdfiRootInfoTest() throws JsonParseException, JsonMappingException, IOException {
//		FacturaDto dto = objectMapper.readValue(new File("src/test/resources/facturas/factura.json"),
//				FacturaDto.class);
//		EmpresaDto empresaDto = objectMapper.readValue(new File("src/test/resources/empresas/empresa.json"),
//				EmpresaDto.class);
//		Cfdi cdfi = mapper.cdfiRootInfo(dto,empresaDto);
//		assertEquals(dto.getFolio(), cdfi.getFolio());
//		assertNotNull(cdfi);
//	}
}
