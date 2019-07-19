package com.vyin.baidu.bean;

import java.util.Date;

import lombok.Data;

/**
 * @ author liu qihang:
 * @version 创建时间 2018年6月15日下午4:31:39
 */
@Data
public class JpRequestVo {

	private Integer id;
	private String apiVersion;
	private String requestId;
	private String appId;
	private Integer termId;
	private String adSlotId;
	private Integer mutualStatus;
	private Date createTime;
}
