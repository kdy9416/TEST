package com.example.login.repository;

import java.util.List;

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
	
}
