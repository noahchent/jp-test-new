package com.vyin.baidu.bean;

import lombok.Data;

/**
* @ author liu qihang:
* @version 创建时间 2018年6月14日下午1:31:30
*/
@Data
public class VyinReqInfoVo{
	
	private String slotId; //mssp生成
	private Integer slotType;
	private Integer showDuration;
	private Terminals terminals;
	private Integer maxTime;
}
