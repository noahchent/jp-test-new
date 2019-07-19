package com.vyin.baidu.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	// 全局数组
    private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    public MD5() {
    }
    
    // 返回形式为数字跟字符串
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        // System.out.println("iRet="+iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    // 返回形式只为数字
    @SuppressWarnings("unused")
	private static String byteToNum(byte bByte) {
        int iRet = bByte;
        System.out.println("iRet1=" + iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        return String.valueOf(iRet);
    }

    // 转换字节数组为16进制字串
    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }
    public static String GetMD5Code(String strObj) {
        String resultString = null;
        try {
            resultString = new String(strObj);
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest() 该函数返回值为存放哈希值结果的byte数组
            resultString = byteToString(md.digest(strObj.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return resultString;
    }
	//用户密码加密方法
	 public final static String encryptPwd(String password) {  
	        char hexDigits[] = { '0', '1', '2', '3', '4',  
	                             '5', '6', '7', '8', '9',  
	                             'a', 'b', 'c', 'd', 'e', 'f' };  
	        try {  
	            byte[] btInput = password.getBytes();
	     //先进行Base64加密      
	            String resultString = Base64.encodeToString(btInput,Base64.DEFAULT);
	     //获得MD5摘要算法的 MessageDigest 对象  
	            MessageDigest mdInst = MessageDigest.getInstance("MD5");  
	     //使用指定的字节更新摘要  
	            mdInst.update(resultString.getBytes());  
	     //获得密文  
	            byte[] md = mdInst.digest();  
	     //把密文转换成十六进制的字符串形式  
	            int j = md.length;  
	            char str[] = new char[j * 2];  
	            int k = 0;  
	            for (int i = 0; i < j; i++) {  
	                byte byte0 = md[i];  
	                str[k++] = hexDigits[byte0 >>> 4 & 0xf];  
	                str[k++] = hexDigits[byte0 & 0xf];  
	            }  
	            return new String(str);  
	        }  
	        catch (Exception e) {  
	            e.printStackTrace();  
	            return null;  
	        }  
	    }  
	public static void main(String[] args) {
        System.out.println(MD5.encryptPwd("123456"));
        //EC3CB4F5F4D4FDF97DD2B4CE97243A97372471DA
    }
}
