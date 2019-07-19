package com.vyin.baidu.bean;

import java.util.Date;

import lombok.Data;

/**
* @author liu qihang
* @version 2018年8月13日 下午5:20:58
*/
@Data
public class BaiDuMaterials {
	
	private Integer id;
	private Integer adId;
    private String imagePath;
    private String imageUrl;
    private Integer reviewStatus;
	private Integer materialTypeId;
	private String videoUrl;
	private Integer videoDuration;
	private Integer materialWidth;
	private Integer materialHeight;
	private Integer materialSize;
	private String materialMd5;
	private Date createTime;
	

}
