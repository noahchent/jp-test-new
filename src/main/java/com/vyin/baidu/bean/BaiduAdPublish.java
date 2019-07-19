package com.vyin.baidu.bean;

import java.util.Date;

import lombok.Data;

/**
* @author liu qihang
* @version 2018年8月13日 下午6:06:53
*/
@Data
public class BaiduAdPublish {

	private Integer id;
	private Integer termId;
	private Integer adId;
	private Date startDate;
	private Date endDate;
	private Date startTime;
	private Date endTime;
	private Integer broadcastTime;
	private Integer publishStatus;
	private Date createTime;

}
