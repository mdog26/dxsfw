package com.dxsfw.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class StringTokenizerUtils {

	/**
	 * 将str将多个分隔符进行切分，
	 * 
	 * 示例：StringTokenizerUtils.split("1,2;3 4"," ,;"); 返回: ["1","2","3","4"]
	 * 
	 * @param str
	 * @param seperators
	 * @return
	 */
	@SuppressWarnings("all")
	public static String[] split(String str, String seperators) {
		StringTokenizer tokenlizer = new StringTokenizer(str, seperators);
		List result = new ArrayList();

		while (tokenlizer.hasMoreElements()) {
			Object s = tokenlizer.nextElement();
			result.add(s);
		}
		return (String[]) result.toArray(new String[result.size()]);
	}

	/**
	 * 常用逗号分隔
	 * 
	 * @Author Leo.Zhou
	 * @Version 1.0
	 * @CreateDate 2013-7-30
	 *
	 * @param str
	 * @return
	 */
	public static List split(String str, boolean trimFlag) {
		StringTokenizer tokenlizer = new StringTokenizer(str, ",");
		List result = new ArrayList();

		while (tokenlizer.hasMoreElements()) {
			String s = (String) tokenlizer.nextElement();
			if(trimFlag) {
				result.add(s.trim());
			}else{
				result.add(s);
			}
		}

		return result;
	}
}
