package com.mooc.house.dao;

import java.util.List;

import com.mooc.house.model.User;

/**
 * @package: com.mooc.house.dao
 * @file: UserDao.java
 * @description: TODO
 * @author: empathy
 * @date: 2018年10月4日 下午6:23:59
 * @version: v1.0
 */
public interface UserDao {
	/**
	 * 用户信息
	 * @return
	 */
	public List<User> selectUsers();

}
