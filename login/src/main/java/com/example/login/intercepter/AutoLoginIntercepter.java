package com.example.login.intercepter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.example.login.service.LoginService;

@SuppressWarnings("deprecation")
public class AutoLoginIntercepter extends HandlerInterceptorAdapter{
	
	@Autowired
	LoginService service;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		 HttpSession session = request.getSession();
		 
		 Cookie cookie = WebUtils.getCookie(request,"loginCookie");
		 if(cookie!=null) {
			 String cookieId = cookie.getValue();
			 
		 }
		
		return true;
	}
}
