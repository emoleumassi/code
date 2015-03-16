package de.emo.cit.tuberlin.bootstrap;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import de.emo.cit.tuberlin.Emo;
import de.emo.cit.tuberlin.Track;

@Configuration
@ComponentScan("de.emo.cit.tuberlin")
@EnableTransactionManagement
@Import(value = { PropertyConfiguration.class })
public class ThesisConfiguration implements TransactionManagementConfigurer {

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

//	@Autowired
//	@Bean(name = "sessionFactory")
//	public SessionFactory getSessionFactory(DataSource dataSource) {
//
//		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(
//				dataSource);
//		sessionBuilder.scanPackages("de.emo.cit.tuberlin.model");
//		sessionBuilder.addProperties(getHibernateProperties());
//		return sessionBuilder.buildSessionFactory();
//	}
//
//	@Autowired
//	@Bean(name = "transactionManager")
//	public HibernateTransactionManager getTransactionManager(
//			SessionFactory sessionFactory) {
//		return new HibernateTransactionManager(sessionFactory);
//	}
//
//	@Autowired
//	@Bean(name = "uddiDao")
//	public UDDIDao getUDDIDao(SessionFactory sessionFactory) {
//		return new UDDIDaoImpl(sessionFactory);
//	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.show_sql",
				propertyConfiguration.getJdbcShowSql());
		properties.put("hibernate.dialect",
				propertyConfiguration.getJdbcDialect());
		return properties;
	}
	
	@Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(getDataSource());
    }

    //@Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return txManager();
    }
}
