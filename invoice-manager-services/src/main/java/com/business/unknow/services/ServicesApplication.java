package com.business.unknow.services;

import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.business.unknow.commons.factura.CdfiHelper;
import com.business.unknow.commons.util.DateHelper;
import com.business.unknow.commons.util.FacturaHelper;
import com.business.unknow.commons.util.FileHelper;
import com.business.unknow.commons.util.NumberHelper;
import com.business.unknow.commons.util.StringHelper;
import com.business.unknow.rules.suites.CancelacionSuite;
import com.business.unknow.rules.suites.FacturarSuite;
import com.business.unknow.rules.suites.ComplementoSuite;

/**
 * @author eej000f
 *
 */
@SpringBootApplication
public class ServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicesApplication.class, args);
	}

	@Bean
	public ComplementoSuite getComplementoSuite() {
		return new ComplementoSuite();
	}

	@Bean
	public FacturarSuite getFacturarSuite() {
		return new FacturarSuite();
	}

	@Bean
	public CancelacionSuite getCancelacionSuite() {
		return new CancelacionSuite();
	}
	
	@Bean
	public RulesEngine getRulesEngine() {
		return new DefaultRulesEngine();
	}

	@Bean
	public CdfiHelper getCdfiHelper() {
		return new CdfiHelper();
	}

	@Bean
	public FacturaHelper getFacturaHelper() {
		return new FacturaHelper();
	}

	@Bean
	public DateHelper getDateHelper() {
		return new DateHelper();
	}

	@Bean
	public NumberHelper getNumberHelper() {
		return new NumberHelper();
	}
	
	@Bean
	public FileHelper getFileHelper() {
		return new FileHelper();
	}
	
	@Bean
	public StringHelper getStringHelper() {
		return new StringHelper();
	}

}
