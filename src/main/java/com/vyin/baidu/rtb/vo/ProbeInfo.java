package com.vyin.baidu.rtb.vo;

import java.io.Serializable;

/**
* @ author liu qihang:
* @version 创建时间 2018年5月9日下午2:09:02
* 
*/
//设备探针数据
public class ProbeInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String client_mac; 	//探测设备mac
    public int client_rssi; 	//探测设备热点强度
}
