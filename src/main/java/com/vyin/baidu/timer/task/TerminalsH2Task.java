package com.vyin.baidu.timer.task;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.vyin.baidu.bean.Terminals;
import com.vyin.baidu.service.TerminalsService;
import lombok.extern.log4j.Log4j;

/**
* @ author chentao:
* @version 创建时间 2019年7月17日下午16：28
* 
*/
@SuppressWarnings("restriction")
@Service("terminalsH2Task")
@Log4j
public class TerminalsH2Task {
	
	@Resource
	private TerminalsService terminalsService;
	
//	@Scheduled(cron = "0 0 0/1 * * ?")
//	@Scheduled(fixedRate=3000)
	public void updataTermIdInfo() {
		log.info("timer updataTermIdInfo start ...");
		try {
		 //广告系统终端设备信息
		 List<Terminals> terminals = terminalsService.getTermIdsAll();
		 //缓存终端设备信息
		 List<Terminals> terminalsH2 = terminalsService.getH2AllTermId();
		 //添加缓存终端设备列表
		 List<Terminals> terminalsInsert = new ArrayList<Terminals>();
		 //更新缓存终端设备列表
		 List<Terminals> terminalsUpdate = new ArrayList<Terminals>();
		 
		 if(terminalsH2.size()==0 || terminalsH2==null) {
			 terminalsInsert.addAll(terminals);
		 }else {
			 for (int i = 0; i < terminals.size(); i++) {
				int num = 0;
				for (int j = 0; j < terminalsH2.size(); j++) {
					if(terminals.get(i).getTermId().equals(terminalsH2.get(j).getTermId())) {
						if(terminals.get(i).getTMacId()==null) {
							terminals.get(i).setTMacId("");
						}
						if(terminalsH2.get(i).getTMacId()==null) {
							terminalsH2.get(i).setTMacId("");
						}
						if(!terminals.get(i).getConnectionType().equals(terminalsH2.get(j).getConnectionType())
							|| !terminals.get(i).getTMacId().equals(terminalsH2.get(j).getTMacId())
							|| !terminals.get(i).getIpv4().equals(terminalsH2.get(j).getIpv4())
							|| !terminals.get(i).getOsVersion().equals(terminalsH2.get(j).getOsVersion())
							|| !terminals.get(i).getVendor().equals(terminalsH2.get(j).getVendor())
							|| !terminals.get(i).getModel().equals(terminalsH2.get(j).getModel())
							|| !terminals.get(i).getOsType().equals(terminalsH2.get(j).getOsType())
							|| !terminals.get(i).getOperatorType().equals(terminalsH2.get(j).getOperatorType())
							|| !terminals.get(i).getScreenHeight().equals(terminalsH2.get(j).getScreenHeight())
							|| !terminals.get(i).getScreenWidth().equals(terminalsH2.get(j).getScreenWidth()) ) {
							
							terminalsUpdate.add(terminals.get(i));
						}
						num += 1;
						break;
					}
				}
				if(num==0) {
					 terminalsInsert.add(terminals.get(i));
				}
			}
		 }
		 if(terminalsInsert.size()!=0 && terminalsInsert!=null) {
			 boolean boo1 = terminalsService.setInsertH2TermId(terminalsInsert);
			 if(!boo1) {
				 log.info("timer updataTermIdInfo ，Insert hava error !");
			 }
		 }
		 if(terminalsUpdate.size()!=0 && terminalsUpdate!=null) {
			 boolean boo2 = terminalsService.setUpdateH2TermId(terminalsUpdate);
			 if(!boo2){
				 log.info("timer updataTermIdInfo ，update hava error !");
			 }
		 }
		 
		} catch (Exception e) {
			// TODO: handle exception
		}
		 log.info("timer updataTermIdInfo end ...");
	}

}
