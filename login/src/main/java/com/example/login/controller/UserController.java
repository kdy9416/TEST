package com.example.login.controller;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.example.login.service.LoginService;
import com.example.login.vo.UserVO;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	LoginService service;
	
	//회원등록
	@PostMapping("/")
	public String join(@RequestBody UserVO vo) {
		service.insert(vo);
		System.out.println(vo);
		return "joinSuccess";
	}
	

	//회원조회
	@GetMapping("/{id}")
	public UserVO selectUser(@PathVariable String id) {
		
		return service.getUser(id);
	}
	
	//회원탈퇴
	@DeleteMapping("/{id}")
	public String delete(@PathVariable String id) {
		service.delete(id);
		return "deleteSuccess";
	}
	
	//로그인구현
	@PostMapping("/loginCheck")
	public String login(@RequestBody UserVO input , HttpSession session ,HttpServletResponse response) {
		
		BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
		System.out.println(input);
		String result = null;
		UserVO db = service.getUser(input.getId());
		if(db != null) {
			if(encode.matches(input.getPassword(),db.getPassword())) {
				session.setAttribute("login",db);
				
				//자동로그인을 체크했을시에 실행
				if(input.isAutoLogin()) {
					
					//3개월뒤의 초
					long second = 60 * 60 * 24 * 90;
					
					//쿠키생성
					Cookie cookie = new Cookie("loginCookie",session.getId());
					cookie.setPath("/");
					cookie.setMaxAge((int)second);
				    response.addCookie(cookie);
				    
				    //3개월뒤의 밀리초를 날짜로 변환
				    long millis = System.currentTimeMillis() + (second * 1000); 
				    Date limitDate = new Date(millis);
				    System.out.println(limitDate);
				    
				    //DB에 세션아이디,쿠키만료날짜,회원 아이디 전달
				    service.autoLogin(session.getId(),limitDate,input.getId());
				}
				result = "loginSuccess";
			}else {
				result = "pwFail";
			}
		}else {
			result = "idFail";
		}
		System.out.println(result);
		return result;
	}
	
	//로그아웃처리
	@PostMapping("/logout")
	public String logout(HttpSession session , HttpServletRequest request) {
		
		UserVO user = (UserVO)session.getAttribute("login");
		if(user!= null) {
			session.removeAttribute("login"); 
			session.invalidate();
			Cookie cookie = WebUtils.getCookie(request,"loginCookie");
			
			//자동로그인을 한 상태의 사용자가 로그아웃을 할 경우
			if(cookie != null) {
				cookie.setMaxAge(0);
				service.autoLogin("none",new Date(),user.getId());
			}
		}
		return "logoutSuccess";
	}
	
	//아이디 중복확인
	@PostMapping("/check")
	public String check(@RequestBody String id) {
		String result = null;
		int num = service.checkId(id);
		System.out.println(num);
		//변수 num이 1일경우 아이디 중복 0일경우 아이디 등록가능
		if(num == 1) {
			System.out.println("아이디가 중복됨");
			result="Fail";
		}else {
			System.out.println("아이디사용가능");
			result="Success";
		}
		return result;
	}
	
	
	
	
}
