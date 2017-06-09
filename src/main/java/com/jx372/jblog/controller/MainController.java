package com.jx372.jblog.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class MainController {
	
	
	//@Autowired
	//private AdminService AdminService;

	@RequestMapping({"/", "/main"})
	public String index(Model model
			//, SiteVo sitevo
			){
		
		//sitevo = AdminService.get();
		//model.addAttribute("sitevo", sitevo);

		
		return "main/index";
		
	}
	
	@ResponseBody
	@RequestMapping("/hello")
	public String hello(){
		
		return "<h1>안녕하세요 </h1>";
	}
	

	
}
