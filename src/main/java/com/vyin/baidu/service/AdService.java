package com.vyin.baidu.service;

import java.util.List;

import com.vyin.baidu.bean.VyinReqInfoVo;
import com.vyin.baidu.rtb.vo.TsApiResponse;

/**
* @ author liu qihang:
* @version 创建时间 2018年6月14日下午4:46:14
* 
*/
public interface AdService {
	
//	public boolean saveBaiduAdInfo(TsApiResponse tar, Integer termId);
	
	public boolean saveBaiduAdListInfo(List<TsApiResponse> tarlist, Integer termId);
	
	/**
	 * 获取终端下广告位剩余量，剩余量循环请求百度
	 * @param termId
	 * @param typeCode
	 * @return
	 */
	public int getSurplusMaxTimesBySlotTypeCode(VyinReqInfoVo vyinReqInfoVo, Integer publishTotalTime);
	
	/**
	 * 获取广告位当前正在播放时长
	 * @param termId
	 * @param slotType
	 * @return
	 */
	public int getPublishTotalTime (Integer termId, Integer slotType);
	
	

}
