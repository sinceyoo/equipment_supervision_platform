package com.syo.platform.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface HostMonitorService {

	public List<Map> findHisData(Date start, Date end, String infoType, String ip);
	
	public List<Map> findErrData(Date start, Date end, String infoType, String ip);
	
	public Map<String, Object> findHisChart(Date start, Date end, String infoType, String ip);
	
}
