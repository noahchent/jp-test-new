package com.vyin.baidu.rtb.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @ author liu qihang:
 * 
 * @version 创建时间 2018年5月9日下午2:18:58
 * 
 */
public class TsApiResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 1.0 基础参数
	public String request_id; // 对应请求的接入方自定义请求ID
	public long error_code; // 请求响应出错时的错误码，用于问题排查
	public String adslot_id; // 对应请求时填写的广告位ID
	// 广告清单
	public List<Ad> ads; // 应答广告清单，一次请求可以返回多个广告，需要逐个解析
	public int expiration_time; // 广告清单过期时间戳，单位秒
	public String search_key; // 当次请求百度生成的唯一表示ID
	// 广告logo标识
	public String jp_adtext; // 新广告法出台，要求明确使用"广告"，该字段为"广告"小图标地址，媒体需要在渲染的时候添加
	public String jp_adlogo; // 新广告法出台，该字段为与上述字段配合使用的"熊掌"图标地址，媒体需要在渲染的时候添加
	
	
	public String respContent;//vyin响应报文
	
	@Override
	public String toString() {
		return "TsApiResponse [request_id=" + request_id + ", error_code=" + error_code + ", adslot_id=" + adslot_id
				+ ", ads=" + ads + ", expiration_time=" + expiration_time + ", search_key=" + search_key
				+ ", jp_adtext=" + jp_adtext + ", jp_adlogo=" + jp_adlogo + "]";
	}
	
	

}
