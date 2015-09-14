package com.dxsfw.common.util;

/**
 * 用于向远程服务器发送request请求，此请求使用Post方式
 * @author rich.chen 2012-02-27
 *
 * 返回服务器端响应的String
 */

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpStatus;
import org.apache.http.params.CoreConnectionPNames;


public class IOLHttpClient {

	private static MultiThreadedHttpConnectionManager connectionManager = null;

	private static HttpClient httpclient = null;
	static {
		connectionManager = new MultiThreadedHttpConnectionManager();
		 httpclient = new HttpClient(connectionManager);
		 //链接超时
		 httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(600*1000);  
		 //读取超时
		 httpclient.getHttpConnectionManager().getParams().setSoTimeout(600*1000);
	}

	//	将流转换成为字符串
	private static String getStringFromStream(PostMethod method)
			throws Exception {
		InputStream resStream = method.getResponseBodyAsStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(resStream,"UTF-8"));
		StringBuffer resBuffer = new StringBuffer();
		String resTemp = "";
		while ((resTemp = br.readLine()) != null) {
			resBuffer.append(resTemp);
		}
		String response = resBuffer.toString();
		return response;
	}

	/*
	 * 处理以gzip压缩格式返回的结果
	 */
	private static String getStringFromStreamByGzip(PostMethod method)
			throws Exception {
		// 得到输入流的长度
		InputStream is = method.getResponseBodyAsStream();
		// 得到输入输出字节数组
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// 定义字节数组
		byte[] b = new byte[1024];
		int len = 0;
		// 循环读取所有的字节
		while ((len = is.read(b, 0, 1024)) != -1) {
			baos.write(b, 0, len);
		}
		//清空baos
		baos.flush();
		//转换成buffer
		byte[] buffer = baos.toByteArray();
		// 解压缩
		String json = new String(ZipUtils.unGZip(buffer), "UTF-8");
		return json;
	}

	private static String sendPostRequest(String url,Map<String, String> requestParam, boolean isGzip) {
		String result = null;
		PostMethod httpost = new PostMethod(url);
		try {

			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			if (requestParam != null) {
				// 添加请求参数
				Iterator<String> keySetIterator = requestParam.keySet()
						.iterator();
				while (keySetIterator.hasNext()) {
					String key = keySetIterator.next();
					String value = requestParam.get(key);
					nvps.add(new NameValuePair(key, value));
				}
			}
			NameValuePair[] data = nvps.toArray(new NameValuePair[] {});
			//设置参数
			httpost.setRequestBody(data);
			// -------另一种json 格式交互接口 -----start
//			String requestBody = "{\"email\":\"xiazl1987@163.com\"}";
//			httpost.setRequestHeader("Content-Type", "application/json");
//			StringRequestEntity entity = new StringRequestEntity(requestBody, "application/json", "utf-8");
//			httpost.setRequestEntity(entity);
			// -------另一种json 格式交互接口 -----end
			//设置编码格式统一为UTF_8
			httpost.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");		
			//设置超时
			httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 3000); 
			//开始执行
			httpclient.executeMethod(httpost);			
			//如果状态为200，表示执行成功
			if (httpost.getStatusCode() == HttpStatus.SC_OK) {
				//如果是Gzip的压缩返回值
				if (isGzip) {
					result = getStringFromStreamByGzip(httpost);
				} else {
					result = getStringFromStream(httpost);
				}
			} else {
				System.out.println(httpost.getStatusCode());
			}
			return result;
		} catch (Exception e) {
			System.out.println("HttpRequest请求出错,错误信息:" + e.getMessage());
			return null;
		} finally {
			httpost.releaseConnection();
			httpclient.getHttpConnectionManager().closeIdleConnections(0);
		}
	}
			
	
	//普通的http请求
	public static String sendPostRequest(String url,
			Map<String, String> requestParam) {
		return sendPostRequest(url, requestParam, false);
	}

	/*
	 * 返回以gzip方式压缩的字符串
	 */
	public static String sendPostRequestByGzip(String url,
			Map<String, String> requestParam) {

		return sendPostRequest(url, requestParam, true);
	}
	
	/**
	 * Josn 接口交互
	 */
	public static String postJsonRequest(String url, String requestJsonBody) {
		HttpClient httpClient = new HttpClient();
		PostMethod method = new PostMethod(url);
		try {
			if (requestJsonBody != null && !requestJsonBody.trim().equals("")) {
				RequestEntity requestEntity = new StringRequestEntity(
						requestJsonBody, "application/json", "UTF-8");
				method.setRequestEntity(requestEntity);
			}
			method.releaseConnection();
			httpClient.executeMethod(method);
			String responses = method.getResponseBodyAsString();
			return responses;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static void main(String[] args) {
		String url = "http://120.25.102.25:8080/dxsfw/pub/updateUser?token=4173D89A3322E9B5AE0384E582782549";
//		String url = "http://127.0.0.1:8080/dxsfw/pub/updateUser?token=6BEFB92F1EBD55723EBA8565B17D2580";
//		String s=IOLHttpClient.sendPostRequest(url,map);
		String params = "{\"userid\": 1, \"name\":\"中午\"}";
		
		HttpClient httpClient = new HttpClient();
	    PostMethod method = new PostMethod(url);
	    try {
	      if(params != null && !params.trim().equals("")) {
	        RequestEntity requestEntity = new StringRequestEntity(params,"application/json","UTF-8");
	        method.setRequestEntity(requestEntity);
	      }
	      method.releaseConnection();
	      httpClient.executeMethod(method);
	      String responses= method.getResponseBodyAsString();
	      System.out.println(responses);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	}	
	
}
