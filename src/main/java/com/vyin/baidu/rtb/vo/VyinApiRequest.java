package com.vyin.baidu.rtb.vo;

import java.io.Serializable;

/**
* @ author liu qihang:
* @version 创建时间 2018年5月9日下午1:32:02
* 
*/
//	* 标为选填参数
public class VyinApiRequest  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String requestId;	//广告请求ID
	public Version version;		//接口版本(6.0.0)
	public String appId;		//资源方ID
	public SlotInfo slotInfo;	//广告位参数
	public Device device;		//设备参数
	public Network network;		//网络环境信息
	public WiFiAp wiFiAp;		// * 扫描wifi ap 参数	选填！连接的wifi热点参数, OTT强烈建议填写
	public ProbeInfo probeInfo;	// * 探针参数
	public Gps gps;				// * GPS定位信息，用于辅助触发LBS广告
	public UserInfo userInfo;	// * 用户参数
	public WiFiAp scan_wifi_ap;	// * 扫描wifi ap 参数	...
	
	/**
	 * 
	 */
	public int termId;	//终端编号，方便保存报文使用
	
}
