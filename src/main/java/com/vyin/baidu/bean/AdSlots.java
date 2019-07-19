package com.vyin.baidu.bean;

import java.util.Date;

import lombok.Data;

/**
* @ author liu qihang:
* @version 创建时间 2018年6月14日下午8:10:41
*/
@Data
public class AdSlots {
	
	private Integer id;
	private String slotMsspId;
	private Integer slotType;
	private Integer slotResolutionWidth;
	private Integer slotResolutionHeight;
	private Integer uv;
	private Integer pv;
	private Integer materialType;
	private Integer voiceSupport;
	private Integer adFlagStatus;
	private Integer format;
	private Integer materialMaxSize;
	private Integer showType;
	private String showTypeDetails;
	private Integer dailyShowTimes;
	private Integer showDuration;
	private Integer price;
	private Date createTime;
	private Date updateTime;
	
	private Integer termId;
	
	
	
	

}
