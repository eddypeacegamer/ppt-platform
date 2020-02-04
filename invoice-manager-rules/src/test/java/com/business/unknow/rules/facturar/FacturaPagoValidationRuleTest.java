package com.business.unknow.rules.facturar;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jeasy.rules.api.Facts;
import org.junit.Before;
import org.junit.Test;

import com.business.unknow.enums.RevisionPagosEnum;
import com.business.unknow.model.PagoDto;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.rules.AbstractRuleTest;
import com.business.unknow.rules.timbrado.FacturaPagoValidationRule;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;

public class FacturaPagoValidationRuleTest extends AbstractRuleTest {

	@Before
	public void init() {
		rules.register(new FacturaPagoValidationRule());
	}

	@Test
	public void timbrarPueTest() throws JsonParseException, JsonMappingException, IOException {
		List<PagoDto> dtos = objectMapper.readValue(new File("src/test/resources/pago/dto/pagos.json"),
				new TypeReference<List<PagoDto>>() {
				});
		FacturaContext fc = new FacturaContext();
		fc.setPagos(dtos);
		Facts facts = new Facts();
		facts.put("facturaContext", fc);
		rulesEngine.fire(rules, facts);
		assertTrue(fc.isValid());
	}

	@Test
	public void timbrarPueTest_fail() throws JsonParseException, JsonMappingException, IOException {
		List<PagoDto> dtos = objectMapper.readValue(new File("src/test/resources/pago/dto/pagos.json"),
				new TypeReference<List<PagoDto>>() {
				});
		FacturaContext fc = new FacturaContext();
		dtos.forEach(a -> a.setStatusPago(RevisionPagosEnum.VALIDACION.getDescripcion()));
		fc.setPagos(dtos);
		Facts facts = new Facts();
		facts.put("facturaContext", fc);
		rulesEngine.fire(rules, facts);
		assertFalse(fc.isValid());
	}
}
