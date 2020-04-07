package com.business.unknow.rules.facturar;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.business.unknow.rules.AbstractRuleTest;
import com.business.unknow.rules.timbrado.FacturaStatusRule;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class FacturaStatusRuleTest extends AbstractRuleTest {

	@Before
	public void init() {
		rules.register(new FacturaStatusRule());
	}

	@Test
	public void timbrarPueTest() throws JsonParseException, JsonMappingException, IOException {
	}
	
	@Test
	public void timbrarPueTest_failCancelado() throws JsonParseException, JsonMappingException, IOException {
	}

}
