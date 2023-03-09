package Utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

	private static final String CONFIG_FILEPATH = System.getProperty("user.dir") + "/config.properties";
	private static Properties properties;
	private ConfigReader(){}

	static {
		try {
			FileInputStream inputStream = new FileInputStream(CONFIG_FILEPATH);
			properties = new Properties();
			properties.load(inputStream);
			inputStream.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	public static String getProperty(String keyName){
		return properties.getProperty(keyName);
	}
}