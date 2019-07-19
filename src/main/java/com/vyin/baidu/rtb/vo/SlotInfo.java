package com.vyin.baidu.rtb.vo;

import java.io.Serializable;

import com.vyin.baidu.utils.ByteStringConvertor;

/**
* @ author liu qihang:
* @version 创建时间 2018年5月9日下午1:41:04
* 
*/
//广告位参数
public class SlotInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String adslot_id;	//广告位id，平台生成
	public int base_price;		//实时底价	选填! [仅百度内部流量可用，其它流量暂时不允许填充]
	public int multi_show;		//展现次数	选填! [仅百度内部流量可用， 其它流量暂时不允许填充]
	
	public Juping.SlotInfo getSlotInfo(){
		Juping.SlotInfo.Builder slotInfoBuilder = Juping.SlotInfo.newBuilder();
		slotInfoBuilder.setAdslotId(ByteStringConvertor.getByteString(adslot_id));
		slotInfoBuilder.setBasePrice(base_price);
		slotInfoBuilder.setMultiShow(multi_show);
		return slotInfoBuilder.build();
	}

	public SlotInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public SlotInfo(String adslot_id) {
		super();
		this.adslot_id = adslot_id;
	}
	
	

}
