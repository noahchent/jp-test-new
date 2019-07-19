package com.vyin.baidu.timer.task;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vyin.baidu.bean.Terminals;
import com.vyin.baidu.bean.VyinReqInfoVo;
import com.vyin.baidu.protocol.JuPingMutualProtocol;
import com.vyin.baidu.protocol.TsApiRTBProtocol;
import com.vyin.baidu.protocol.VyinRTBProtocol;
import com.vyin.baidu.rtb.vo.Device;
import com.vyin.baidu.rtb.vo.Juping;
import com.vyin.baidu.rtb.vo.Network;
import com.vyin.baidu.rtb.vo.Size;
import com.vyin.baidu.rtb.vo.SlotInfo;
import com.vyin.baidu.rtb.vo.TsApiResponse;
import com.vyin.baidu.rtb.vo.UdId;
import com.vyin.baidu.rtb.vo.Version;
import com.vyin.baidu.rtb.vo.VyinApiRequest;
import com.vyin.baidu.service.AdService;
import com.vyin.baidu.service.BaiduMutualService;
import com.vyin.baidu.service.TerminalsService;
import com.vyin.baidu.utils.ImageUtil;
import com.vyin.baidu.utils.JuPingUtil;
import com.vyin.baidu.utils.StringUtils;

import lombok.extern.log4j.Log4j;

/**
 * @ author liu qihang:
 * 
 * @version 创建时间 2018年6月14日下午2:35:47
 */
@Service("baiduReqTask")
@Log4j
public class BaiduQualzTask {

	@Resource
	private TerminalsService terminalsService;
	
	@Resource
	private BaiduMutualService baiduMutualService;

	@Resource
	private AdService adService;
	
	@Value("${juping_req_url}")
	private String juping_req_url;

	@Value("${app_id}")
	private String app_id;

	@Value("${api_version}")
	private String api_version;

	//暂时弃用
	public void qualzReqGetAd() {
		log.info("获取空闲广告位请求聚屏   start...");
		long startTime = System.currentTimeMillis();

		List<Terminals> terminalsList = terminalsService.getAllTermId();// 获取全部终端数据
		task(terminalsList);

		long endTime = System.currentTimeMillis();
		log.info("请求聚屏获取广告信息结束  耗时" + (endTime - startTime)+ "ms   end...");
	}

	public void task(List<Terminals> terminalsList) {

//		ExecutorService service = Executors.newFixedThreadPool(10); // 初始化线程池，加载10个线程
		long forLoopStartTime = System.currentTimeMillis();
		
		//终端list不为空
		if (terminalsList == null || terminalsList.size() <= 0) {
			return;
		}
		
		final Terminals terminals = terminalsList.get(0);
		if(terminals == null || terminals.getTermId() == null || terminals.getTMacId() == null){
			log.error("终端" + terminals.getTermId() == null ? "null": terminals.getTermId() + "信息不符合参数规则...");
			return;
		} 
		//这里必须验证，否则后续sql将会查询出所有终端广告位信息
		Thread thread = new Thread(){
		   public void run(){
			   method(terminals, this);
		    }
		};
		thread.start();

		long forLoopEndTime = System.currentTimeMillis();
		log.info("遍历终端ID list结束  耗时" + (forLoopEndTime - forLoopStartTime) + "ms   end...");
	}
	
	public  void method(Terminals terminals,Thread thread) {
		List<VyinReqInfoVo> vyinReqInfoVoList = baiduMutualService.getVyinReqData(null, terminals.getTermId());

		if (vyinReqInfoVoList == null || vyinReqInfoVoList.size() <= 0) {
			log.info("终端ID=" + terminals.getTermId() + ":未获取到空闲广告位...");
			return ;
		}
		
		Integer publishTotalTime = null;
		
		//用于判断最后一次执行是否得到201000，如果得到201000则为true，展示时间短的广告位就不再获取广告
		boolean lastIsERR = false;	//第一次是false
		for (VyinReqInfoVo vyinReqInfoVo : vyinReqInfoVoList) {
			if (lastIsERR) {
				break;
			}
			
			vyinReqInfoVo.setTerminals(terminals);
			try {
				if (StringUtils.checkObjectContainNullElement(vyinReqInfoVo)) {
					log.warn("*******" + vyinReqInfoVo.getTerminals().getTermId() + "含有空值，设备参数不符合规则=" + vyinReqInfoVo.getSlotId());//后期将含空值的termId做统计(最好以后这行代码不用)
					continue;
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			
			//如果没有广告总播放时长则查询一次，如果有则跳过
			if (publishTotalTime == null) {
//				publishTotalTime = adService.getPublishTotalTime(terminals.getTermId(), publishTotalTime);
				//不查询数据库 直接使用0 相当于未发布任何广告
				publishTotalTime = 0;
			}
			
			//计算可获取广告数
			Integer num = adService.getSurplusMaxTimesBySlotTypeCode(vyinReqInfoVo, publishTotalTime);
			
			log.info("线程名:" + Thread.currentThread().getName() + "启动开始, 终端id=" + vyinReqInfoVo.getTerminals().getTermId() + ", 广告位=" + vyinReqInfoVo.getSlotId() + "/广告位类型type=" + vyinReqInfoVo.getSlotType() + "剩余可发布广告量num=" + num);
			
			
			List<VyinApiRequest> vaReqlist = new ArrayList<VyinApiRequest>();
			List<TsApiResponse> tarlist  = new ArrayList<TsApiResponse>();
			for (int i = 0; i < num; i++) {
				log.debug("请求第" + (i + 1) + "次");
				
				VyinApiRequest vyinApiRequest  =  getVyinApiRequestVo(vyinReqInfoVo);// 媒体端打包请求数据
				Juping.TsApiRequest tsApiRequest = packData(vyinApiRequest);
				
				vaReqlist.add(vyinApiRequest);
				
				InputStream iputStream = null;
				try {
					iputStream = JuPingUtil.sendHTTPReqToBaidu(tsApiRequest, juping_req_url);// 发送请求
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (iputStream == null) {
					log.error("*******未得到响应slotId=" + vyinReqInfoVo.getSlotId());
					continue;
				}
				TsApiResponse tar = receiveBaiduAdInfo(iputStream);// 接收百度响应数据并解析
				if (JuPingMutualProtocol.NO_AD == tar.error_code) {
					log.error("百度聚屏内部错误,导致无广告返回=" + tar.error_code);
					continue;
				}
				
				if (JuPingMutualProtocol.AD_NO_DATA == tar.error_code) {
					log.error("无广告主投放,无广告返回=" + tar.error_code);
					lastIsERR = true;
					continue;
				}
				if (JuPingMutualProtocol.NO_ERROR != tar.error_code) {//如果得到非0error_code, 跳过当前循环
					log.error("错误响应=" + tar.error_code);
					continue;
				}
				publishTotalTime += vyinReqInfoVo.getShowDuration();
//				saveBaiduAdInfo(tar, terminals.getTermId());// 保存广告数据到数据库
				tarlist.add(tar);
			}
			long forSavaStartTime = System.currentTimeMillis();
			// 批量保存广告数据到数据库
			saveBaiduAdListInfo(tarlist, terminals.getTermId());
			long forSavaEndTime = System.currentTimeMillis();
			log.info("批量保存广告数据到数据库   耗时" + (forSavaEndTime - forSavaStartTime) + "ms   end...");
			//每台设备批量保存一次req表
			insertBatchVyinReqJpDate(vaReqlist);
		}
	}
	
	public VyinApiRequest getVyinApiRequestVo(VyinReqInfoVo vyinReqInfoVo) {
		VyinApiRequest vyinApiRequest = new VyinApiRequest();

		vyinApiRequest.requestId = JuPingUtil.generateRequestId();

		int[] ints = JuPingUtil.getApiVersion(api_version);
		vyinApiRequest.version = new Version(ints[0], ints[1], ints[2]);

		vyinApiRequest.appId = app_id;
		vyinApiRequest.slotInfo = new SlotInfo(vyinReqInfoVo.getSlotId());

		int[] osvs = JuPingUtil.getApiVersion(vyinReqInfoVo.getTerminals().getOsVersion());
		Version osVersion = new Version(osvs[0], osvs[1], osvs[2]);

		vyinApiRequest.device = new Device(new UdId(JuPingMutualProtocol.MAC, vyinReqInfoVo.getTerminals().getTMacId()),
				vyinReqInfoVo.getTerminals().getOsType(), osVersion, vyinReqInfoVo.getTerminals().getVendor(), vyinReqInfoVo.getTerminals().getModel(),
				new Size(vyinReqInfoVo.getTerminals().getScreenWidth(), vyinReqInfoVo.getTerminals().getScreenHeight()));

		vyinApiRequest.network = new Network(vyinReqInfoVo.getTerminals().getIpv4(), vyinReqInfoVo.getTerminals().getConnectionType(),
				vyinReqInfoVo.getTerminals().getOperatorType());
		
		vyinApiRequest.termId = vyinReqInfoVo.getTerminals().getTermId();

		// **** 保存请求数据,放到请求之后批量保存，单存暂时不用了
//		JpRequestVo jpRequestVo = new JpRequestVo();
//		jpRequestVo.setApiVersion(vyinApiRequest.version.major + "." + vyinApiRequest.version.minor + "." + vyinApiRequest.version.micro);
//		jpRequestVo.setRequestId(vyinApiRequest.requestId);
//		jpRequestVo.setAppId(vyinApiRequest.appId);
//		jpRequestVo.setTermId(vyinReqInfoVo.getTerminals().getTermId());
//		jpRequestVo.setAdSlotId(vyinReqInfoVo.getSlotId());
//		jpRequestVo.setMutualStatus(0);
//		baiduMutualService.insertVyinReqJpData(jpRequestVo);
		
		return vyinApiRequest;
	}
	
	public int insertBatchVyinReqJpDate(List<VyinApiRequest> vaReqList) {
		int result = 0;
		baiduMutualService.insertBatchVyinReqJpData(vaReqList);
		
		return result;
	}
	
	
	public Juping.TsApiRequest packData(VyinApiRequest vyinApiRequest) {
		try {
			

			Juping.TsApiRequest tsApiRequest = VyinRTBProtocol.serverPack(vyinApiRequest);// 数据打包

			String req_content = tsApiRequest.toString();// 请求报文

//			byte[] bytes = tsApiRequest.toByteArray();// 请求proto byte数组

//			int flag = baiduMutualService.insertBaiduRequestProto(vyinApiRequest.requestId,JuPingUtil.base64Encode(bytes));
//			baiduMutualService.insertBaiduRequestContent(vyinApiRequest.requestId, req_content, flag > 0 ? 0 : 1);

			log.debug("req_content :" + req_content + "bytes长度" + tsApiRequest.toByteString().size());

			return tsApiRequest;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("packData 发送异常" + e);
		}
		return null;
	}

	public TsApiResponse receiveBaiduAdInfo(InputStream inputStream) {
		TsApiResponse tar = null;
		try {
			ImageUtil iu = new ImageUtil();
			byte[] bytes = iu.readInputStream(inputStream);
			tar = TsApiRTBProtocol.serverUnpack(bytes);
			log.info("聚屏返回信息：" + tar.toString());

//			int flag = baiduMutualService.insertBaiduResponseContent(tar.request_id, tar.respContent);
//			baiduMutualService.insertBaiduResponseProto(tar.request_id, JuPingUtil.base64Encode(bytes), flag > 0 ? 0 : 1);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("receiveBaiduAdInfo 发送异常" + e);
		}
		return tar;
	}

	public void saveBaiduAdListInfo(List<TsApiResponse> tarlist, Integer termId) {
		boolean result = adService.saveBaiduAdListInfo(tarlist, termId);
		if (!result) {
			log.info("保存广告信息失败");
			return;
		}
		log.info("保存广告信息成功");
	}
	
	
	

}
