package com.vyin.baidu.rtb.vo;

import java.io.Serializable;

/**
 * @ author liu qihang:
 * 
 * @version 创建时间 2018年5月9日下午2:16:55
 * 
 */
//// 广告效果跟踪信息
public class Tracking implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 广告展示过程事件类型
	public enum TrackingEvent {
		// 视频类广告展示过程事件
		VIDEO_AD_START, // 视频开始播放
		VIDEO_AD_FULL_SCREEN, // 视频全屏
		VIDEO_AD_END, // 视频正常播放结束
		VIDEO_AD_START_CARD_CLICK // 点击预览图播放视频
	};

	public TrackingEvent tracking_event; // 被跟踪的广告展示过程事件
	public String tracking_url; // 事件监控URL
}
