package com.jx372.jblog.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jx372.jblog.service.UserService;



@Controller("userApiController") //동일한 클래스 이름이 존재하기 때문에 충돌이 발생함 
@RequestMapping("/user/api")
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	@ResponseBody
	@RequestMapping("/checkemail")
	public Map<String, Object> checkEmail(@RequestParam(value="id",required=true, defaultValue="") String id){
		
		
		
		boolean exist=userService.existEmail(id);
		
		
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", exist);
		
		return map;
	}
	

}
