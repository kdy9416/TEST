package com.example.login.vo;

import java.util.Date;

public class UserVO {
	private String id;
	private String password;
	private String name;
	private Date joinDate;
	private boolean autoLogin;
	private String sessionId;
	private Date limitDate;
	
	
	
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Date getLimitDate() {
		return limitDate;
	}

	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}

	public boolean isAutoLogin() {
		return autoLogin;
	}
	
	public void setAutoLogin(boolean autoLogin) {
		this.autoLogin = autoLogin;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
		return "UserVO [id=" + id + ", password=" + password + ", name=" + name + ", joinDate=" + joinDate
				+ ", autoLogin=" + autoLogin + ", sessionId=" + sessionId + ", limitDate=" + limitDate + "]";
	}

	
}
