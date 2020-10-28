package test.br.com.luizalabs.api.snips;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import org.junit.Test;

import br.com.luizalabs.api.jdbi.DataBaseResources;

public class LocalPropsTest {

	private final static String FULL_PATH_EXTERNAL = "/Users/RafaMilow/Desktop/LuizaLabs/tomcat/conf_extra/";
	//private final static String FULL_PATH_EXTERNAL = "/tomcat/conf_extra/";

	@Test
	//@Ignore
	public void getLocalPropNormalTest() {
		Properties props = getPropsFromSystem("mysql.properties");
		System.out.println("---> " + props);
	}

	private static Properties getPropFromResources(String fileName) {
		Properties props = new Properties();
		try {
			//InputStream is = this.getClass().getClassLoader().getResourceAsStream(fileName);
			InputStream is = DataBaseResources.class.getClassLoader().getResourceAsStream(fileName);
			props.load(is);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return props;
	}

	private static Properties getPropsFromSystem(String fileName) {
		Properties props = new Properties();
		try {
			FileInputStream inputStream = new FileInputStream(FULL_PATH_EXTERNAL + fileName);
			InputStreamReader isr = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
			props.load(isr);
			inputStream.close();
		} catch (IOException e) {
			System.out.println("nao achei vou tentar outra estraregia");
			return getPropFromResources(fileName);
		}
		return props;
	}

}
