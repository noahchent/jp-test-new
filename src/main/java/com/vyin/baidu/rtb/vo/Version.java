package com.vyin.baidu.rtb.vo;

import java.io.Serializable;

/**
* @ author liu qihang:
* @version 创建时间 2018年5月9日下午1:38:52
* 
*/
//接口版本(初始版本6.0.0)
public class Version implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int major;	//6
	public int minor;	//0
	public int micro;	//0
	
	public Juping.Version getVersion(){
		Juping.Version.Builder versionBuilder = Juping.Version.newBuilder();
		versionBuilder.setMajor(major).setMinor(minor).setMicro(micro);
		return versionBuilder.build();
	}

	public Version() {
	}
	
	public Version(int major, int minor, int micro) {
		super();
		this.major = major;
		this.minor = minor;
		this.micro = micro;
	}
	

	
}
