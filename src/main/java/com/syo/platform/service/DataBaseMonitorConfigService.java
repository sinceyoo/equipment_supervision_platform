package com.syo.platform.service;

import java.util.List;

import com.syo.platform.entity.DataBaseMonitorConfig;

public interface DataBaseMonitorConfigService {
	
	public void saveConfig(DataBaseMonitorConfig config);
	
	public List<DataBaseMonitorConfig> findConfig(String vague);
	
	public DataBaseMonitorConfig findConfigById(String id);
	
	public void deleteConfig(String id);
	
}
