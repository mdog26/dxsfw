package com.dxsfw.common.util;

import java.util.ResourceBundle;

/**
 * 用于根据Key读取properties文件
 * 
 * @Author cole.jiang
 * @CreateDate 2013-7-17
 * @Version 1.0
 */

public class PropertiesReader {
	
	//读取系统配置文件-sysConfig
	public static String readByKey(String key){
		ResourceBundle bundle = ResourceBundle.getBundle("sysConfig");
        return bundle.getString(key);
	}
	
	//读取系统配置文件-interfaceUrl
	public static String readInterfaceUrlByKey(String key){
		ResourceBundle bundle = ResourceBundle.getBundle("interfaceUrl");
        return bundle.getString(key);
	}
	
}
