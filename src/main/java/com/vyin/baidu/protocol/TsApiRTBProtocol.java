package com.vyin.baidu.protocol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.vyin.baidu.rtb.vo.Ad;
import com.vyin.baidu.rtb.vo.Juping;
import com.vyin.baidu.rtb.vo.MaterialMeta;
import com.vyin.baidu.rtb.vo.TsApiResponse;
import com.vyin.baidu.utils.ByteStringConvertor;

/**
 * @ author liu qihang:
 * 
 * @version 创建时间 2018年5月10日上午10:36:20
 * 
 */
public class TsApiRTBProtocol {

	public static Juping.TsApiResponse clientPack(TsApiResponse tar) {
		Juping.TsApiResponse.Builder tsApiResponseBuilder = Juping.TsApiResponse.newBuilder();
		tsApiResponseBuilder.setRequestId(ByteStringConvertor.getByteString(tar.request_id));
		tsApiResponseBuilder.setErrorCode(tar.error_code);
		tsApiResponseBuilder.setAdslotId(ByteStringConvertor.getByteString(tar.adslot_id));
		
		List<Ad> ads = tar.ads;
		for (int i = 0, size = ads.size(); i < size; i++) {
			Ad ad = ads.get(i);
			tsApiResponseBuilder.addAds(ad.getAd());
		}
		ads = null;
		
		tsApiResponseBuilder.setExpirationTime(tar.expiration_time);
		tsApiResponseBuilder.setSearchKey(ByteStringConvertor.getByteString(tar.search_key));
		tsApiResponseBuilder.setJpAdtext(ByteStringConvertor.getByteString(tar.jp_adtext));
		tsApiResponseBuilder.setJpAdlogo(ByteStringConvertor.getByteString(tar.jp_adlogo));
		
		return tsApiResponseBuilder.build();
	}

	//广告接口返回解包
	public static TsApiResponse serverUnpack(byte[] bytes) throws IOException {
		Juping.TsApiResponse j_tar = Juping.TsApiResponse.parseFrom(bytes);

		TsApiResponse tar_v = new TsApiResponse();
		tar_v.respContent = j_tar.toString();
		tar_v.request_id = ByteStringConvertor.getString(j_tar.getRequestId());
		tar_v.error_code = j_tar.getErrorCode();
		tar_v.adslot_id = ByteStringConvertor.getString(j_tar.getAdslotId());
		tar_v.ads = unpackAd(j_tar.getAdsList());
		tar_v.expiration_time = j_tar.getExpirationTime();
		tar_v.search_key = ByteStringConvertor.getString(j_tar.getSearchKey());
		tar_v.jp_adlogo = ByteStringConvertor.getString(j_tar.getJpAdlogo());
		tar_v.jp_adtext = ByteStringConvertor.getString(j_tar.getJpAdtext());
		return tar_v;
	}

	public static List<Ad> unpackAd(List<Juping.Ad> j_adList) {
		List<Ad> v_adList = new ArrayList<Ad>();

		for (int i = 0, size = j_adList.size(); i < size; i++) {
			Ad ad = new Ad();
			Juping.Ad j_ad = j_adList.get(i);

			/*** 预留参数(接入媒体无需关注) start ****************/
			ad.title = ByteStringConvertor.getString(j_ad.getTitle());
			ad.brand_name = ByteStringConvertor.getString(j_ad.getBrandName());
			ad.description = ByteStringConvertor.getString(j_ad.getDescription());
			ad.icon_url = ByteStringConvertor.getString(j_ad.getIconUrl());
			ad.ad_tracking = null;// 暂时赋值null
			/************ 预留参数 end ******************************************/

			ad.material_metas = unpackMaterialMeta(j_ad.getMaterialMetasList());
			ad.win_notice_url = ByteStringConvertor.convertStringTypeList(j_ad.getWinNoticeUrlList());
			ad.third_monitor_url = ByteStringConvertor.convertStringTypeList(j_ad.getThirdMonitorUrlList());
			ad.ad_key = ByteStringConvertor.getString(j_ad.getAdKey());
			v_adList.add(ad);
		}
		return v_adList;
	}

	public static List<MaterialMeta> unpackMaterialMeta(List<Juping.MaterialMeta> j_mmList) {
		List<MaterialMeta> v_mmList = new ArrayList<MaterialMeta>();

		for (int i = 0, size = j_mmList.size(); i < size; i++) {
			MaterialMeta materialMeta = new MaterialMeta();
			Juping.MaterialMeta j_mm = j_mmList.get(i);
			materialMeta.material_type = j_mm.getMaterialType().getNumber();
			materialMeta.image_src = ByteStringConvertor.convertStringTypeList(j_mm.getImageSrcList());
			materialMeta.video_url = ByteStringConvertor.getString(j_mm.getVideoUrl());
			materialMeta.video_duration = j_mm.getVideoDuration();
			materialMeta.material_width = j_mm.getMaterialWidth();
			materialMeta.material_height = j_mm.getMaterialHeight();
			materialMeta.material_size = j_mm.getMaterialSize();
			materialMeta.material_md5 = ByteStringConvertor.getString(j_mm.getMaterialMd5());
			v_mmList.add(materialMeta);
		}
		return v_mmList;
	}

}
