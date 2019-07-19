package com.vyin.baidu.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * @ author liu qihang:
 * 
 * @version 创建时间 2018年5月10日下午2:25:20
 * 
 */
public class HttpUtil {
	
	public static void sendReq(byte[] content, String urlStr) throws Exception {
		String responseInfo = null;
		StringBuffer buffer = new StringBuffer();
		
		URL url = new URL(urlStr);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestProperty("Content-Type", "application/x-protobuf");
		connection.setRequestProperty("Accept", "application/x-protobuf");
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Connect-Length", Integer.toString(content.length));
		connection.setFixedLengthStreamingMode(content.length);
		connection.connect();
		
		OutputStream outputStream = connection.getOutputStream();
		outputStream.write(content);
		outputStream.flush();
		outputStream.close();
		
		int responseCode = connection.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			InputStream inputStream = connection.getInputStream();// 获取servlet返回值，接收
			buffer = new StringBuffer();
			String line = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();
		} else {
			System.out.println("response code=" + responseCode + " error");
		}
		responseInfo = buffer.toString();
		System.out.println(responseInfo);
	}
	
}
