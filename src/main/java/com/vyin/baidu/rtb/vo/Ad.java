package com.vyin.baidu.rtb.vo;

import java.io.Serializable;
import java.util.List;

import com.vyin.baidu.utils.ByteStringConvertor;

/**
 * @ author liu qihang:
 * 
 * @version 创建时间 2018年5月9日下午2:15:38
 * 
 */
public class Ad implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String title; // 广告标题，utf-8 编码		预留
	public String brand_name; // 广告品牌名，utf-8 编码	预留
	public String description; // 广告描述，utf-8编码	预留
	public String icon_url; // 广告主品牌logo		预留
	
	public List<MaterialMeta> material_metas; // 物料的元数据
	public List<String> win_notice_url; // 曝光日志URL列表，在曝光后必须在客户端逐个汇报完
	public List<String> third_monitor_url; // (第三方监控)广告主监控请求，在曝光后必须在客户端端逐个汇报完
	public List<Tracking> ad_tracking; //预留  广告监控信息(预留接口，媒体暂时不需要关注)
	public String ad_key; // 广告唯一标识id
	
	public Juping.Ad getAd(){
		Juping.Ad.Builder adBuilder = Juping.Ad.newBuilder();
		adBuilder.setTitle(ByteStringConvertor.getByteString(title));
		adBuilder.setBrandName(ByteStringConvertor.getByteString(brand_name));
		adBuilder.setDescription(ByteStringConvertor.getByteString(description));
		adBuilder.setIconUrl(ByteStringConvertor.getByteString(icon_url));
		
		for (int i = 0, size = material_metas.size(); i < size; i++) {
			adBuilder.addMaterialMetas(material_metas.get(i).getMaterialMeta());
		}
		for (int i = 0, size = win_notice_url.size(); i < size; i++) {
			adBuilder.addWinNoticeUrl(ByteStringConvertor.getByteString(win_notice_url.get(i)));
		}
		for (int i = 0, size = third_monitor_url.size(); i < size; i++) {
			adBuilder.addThirdMonitorUrl(ByteStringConvertor.getByteString(third_monitor_url.get(i)));
		}
		
		adBuilder.setAdKey(ByteStringConvertor.getByteString(ad_key));
		return adBuilder.build();
	}
}
