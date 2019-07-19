package com.vyin.baidu.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vyin.baidu.bean.AdMaterials;
import com.vyin.baidu.bean.AdPublish;
import com.vyin.baidu.bean.AdSlots;
import com.vyin.baidu.bean.Advertisement;
import com.vyin.baidu.bean.BaiDuAd;
import com.vyin.baidu.bean.BaiDuMaterials;
import com.vyin.baidu.bean.BaiduAdPublish;
import com.vyin.baidu.bean.JpAd;
import com.vyin.baidu.bean.JpAdMaterials;
import com.vyin.baidu.bean.JpRespInfoVo;
import com.vyin.baidu.bean.VyinReqInfoVo;
import com.vyin.baidu.mapper.AdMapper;
import com.vyin.baidu.mapper.AdSlotsMapper;
import com.vyin.baidu.mapper.BaiduMutualMapper;
import com.vyin.baidu.protocol.JuPingMutualProtocol;
import com.vyin.baidu.rtb.vo.Ad;
import com.vyin.baidu.rtb.vo.MaterialMeta;
import com.vyin.baidu.rtb.vo.TsApiResponse;
import com.vyin.baidu.service.AdService;
import com.vyin.baidu.utils.DateUtil;
import com.vyin.baidu.utils.ImageUtil;
import com.vyin.baidu.utils.StringUtils;

import lombok.extern.log4j.Log4j;

/**
 * @ author liu qihang:
 * 
 * @version 创建时间 2018年6月14日下午4:58:19
 * 
 */
@Service
@Log4j
public class AdServiceImpl implements AdService {

	@Resource
	private AdMapper adMapper;

	@Resource
	private AdSlotsMapper adSlotsMapper;

	@Resource
	private BaiduMutualMapper baiduMutualMapper;

	@Value("${image_local_path}")
	private String image_local_path;

	@Value("${appendDir}")
	private String appendDir;
	
	@Override
	public boolean saveBaiduAdListInfo(List<TsApiResponse> tarlist, Integer termId) {
		try {
			
		Map<Integer, List<BaiDuAd>> baiduadidlist  = savebaiDuAdlist(tarlist,termId);	
		List<Ad> adlist = new ArrayList<Ad>();
		List<BaiduAdPublish> baiduAdPublishList = new ArrayList<BaiduAdPublish>();
		List<BaiDuMaterials> baiDuMaterialsList = new ArrayList<BaiDuMaterials>();
		
		for (int i = 0; i < tarlist.size(); i++) {
			String slotMsspId = tarlist.get(i).adslot_id;
			
			// 根据mssp ad_slot_id获取广告位相关信息
			List<AdSlots> adSlotsList = getAdSlotInfoBySlotId(slotMsspId, termId);
			if (adSlotsList == null || adSlotsList.size() <= 0) {
				log.info("没有对应广告位信息，保存失败...slotMsspId=" + slotMsspId);
				return false;
			}
			
			List<String> adIdList = new ArrayList<String>();// 广告ID
			List<BaiDuAd> baiDuAdLists = baiduadidlist.get(i);
			List<MaterialMeta> material_metas =  new ArrayList<MaterialMeta>();
			List<Ad> adlists = tarlist.get(i).ads;// 目前暂时返回一条，但预留为list(详情见接口文档)
			
			for (int j = 0; j < adlists.size(); j++) {
				adlist.add(adlists.get(j));
				material_metas.addAll(adlists.get(j).material_metas);
				BaiDuAd baiDuAd  = baiDuAdLists.get(j);
				
				Integer adId = baiDuAd.getId();// 返回自增广告ID
				adIdList.add(String.valueOf(adId));

				for (MaterialMeta materialMeta : material_metas) {
					 baiDuMaterialsList.addAll(getBaiDuMaterials(materialMeta, adId));
						
				}
			}
				
				
				for (String adIdStr : adIdList) {
					baiduAdPublishList.addAll(getBaiduAdPublish(adSlotsList, Integer.valueOf(adIdStr), tarlist.get(i)));
//					log.info("广告" + adIdStr + "发布成功");
				}
				
			
		}
				adMapper.insertBaiDuMaterialsList(baiDuMaterialsList);
				// 发布广告
				adMapper.insertBaiDuAdPublishList(baiduAdPublishList);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("保存广告数据异常：" + e);
		}
		return true;
	}

	public Map<Integer, List<BaiDuAd>> savebaiDuAdlist(List<TsApiResponse> tarlist, Integer termId) {
//		List<Integer> baiDuAdIds = new ArrayList<Integer>();
		Map<Integer, List<BaiDuAd>> baiDuAdIdsMap = new HashMap<Integer, List<BaiDuAd>>();
		List<BaiDuAd> baiDuAdlist = new ArrayList<BaiDuAd>();
		for (int i = 0; i < tarlist.size(); i++) {
			String slotMsspId = tarlist.get(i).adslot_id;
			
			// 根据mssp ad_slot_id获取广告位相关信息
			List<AdSlots> adSlotsList = getAdSlotInfoBySlotId(slotMsspId, termId);
			
			List<BaiDuAd> baiDuAd = new ArrayList<BaiDuAd>();
			
			List<Ad> adlists = tarlist.get(i).ads;// 目前暂时返回一条，但预留为list(详情见接口文档)
			for (int j = 0; j < adlists.size(); j++) {
				baiDuAd.add(getBaiduAd(adlists.get(j), adSlotsList.get(0).getSlotType()));
			}
			baiDuAdlist.addAll(baiDuAd);
			baiDuAdIdsMap.put(i,baiDuAd);
		}
		adMapper.insertBaiduAdvertisementList(baiDuAdlist);
		return baiDuAdIdsMap;
	
	}

//	@Override
//	public boolean saveBaiduAdInfo(TsApiResponse tar, Integer termId) {
//		List<String> adIdList = new ArrayList<String>();// 广告ID
//		
//		try {
////			List<JpRequestVo> jpRequestVoList = baiduMutualMapper.getJpRequestVoByReqId(tar.request_id);
////			if(jpRequestVoList == null || jpRequestVoList.size() <= 0){
////				return false;
////			}
////			JpRequestVo jpRequestVo = jpRequestVoList.get(0);
//			String slotMsspId = tar.adslot_id;
//			
//			// 根据mssp ad_slot_id获取广告位相关信息
//			List<AdSlots> adSlotsList = getAdSlotInfoBySlotId(slotMsspId, termId);
//			if (adSlotsList == null || adSlotsList.size() <= 0) {
//				log.info("没有对应广告位信息，保存失败...slotMsspId=" + slotMsspId);
//				return false;
//			}
//			List<Ad> adlist = tar.ads;// 目前暂时返回一条，但预留为list(详情见接口文档)
//			
//			for (Ad ad : adlist) {
//				/** 保存聚屏广告数据 **/
//				BaiDuAd baiDuAd = getBaiduAd(ad, adSlotsList.get(0).getSlotType());
//				adMapper.insertBaiduAdvertisement(baiDuAd);
//				Integer adId = baiDuAd.getId();// 返回自增广告ID
//				adIdList.add(String.valueOf(adId));
//
//				List<MaterialMeta> material_metas = ad.material_metas;
//				for (MaterialMeta materialMeta : material_metas) {
//					List<BaiDuMaterials> baiDuMaterialsList = getBaiDuMaterials(materialMeta, adId);
//					for (BaiDuMaterials baiDuMaterials : baiDuMaterialsList) {
//						adMapper.insertBaiDuMaterials(baiDuMaterials);
//					}
//				}
//			}
//			
//			// 保存聚屏响应数据
////			JpRespInfoVo jpRespInfoVo = getJpRespInfoVo(tar, StringUtils.listToString(adIdList));
////			baiduMutualMapper.insertJpResponseData(jpRespInfoVo);
//
//			// 发布广告
//			for (String adIdStr : adIdList) {
//				List<BaiduAdPublish> baiduAdPublishList = getBaiduAdPublish(adSlotsList, Integer.valueOf(adIdStr), tar);
//				for (BaiduAdPublish baiduAdPublish : baiduAdPublishList) {
//					adMapper.insertBaiDuAdPublish(baiduAdPublish);
//				}
//				log.info("广告" + adIdStr + "发布成功");
//			}
//			
////			baiduMutualMapper.updateJpApiRequestMutualStatus(jpRequestVo.getId(), 2);//采用主键更新，减少Deadlock
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.error("保存广告数据异常：" + e);
//		}
//		return true;
//	}

	public List<AdSlots> getAdSlotInfoBySlotId(String slotMsspId, Integer termId) {
		List<AdSlots> adSlotsList = adSlotsMapper.getAdSlotInfoBySlotId(slotMsspId, termId);
		return adSlotsList;
	}

	public JpRespInfoVo getJpRespInfoVo(TsApiResponse tar, String adIds) {
		JpRespInfoVo jpRespInfoVo = new JpRespInfoVo();
		jpRespInfoVo.setRequestId(tar.request_id);
		jpRespInfoVo.setErrorCode(tar.error_code);
		jpRespInfoVo.setAdSlotId(tar.adslot_id);
		jpRespInfoVo.setAdIds(adIds);
		jpRespInfoVo.setExpirationTime(tar.expiration_time);
		jpRespInfoVo.setSearchKey(tar.search_key);
		jpRespInfoVo.setJpAdtext(tar.jp_adtext);
		jpRespInfoVo.setJpAdlogo(tar.jp_adlogo);
		return jpRespInfoVo;
	}

	public List<BaiduAdPublish> getBaiduAdPublish(List<AdSlots> adSlotsList, Integer adId, TsApiResponse tar) {
		List<BaiduAdPublish> baiduAdPublishList = new ArrayList<BaiduAdPublish>();
		for (AdSlots as : adSlotsList) {
			BaiduAdPublish baiduAdPublish = new BaiduAdPublish();
			baiduAdPublish.setTermId(as.getTermId());
			baiduAdPublish.setAdId(adId);
			AdPublishDate adPublishDate = getAdPublishDate(tar.expiration_time);
			baiduAdPublish.setStartDate(adPublishDate.startDate);
			baiduAdPublish.setEndDate(adPublishDate.endDate);
			baiduAdPublish.setStartTime(adPublishDate.startTime);
			baiduAdPublish.setEndTime(adPublishDate.endTime);
			baiduAdPublish.setBroadcastTime(as.getShowDuration());
			baiduAdPublish.setPublishStatus(0);// 发布状态：0
			baiduAdPublishList.add(baiduAdPublish);
		}
		return baiduAdPublishList;
	}
	
	public BaiDuAd getBaiduAd(Ad ad, Integer slotType){
		BaiDuAd baiDuAd = new BaiDuAd();
		baiDuAd.setTitle((ad.title == null || "".equals(ad.title.trim()))? "百度聚屏广告" : ad.title);
		baiDuAd.setAdType(slotType);
		baiDuAd.setAdStatus(0);
		baiDuAd.setWinNoticeUrl(StringUtils.listToString(ad.win_notice_url));
		baiDuAd.setThirdMonitorUrl(StringUtils.listToString(ad.third_monitor_url));
		baiDuAd.setJpAdKey(ad.ad_key);
		return baiDuAd;
	}
	
	public List<BaiDuMaterials> getBaiDuMaterials(MaterialMeta materialMeta, Integer adId) throws Exception{
		List<BaiDuMaterials> baiDuMaterialsList = new ArrayList<BaiDuMaterials>();
		List<String> image_srcs = materialMeta.image_src;
		for (String image_src : image_srcs) {
			log.info("图片源路径:" + image_src);
			List<BaiDuMaterials> bdmList = adMapper.getBaiduMaterialByImageMd5(materialMeta.material_md5);
			
			String imagePath = null;
			if(bdmList == null || bdmList.size() <= 0){
				imagePath = saveImage(image_src);
			}else {
				imagePath = bdmList.get(0).getImagePath();
				if(imagePath == null || imagePath.length() <= 0){
					imagePath = saveImage(image_src);
				}
			}
			log.info("保存名称:" + image_src +"的图片至" + imagePath + "成功");
			BaiDuMaterials baiDuMaterials = new BaiDuMaterials();
			baiDuMaterials.setAdId(adId);
			baiDuMaterials.setImageUrl(image_src);
			baiDuMaterials.setImagePath(imagePath);
			baiDuMaterials.setReviewStatus(1);// 默认审核通过
			
			baiDuMaterials.setMaterialTypeId(materialMeta.material_type);
			baiDuMaterials.setVideoUrl(materialMeta.video_url);
			baiDuMaterials.setVideoDuration(materialMeta.video_duration);
			baiDuMaterials.setMaterialWidth(materialMeta.material_width);
			baiDuMaterials.setMaterialHeight(materialMeta.material_height);
			baiDuMaterials.setMaterialSize(materialMeta.material_size);
			baiDuMaterials.setMaterialMd5(materialMeta.material_md5);
			
			baiDuMaterialsList.add(baiDuMaterials);
		}
		
		return baiDuMaterialsList;
	}
	
	public String saveImage(String image_src) throws Exception{
		String imagePath = null;
		ImageUtil im = new ImageUtil(image_local_path, appendDir);
		imagePath = im.getImageLocalPath(image_src);
		return imagePath;
	}
	
	//暂时弃用
	public List<AdPublish> getAdPublish(List<AdSlots> adSlotsList, Integer adId, TsApiResponse tar) {
		List<AdPublish> adPublishList = new ArrayList<AdPublish>();
		for (AdSlots as : adSlotsList) {
			AdPublish adPublish = new AdPublish();
			adPublish.setTermId(as.getTermId());
			adPublish.setAdId(adId);
			AdPublishDate adPublishDate = getAdPublishDate(tar.expiration_time);
			adPublish.setStartDate(adPublishDate.startDate);
			adPublish.setEndDate(adPublishDate.endDate);
			adPublish.setStartTime(adPublishDate.startTime);
			adPublish.setEndTime(adPublishDate.endTime);
			adPublish.setBroadcastTime(as.getShowDuration());
			adPublish.setPublishStatus(0);// 发布状态：0
			adPublishList.add(adPublish);
		}
		return adPublishList;
	}
	
	//暂时弃用
	public Advertisement getAdvertisement(Ad ad, Integer slotType) {
		Advertisement am = new Advertisement();
		try {
			am.setTitle((ad.title == null || "".equals(ad.title.trim()))? "百度聚屏广告" : ad.title);
//			am.setTitle((ad.title == null || "".equals(ad.title.trim()))? "百度聚屏广告" : new String(ad.title.getBytes("GBK"), "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		am.setAdType(slotType);
		am.setAdStatus(0);
		am.setAdSource(1);// 百度聚屏投放
		return am;
	}

	//暂时弃用
	public JpAd getJpAd(Ad ad, Integer adId) {
		JpAd ja = new JpAd();
		ja.setAdId(adId);
		ja.setWinNoticeUrl(StringUtils.listToString(ad.win_notice_url));
		ja.setThirdMonitorUrl(StringUtils.listToString(ad.third_monitor_url));
		ja.setJpAdKey(ad.ad_key);
		return ja;
	}

	//暂时弃用
	public List<AdMaterials> getAdMaterials(MaterialMeta materialMeta, Integer adId) {
		List<AdMaterials> adMaterialsList = new ArrayList<AdMaterials>();
		List<String> image_srcs = materialMeta.image_src;
		for (String image_src : image_srcs) {
			String imagePath = null;
			ImageUtil im = new ImageUtil(image_local_path, appendDir);
			try {
				imagePath = im.getImageLocalPath(image_src);
			} catch (Exception e) {
				e.printStackTrace();
			}
			AdMaterials am = new AdMaterials();
			am.setAdId(adId);
			am.setImagePath(imagePath);
			am.setImageUrl(image_src);
			am.setReviewStatus(1);// 默认审核通过
			adMaterialsList.add(am);
		}
		return adMaterialsList;
	}

	//暂时弃用
	public JpAdMaterials getJpAdMaterials(MaterialMeta materialMeta, Integer adId, Integer adMaterialsId) {
		JpAdMaterials jam = new JpAdMaterials();
		jam.setAdId(adId);
		jam.setAdMaterialsId(adMaterialsId);
		jam.setMaterialTypeId(materialMeta.material_type);
		jam.setVideoUrl(materialMeta.video_url);
		jam.setVideoDuration(materialMeta.video_duration);
		jam.setMaterialWidth(materialMeta.material_width);
		jam.setMaterialHeight(materialMeta.material_height);
		jam.setMaterialSize(materialMeta.material_size);
		jam.setMaterialMd5(materialMeta.material_md5);
		return jam;
	}

	public AdPublishDate getAdPublishDate(long expiration_time) {
		AdPublishDate apd = new AdPublishDate();

		apd.startDate = DateUtil.getDateFromStr(DateUtil.getCurrentDate("yyyy-MM-dd"), "yyyy-MM-dd");
		apd.startTime = DateUtil.getDateFromStr(DateUtil.getCurrentDate("HH:mm:ss"), "HH:mm:ss");
		// HH 24小时制 hh 12小时制
		Date date = new Date();
		date.setTime(expiration_time * 1000);
		apd.endDate = DateUtil.getDateFromStr(DateUtil.getDateFormatter(date, "yyyy-MM-dd"), "yyyy-MM-dd");
		apd.endTime = DateUtil.getDateFromStr(DateUtil.getDateFormatter(date, "HH:mm:ss"), "HH:mm:ss");
		return apd;
	}

	protected class AdPublishDate {
		public Date startDate;
		public Date endDate;
		public Date startTime;
		public Date endTime;

		@Override
		public String toString() {
			return "AdPublishDate [startDate=" + DateUtil.getDateFormatter(startDate) + ", endDate="
					+ DateUtil.getDateFormatter(endDate) + ", startTime=" + DateUtil.getDateFormatter(startTime)
					+ ", endTime=" + DateUtil.getDateFormatter(endTime) + "]";
		}
	}

	@Override
	public int getSurplusMaxTimesBySlotTypeCode(VyinReqInfoVo vyinReqInfoVo, Integer publishTotalTime) {
		
		//计算剩余可取广告时长
		int surplusMaxPublishNum = 0;// 剩余最大发布量
		Integer maxTime = vyinReqInfoVo.getMaxTime();
		log.debug("播放总时长" + maxTime);
		if (maxTime == null) {
			maxTime = JuPingMutualProtocol.DEFAULT_PUBLISH_AD_MAX;
		}
		
		if (maxTime.compareTo(publishTotalTime) <= 0) {
			return 0;
		}
		int surplusTime = maxTime - publishTotalTime;//剩余时长
		
		// 根据剩余可取广告时长，计算可取广告数量
		Integer slotShowDuration;
		if(vyinReqInfoVo.getShowDuration() == null){
			slotShowDuration = JuPingMutualProtocol.DEFAULT_PLAY_DURATION;
		} else {
			slotShowDuration = vyinReqInfoVo.getShowDuration();
		}
		
		surplusMaxPublishNum = surplusTime/slotShowDuration;
		return surplusMaxPublishNum;
	}
	
	@Override
	public int getPublishTotalTime(Integer termId, Integer slotType) {
	Integer publishTotalTime = adSlotsMapper.getTerminalAdSlotPublishTotalTimeByTypeCode(termId, slotType);
		if (publishTotalTime == null) {
			publishTotalTime = 0;
		}
		return publishTotalTime;
	}	
	
	public static void main(String[] args) {
		String str = "���Թ��0626";
		try {
			String flag = new String(str.getBytes("UTF-8"), "GBK");
			System.out.println(new String(flag.getBytes(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
