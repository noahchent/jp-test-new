package com.vyin.baidu.rtb.vo;

import java.io.Serializable;

/**
 * @ author liu qihang:
 * 
 * @version 创建时间 2018年5月9日下午2:07:02
 * 
 */
// wifi 热点信息
public class WiFiAp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String ap_mac; // 必填！热点MAC地址，格式要求[0-9A-F]{2}:[0-9A-F]{2}:[0-9A-F]{2}:[0-9A-F]{2}:[0-9A-F]{2}:[0-9A-F]{2}
	public int rssi; // 必填！热点信号强度，通常是负数
	public String ap_name; // 必填！热点名称，可不传递，建议传递当前接入热点的名称，用于判断用户当前所处场所，中文需要UTF-8编码

}
