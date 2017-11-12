package com.bulletproof;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:Hibernate.properties")

@EnableTransactionManagement
public class DatabaseConfig {

    private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
    private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";

    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";

    @Resource
    private Environment environment;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
	entityManagerFactoryBean.setDataSource(dataSource());
	entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
	entityManagerFactoryBean
		.setPackagesToScan(environment.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));
	entityManagerFactoryBean.setJpaProperties(getHibernateProperties());
	return entityManagerFactoryBean;
    }

    @Bean
    public DataSource dataSource() {
	DriverManagerDataSource dataSource = new DriverManagerDataSource();

	dataSource.setDriverClassName(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
	dataSource.setUrl(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
	dataSource.setUsername(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
	dataSource.setPassword(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));

	return dataSource;
    }

    private Properties getHibernateProperties() {
	Properties properties = new Properties();
	properties.put(PROPERTY_NAME_HIBERNATE_DIALECT,
		environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
	properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL,
		environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
	properties.put("hibernate.hbm2ddl.auto", "create");
	return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
	JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
	jpaTransactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
	return jpaTransactionManager;
    }

}