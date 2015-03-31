package de.emo.cit.tuberlin.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = { "classpath:database.properties" })
public class PropertyConfiguration implements InitializingBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(PropertyConfiguration.class);

	@Value("${jdbc.username}")
	private String jdbcUsername;

	@Value("${jdbc.password}")
	private String jdbcPassword;

	@Value("${jdbc.url}")
	private String jdbcUrl;

	@Value("${hibernate.dialect}")
	private String jdbcDialect;

	@Value("${hibernate.showsql}")
	private String jdbcShowSql;

	@Value("${hibernate.hbm2ddl.auto}")
	private String jdbcHbm2ddlAuto;
	
	@Value("${jdbc.driverClassName}")
	private String jdbcDriverClassName;
	
	@Value("{thesis.host}")
	private String thesisHost;

	public String getJdbcDialect() {
		return jdbcDialect;
	}

	public String getJdbcUsername() {
		return jdbcUsername;
	}

	public String getJdbcPassword() {
		return jdbcPassword;
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public String getJdbcShowSql() {
		return jdbcShowSql;
	}
	
	public String getJdbcHbm2ddlAuto() {
		return jdbcHbm2ddlAuto;
	}

	public String getJdbcDriverClassName() {
		return jdbcDriverClassName;
	}
	
	public String getThesisHost() {
		return thesisHost;
	}

	//@Override
	public void afterPropertiesSet() throws Exception {
		LOGGER.info("jdbcDriverClassName.....................: " + jdbcDriverClassName);
		LOGGER.info("jdbcUsername............................: " + jdbcUsername);
		LOGGER.info("jdbcPassword............................: " + jdbcPassword);
		LOGGER.info("jdbcUrl.................................: " + jdbcUrl);
		LOGGER.info("jdbcShowSql.............................: " + jdbcShowSql);
		LOGGER.info("jdbcDialect.............................: " + jdbcDialect);
		LOGGER.info("jdbcHbm2ddlAuto.........................: " + jdbcHbm2ddlAuto);
		LOGGER.info("thesisHost..............................: " + thesisHost);
	}
}
