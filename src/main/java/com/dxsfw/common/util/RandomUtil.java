package com.dxsfw.common.util;

import java.util.LinkedHashMap;
import java.util.Random;

public class RandomUtil {
	private static Random r = new Random();
	
	/**
	 * 生成随机4位数短信验证码
	 * @return
	 */
	public static String general4Number(){
		String number = "";
		for (int i = 0; i < 4; i++) {
			int value = Math.abs(r.nextInt()%10);
			number += String.valueOf(value);
		}
		return number;
	}

	public static void main(String[] args) throws Exception {
		long l = System.currentTimeMillis();
		
		
		LinkedHashMap<String, Long> MOBILE_CHECK_MAP = new LinkedHashMap<String, Long>();
		for (int i = 0; i < 1000000; i++) {
			MOBILE_CHECK_MAP.put("15208918276-19283287", l);
		}
		long l1 = System.currentTimeMillis();
		System.out.println(l1 - l);
		System.out.println(MOBILE_CHECK_MAP.values().size());
		
		Thread.currentThread().sleep(1000*10);
	}
}
