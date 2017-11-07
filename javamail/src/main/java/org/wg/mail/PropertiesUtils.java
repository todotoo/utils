package org.wg.mail;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

;

public class PropertiesUtils {
	Properties properties = new Properties();

	public PropertiesUtils(String file) {
		try {
			//获取资源文件路径
			InputStream in = PropertiesUtils.class.getClassLoader().getResourceAsStream(file);
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public static void main(String[] args) {
		System.out.println(new PropertiesUtils("config/system.properties").getProperty("file_upload_path"));
	}
}
