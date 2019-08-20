package com.business.unknow.services.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author eej000f
 *
 */
@EnableTransactionManagement
@EnableJpaRepositories("com.business.unknow.services.repositories")
public class SpringDataConfig {

}
