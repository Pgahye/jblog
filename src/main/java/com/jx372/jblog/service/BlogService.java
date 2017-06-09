package com.jx372.jblog.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jx372.jblog.repository.BlogDao;
import com.jx372.jblog.vo.BlogVo;
import com.jx372.jblog.vo.CategoryVo;
import com.jx372.jblog.vo.UserVo;





@Service
public class BlogService {
	
	@Autowired
	private BlogDao blogDao;
	
	private static final String SAVE_PATH = "/uploads"; //c드라이브 안붙인 이유는 루트 디렉토리에 만듬   
	private static final String PREFIX_URL = "/uploads/images/";
	
	
public void update(BlogVo vo) {
		
		blogDao.update(vo);
	}

	
	
	public void join(UserVo userVo){
		
		// 1.db에 사용정보 저장
		
		blogDao.insertblog(userVo);
		
		
		// 2. 인증메일보내기 
		
		
	}
	

	public BlogVo get (Long user_no){
		
		return blogDao.get(user_no);
		
	}
	
	public String restore(MultipartFile multipartFile) {
	
		
		String url="";
		
		
		try{
		
	
		if(multipartFile.isEmpty()==true){
			
			return url;
			
		}
		
		String originalFileName = multipartFile.getOriginalFilename();
		
		String extName = originalFileName.substring(originalFileName.lastIndexOf('.'), originalFileName.length());
		Long fileSize = multipartFile.getSize();
		String saveFileName = gensaveFileName(extName);
	
		//System.out.println("#########"+originalFileName);
		//System.out.println("#########"+extName);
		//System.out.println("#########"+fileSize);
		//System.out.println("#########"+saveFileName);
		
		writeFile(multipartFile, saveFileName);
		
		url=PREFIX_URL+saveFileName;
		
		} catch( IOException e){
			
			throw new RuntimeException();
			
		}
		return url;
	}
	
private void writeFile(MultipartFile multipartFile, String saveFileName) throws IOException{
		
		byte[] fileData = multipartFile.getBytes();
		
		FileOutputStream fos = new FileOutputStream( SAVE_PATH+ "/"+saveFileName);
		
		fos.write(fileData);
		
		fos.close();
		
	}
	
	
	private String gensaveFileName(String extName){
		
		String fileName= "";
		
		Calendar calendar =Calendar.getInstance();
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += extName;
		
		return fileName;
		
		
	}
	


}
