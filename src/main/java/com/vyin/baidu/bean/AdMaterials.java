package com.vyin.baidu.bean;

import java.util.Date;

import lombok.Data;

/**
* @ author liu qihang:
* @version 创建时间 2018年6月14日下午4:55:57
*/

@Data
public class AdMaterials {

	private Integer id;
    private Integer adId;
    private String imagePath;
    private String imageUrl;
    private Integer reviewStatus;
    private Date createTime;
	
}
