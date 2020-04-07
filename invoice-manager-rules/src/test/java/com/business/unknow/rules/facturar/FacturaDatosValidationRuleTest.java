package com.business.unknow.rules.facturar;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.business.unknow.rules.AbstractRuleTest;
import com.business.unknow.rules.timbrado.FacturaDatosValidationRule;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class FacturaDatosValidationRuleTest extends AbstractRuleTest {

	@Before
	public void init() {
		rules.register(new FacturaDatosValidationRule());
	}

	@Test
	public void timbrarPueTest() throws JsonParseException, JsonMappingException, IOException {
	}
}
