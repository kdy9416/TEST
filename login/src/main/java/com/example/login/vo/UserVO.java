package com.example.login.vo;

import java.util.Date;

public class UserVO {
	private String id;
	private String password;
	private String name;
	private Date joinDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return password;
	}
	public void setPw(String pw) {
		this.password = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	@Override
	public String toString() {
		return "UserVO [id=" + id + ", pw=" + password + ", name=" + name + ", joinDate=" + joinDate + "]";
	}
	
	
	
	
}
