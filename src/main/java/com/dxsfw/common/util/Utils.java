package com.dxsfw.common.util;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.UUID;


public class Utils {
		
	//编码格式
	public static final String CHARSET = "UTF-8";
	
	public static final String toJson(Object o) {		
		ObjectMapper mapper = new ObjectMapper();
		String outJson=null;
		try {
            mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));   //格式化时间类型
            outJson = mapper.writeValueAsString(o);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}				
		return outJson;
	}

	  /**  
	     * 如果JSON字符串为Null或"null"字符串,返回Null.  
	     * 如果JSON字符串为"[]",返回空集合.  
	     *   
	     * 如需读取集合如List/Map,且不是List<String>这种简单类型时使用如下语句:  
	     * List<MyBean> beanList = binder.getMapper().readValue(listString, new TypeReference<List<MyBean>>() {});  
	     */  
	public static <T> T fromJson(String jsonString, Class<T> clazz) {
		ObjectMapper mapper = new ObjectMapper();
	    if (StringUtils.isEmpty(jsonString)) {   
	        return null;   
	    }   

	   try {   
	        return mapper.readValue(jsonString, clazz);   
	    } catch (IOException e) {   
	        return null;   
	    }   
	}   


    public static String getUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().toLowerCase().replace("-", "");
    }


    public static String getDateTime(Date value){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = formatter.format(value);
        return formattedDate;
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    
    //判断对象是否为空
  	public static boolean empty(Object obj) {
  		if (obj == null) {
  			return true;
  		} else if (obj instanceof String && (obj.equals("") || obj.equals("null"))) {
  			return true;
  		} else if (obj instanceof Number && ((Number) obj).doubleValue() == 0) {
  			return true;
  		} else if (obj instanceof Boolean && !((Boolean) obj)) {
  			return true;
  		} else if (obj instanceof Collection && ((Collection) obj).isEmpty()) {
  			return true;
  		} else if (obj instanceof Map && ((Map) obj).isEmpty()) {
  			return true;
  		} else if (obj instanceof Object[] && ((Object[]) obj).length == 0) {
  			return true;
  		}
  		return false;
  	}
  	
  	//对真实数据简单加密
  	public static String authCode(String value, String operation) {
  		
  		String string = "";
  		value = value==null ? "":value;
  		
  		if("encode".equals(operation)){//编码
  			string = Base64.encode(value.getBytes());
  		}else if("decode".equals(operation)){//解码
  			try {
  				string = new String(Base64.decode(value));
  			} catch (Exception e) {
  				e.printStackTrace();
  				string = "";
  			}
  		}
  		return string;
  	}
  	
  	//得到当前站点的全称URL
  	public static String getSiteUrl(HttpServletRequest request) throws UnknownHostException {
  		int port = request.getServerPort();
  		String ip = request.getServerName();
  		return request.getScheme() + "://" + ip + (port == 80 ? "" : ":" + port)+ request.getContextPath() + "/";
  	}
  	
  	//URL编码转换
  	public static String urlEncode(String s) {
  		return urlEncode(s, CHARSET);
  	}
  	
  	public static String urlEncode(String s, String enc) {
  		if (!empty(s)) {
  			try {
  				return URLEncoder.encode(s, enc);
  			} catch (UnsupportedEncodingException e) {
  				e.printStackTrace();
  			}
  		}
  		return s;
  	}
}
