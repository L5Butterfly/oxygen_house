package com.mooc.house.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mooc.house.mapper.UserMapper;
import com.mooc.house.model.User;

/**
 * @package: com.mooc.house.service
 * @file: UserService.java
 * @description: TODO
 * @author: empathy
 * @date: 2018年10月4日 下午12:57:26
 * @version: v1.0
 */

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 获取所有用户信息
	 * @return
	 */
	public List<User> getUesrs() {
		return userMapper.selectUsers();
	}
	
	/**
	 * 通过用户编码获取用户信息
	 * @return
	 */
	public User getUesrById(User user) {
		return userMapper.queryUserById(user.getId());
	}
}
