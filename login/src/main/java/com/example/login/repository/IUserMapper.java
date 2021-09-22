package com.example.login.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.login.vo.UserVO;

@Mapper
public interface IUserMapper {
	
	//회원정보 등록
	void insert(UserVO vo);
	
	//아이디 중복확인
	int checkId(String id);
	
	//회원 탈퇴
	void delete(String id);
	
	//회원정보 조회
	UserVO getUser(String id);
	
	//회원정보 전체조회
	List<UserVO> getAllUser();
	
	//특정 회원의 세션아이디와 쿠키 유효기간을 저장
	void autoLogin(Map<String,Object> map);
	
	//세션아이디로 회원조회
	UserVO selectSession(String sessionId);
}
