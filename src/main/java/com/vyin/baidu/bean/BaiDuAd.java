package com.vyin.baidu.bean;

import java.util.Date;

import lombok.Data;

/**
 * @author liu qihang
 * @version 2018年8月13日 下午5:20:43
 */
@Data
public class BaiDuAd {

	private Integer id;
	private String title;
	private Integer adType;
	private Integer adStatus;
	private String winNoticeUrl;
	private String thirdMonitorUrl;
	private String jpAdKey;
	private Date createTime;

}
