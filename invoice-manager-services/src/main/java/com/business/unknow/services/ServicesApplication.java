package com.business.unknow.services;

import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
	public ComplementoSuite getPreValidationSuite() {
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
}
