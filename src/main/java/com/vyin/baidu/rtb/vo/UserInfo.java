package com.vyin.baidu.rtb.vo;

import java.io.Serializable;

/**
* @ author liu qihang:
* @version 创建时间 2018年5月9日下午2:11:22
* 
*/
//媒体的用户特征数据
public class UserInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String media_channel; //选填! 电视频道、公交路线等。需预先和百度确定映射关系
    public String tags; //选填! 当前设备用户画像。需预先和百度确定映射关系
    public String phone_numbers; //选填! 当前设备用户手机号当前设备用户手机号。加密规则预先约定，需支持反解
    public String browse; // 选填! 当前设备历史流量信息。需预先和百度确定映射关系
	
}
