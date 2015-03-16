package de.emo.cit.tuberlin.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = { "file:/media/ferdinand/Emo/Kurses/Master/Arbeit/code/resources/properties/database.properties" })
public class PropertyConfiguration implements InitializingBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(PropertyConfiguration.class);

	@Value("${jdbc.username}")
	private String jdbcUsername;

	@Value("${jdbc.password}")
	private String jdbcPassword;

	@Value("${jdbc.url}")
	private String jdbcUrl;

	@Value("${jdbc.dialect}")
	private String jdbcDialect;

	@Value("${jdbc.showsql}")
	private String jdbcShowSql;

	@Value("${jdbc.driverClassName}")
	private String jdbcDriverClassName;

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

	public String getJdbcDriverClassName() {
		return jdbcDriverClassName;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		LOGGER.info("jdbcDriverClassName.....................: " + jdbcDriverClassName);
		//LOGGER.info("jdbcUsername............................: " + jdbcUsername);
		//LOGGER.info("jdbcPassword............................: " + jdbcPassword);
		//LOGGER.info("jdbcUrl.................................: " + jdbcUrl);
		LOGGER.info("jdbcShowSql.............................: " + jdbcShowSql);
		LOGGER.info("jdbcDialect.............................: " + jdbcDialect);
	}
}
