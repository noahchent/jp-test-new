package com.vyin.baidu.protocol;

/**
 * @ author liu qihang:
 * 
 * @version 创建时间 2018年5月4日下午5:35:03
 * 
 */
public class JuPingMutualProtocol {

	/*********************** 聚屏请求媒体交互类型 *************************/
	public static final Byte orderAsk = 1; // 订单询量
	public static final Byte orderReserve = 2; // 订单预定
	public static final Byte metarialSync = 3; // 物料同步
	public static final Byte orderUpdate = 4; // 订单更新
	public static final Byte putNotice = 5; // 投放通知
	public static final Byte orderDelete = 6; // 订单删除

	/*********************** 媒体请求聚屏交互类型 *************************/
	public static final Byte reportPush = 1; // 报表推送
	public static final Byte monitorPush = 2; // 监播推送

	/*********************** 交互返回状态定义 *************************/
	public static final Byte success = 0; // 成功
	public static final Byte illegalParam = 10; // 非法参数类
	public static final Byte illegalUser = 11; // 非法用户
	public static final Integer retain1 = 101; // 保留1
	public static final Byte businessException = 30; // 业务异常类
	public static final Byte repetitionReq = 31; // 重复请求(丢弃，无需重发)
	public static final Byte reqPast = 32; // 请求过期(丢弃，无需重发)
	public static final Integer retain2 = 303; // 保留2
	public static final Byte SystemException = 50; // 系统异常
	public static final Byte SystemBusy = 51; // 系统繁忙
	public static final Integer retain3 = 505; // 保留3
	public static final Byte otherFail = 99; // 其他失败类型

	// 唯一用户标识，必需使用明文，必需按要求填写，具体填写指导详见接口说明文档
	public static final int MAC = 1;
	public static final int MEDIA_ID = 2;

	// 操作系统类型
	public static final int UNKNOWN = 0; // 未知类型
	public static final int ANDROID = 1; // Android
	public static final int IOS = 2; // iOS
	public static final int WINDOWS = 3; // windows

	// 网络连接类型 ConnectionType
	public static final int UNKNOWN_NETWORK = 0;
	public static final int WIFI = 1;
	public static final int MOBILE_2G = 2;
	public static final int MOBILE_3G = 3;
	public static final int MOBILE_4G = 4;
	public static final int ETHERNET = 5;// 以太网接入
	public static final int NEW_TYPE = 6; // 未知新类型

	// 运营商代号，每个运营商唯一(PLMN码每个运营商可能有多个) OperatorType
	public static final int ISP_UNKNOWN = 0; // 未知运营商
	public static final int ISP_CHINA_MOBILE = 1; // 中国移动
	public static final int ISP_CHINA_UNICOM = 2; // 中国联通
	public static final int ISP_CHINA_TELECOM = 3; // 中国电信
	public static final int ISP_FOREIGN = 4; // 其它运营商

	// 广告物料类型
	public static final int VIDEO = 1; // (视频)
	public static final int IMAGE = 2; // 图片
	
	//广告位默认最大发布量
	public static final int DEFAULT_PUBLISH_AD_MAX = 150;//秒s
	public static final int DEFAULT_PLAY_DURATION = 15;
	
	//聚屏返回状态码
	public static final long NO_ERROR = 0;	//响应正常
	public static final long NO_AD = 200000; //百度聚屏内部错误,导致无广告返回
	public static final long AD_NO_DATA = 201000;	//指请求处理正常,但无广告主投放,因此无广告返回;返回 201000 可以认为接口测 试通过

}
