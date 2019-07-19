package com.vyin.baidu.bean;

import java.io.Serializable;

import lombok.Data;

/**
* @ author liu qihang:
* @version 创建时间 2018年6月25日上午10:56:16
* 
*/
@Data
public class Terminals implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer termId; 
	private String tMacId; 
	private Integer osType; 
	private String osVersion; 
	private String vendor; 
	private String model; 
	private Integer screenWidth; 
	private Integer screenHeight; 
	private String ipv4; 
	private Integer connectionType; 
	private Integer operatorType;

}
