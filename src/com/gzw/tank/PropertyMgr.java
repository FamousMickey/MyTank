package com.gzw.tank;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {
	static Properties props = new Properties();
	
	static {
		try {
			props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private PropertyMgr() {
	}
	
	public static String get(String key) {
		if (props == null) {
			return null;
		}
		return props.getProperty(key);
	}
}
