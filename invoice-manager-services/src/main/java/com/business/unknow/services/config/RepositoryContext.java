package com.business.unknow.services.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author eej000f
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.business.unknow.services.repositories")
@EnableJpaAuditing
public class RepositoryContext {

}
