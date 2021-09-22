package com.example.login.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.login.repository.IUserMapper;
import com.example.login.vo.UserVO;

@Service
public class LoginService implements ILoginService {

	@Autowired
	IUserMapper mapper;
	
	@Override
	public void insert(UserVO vo) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println("암호화 전: " + vo.getPassword());
		String securePw = encoder.encode(vo.getPassword());
		vo.setPassword(securePw);
		System.out.println("암호화 후: " + securePw);
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

	@Override
	public List<UserVO> getAllUser() {
		return null;
	}

	@Override
	public void autoLogin(String sessionId,Date limitDate,String id) {
		
		Map<String,Object> map = new HashMap<>();
		map.put("sessionId",sessionId);
		map.put("limitDate",limitDate);
		map.put("id",id);
		System.out.print(map);
		
		mapper.autoLogin(map);
		
	}

	@Override
	public UserVO selectSession(String sessionId) {
		return mapper.selectSession(sessionId);
	}



}
