package com.dxsfw.common.constants;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

import com.dxsfw.common.util.PropertiesReader;

public class GlobalValue {
	public final static String MODLE_USER = "user";
	public final static String MODLE_JIANLI = "jianli";
	public final static String MODLE_JIANZHI = "jianzhi";
	public final static String MODLE_PARTY = "party";
	public final static String MODLE_BBS = "bbs";
	public final static String MODLE_REPLY = "reply";
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
	
	/**
	 * 单图片业务模块
	 * 模块和表映射
	 */
	public static Set<String> TABLE_SET = new HashSet<String>();
	
	/** @Fields SYS_DEFAULT_PAGE_SIZE : 默认列表展现数 */
	public final static int SYS_DEFAULT_PAGE_SIZE = Integer.parseInt(PropertiesReader.readByKey("default.page.size"));
	
	/** @Fields SYS_DEFAULT_PAGE_SHOWN : 默认列表显示分页数：上一页 1 2 3 4 5 6 下一页 */
	public final static int SYS_DEFAULT_PAGE_SHOWN = 1;//这里是手机端，默认1页
	
	//公共图片路径
	public static String PATH_PICTURE = null;
		
	//个人头像路径
	public static String PATH_USER_PICTURE = null;
	
	//简历图片路径
	public static String PATH_JIANLI_PICTURE = null;
	
	//简历头像路径
	public static String PATH_JIANLI_FUJIAN = null;

	static {
		TABLE_SET.add(MODLE_JIANZHI);
		TABLE_SET.add(MODLE_REPLY);
	}
}
