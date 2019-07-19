package com.vyin.baidu.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import lombok.extern.log4j.Log4j;

/**
 * @ author liu qihang:
 * 
 * @version 创建时间 2018年6月15日下午8:28:54
 * 
 */
@Log4j
public class ImageUtil {

	private String image_local_path;

	private String appendDir;
	
	public ImageUtil() {}

	public ImageUtil(String image_local_path, String appendDir) {
		super();
		this.image_local_path = image_local_path;
		this.appendDir = appendDir;
	}

	public String getImageLocalPath(String image_url) throws Exception {
		URL url = new URL(image_url);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(6 * 1000); // 6s
		// 通过输入流获取图片数据
		InputStream inStream = conn.getInputStream();
		// 得到图片的二进制数据
		byte[] data = readInputStream(inStream);

		String fileName = image_url.substring(image_url.lastIndexOf("/") + 1);//截取url得到源文件名称
		String timepath = generateFilePath(); // 分时目录，多级，年、月、日
		String subDirectoryPath = appendDir + timepath; // 子目录路径
		String image_path = subDirectoryPath + fileName; // 相对路径
		String fileAbsolutePath = image_local_path + subDirectoryPath; // 绝对路径
		
		File tempFile = new File(new File(fileAbsolutePath), fileName);// 判断当前分级时间文件夹是否存在
		if (!tempFile.getParentFile().exists()) {
			tempFile.getParentFile().mkdirs();
		}
		if(tempFile.exists()){
			log.debug("文件已存在" + image_path);
			return image_path;
		}
		
		FileOutputStream outStream = new FileOutputStream(tempFile);
		outStream.write(data);
		outStream.close();
		return image_path;
	}

	public byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}

	public String generateFilePath() {
		String year = DateUtil.getDateFormatter(new Date(), "yyyy");
		String month = DateUtil.getDateFormatter(new Date(), "MM");
		String day = DateUtil.getDateFormatter(new Date(), "dd");
		String timePath = year + "/" + month + "/" + day + "/"; // 分时目录，多级，年、月、日
		return timePath;
	}
	
	public static void main(String[] args) throws Exception {
		String image_url = "http://imgsrc.baidu.com/forum/w%3D580/sign=c95042cf1a3853438ccf8729a312b01f/d065be0e7bec54e7caababbdb3389b504ec26aad.jpg";
		ImageUtil imageUtil = new ImageUtil();
		String str = imageUtil.getImageLocalPath(image_url);
		System.out.println(str);
	}

}
