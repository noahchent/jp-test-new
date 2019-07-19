package com.vyin.baidu.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.vyin.baidu.bean.JpRequestVo;
import com.vyin.baidu.bean.JpRespInfoVo;
import com.vyin.baidu.bean.VyinReqInfoVo;

/**
* @ author liu qihang:
* @version 创建时间 2018年6月12日下午4:46:12
* 
*/
public interface BaiduMutualMapper {
	
	/**		保存server请求百度数据内容	***/
	public int insertBaiduRequestContent(Map<Object, Object> paramMap);
	
	/**		保存server请求百度数据proto	***/
	public int insertBaiduRequestProto(Map<Object, Object> paramMap);
	
	/**		保存百度返回server数据proto	***/
	public int insertBaiduResponseProto(Map<Object, Object> paramMap);
	
	/**		保存百度返回server数据内容	***/
	public int insertBaiduResponseContent(Map<Object, Object> paramMap);
	
	/**		获取请求聚屏数据list			***/
	public List<VyinReqInfoVo> getVyinReqData(@Param("slotMsspId")String slotMsspId, @Param("termId")Integer termId);
	
	/*** 	保存返回实体			**/
	public int insertJpResponseData(JpRespInfoVo jpRespInfoVo);
	
	public int insertVyinReqJpData(JpRequestVo jpRequestVo);
	
	public List<JpRequestVo> getJpRequestVoByReqId(@Param("requestId")String requestId);
	
	/*修改jp_api_request_请求状态为2，表示聚屏/媒体交互成功*/
	public int updateJpApiRequestMutualStatus(@Param("id")Integer jpAdRequestId, @Param("mutualStatus")Integer mutualStatus);
	
	public int insertBatchVyinReqJpData(List<JpRequestVo> jpReqList);

}
