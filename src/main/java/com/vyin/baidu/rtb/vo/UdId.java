package com.vyin.baidu.rtb.vo;

import java.io.Serializable;

import com.vyin.baidu.utils.ByteStringConvertor;

/**
* @ author liu qihang:
* @version 创建时间 2018年5月9日下午1:55:42
* 
*/
//唯一设备标识
public class UdId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int UdIdType;
	public String id;//(mac 或 media_id)

	public Juping.UdId getUdId(){
		Juping.UdId.Builder udIdBuilder = Juping.UdId.newBuilder();
		udIdBuilder.setIdType(Juping.UdIdType.valueOf(UdIdType));
//		udIdBuilder.setIdType(Juping.UdIdType.forNumber(UdIdType));
		udIdBuilder.setId(ByteStringConvertor.getByteString(id));
		return udIdBuilder.build();
	}

	public UdId() {
		// TODO Auto-generated constructor stub
	}
	
	public UdId(int udIdType, String id) {
		super();
		UdIdType = udIdType;
		this.id = id;
	}
	
	
	
}
