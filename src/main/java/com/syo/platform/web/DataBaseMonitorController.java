package com.syo.platform.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.syo.platform.entity.DataBaseMonitorConfig;
import com.syo.platform.entity.HostMonitorConfig;
import com.syo.platform.service.DataBaseMonitorConfigService;

@Controller
@RequestMapping("/dbMonitor")
public class DataBaseMonitorController {
	
	@Autowired
	private DataBaseMonitorConfigService dataBaseMonitorConfigService;

	@RequestMapping({"","/"})
	public String dbmonitor(String vague, Model model) {
		model.addAttribute("configs", dataBaseMonitorConfigService.findConfig(vague));
		model.addAttribute("vague", vague);
		
		return "dbMonitor";
	}
	
	@PostMapping("/config_save")
	@ResponseBody
	public String saveHostConfig(DataBaseMonitorConfig config) {
		dataBaseMonitorConfigService.saveConfig(config);
		return "SUCCESS";
	}
	
	@RequestMapping("/config/{id}")
	@ResponseBody
	public DataBaseMonitorConfig findConfigById(@PathVariable("id")String id) {
		return dataBaseMonitorConfigService.findConfigById(id);
	}
	
}
