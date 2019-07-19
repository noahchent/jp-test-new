package com.vyin.baidu.service;

import java.util.List;

import com.vyin.baidu.bean.JpRequestVo;
import com.vyin.baidu.bean.VyinReqInfoVo;
import com.vyin.baidu.rtb.vo.VyinApiRequest;

/**
* @ author liu qihang:
* @version 创建时间 2018年6月14日下午2:44:19
* 
*/
public interface BaiduMutualService {
	
	/**		保存server请求百度数据内容	***/
	public int insertBaiduRequestContent(String requestId, String reqContent, Integer parseStatus);
	
	/**		保存server请求百度数据proto	***/
	public int insertBaiduRequestProto(String requestId, String reqProto);
	
	/**		保存百度返回server数据proto	***/
	public int insertBaiduResponseProto(String requestId, String respProto, Integer parseStatus);
	
	/**		保存百度返回server数据内容	***/
	public int insertBaiduResponseContent(String requestId, String respContent);
	
	public List<VyinReqInfoVo> getVyinReqData(String slotMsspId, Integer termId);
	
	public int insertVyinReqJpData(JpRequestVo jpRequestVo);
	
	/**
	 * 批量更新jp_api_request
	 * @param vaReqList
	 * @return
	 */
	public int insertBatchVyinReqJpData(List<VyinApiRequest> vaReqList);

}
