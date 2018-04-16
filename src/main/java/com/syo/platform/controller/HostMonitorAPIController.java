package com.syo.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syo.platform.entity.HostMonitorConfig;
import com.syo.platform.service.HostMonitorConfigService;

@RestController
public class HostMonitorAPIController {

	@Autowired
	private HostMonitorConfigService hostMonitorConfigService;
	
	@RequestMapping("/config/hostConfig")
	public HostMonitorConfig findConfig(String ip) {
		System.out.println(ip);
		return hostMonitorConfigService.findConfigByIp(ip);
	}
}
