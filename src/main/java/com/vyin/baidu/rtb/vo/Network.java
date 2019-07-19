package com.vyin.baidu.rtb.vo;

import java.io.Serializable;

import com.vyin.baidu.utils.ByteStringConvertor;

/**
 * @ author liu qihang:
 * 
 * @version 创建时间 2018年5月9日下午2:03:37
 * 
 */
// 网络环境信息
public class Network implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String ipv4;  // 必填！用户设备的公网IPv4地址，服务器对接必填，格式要求：255.255.255.255
	public int connection_type;  // 必填！网络连接类型，用于判断网速
	public int operator_type;  // 必填！移动运营商类型，用于运营商定向广告
	
	public Juping.Network getNetWork(){
		Juping.Network.Builder netWorkBuilder = Juping.Network.newBuilder();
		netWorkBuilder.setIpv4(ByteStringConvertor.getByteString(ipv4));
		netWorkBuilder.setConnectionType(Juping.Network.ConnectionType.valueOf(connection_type));
		netWorkBuilder.setOperatorType(Juping.Network.OperatorType.valueOf(operator_type));
		return netWorkBuilder.build();
	}
	
	public Network() {
		// TODO Auto-generated constructor stub
	}

	public Network(String ipv4, int connection_type, int operator_type) {
		super();
		this.ipv4 = ipv4 == null ? "127.0.0.1" : ipv4;
		this.connection_type = connection_type;
		this.operator_type = operator_type;
	}
	
	

}
