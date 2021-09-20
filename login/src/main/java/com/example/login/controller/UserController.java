package com.example.login.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
	public String login(@RequestBody UserVO input , HttpSession session ) {
		
		BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
		System.out.println(input);
		String result = "널";
		UserVO db = service.getUser(input.getId());
		if(db != null) {
			if(encode.matches(input.getPassword(),db.getPassword())) {
				session.setAttribute("login",db);
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
	public ModelAndView logout(HttpSession session) {
		
		if(session.getAttribute("login") != null) {
			session.removeAttribute("login"); 
			session.invalidate();
		}
		return new ModelAndView("redirect:/");
	}
	
	
}
