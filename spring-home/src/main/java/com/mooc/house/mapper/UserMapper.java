package com.mooc.house.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mooc.house.model.User;

/**
 * @package: com.mooc.house.mapper
 * @file: UserMapper.java
 * @description: TODO
 * @author: empathy
 * @date: 2018年10月4日 下午1:02:22
 * @version: v1.0
 */

// 从mybatis3.4.0开始加入了@Mapper注解，目的就是为了不再写mapper映射文件(那个xml写入相关配置,十分麻烦)
// Mapper数据库访问接口相当于Dao层的接口，和Mapper的xml文件一一映射关系

@Mapper
public interface UserMapper {
	
	/**
	 * 用户信息
	 * @return
	 */
	public List<User> selectUsers();
	
	public int insert(User account);

	public int delete(String email);

	public int update(User updateUser);

	public List<User> selectUsersByQuery(User user);
	
	public User queryUserById(Long id);

}
