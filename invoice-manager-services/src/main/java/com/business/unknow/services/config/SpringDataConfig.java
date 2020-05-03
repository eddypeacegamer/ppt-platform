package com.business.unknow.services.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author eej000f
 *
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.business.unknow.services.repositories", entityManagerFactoryRef = "userEntityManager", transactionManagerRef = "cloudRdbmsTransactionManager")
public class SpringDataConfig {

	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean userEntityManager(
			@Qualifier("invoiceDatasource") DataSource dsSlave) {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dsSlave);
		em.setPackagesToScan("com.business.unknow.services.entities");
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "validate");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		em.setJpaPropertyMap(properties);
		return em;
	}

	@Primary
	@Bean
	@Autowired
	public PlatformTransactionManager cloudRdbmsTransactionManager(LocalContainerEntityManagerFactoryBean manager) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(manager.getObject());
		return transactionManager;
	}
	
}
