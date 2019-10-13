package com.business.unknow.commons.util;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;

import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.model.factura.FacturaDto;

public class FacturaCalculatorTest {

	
	 @Test
	 public void folioCalculatorTest() throws InvoiceManagerException {
		 FacturaDto factura= new FacturaDto();
		 factura.setRfcEmisor("A");
		 factura.setRfcRemitente("b");
		 factura.setFechaCreacion(new Date());
		String cadena= FacturaCalculator.folioEncrypt(factura);
		assertNotNull(cadena);
		System.out.println(cadena);
	 }
}
