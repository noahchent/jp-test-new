package com.vyin.baidu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vyin.baidu.bean.AdSlots;

/**
* @ author liu qihang:
* @version 创建时间 2018年6月14日下午8:32:09
* 
*/
public interface AdSlotsMapper {
	
	public List<AdSlots> getAdSlotInfoBySlotId(@Param("slotMsspId")String slotMsspId, @Param("termId")Integer termId);
	
	/**
	 * 获取该广告位最大发布量
	 * @param typeCode
	 * @return
	 */
	public Integer getMaxTimesBySlotTypeCode(@Param("typeCode")Integer typeCode);
	
	/**
	 * 获取终端设备下广告位已发布的 "有效"广告数量
	 * @param termId
	 * @param typeCode
	 * @return
	 */
	public Integer getTerminalAdSlotPublishTotalTimeByTypeCode(@Param("termId")Integer termId, @Param("typeCode")Integer typeCode);

}
