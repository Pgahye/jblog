package com.jx372.jblog.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.NodeList;

import com.jx372.jblog.service.BlogService;
import com.jx372.jblog.service.UserService;
import com.jx372.jblog.vo.UserVo;
import com.jx372.security.Auth;
import com.jx372.security.AuthUser;




@Controller
@RequestMapping("/user")
public class UserController {
	

	@Autowired
	private UserService userService;
	
	


	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(@ModelAttribute UserVo userVo) {
		
	
		

		return "user/join";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo userVo, BindingResult result, Model model) {

		
		if(result.hasErrors()){
			
			model.addAllAttributes(result.getModel());
			return "user/join";
			
			
		}
		 userService.join(userVo); //@ModelAttribute 써주면 jsp로 값이 넘어가게 되어있음 
		
	

		return "redirect:/user/joinsuccess";
	}

	
	@RequestMapping(value = "/joinsuccess") //??
	public String joinsuccess() {

	

		
		return "user/joinsuccess";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {

		return "user/login";

	}
	

	
	
	
}
