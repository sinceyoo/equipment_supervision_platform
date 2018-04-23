package com.syo.platform.web;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
import com.syo.platform.service.HostMonitorService;

@Controller
public class HostMonitorController {

	@Autowired
	HostMonitorConfigService hostMonitorConfigService;
	
	@Autowired
	HostMonitorService hostMonitorService;
	
	@Autowired
	private RestTemplate restTemplate;
	

	@RequestMapping("/hostMonitor")
	public String hostMonitor(Model model) {
		model.addAttribute("configs", hostMonitorConfigService.findConfig(null));
		
		Date now = new Date();
		Calendar c = Calendar.getInstance();
//		c.set(Calendar.HOUR_OF_DAY, 0);
		c.add(Calendar.HOUR_OF_DAY, -1);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		model.addAttribute("defaultStart", c.getTime());
		model.addAttribute("defaultEnd", now);
		
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
		//return "SUCCESS";
		return restTemplate.getForObject("http://"+config.getIpAddress()+":6666/updateConfig", String.class);
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
	
	@RequestMapping("/hostMonitor/processes")
	@ResponseBody
	public List<Map<String, Object>> processes(String ip) {
		return restTemplate.getForObject("http://"+ip+":6666/processes", List.class);
	}
	
	@RequestMapping("/hostMonitor/services")
	@ResponseBody
	public List<Map<String, Object>> services(String ip) {
		return restTemplate.getForObject("http://"+ip+":6666/services", List.class);
	}
	
	@RequestMapping("/hostMonitor/hisChart")
	@ResponseBody
	public Map<String, Object> hisChart(Date start, Date end, String infoType, String ip) {
		return hostMonitorService.findHisChart(start, end, infoType, ip);
	}
	
	@RequestMapping("/hostMonitor/errors")
	@ResponseBody
	public List errors(Date start, Date end, String infoType, String ip) {
		//return hostMonitorService.findErrData(start, end, infoType, ip);
		List list = hostMonitorService.findErrData(start, end, infoType, ip);
		//System.out.println(((Map)list.get(0)).get("time").getClass());
		return list;
	}
	
}
