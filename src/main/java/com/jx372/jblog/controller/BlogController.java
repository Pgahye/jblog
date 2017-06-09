package com.jx372.jblog.controller;



import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jx372.jblog.service.BlogService;
import com.jx372.jblog.service.CategoryService;
import com.jx372.jblog.service.PostService;
import com.jx372.jblog.service.UserService;
import com.jx372.jblog.vo.BlogVo;
import com.jx372.jblog.vo.CategoryVo;
import com.jx372.jblog.vo.PostVo;
import com.jx372.jblog.vo.UserVo;



@Controller
@RequestMapping("/blog") //"/{id:(?!assets).*}"
public class BlogController {
	
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;
	
	@RequestMapping({"/main/{id}/{no}", "/main/{id}"}) // no값을 쓰면서 이중으로 받을수 없나....
	public String main(Model model, BlogVo BlogVo, PostVo mainvo, @PathVariable("id") String id, @PathVariable("no") Optional<Long> no) {
		
		
		UserVo uservo = userService.get(id);
		BlogVo = blogService.get(uservo.getNo());

		List<CategoryVo> categoryVo = categoryService.get(uservo.getNo());
		
		List<PostVo> postvo = postService.getList(uservo.getNo());
		
		if(no.isPresent() ){
		mainvo = postService.get(uservo.getNo(),no.get());
		
		}
		else{
			mainvo = postService.get(uservo.getNo());
		}
		model.addAttribute("BlogVo", BlogVo);
		model.addAttribute("Categorylist", categoryVo);
		model.addAttribute("UserVo", uservo);
		model.addAttribute("Postlist", postvo);
		model.addAttribute("MainPost", mainvo);
		
		

		return "blog/blog-main";
	}
	

	
	
	
	@RequestMapping(value = "/basic/{id}", method = RequestMethod.GET)
	public String basic(Model model, UserVo uservo, BlogVo BlogVo,@PathVariable("id") String id){
		
		uservo = userService.get(id);
		BlogVo = blogService.get(uservo.getNo());
		
		model.addAttribute("BlogVo", BlogVo);
		model.addAttribute("UserVo", uservo);
		
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping(value="/upload/{id}", method=RequestMethod.POST)
	public String upload(
			@ModelAttribute BlogVo blogvo,
			 UserVo uservo,
			@PathVariable("id") String id,
			@RequestParam(value="file1") MultipartFile file1,
			Model model
			){
		
		uservo = userService.get(id);
		
		BlogVo BlogVo = blogService.get(uservo.getNo()); //user_id 가지고 오기 
		
		String url =  blogService.restore(file1);
		blogvo.setLogo(url);
		blogvo.setUser_no(BlogVo.getUser_no());
		
	
		blogService.update(blogvo);
	
		
		
		return "redirect:/blog/main/{id}";
	}
	
	
	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
	public String category(Model model, UserVo uservo, BlogVo BlogVo,@PathVariable("id") String id){
		
		uservo = userService.get(id);
		BlogVo = blogService.get(uservo.getNo());
		List<CategoryVo> categoryVo = categoryVo=categoryService.get(uservo.getNo());
		
		
		
		model.addAttribute("BlogVo", BlogVo);
		model.addAttribute("UserVo", uservo);
		model.addAttribute("Categorylist", categoryVo);
		
		
		return "blog/blog-admin-category";
	}
	
	
	@RequestMapping(value="/category/{id}", method=RequestMethod.POST)
	public String  category(
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
		
		
		
		return "redirect:/blog/category/{id}";
	}
	
	@RequestMapping(value = "/category/delete/{id}/{no}", method = RequestMethod.GET)
	public String categorydelete(Model model, UserVo uservo, BlogVo BlogVo, @PathVariable("no") Long no,
			@PathVariable("id") String id) {

		categoryService.delete(no);

		return "redirect:/blog/category/{id}";
	}

	
	
	@RequestMapping(value = "/write/{id}", method = RequestMethod.GET)
	public String write(Model model, UserVo uservo, BlogVo BlogVo, 
			@PathVariable("id") String id) {

	
		
		uservo = userService.get(id);
		BlogVo = blogService.get(uservo.getNo());
		List<CategoryVo> categoryVo = categoryVo=categoryService.get(uservo.getNo());
		
		model.addAttribute("BlogVo", BlogVo);
		model.addAttribute("UserVo", uservo);
		model.addAttribute("Categorylist", categoryVo);

		return "blog/blog-admin-write";
	}
	
	
	@RequestMapping(value="/write/{id}", method=RequestMethod.POST)
	public String write(
			@ModelAttribute PostVo postvo,
			 UserVo uservo,
			@PathVariable("id") String id,
			Model model
			){
		
		uservo = userService.get(id);
		
		BlogVo BlogVo = blogService.get(uservo.getNo()); //user_id 가지고 오기 
		
		postService.insert(postvo);
	
		
		
		return "redirect:/blog/write/{id}";
	}

	@RequestMapping("/category/view/{id}/{no}")
	public String categoryview(Model model, BlogVo BlogVo, PostVo mainvo, @PathVariable("id") String id, @PathVariable("no") Long no) {
		
		
		UserVo uservo = userService.get(id);
		BlogVo = blogService.get(uservo.getNo());

		List<CategoryVo> categoryVo = categoryService.get(uservo.getNo());
		
		List<PostVo> postvo = postService.CagetList(no, uservo.getNo());
		

		mainvo = postService.CagetOne(no, uservo.getNo());
		
	
		
		model.addAttribute("BlogVo", BlogVo);
		model.addAttribute("Categorylist", categoryVo);
		model.addAttribute("UserVo", uservo);
		model.addAttribute("Postlist", postvo);
		model.addAttribute("MainPost", mainvo);
		
		

		return "blog/blog-main";
	}
	
	
}
