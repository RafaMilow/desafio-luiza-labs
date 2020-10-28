package br.com.luizalabs.api.jdbi;

import java.util.Properties;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.tweak.HandleCallback;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import br.com.luizalabs.api.app.Log;
import br.com.luizalabs.api.commons.PropertiesLoader;

public class DataBaseResources {

	final static MysqlDataSource ds = getMySQLDataSource();
	final private static String MYSQL_FILE_NAME = "mysql.properties"; 
	
	private DataBaseResources() {
	}

	public static final <T> T handler(HandleCallback<T> handleCallback) {
		DBI dbi = new DBI(ds);
		return dbi.withHandle(handleCallback);
	}

	private static MysqlDataSource getMySQLDataSource() {
		Properties props = PropertiesLoader.get(MYSQL_FILE_NAME);
		Log.info("Data fom DS MYSQL: Props {}", props);
		MysqlDataSource ds = new MysqlConnectionPoolDataSource();
		ds.setURL(props.getProperty("mysql.url"));
		ds.setUser(props.getProperty("mysql.username"));
		ds.setPassword(props.getProperty("mysql.password"));
		ds.setUseSSL(false);
		return ds;
	}

}
