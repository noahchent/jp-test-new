package com.vyin.baidu.bean;

import java.util.Date;

import lombok.Data;

/**
* @ author liu qihang:
* @version 创建时间 2018年6月14日下午4:53:18
* 
*/
@Data
public class JpAdMaterials {
	
	private Integer id;
	private Integer adId;
	private Integer adMaterialsId;
	private Integer materialTypeId;
	private String videoUrl;
	private Integer videoDuration;
	private Integer materialWidth;
	private Integer materialHeight;
	private Integer materialSize;
	private String materialMd5;
	private Date createTime;

}
