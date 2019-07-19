package com.vyin.baidu.service;

import java.util.List;

import com.vyin.baidu.bean.Terminals;

/**
* @ author liu qihang:
* @version 创建时间 2018年6月24日上午12:15:22
* 
*/
public interface TerminalsService {
	
	public List<Terminals> getAllTermId();
	
	public List<Terminals> getTermIdsAll();
	
	public List<Terminals> getTermInfoByTermId(Integer termId);
	
	public List<Terminals> getH2AllTermId();
	
	public List<Terminals> getH2TermInfoByTermId(Integer termId);
	
	public boolean  setInsertH2TermId(List<Terminals> terminals);
	
	public boolean  setUpdateH2TermId(List<Terminals> terminals);

}
