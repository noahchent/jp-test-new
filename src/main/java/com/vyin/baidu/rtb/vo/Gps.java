package com.vyin.baidu.rtb.vo;

import java.io.Serializable;

/**
 * @ author liu qihang:
 * 
 * @version 创建时间 2018年5月9日下午2:10:04
 * 
 */
public class Gps implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public CoordinateType coordinate_type; // 必填！坐标类型
	public double longitude = 2; // 必填！经度
	public double latitude = 3; // 必填！纬度

	// GPS坐标类型
	public enum CoordinateType {
		WGS84, // 全球卫星定位系统坐标系
		GCJ02, // 国家测绘局坐标系
		BD09 // 百度坐标系
	};

}
