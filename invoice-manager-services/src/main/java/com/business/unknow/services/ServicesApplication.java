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
import com.business.unknow.rules.suites.TimbradoSuite;
import com.business.unknow.rules.suites.ComplementoSuite;
import com.business.unknow.rules.suites.FacturaSuite;
import com.business.unknow.rules.suites.PagoPpdSuite;
import com.business.unknow.rules.suites.PagoPueSuite;

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
	public FacturaSuite getFacturaSuite() {
		return new FacturaSuite();
	}
	
	@Bean
	public TimbradoSuite getFacturarSuite() {
		return new TimbradoSuite();
	}
	
	@Bean
	public PagoPueSuite getPagoPueSuite() {
		return new PagoPueSuite();
	}
	
	@Bean
	public PagoPpdSuite getPagoPpdSuite() {
		return new PagoPpdSuite();
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
