package com.vyin.baidu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vyin.baidu.bean.Terminals;

/**
* @ author liu qihang:
* @version 创建时间 2018年6月12日下午4:11:32
* 
*/
public interface TerminalsMapper {
	
	public List<Terminals> getAllTermId();
	
	public List<Terminals> getTermIdsAll();
	
	public List<Terminals> getTermInfoByTermId(@Param("termId")Integer termId);

}
