package com.nikitachizhik91.university.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Connector {

	private final static Logger log = LogManager.getLogger(Connector.class.getName());

	private DataSource dataSource;

	public Connection getConnection() throws DaoException {

		log.trace("Started getConnection() method.");

		Connection connection;
		ClassPathXmlApplicationContext context = null;

		try {

			if (dataSource != null) {

				connection = dataSource.getConnection();
				return connection;
			}

			context = new ClassPathXmlApplicationContext("ApplicationContext.xml");

			dataSource = context.getBean("dataSource", BasicDataSource.class);

			connection = dataSource.getConnection();

		} catch (SQLException e) {

			log.error("Cannot get connection", e);
			throw new DaoException("Cannot get connection", e);

		} finally {

			if (context != null) {
				context.close();
			}

		}

		log.trace("Finished getConnection().");

		return connection;
	}
}
// Context context = (Context) new
// InitialContext().lookup("java:comp/env");

// dataSource = (DataSource) context.lookup("jdbc/university2");