package com.br.luizalabs.api.jdbi;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.tweak.HandleCallback;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DataBaseResources {

	final static MysqlDataSource ds = getMySQLDataSource();

	private DataBaseResources() {
	}

	public static final <T> T handler(HandleCallback<T> handleCallback) {
		DBI dbi = new DBI(ds);
		return dbi.withHandle(handleCallback);
	}

	private static MysqlDataSource getMySQLDataSource() {

		Properties props = new Properties();
		// FileInputStream fis = null;
		MysqlDataSource ds = null;
		InputStream is = null;
		try {
			is = DataBaseResources.class.getClassLoader().getResourceAsStream("mysql.properties");
			// fis = new FileInputStream("src/test/Resources/mysql.properties");
			props.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ds = new MysqlConnectionPoolDataSource();
		ds.setURL(props.getProperty("mysql.url"));
		ds.setUser(props.getProperty("mysql.username"));
		ds.setPassword(props.getProperty("mysql.password"));
		ds.setUseSSL(false);

		return ds;
	}

}
