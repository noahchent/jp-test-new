package com.vyin.baidu.mapper.h2;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.vyin.baidu.bean.Terminals;

/**
* @ author chentao:
* @version 创建时间 2019年7月17日下午16：28
* 
*/
public interface TerminalsH2Mapper {
	
	public List<Terminals> getH2AllTermId();
	
	public List<Terminals> getH2TermInfoByTermId(@Param("termId")Integer termId);
	
	public boolean  setInsertH2TermId(List<Terminals> terminals);
	
	public boolean  setUpdateH2TermId(Terminals terminals);

}
