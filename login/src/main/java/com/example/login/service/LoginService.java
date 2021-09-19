package com.example.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.login.repository.IUserMapper;
import com.example.login.vo.UserVO;

@Service
public class LoginService implements ILoginService {

	@Autowired
	IUserMapper mapper;
	
	@Override
	public void insert(UserVO vo) {
		
		mapper.insert(vo);
	}

	@Override
	public int checkId(String id) {
		
		return mapper.checkId(id);
	}
	
	@Override
	public void delete(String id) {
		
		mapper.delete(id);
	}

	@Override
	public UserVO getUser(String id) {
		
		return mapper.getUser(id);
	}



}
