package com.vyin.baidu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vyin.baidu.bean.Terminals;
import com.vyin.baidu.mapper.TerminalsMapper;
import com.vyin.baidu.mapper.h2.TerminalsH2Mapper;
import com.vyin.baidu.rtb.vo.TsApiResponse;
import com.vyin.baidu.service.TerminalsService;
import com.vyin.baidu.utils.datasource.CustomerContextHolder;

/**
* @ author liu qihang:
* @version 创建时间 2018年6月24日上午12:16:15
* 
*/
@Service
public class TerminalsServiceImpl implements TerminalsService{
	
	@Resource
	private TerminalsMapper terminalsMapper;
	
	@Resource
	private TerminalsH2Mapper terminalsMapH2Mapper;

	@Override
	public List<Terminals> getAllTermId() {
		return terminalsMapper.getAllTermId();
	}
	
	@Override
	public List<Terminals> getTermIdsAll() {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATASOURCE_DATASOURCE); 
		return terminalsMapper.getTermIdsAll();
	}

	@Override
	public List<Terminals> getTermInfoByTermId(Integer termId) {
		return terminalsMapper.getTermInfoByTermId(termId);
	}

	@Override
	public List<Terminals> getH2AllTermId() {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATASOURCE_H2DATASOURCE); 
		return terminalsMapH2Mapper.getH2AllTermId();
	}
	
	@Override
	public List<Terminals> getH2TermInfoByTermId(Integer termId) {
		CustomerContextHolder.setCustomerType(CustomerContextHolder.DATASOURCE_H2DATASOURCE); 
		return terminalsMapH2Mapper.getH2TermInfoByTermId(termId);
	}
	
	@Override
	public boolean setInsertH2TermId(List<Terminals> terminals) {
		boolean boo  = true;
		try {
			CustomerContextHolder.setCustomerType(CustomerContextHolder.DATASOURCE_H2DATASOURCE); 
			boo = terminalsMapH2Mapper.setInsertH2TermId(terminals);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return boo;
	}
	
	@Override
	public boolean setUpdateH2TermId(List<Terminals> terminals) {
		boolean boo  = true;
		try {
			
			for (Terminals terminals2 : terminals) {
				CustomerContextHolder.setCustomerType(CustomerContextHolder.DATASOURCE_H2DATASOURCE);
				boo = terminalsMapH2Mapper.setUpdateH2TermId(terminals2);
				if(!boo) {
					break;
				}
			}
			 
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return boo;
	}



	

	
}
