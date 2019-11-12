package com.business.unknow.rules.facturar;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.jeasy.rules.api.Facts;
import org.junit.Before;
import org.junit.Test;

import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.factura.FacturaDto;
import com.business.unknow.rules.AbstractRuleTest;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class FacturaPadreStatusValidationRuleTest extends AbstractRuleTest {

	@Before
	public void init() {
		rules.register(new FacturaPadreStatusValidationRule());
	}

	@Test
	public void timbrarComplementoTest() throws JsonParseException, JsonMappingException, IOException {
		FacturaDto dto = objectMapper.readValue(new File("src/test/resources/factura/dto/facturaPadre.json"),
				FacturaDto.class);
		FacturaContext fc = new FacturaContext();
		fc.setFacturaPadreDto(dto);
		Facts facts = new Facts();
		facts.put("facturaContext", fc);
		rulesEngine.fire(rules, facts);
		assertTrue(fc.isValid());
	}
	
	@Test
	public void timbrarComplementoTest_failNoTimbrada() throws JsonParseException, JsonMappingException, IOException {
		FacturaDto dto = objectMapper.readValue(new File("src/test/resources/factura/dto/facturaPadre.json"),
				FacturaDto.class);
		dto.setStatusFactura(2);
		FacturaContext fc = new FacturaContext();
		fc.setFacturaPadreDto(dto);
		Facts facts = new Facts();
		facts.put("facturaContext", fc);
		rulesEngine.fire(rules, facts);
		assertFalse(fc.isValid());
	}
	
	@Test
	public void timbrarComplementoTest_failCancelada() throws JsonParseException, JsonMappingException, IOException {
		FacturaDto dto = objectMapper.readValue(new File("src/test/resources/factura/dto/facturaPadre.json"),
				FacturaDto.class);
		dto.setStatusFactura(6);
		FacturaContext fc = new FacturaContext();
		fc.setFacturaPadreDto(dto);
		Facts facts = new Facts();
		facts.put("facturaContext", fc);
		rulesEngine.fire(rules, facts);
		assertFalse(fc.isValid());
	}
}
