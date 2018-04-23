package com.syo.platform.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syo.platform.repository.HostMonitorDataDao;
import com.syo.platform.service.HostMonitorService;

@Service
public class HostMonitorServiceImpl implements HostMonitorService {
	
	@Autowired
	HostMonitorDataDao dao;

	@Override
	public List<Map> findHisData(Date start, Date end, String infoType, String ip) {
		return dao.findHisData(start, end, infoType, ip);
	}

	@Override
	public List<Map> findErrData(Date start, Date end, String infoType, String ip) {
		return dao.findErrData(start, end, infoType, ip);
	}

	@Override
	public Map<String, Object> findHisChart(Date start, Date end, String infoType, String ip) {
		List<Map> datas = dao.findHisData(start, end, infoType, ip);
		Map<String, Object> result = new HashMap<String, Object>();
		
		if(infoType.equals("CPUInfo")) {
			result.put("chartName", "cpu");
			
			Object[] times = new Object[datas.size()];
			Object[] combineds = new Object[datas.size()];
			Object[] idles = new Object[datas.size()];
			
			result.put("combineds", combineds);
			result.put("idles", idles);
			result.put("times", times);
			for(int i=0; i<datas.size(); i++) {
				Map data = datas.get(i);
				combineds[i] = data.get("combined");
				idles[i] = data.get("idle");
				
				times[i] = "";
			}
		} else if(infoType.equals("MemoryInfo")) {
			result.put("chartName", "memory");
			
			Object[] times = new Object[datas.size()];
			Object[] usedPercents = new Object[datas.size()];

			result.put("usedPercents", usedPercents);
			result.put("times", times);
			for(int i=0; i<datas.size(); i++) {
				Map data = datas.get(i);
				usedPercents[i] = data.get("usedPercent");
				
				times[i] = "";
			}
		} else if(infoType.equals("NetworkInfo")) {
			result.put("chartName", "network");
			
			Object[] times = new Object[datas.size()];
			Object[] rxBytes = new Object[datas.size()];
			Object[] txBytes = new Object[datas.size()];
			
			result.put("rxBytes", rxBytes);
			result.put("txBytes", txBytes);
			result.put("times", times);
			for(int i=0; i<datas.size(); i++) {
				Map data = datas.get(i);
				rxBytes[i] = data.get("rxBytes");
				txBytes[i] = data.get("txBytes");
				
				times[i] = "";
			}
		} else {
			if(datas.size()<=0) {
				start = new Date(start.getTime()-3600000);
				end = new Date(end.getTime()+3600000);
				datas =  dao.findHisData(start, end, infoType, ip);
			}
			result.put("chartName", "disk");
			Map diskInfo = datas.get(datas.size()-1);
			List<Map> items = (List<Map>) diskInfo.get("items");
			
			Object[] parts = new Object[items.size()];
			Object[] totals = new Object[items.size()];
			Object[] useds = new Object[items.size()];
			
			result.put("parts", parts);
			result.put("totals", totals);
			result.put("useds", useds);
			
			for(int i=0; i<items.size(); i++) {
				parts[i] = items.get(i).get("devName");
				totals[i] = items.get(i).get("total");
				useds[i] = items.get(i).get("used");
			}
		}
		
		
		return result;
	}

}
