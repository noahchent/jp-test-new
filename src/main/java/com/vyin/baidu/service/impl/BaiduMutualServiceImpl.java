package com.vyin.baidu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vyin.baidu.bean.JpRequestVo;
import com.vyin.baidu.bean.VyinReqInfoVo;
import com.vyin.baidu.mapper.BaiduMutualMapper;
import com.vyin.baidu.rtb.vo.VyinApiRequest;
import com.vyin.baidu.service.BaiduMutualService;

/**
 * @ author liu qihang:
 * 
 * @version 创建时间 2018年6月14日下午2:46:03
 * 
 */
@Service
public class BaiduMutualServiceImpl implements BaiduMutualService {

	@Resource
	private BaiduMutualMapper baiduMutualMapper;

	@Override
	public int insertBaiduRequestContent(String requestId, String reqContent, Integer parseStatus) {
		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("requestId", requestId);
		paramMap.put("reqContent", reqContent);
		paramMap.put("parseStatus", parseStatus);
		return baiduMutualMapper.insertBaiduRequestContent(paramMap);
	}

	@Override
	public int insertBaiduRequestProto(String requestId, String reqProto) {
		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("requestId", requestId);
		paramMap.put("reqProto", reqProto);
		return baiduMutualMapper.insertBaiduRequestProto(paramMap);
	}

	@Override
	public int insertBaiduResponseProto(String requestId, String respProto, Integer parseStatus) {
		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("requestId", requestId);
		paramMap.put("respProto", respProto);
		paramMap.put("parseStatus", parseStatus);
		return baiduMutualMapper.insertBaiduResponseProto(paramMap);
	}

	@Override
	public int insertBaiduResponseContent(String requestId, String respContent) {
		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("requestId", requestId);
		paramMap.put("respContent", respContent);
		return baiduMutualMapper.insertBaiduResponseContent(paramMap);
	}

	@Override
	public List<VyinReqInfoVo> getVyinReqData(String slotMsspId, Integer termId) {
		return baiduMutualMapper.getVyinReqData(slotMsspId, termId);
	}

	@Override
	public int insertVyinReqJpData(JpRequestVo jpRequestVo) {
		return baiduMutualMapper.insertVyinReqJpData(jpRequestVo);
	}
	
	@Override
	public int insertBatchVyinReqJpData(List<VyinApiRequest> vaReqList) {
		int result = 0;
		
		if (vaReqList == null || vaReqList.size() == 0) {
			return -1;
		}
		
		List<JpRequestVo> jpReqList = new ArrayList<JpRequestVo>();
		JpRequestVo jpRequestVo;
		for (int i = 0; i < vaReqList.size(); i ++) {
			VyinApiRequest vyinApiRequest = vaReqList.get(i);
			jpRequestVo = new JpRequestVo();
			jpRequestVo.setApiVersion(vyinApiRequest.version.major + "." + vyinApiRequest.version.minor + "." + vyinApiRequest.version.micro);
			jpRequestVo.setRequestId(vyinApiRequest.requestId);
			jpRequestVo.setAppId(vyinApiRequest.appId);
			jpRequestVo.setTermId(vyinApiRequest.termId);
			jpRequestVo.setAdSlotId(vyinApiRequest.slotInfo.adslot_id);
			jpRequestVo.setMutualStatus(0);
			jpReqList.add(jpRequestVo);
		}
		
		baiduMutualMapper.insertBatchVyinReqJpData(jpReqList);
		
		return result;
	}

}
