package com.vyin.baidu.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.security.SecureRandom;

import com.vyin.baidu.rtb.vo.Juping;

/**
 * @ author liu qihang:
 * 
 * @version 创建时间 2018年5月4日下午5:32:55
 */
public class JuPingUtil {

	public static String generateJuPingToken(String searchId, String secretKey) {
		if (StringUtils.verifyIsNull(searchId, secretKey))
			return null;
		String token = MD5.encryptPwd(searchId + secretKey);
		return token;
	}

	/**
	 * 生成广告请求request_id (32位)
	 * 
	 * @return
	 */
	public static String generateRequestId() {
		return StringUtils.getRandomStr(32);
	}

	public static int[] getApiVersion(String api_version) {
		int[] ints = null;
		if (api_version == null || api_version.trim().length() == 0) {
			ints = new int[] { 6, 0, 0 };
		} else {
			String[] strs = api_version.split("[.]");// java 按.分割使用"[.]" 来分割
			ints = new int[strs.length];
			for (int i = 0; i < strs.length; i++) {
				ints[i] = Integer.valueOf(strs[i]);
			}
		}
		return ints;
	}

	public static String base64Encode(byte[] bytes) {
		return Base64.encodeToString(bytes, 0);
	}

	public static byte[] base64Decode(String byteStr) {
		return Base64.decode(byteStr, 0);
	}

	public static InputStream sendHTTPSReqToBaidu(Juping.TsApiRequest tsApiRequest, String urlStr) throws Exception {
		InputStream inputStream = null;
		SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
		TrustManager[] trustManagers = { new MyX509TrustManager() };
		// 初始化
		sslContext.init(null, trustManagers, new SecureRandom());
		// 获取 sslSocketFactory 对象
		SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
		// 设置当前实例使用

		// 传递的URL
		URL url = new URL(urlStr);
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		// 实现自定义SSLSocketFactory，见下一步
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setSSLSocketFactory(sslSocketFactory);
		connection.setRequestProperty("Content-Type", "application/x-protobuf;charset=UTF-8;");
		connection.setRequestProperty("Accept", "application/x-protobuf");
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Connect-Length", Integer.toString(tsApiRequest.toByteString().size()));
		connection.setFixedLengthStreamingMode(tsApiRequest.toByteString().size());
		connection.connect();

		OutputStream outputStream = connection.getOutputStream();
		tsApiRequest.writeTo(outputStream);
		outputStream.flush();
		outputStream.close();

		int responseCode = connection.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			inputStream = connection.getInputStream();// 获取servlet返回值，接收
		} else {
			System.out.println("请求失败");
		}
		return inputStream;
	}
	
	/**
	 * 聚屏发送http请求url接口方法
	 * 
	 * @param content
	 * @param urlStr
	 * @throws Exception
	 */
	public static InputStream sendHTTPReqToBaidu(Juping.TsApiRequest tsApiRequest, String urlStr) throws Exception {
		InputStream inputStream = null;
		URL url = new URL(urlStr);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestProperty("Content-Type", "application/x-protobuf;charset=UTF-8;");
		connection.setRequestProperty("Accept", "application/x-protobuf");
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Connect-Length", Integer.toString(tsApiRequest.toByteString().size()));
		connection.setFixedLengthStreamingMode(tsApiRequest.toByteString().size());
		connection.connect();

		OutputStream outputStream = connection.getOutputStream();
		tsApiRequest.writeTo(outputStream);
		outputStream.flush();
		outputStream.close();

		int responseCode = connection.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			inputStream = connection.getInputStream();// 获取servlet返回值，接收
		} else {
			System.out.println("请求失败");
		}
		return inputStream;
	}

}
