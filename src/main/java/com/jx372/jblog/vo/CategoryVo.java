package com.jx372.jblog.vo;

public class CategoryVo {
	
	
	private Long no;
	private Long user_no;
	private String name;
	private String description;
	private String reg_date;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getUser_no() {
		return user_no;
	}
	public void setUser_no(Long user_no) {
		this.user_no = user_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", user_no=" + user_no + ", name=" + name + ", description=" + description
				+ ", reg_date=" + reg_date + "]";
	}
	
	
	

}
