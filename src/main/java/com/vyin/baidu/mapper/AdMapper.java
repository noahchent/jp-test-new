package com.vyin.baidu.mapper;

import java.util.List;

import com.vyin.baidu.bean.BaiDuAd;
import com.vyin.baidu.bean.BaiDuMaterials;
import com.vyin.baidu.bean.BaiduAdPublish;

/**
* @ author liu qihang:
* @version 创建时间 2018年6月12日下午3:00:23
* 
*/
public interface AdMapper {
	
	public int insertBaiduAdvertisementList(List<BaiDuAd> baiDuAdList);
	
	public int insertBaiDuMaterialsList(List<BaiDuMaterials> baiDuMaterialslist);
	
	public int insertBaiDuAdPublishList(List<BaiduAdPublish> baiduAdPublishlist);
	
	public List<BaiDuMaterials> getBaiduMaterialByImageMd5(String md5);
	

}
