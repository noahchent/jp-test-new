package com.vyin.baidu.bean;

import java.util.Date;

import lombok.Data;

/**
* @ author liu qihang:
* @version 创建时间 2018年6月14日下午4:49:03
* 
*/
@Data
public class Advertisement {
	
    private Integer id;
    private String title;
    private Integer adType;
    private String remarks;
    private Integer companyId;
    private Integer adSource;
    private Integer adStatus;
    private Date createTime;


}
