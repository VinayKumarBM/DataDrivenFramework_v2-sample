package com.dff.configs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Config implements GlobalVariables{	
	private static Properties properties = new Properties();
	private static final Logger Log = LogManager.getLogger(Config.class.getName());
	
	public static String getProperty(String key) {
		try {
			InputStream stream = new FileInputStream(new File(CONFIG_FILE));
			properties.load(stream);
			stream.close();
		} catch (FileNotFoundException e) {
			Log.error("File was Not Found: "+e.getMessage());
		} catch (IOException e) {
			Log.error("There was a IO Exception: ", e);
		} 
		return properties.getProperty(key);
	}
}