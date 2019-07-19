package com.vyin.baidu.bean;

import java.util.Date;

import lombok.Data;

/**
* @ author liu qihang:
* @version 创建时间 2018年6月14日下午8:34:44
*/
@Data
public class JpRespInfoVo {
	
	private String id;
	private String requestId;
	private Long errorCode;
	private String adSlotId;
	private String adIds;
	private Integer expirationTime;
	private String searchKey;
	private String jpAdtext;
	private String jpAdlogo;
	private Date createTime;

}
