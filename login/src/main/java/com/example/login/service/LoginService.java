package com.example.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.login.repository.IUserMapper;
import com.example.login.vo.UserVO;

public class LoginService implements ILoginService {

	@Autowired
	IUserMapper mapper;
	
	@Override
	public void insert(UserVO vo) {
		
		mapper.insert(vo);
	}

	@Override
	public void delete(String id) {
		
		mapper.delete(id);
	}

	@Override
	public List<UserVO> getUser(String id) {
		
		return mapper.getUser(id);
	}

}
