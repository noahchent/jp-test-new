package com.vyin.baidu.utils;

import java.util.ArrayList;
import java.util.List;

import com.google.protobuf.ByteString;

/**
* @ author liu qihang:
* @version 创建时间 2018年5月9日下午2:32:31
* 
*/
public class ByteStringConvertor {
	
	public static ByteString getByteString(String value){
		ByteString bs = ByteString.copyFrom(value.getBytes());
		return bs;
	}
	
	public static String getString(ByteString byteString){
		byte[] bytes = byteString.toByteArray();
		return new String(bytes);
	}
	
	public static List<String> convertStringTypeList(List<ByteString> bsList){
		List<String> strList = new ArrayList<String>();
		for (int i = 0, size = bsList.size(); i < size; i++) {
			ByteString bs = bsList.get(i);
			strList.add(getString(bs));
		}
		return strList;
	}
	
	public static void main(String[] args) {
		String str = "lqh330sdkjfh948";
		ByteString bs = getByteString(str);
		System.out.println(bs);
		System.out.println(getString(bs));
	}

}
