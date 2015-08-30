package com.dxsfw.common.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.lang.StringUtils;

/**
 * @source：ConnectUrlUtils.java
 * @content：链接URL工具类
 * @date：2014-12-09
 * @author： Lynn Zhang
 * @version：
 */
public class ConnectUrlUtils {
	
	/**
	 * 访问URL返回字符串
	 * @param urlStr
	 * @param argContent
	 * @return
	 */
	public static String connectUrl(String urlStr, String argContent) {
		URL url = null;
		HttpURLConnection connection = null;
		DataOutputStream  out = null;
		InputStream is = null;
		BufferedReader br = null;
		String dataStr = "";
		try {
			/*
			 * 创建连接
			 */
			url = new URL(urlStr);
			connection = (HttpURLConnection)url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			connection.setRequestProperty("Accept-Charset", "utf-8");
			connection.connect();
			/*
			 * 写入参数
			 */
			out = new DataOutputStream(connection.getOutputStream());
			if (StringUtils.isNotEmpty(argContent)) {
				out.writeBytes(argContent);
			}
			out.flush();
			/*
			 * 读取流中的json数据
			 */
			is = connection.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			String str = null;
			StringBuffer arrayStr = new StringBuffer("");
			while ((str = br.readLine()) != null) {
				arrayStr.append(str);
			}
			dataStr = arrayStr.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataStr;
	}

}
