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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
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
	
	public static void main(String[] args) {
		Map map=new HashMap();
		map.put("agentIolId ", "AA1000000046");
		String s=IOLHttpClient.sendPostRequest("http://10.0.111.184:8088/IOL_CC/getAgentInfo.do",map);
		System.out.println("www--- " + s);
	}	
	
}
