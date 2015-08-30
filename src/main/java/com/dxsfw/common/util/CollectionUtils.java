package com.dxsfw.common.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class CollectionUtils {

	public static <E> boolean isNil(Collection<E> colletion){
		return colletion == null || colletion.isEmpty();
	}
	
	public static <E> boolean isNotNil(Collection<E> colletion) {
		return !isNil(colletion);
	}
	
	public static List<BigDecimal> csv2Decimal(String csv) {
		List<BigDecimal> list = new ArrayList<BigDecimal>();
		if (csv != null && csv.length() > 0) {
			String[] string = csv.split(",");
			if (string != null) {
				for (String value : string) {
					if (value != null && value.trim().length() > 0) {
						list.add(new BigDecimal(value.trim()));
					}
				}
			}
		}

		return list;
	}

	public static List<String> csv2String(String csv) {
		List<String> list = new ArrayList<String>();
		if (csv != null && csv.length() > 0) {
			String[] string = csv.split(",");
			if (string != null) {
				for (String value : string) {
					if (value != null && value.trim().length() > 0) {
						list.add(value.trim());
					}
				}
			}
		}

		return list;
	}
	
	public static List<String> csv2String(String csv, String separtor) {
		List<String> list = new ArrayList<String>();
		if (csv != null && csv.length() > 0) {
			String[] string = csv.split(separtor);
			if (string != null) {
				for (String value : string) {
					if (value != null && value.trim().length() > 0) {
						list.add(value.trim());
					}
				}
			}
		}
		return list;
	}

	/**
	 * 字符串集合转字符串
	 * 
	 * @Author Leo.Zhou
	 * @Version 1.0
	 * @CreateDate 2013-2-16
	 *
	 * @param list
	 * @return
	 */
	public static String sl2string(List<String> list) {
		return sl2string(list, Constants.SEPARTOR);
	}

	/**
	 * 字符串集合转字符串
	 * 
	 * @Author Leo.Zhou
	 * @Version 1.0
	 * @CreateDate 2013-2-16
	 *
	 * @param list
	 * @param separtor
	 * @return
	 */
	public static String sl2string(List<String> list, String separtor) {
		if(list != null) {
			StringBuffer sb = new StringBuffer();
			for(String s : list) {
				sb.append(s).append(separtor);
			}
			return StringUtils.removeEnd(sb.toString(), separtor);
		}
		
		return null;
	}
}
