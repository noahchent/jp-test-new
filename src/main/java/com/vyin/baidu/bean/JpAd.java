package com.vyin.baidu.bean;

import java.util.Date;

import lombok.Data;

/**
* @ author liu qihang:
* @version 创建时间 2018年6月14日下午4:50:33
* 
*/
@Data
public class JpAd {
	
	private Integer id;
	private Integer adId;
	private String winNoticeUrl;
	private String thirdMonitorUrl;
	private String jpAdKey;
	private Date createTime;

}
