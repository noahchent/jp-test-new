package com.vyin.baidu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vyin.baidu.bean.Terminals;
import com.vyin.baidu.service.TerminalsService;
import com.vyin.baidu.timer.task.BaiduQualzTask;

import lombok.extern.log4j.Log4j;

/**
* @author liu qihang
* @version 2018年8月20日 上午11:38:03
*/

@Controller
@Log4j
@RequestMapping(value = "/")
public class BaiduSyncAdController {
	
	@Resource
	private TerminalsService terminalsService;
	
	@Resource
	private BaiduQualzTask baiduQualzTask;
	
	@RequestMapping(value = "/syncAd", method=RequestMethod.POST)
	public void syncAd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String termId = request.getParameter("termId");
		log.debug("sync term id=" + termId);
		if(termId == null || "".equals(termId.trim())){
			log.error("参数错误");
			return ;
		}
		long startTime = System.currentTimeMillis();
		try {
		List<Terminals> terminalsList = new ArrayList<Terminals>();
		
			terminalsList = terminalsService.getH2TermInfoByTermId(Integer.valueOf(termId));
			if(terminalsList.size()==0 || "null".equals(terminalsList)) {
				terminalsList = terminalsService.getTermInfoByTermId(Integer.valueOf(termId));
				if(terminalsList.size()==0 || "null".equals(terminalsList)) {
					log.info("该终端"+termId+"不存在...");
				}else {
					baiduQualzTask.task(terminalsList);
					long endTime = System.currentTimeMillis();
					log.info("请求聚屏获取广告信息结束  耗时" + (endTime - startTime)+ "ms   end...");
				}
			}else {
				baiduQualzTask.task(terminalsList);
				long endTime = System.currentTimeMillis();
				log.info("请求聚屏获取广告信息结束  耗时" + (endTime - startTime)+ "ms   end...");
			}
		} catch (Exception e) {
			log.info("该终端"+termId+"不存在...");
		}
	}

}
