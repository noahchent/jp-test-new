package com.vyin.baidu.bean;

import java.util.Date;

import lombok.Data;

/**
* @ author liu qihang:
* @version 创建时间 2018年6月16日下午2:41:42
*/

@Data
public class AdPublish {
	
	private Integer id;
	private Integer termId;
	private Integer adId;
	private Date startDate;
	private Date endDate;
	private Date startTime;
	private Date endTime;
	private Integer broadcastTime;
	private Integer publishStatus;
	private String orderId;
	private Date createTime;


}
