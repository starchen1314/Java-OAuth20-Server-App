package com.dev3l.helwoho.ws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;

import com.dev3l.config.ConfigurationReader;

public class Resources {
	private static final String DATABASE_PROPERTIES = "database.properties";
	private static final String PROPERTIES_KEY_USER = "database.user";
	private static final String PROPERTIES_KEY_PASSWORD = "database.password";
	private static final String PROPERTIES_KEY_URL = "database.url";

	@Produces
	public ConfigurationReader getConfigurationReader() throws ConfigurationException {
		return new ConfigurationReader(DATABASE_PROPERTIES);
	}

	@Produces
	public Connection getConnection(final ConfigurationReader configurationReader) throws SQLException {
		return DriverManager.getConnection(
				"jdbc:mysql://127.1.249.130:3306", 
				"admin6gddn5w",
				"J_CLbcIHm_k2");
	}

	@Produces
	public DSLContext getDSLContext(final Connection connection) throws SQLException {
		return DSL.using(connection, SQLDialect.MYSQL, new Settings());
	}

	@Produces
	@ApplicationScoped
	public Logger getLogger() {
		return LogManager.getLogger();
	}
}
