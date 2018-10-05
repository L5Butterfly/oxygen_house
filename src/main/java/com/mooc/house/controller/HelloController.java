package com.mooc.house.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mooc.house.model.User;
import com.mooc.house.service.UserService;

/**
 * @package: com.mooc.house.controller
 * @file: HelloController.java
 * @description: TODO
 * @author: empathy
 * @date: 2018年10月5日 上午11:56:59
 * @version: v1.0
 */

@Controller
@RequestMapping("hello")
public class HelloController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("hello")
	public String getUesrs(ModelMap modeMap) {
		List<User> users=userService.getUesrs();
		User user=users.get(0);
		modeMap.put("user", user);
		return "hello";
	}

}
