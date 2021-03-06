package com.syo.platform.web;

import org.apache.catalina.startup.HostConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping({"","/","/index"})
	public String index() {
		return "index";
	}
	
	@RequestMapping("/map")
	public String map() {
		return "map_data";
	}
	
}
