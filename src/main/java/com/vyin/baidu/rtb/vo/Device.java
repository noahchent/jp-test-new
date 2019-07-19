package com.vyin.baidu.rtb.vo;

import java.io.Serializable;

import com.vyin.baidu.utils.ByteStringConvertor;

/**
* @ author liu qihang:
* @version 创建时间 2018年5月9日下午1:48:02
* 
*/
//设备参数
public class Device implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UdId udId;			//唯一设备标识
	public int osType = 3;		//操作系统类型
	public Version os_version;		//操作系统版本
	public String vendor = "";		//设备厂商名称，中文需要UTF-8编码
	public String model = "";		//设备型号，中文需要UTF-8编码
	public Size screen_size;	//设备屏幕宽高
	
	public Juping.Device getDevice(){
		Juping.Device.Builder deviceBuilder = Juping.Device.newBuilder();
		deviceBuilder.setUdid(udId.getUdId());
		deviceBuilder.setOsType(Juping.OsType.valueOf(osType));
		deviceBuilder.setOsVersion(os_version.getVersion());
		deviceBuilder.setVendor(ByteStringConvertor.getByteString(vendor));
		deviceBuilder.setModel(ByteStringConvertor.getByteString(model));
		deviceBuilder.setScreenSize(screen_size.getSize());
		return deviceBuilder.build();
	}
	
	public Device() {
		// TODO Auto-generated constructor stub
	}

	public Device(UdId udId, int osType, Version os_version, String vendor, String model, Size screen_size) {
		super();
		this.udId = udId;
		this.osType = osType;
		this.os_version = os_version;
		this.vendor = vendor == null ? "" : vendor;
		this.model = model == null ? "" : model;
		this.screen_size = screen_size;
	}
	
	
	

}
