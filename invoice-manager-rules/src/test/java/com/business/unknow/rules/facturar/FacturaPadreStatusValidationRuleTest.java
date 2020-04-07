package com.business.unknow.rules.facturar;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.business.unknow.rules.AbstractRuleTest;
import com.business.unknow.rules.timbrado.FacturaPadreStatusValidationRule;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class FacturaPadreStatusValidationRuleTest extends AbstractRuleTest {

	@Before
	public void init() {
		rules.register(new FacturaPadreStatusValidationRule());
	}

	@Test
	public void timbrarComplementoTest_failNoTimbrada() throws JsonParseException, JsonMappingException, IOException {
	}
	
	@Test
	public void timbrarComplementoTest_failCancelada() throws JsonParseException, JsonMappingException, IOException {
	}
}
