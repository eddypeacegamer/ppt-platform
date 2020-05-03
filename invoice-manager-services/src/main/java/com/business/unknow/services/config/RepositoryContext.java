package com.business.unknow.services.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.business.unknow.services.config.properties.InvoiceConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @author eej000f
 */
@Configuration
@EnableTransactionManagement
@EnableJpaAuditing
public class RepositoryContext {
	
	@Autowired
	private InvoiceConfig cloud;
	
	@Bean(name = "invoiceDatasource")
	public HikariDataSource cloudrdbmsDatasource() {
		return DataSourceBuilder.create().type(HikariDataSource.class).url(cloud.getDataSourceUrl())
				.driverClassName(cloud.getDataSourceClassName()).username(cloud.getDataSourceUser())
				.password(cloud.getDataSourcePass()).build();
	}

	@Bean(name = "invoiceManagerTemplate")
	@Autowired
	public JdbcTemplate associateEvaluationLogTemplate(@Qualifier("invoiceDatasource") DataSource dsSlave) {
		return new JdbcTemplate(dsSlave);
	}

}
