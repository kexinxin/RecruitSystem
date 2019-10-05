package com.kexinxin.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	private static final Logger logger = Logger.getLogger(HomeController.class);
	
	@RequestMapping(value = "/home/getHome", method = RequestMethod.GET)
	public String getHome() {
		return "home/home";
	}
}
