package com.syo.platform.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.syo.platform.entity.HostMonitorConfig;
import com.syo.platform.service.HostMonitorConfigService;

@Controller
public class HostMonitorController {

	@Autowired
	HostMonitorConfigService hostMonitorConfigService;
	
	@Autowired
	private RestTemplate restTemplate;
	

	@RequestMapping("/hostMonitor")
	public String hostMonitor() {
		return "hostMonitor";
	}
	
	@RequestMapping("/hostConfig")
	public String hostConfig(String vague, Model model) {
		model.addAttribute("configs", hostMonitorConfigService.findConfig(vague));
		model.addAttribute("vague", vague);
		return "hostMonitorConfig";
	}
	
	@PostMapping("/hostConfig_save")
	@ResponseBody
	public String saveHostConfig(HostMonitorConfig config) {
		hostMonitorConfigService.saveConfig(config);
		return "SUCCESS";
	}
	
	@RequestMapping("/hostConfig/item/{id}")
	@ResponseBody
	public HostMonitorConfig findConfigById(@PathVariable("id")String id) {
		return hostMonitorConfigService.findConfigById(id);
	}
	
	@RequestMapping("/hostConfig_del/item/{id}")
	@ResponseBody
	public String deleteHostConfig(@PathVariable("id")String id) {
		hostMonitorConfigService.deleteConfig(id);
		return "SUCCESS";
	}
	
	@RequestMapping("/hostMonitor/monitor")
	@ResponseBody
	public Map<String, Object> monitor(String ip) {
		return restTemplate.getForObject("http://"+ip+":6666/monitorInfos", Map.class);
	}
	
}
