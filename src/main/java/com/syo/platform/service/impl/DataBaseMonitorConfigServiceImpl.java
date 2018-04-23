package com.syo.platform.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syo.platform.entity.DataBaseMonitorConfig;
import com.syo.platform.repository.DataBaseMonitorConfigRepository;
import com.syo.platform.service.DataBaseMonitorConfigService;

@Service
public class DataBaseMonitorConfigServiceImpl implements DataBaseMonitorConfigService {

	@Autowired
	private DataBaseMonitorConfigRepository dataBaseMonitorConfigRepository;
	
	@Override
	public void saveConfig(DataBaseMonitorConfig config) {
		if(config.getId()==null || config.getId().trim().equals("")) {
			config.setLastUpdateTime(new Date());
			config.setCreateTime(config.getLastUpdateTime());
			dataBaseMonitorConfigRepository.save(config);
		} else {
			DataBaseMonitorConfig currentConfig = dataBaseMonitorConfigRepository.findOne(config.getId());
			if(currentConfig!=null) {
				currentConfig.setName(config.getName());
				currentConfig.setCron(config.getCron());
				currentConfig.setDbDriver(config.getDbDriver());
				currentConfig.setDbName(config.getDbName());
				currentConfig.setDbPassword(config.getDbPassword());
				currentConfig.setDbPort(config.getDbPort());
				currentConfig.setDbUser(config.getDbUser());
				currentConfig.setDbVendor(config.getDbVendor());
				currentConfig.setSmsReceivers(config.getSmsReceivers());
				currentConfig.setEmailReceivers(config.getEmailReceivers());
				currentConfig.setErrorContinued(config.getErrorContinued());
				currentConfig.setIpAddress(config.getIpAddress());
				currentConfig.setLastUpdateTime(new Date());
				currentConfig.setRemarks(config.getRemarks());
				currentConfig.setTaskDays(config.getTaskDays());
				currentConfig.setTaskHours(config.getTaskHours());
				currentConfig.setTaskWeeks(config.getTaskWeeks());
				currentConfig.setTaskType(config.getTaskType());
				
				dataBaseMonitorConfigRepository.save(currentConfig);
			}
		}
		//dataBaseMonitorConfigRepository.save(config);
	}

	@Override
	public List<DataBaseMonitorConfig> findConfig(String vague) {
		if(vague==null || vague.trim().equals("")) {
			return dataBaseMonitorConfigRepository.findAll();
		}
		return dataBaseMonitorConfigRepository.findByVague(vague);
	}

	@Override
	public DataBaseMonitorConfig findConfigById(String id) {
		return dataBaseMonitorConfigRepository.findOne(id);
	}


	@Override
	public void deleteConfig(String id) {
		dataBaseMonitorConfigRepository.delete(id);
	}

}
