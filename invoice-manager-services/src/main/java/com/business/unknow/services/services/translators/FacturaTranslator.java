package com.business.unknow.services.services.translators;

import org.springframework.stereotype.Service;

import com.business.unknow.model.context.FacturaContext;

@Service
public class FacturaTranslator {
	
	public FacturaContext translateFactura(FacturaContext context) {
		return context;
	}

}
