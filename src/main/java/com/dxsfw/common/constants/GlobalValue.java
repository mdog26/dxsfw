package com.dxsfw.common.constants;

import java.util.LinkedHashMap;

public class GlobalValue {
	/**
	 * 记录用户登录的token信息
	 * Map<sessionid, userid>
	 */
	public static LinkedHashMap<String, Integer> USER_TOKEN_MAP = new LinkedHashMap<String, Integer>();
	
	/**
	 * 手机验证码
	 * Map<mobile, check msg>
	 */
	public static LinkedHashMap<String, Long> MOBILE_CHECK_MAP = new LinkedHashMap<String, Long>();
	
	/** @Fields SYS_DEFAULT_PAGE_SIZE : 默认列表展现数 */
	public final static int SYS_DEFAULT_PAGE_SIZE = 15;
	
	/** @Fields SYS_DEFAULT_PAGE_SHOWN : 默认列表显示分页数：上一页 1 2 3 4 5 6 下一页 */
	public final static int SYS_DEFAULT_PAGE_SHOWN = 5;
	
	//个人头像路径
	public static String PATH_USER_PICTURE = null;
	
	//简历图片路径
	public static String PATH_JIANLI_PICTURE = null;
	
	//简历头像路径
	public static String PATH_JIANLI_FUJIAN = null;

}
