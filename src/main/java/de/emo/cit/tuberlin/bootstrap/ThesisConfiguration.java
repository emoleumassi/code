package de.emo.cit.tuberlin.bootstrap;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import de.emo.cit.tuberlin.Emo;
import de.emo.cit.tuberlin.Track;
import de.emo.cit.tuberlin.service.UDDIService;
import de.emo.cit.tuberlin.service.impl.UDDIServiceImpl;

@Configuration
@ComponentScan("de.emo.cit.tuberlin")
@EnableTransactionManagement
@Import(value = { PropertyConfiguration.class })
public class ThesisConfiguration /* implements TransactionManagementConfigurer */{

	private static final String DEFAULT_PACKAGE = "de.emo.cit.tuberlin.model";

	@Autowired
	PropertyConfiguration propertyConfiguration;

	@Bean
	public Emo emo() {
		return new Emo();
	}

	@Bean
	public Track track() {
		return new Track();
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		propertySourcesPlaceholderConfigurer
				.setIgnoreUnresolvablePlaceholders(true);
		return propertySourcesPlaceholderConfigurer;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(getDataSource());
		em.setPackagesToScan(DEFAULT_PACKAGE);

		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		em.setJpaProperties(getHibernateProperties());
		return em;
	}

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(propertyConfiguration
				.getJdbcDriverClassName());
		dataSource.setUrl(propertyConfiguration.getJdbcUrl());
		dataSource.setUsername(propertyConfiguration.getJdbcUsername());
		dataSource.setPassword(propertyConfiguration.getJdbcPassword());
		return dataSource;
	}

	// @Autowired
//	@Bean(name = "uddiService")
//	public UDDIService getUDDIService(EntityManagerFactory entityManagerFactory) {
//		return new UDDIServiceImpl(entityManagerFactory);
//	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.show_sql",
				propertyConfiguration.getJdbcShowSql());
		properties.put("hibernate.dialect",
				propertyConfiguration.getJdbcDialect());
		return properties;
	}

	@Bean(name = "transactionManager")
	public PlatformTransactionManager txManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
}
