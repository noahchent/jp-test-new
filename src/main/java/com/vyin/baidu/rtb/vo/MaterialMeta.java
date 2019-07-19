package com.vyin.baidu.rtb.vo;

import java.io.Serializable;
import java.util.List;

import com.vyin.baidu.utils.ByteStringConvertor;

/**
 * @ author liu qihang:
 * 
 * @version 创建时间 2018年5月9日下午2:13:05
 * 
 */
//广告物料元数据信息 [*标为预留参数，媒体无需关注]
public class MaterialMeta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int material_type;	// 物料类型 视频(1),图片(2)
//	public MaterialType material_type; // 物料类型 图片，视频
	public String click_url; // *点击行为地址，用户点击后，在客户端进行响应，会经过多次302跳转最终到达目标地址
	public List<String> icon_src; // *广告图标地址，注意：单个广告可能有多张图标返回
	public List<String> image_src; // 广告图片地址，注意：单个广告可能有多张图片返回
	public String video_url; // 广告视频物料地址
	public int video_duration; // 广告视频物料时长
	public int material_width; // 物料的宽度:如果是图片,表示图片的宽度;如果是视频(含有视频截图),则为视频宽度;如果是图文或文本,则不会填充此字段
	public int material_height; // 物料的高度:如果是图片,表示图片的高度;如果是视频(含有视频截图),则为视频高度;如果是图文或文本,则不会填充此字段
	public int material_size; // 图片、视频物料大小,单位kb
	public String material_md5; // 图片，视频物料的md5值
	
	public Juping.MaterialMeta getMaterialMeta() {
		Juping.MaterialMeta.Builder materialMeta = Juping.MaterialMeta.newBuilder();

		materialMeta.setMaterialType(Juping.MaterialType.valueOf(material_type));
		materialMeta.setClickUrl(ByteStringConvertor.getByteString(click_url));

		for (int i = 0, size = icon_src.size(); i < size; i++) {
			materialMeta.addIconSrc(ByteStringConvertor.getByteString(icon_src.get(i)));
		}
		for (int i = 0, size = image_src.size(); i < size; i++) {
			materialMeta.addImageSrc(ByteStringConvertor.getByteString(image_src.get(i)));
		}

		materialMeta.setVideoUrl(ByteStringConvertor.getByteString(video_url));
		materialMeta.setVideoDuration(video_duration);
		materialMeta.setMaterialWidth(material_width);
		materialMeta.setMaterialHeight(material_height);
		materialMeta.setMaterialSize(material_size);
		materialMeta.setMaterialMd5(ByteStringConvertor.getByteString(material_md5));

		return materialMeta.build();
	}

}
