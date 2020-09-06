package com.fdmgroup.jdbcdemo.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {
	private Properties properties;

	public PropertiesManager(String propertiesPath) {
		super();
		loadProperties(propertiesPath);
	}

	private void loadProperties(String propertiesPath) {
		if (propertiesPath == null || propertiesPath.trim().isEmpty()) {
			throw new IllegalArgumentException("property path argument empty or null");
		}
		
		File file = new File(propertiesPath);
		
		
		try (FileReader filereader = new FileReader(file);){
			properties = new Properties();
			properties.load(filereader);
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	public String get(String key) {
		String x=properties.getProperty(key);
		return x;
	}

}
