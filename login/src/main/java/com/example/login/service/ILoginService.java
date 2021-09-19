package com.example.login.service;

import java.util.List;

import com.example.login.vo.UserVO;

public interface ILoginService {
	//회원정보 등록
		void insert(UserVO vo);
		
		//회원 탈퇴
		void delete(String id);
		
		//회원정보 조회
		List<UserVO> getUser(String id);
		
}
