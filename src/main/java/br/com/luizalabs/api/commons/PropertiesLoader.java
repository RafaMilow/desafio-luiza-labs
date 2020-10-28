package br.com.luizalabs.api.commons;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import br.com.luizalabs.api.app.Log;

public class PropertiesLoader {

	private final static String FULL_PATH_EXTERNAL = "/usr/local/tomcat/conf_extra/";

	public static Properties get(String fileName) {
		Properties props = new Properties();
		try {
			FileInputStream inputStream = new FileInputStream(FULL_PATH_EXTERNAL + fileName);
			InputStreamReader isr = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
			props.load(isr);
			inputStream.close();
		} catch (IOException e) {
			return getPropFromResources(fileName);
		}
		Log.info("Data fom PL SYSTEM: Props {}", props);
		return props;
	}

	private static Properties getPropFromResources(String fileName) {
		Properties props = new Properties();
		try {
			InputStream is = PropertiesLoader.class.getClassLoader().getResourceAsStream(fileName);
			props.load(is);
			is.close();
		} catch (Exception e) {
			Log.error("problemas no PL", e);
		}
		Log.info("Data fom PL RESOURCES: Props {}", props);
		return props;
	}
	
}
