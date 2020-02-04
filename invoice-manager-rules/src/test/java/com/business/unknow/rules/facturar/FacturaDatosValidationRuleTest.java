package com.business.unknow.rules.facturar;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.jeasy.rules.api.Facts;
import org.junit.Before;
import org.junit.Test;

import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.factura.FacturaDto;
import com.business.unknow.rules.AbstractRuleTest;
import com.business.unknow.rules.timbrado.FacturaDatosValidationRule;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class FacturaDatosValidationRuleTest extends AbstractRuleTest{

	
	@Before
	public void init() {
		rules.register(new FacturaDatosValidationRule());
	}

	@Test
	public void timbrarPueTest() throws JsonParseException, JsonMappingException, IOException {
		FacturaDto dto = objectMapper.readValue(new File("src/test/resources/factura/dto/facturaPUE.json"),
				FacturaDto.class);
		FacturaContext fc = new FacturaContext();
		fc.setFacturaDto(dto);
		Facts facts = new Facts();
		facts.put("facturaContext", fc);
		rulesEngine.fire(rules, facts);
		assertTrue(fc.isValid());
	}
	
	@Test
	public void timbrarPueTest_fail() throws JsonParseException, JsonMappingException, IOException {
		FacturaDto dto = objectMapper.readValue(new File("src/test/resources/factura/dto/facturaPUE.json"),
				FacturaDto.class);
		dto.setUuid("asd");
		FacturaContext fc = new FacturaContext();
		fc.setFacturaDto(dto);
		Facts facts = new Facts();
		facts.put("facturaContext", fc);
		rulesEngine.fire(rules, facts);
		assertFalse(fc.isValid());
	}
	
	@Test
	public void timbrarPpdTest() throws JsonParseException, JsonMappingException, IOException {
		FacturaDto dto = objectMapper.readValue(new File("src/test/resources/factura/dto/facturaPPD.json"),
				FacturaDto.class);
		FacturaContext fc = new FacturaContext();
		fc.setFacturaDto(dto);
		Facts facts = new Facts();
		facts.put("facturaContext", fc);
		rulesEngine.fire(rules, facts);
		assertTrue(fc.isValid());
	}
	
	@Test
	public void timbrarPpdTest_fail() throws JsonParseException, JsonMappingException, IOException {
		FacturaDto dto = objectMapper.readValue(new File("src/test/resources/factura/dto/facturaPPD.json"),
				FacturaDto.class);
		FacturaContext fc = new FacturaContext();
		dto.setFechaTimbrado(new Date());
		fc.setFacturaDto(dto);
		Facts facts = new Facts();
		facts.put("facturaContext", fc);
		rulesEngine.fire(rules, facts);
		assertFalse(fc.isValid());
	}
	
	
	@Test
	public void timbrarComplementoTest() throws JsonParseException, JsonMappingException, IOException {
		FacturaDto dto = objectMapper.readValue(new File("src/test/resources/factura/dto/facturaComplemento.json"),
				FacturaDto.class);
		FacturaContext fc = new FacturaContext();
		fc.setFacturaDto(dto);
		Facts facts = new Facts();
		facts.put("facturaContext", fc);
		rulesEngine.fire(rules, facts);
		assertTrue(fc.isValid());
	}
	
	@Test
	public void timbrarComplementoTest_fail() throws JsonParseException, JsonMappingException, IOException {
		FacturaDto dto = objectMapper.readValue(new File("src/test/resources/factura/dto/facturaComplemento.json"),
				FacturaDto.class);
		dto.setFolioPadre(null);
		FacturaContext fc = new FacturaContext();
		fc.setFacturaDto(dto);
		Facts facts = new Facts();
		facts.put("facturaContext", fc);
		rulesEngine.fire(rules, facts);
		assertFalse(fc.isValid());
	}
}
