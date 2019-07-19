package com.vyin.baidu.utils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


public class StringUtils {

	/**
	 * 定义分割常量 #用于list中每个元素间的分割
	 */
	private static final String SEP1 = ",";

	public static boolean isEmpty(String str) {
		if (str != null) {
			int len = str.length();
			for (int x = 0; x < len; ++x) {
				if (str.charAt(x) > ' ') {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 获取随机字符串
	 * 
	 * @param length
	 *            随机串的长度（最长62位）
	 * @return
	 */
	public static String getRandomStr(int length) {
		if (length > 62 || length < 0) {
			return null;
		}
		String[] beforeShuffle = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
				"o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I",
				"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3",
				"4", "5", "6", "7", "8", "9" };
		List<String> list = Arrays.asList(beforeShuffle);
		Collections.shuffle(list);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
		}
		String afterShuffle = sb.toString();
		String result = afterShuffle.substring(0, length);
		return result;

	}

	/**
	 * 获取全球唯一字符串
	 * 
	 * @return String
	 * @author zhangLei
	 */
	public static String getRandomUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	// 参数列表有空值则返回ture
	public static boolean verifyIsNull(Object... values) {
		for (int i = 0; i < values.length; i++) {
			if (values[i] == null || "".equals(values[i]))
				return true;
		}
		return false;
	}

	/**
	 * List转换String
	 * 
	 * @param list
	 *            :需要转换的List
	 * @return String转换后的字符串
	 */
	public static String listToString(List<String> list) {
		StringBuffer sb = new StringBuffer();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) == null || list.get(i) == "") {
					continue;
				}
				sb.append(list.get(i));
				sb.append(SEP1);
			}
		}
		String str = sb.toString();
		if(str == null || "".equals(str.trim())){
			return null;
		}
		return str.substring(0, str.length()-1);
	}
	
	/**
	 * 验证对象是否有空值属性
	 * @param obj
	 * @return
	 * @throws IllegalAccessException
	 */
	public static boolean checkObjectContainNullElement(Object obj) throws IllegalAccessException {
		boolean flag = false;
		for (Field f : obj.getClass().getDeclaredFields()) {
			f.setAccessible(true);
			if (f.get(obj) == null) {
				flag = true;
				return flag;
			}
		}
		return flag;
	}
	
}
