package com.jx372.jblog.controller.api;



import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jx372.jblog.dto.JSONResult;
import com.jx372.jblog.service.BlogService;
import com.jx372.jblog.service.CategoryService;
import com.jx372.jblog.service.PostService;
import com.jx372.jblog.service.UserService;
import com.jx372.jblog.vo.BlogVo;
import com.jx372.jblog.vo.CategoryVo;
import com.jx372.jblog.vo.PostVo;
import com.jx372.jblog.vo.UserVo;


//@CrossOrigin( { "http://localhost:8080", "http://www.blogss.com:8080" } )
//@CrossOrigin("*")
@Controller("blogApiController") //동일한 클래스 이름이 존재하기 때문에 충돌이 발생함 
@RequestMapping("/blog/api")
public class BlogController {
	
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;
	

	@ResponseBody
	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
	public JSONResult category(Model model, UserVo uservo, BlogVo BlogVo,@PathVariable("id") String id){
		
		uservo = userService.get(id);
		BlogVo = blogService.get(uservo.getNo());
		List<CategoryVo> categoryVo = categoryService.get(uservo.getNo());
		
		
		
		model.addAttribute("BlogVo", BlogVo);
		model.addAttribute("UserVo", uservo);
		model.addAttribute("Categorylist", categoryVo);
		
	
		
		return JSONResult.success(categoryVo);
	}
	
/*
	@ResponseBody
	@RequestMapping(value="/category/{id}", method=RequestMethod.POST)
	public JSONResult category(
			@ModelAttribute BlogVo blogvo,
			 UserVo uservo,
			@PathVariable("id") String id,
			@ModelAttribute CategoryVo categoryvo,
			Model model
			){
		
		uservo = userService.get(id);
		
		BlogVo BlogVo = blogService.get(uservo.getNo()); //user_id 가지고 오기 
		
		categoryvo.setUser_no(uservo.getNo());
	
		
		categoryService.insert(categoryvo);
		
		
		
		return JSONResult.success(categoryvo);
	}
	
*/	
	@ResponseBody
	@RequestMapping(value="/category/{id}", method=RequestMethod.POST)
	public JSONResult category(
			@ModelAttribute BlogVo blogvo,
			 UserVo uservo,
			@PathVariable("id") String id,
			@RequestBody CategoryVo categoryvo,
			Model model
			){
		
		uservo = userService.get(id);
		
		BlogVo BlogVo = blogService.get(uservo.getNo()); //user_id 가지고 오기 
		
		categoryvo.setUser_no(uservo.getNo());
	
		
		categoryService.insert(categoryvo);
		
		
		
		return JSONResult.success(categoryvo);
	}
	
	
	
	
	@ResponseBody
	@RequestMapping(value = "/category/delete/{id}/{no}", method = RequestMethod.GET)
	public JSONResult categorydelete(Model model, UserVo uservo, BlogVo BlogVo, @PathVariable("no") Long no,
			@PathVariable("id") String id) {

		boolean result = categoryService.delete(no);

		return JSONResult.success( result? no : -1);
	}
	
	
}
