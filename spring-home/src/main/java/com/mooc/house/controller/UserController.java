package com.mooc.house.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.mooc.house.exception.BusinessException;
import com.mooc.house.model.User;
import com.mooc.house.service.UserService;

/**
 * @package: com.mooc.house.controller
 * @file: UserController.java
 * @description: TODO
 * @author: empathy
 * @date: 2018年10月4日 下午12:56:21
 * @version: v1.0
 */

@RestController
@RequestMapping("user")
public class UserController {
	
	private Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

	/**
	 * 获取用户信息
	 * 请求方式：GET;不加method = RequestMethod.GET,2种方式都支持
	 * @return
	 */
	//@RequestMapping(value="user",method = RequestMethod.GET)
	@GetMapping("getUsers")
	public List<User> getUesrs() throws Exception{
		//抛出异常
		int flag=0;
		if(flag<1){
			throw new BusinessException("303", "业务异常");
		}
		return userService.getUesrs();
	}
	
	
	 /***
     * GET、POST方式提时， 根据request header Content-Type的值来判断:   
     * application/x-www-form-urlencoded， 可选
     * 即非必须，因为这种情况的数据@RequestParam, @ModelAttribute也可以处理，当然@RequestBody也能处理；   
     * multipart/form-data, 不能处理（即使用@RequestBody不能处理这种格式的数据）；  
     *
     */


    /**
     * 获取参数的几种常用注解
     * url:(user/getUserInfo?id=8)
     * @return
     */
    @RequestMapping(value = "getUserInfo",method = RequestMethod.GET)
    public User getUserInfo(@RequestParam(value = "id",required = false,defaultValue = "0") Long id) {
    	User user=new User();
		user.setId(id);
		return userService.getUesrById(user);
    }
    


	/**
	 * 通过用户编号获取用户信息
	 * 1. 请求参数：POST form表单方式提交
	 * 2. 接收数据：实体类对象-一一映射关系
	 * @param user
	 * @return
	 */
	//@RequestMapping(value = "userById", method = RequestMethod.POST)
	@PostMapping("userById")
	public User gerUserById(User user) {
		//打印日志输出请求参数信息
		log.info("id:"+user.getId());
		log.info("user:"+user.toString());
		return userService.getUesrById(user);
	}
	
	
	/**
	 * 通过用户编号获取用户信息,参数通过url路径传入
	 * 1. 参数：动态Id获取数据
	 * @param user
	 * @return
	 */
	//@GetMapping(value = "getUser/{id}", method = RequestMethod.GET)
	@GetMapping("getUser/{id}")
	public User gerUserById(@PathVariable("id") Long id) {
		//打印日志输出请求参数信息
		User user=new User();
		user.setId(id);
		return userService.getUesrById(user);
	}
	
	
	/**
	 * 通过用户编号获取用户信息(post json传递参数)
	 * 1. 请求参数：POST JSON格式
	 * 2. 实体类对象接收参数
	 * 3. 请求数据类型：application/json;charset=UTF-8
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "userByIdJson", method = RequestMethod.POST)
	//@PostMapping("userByIdJson")
	public User gerUserById3(@RequestBody User user) {
		//打印日志输出请求参数信息
		log.info("id:"+user.getId());
		log.info("user:"+user.toString());
		return userService.getUesrById(user);
	}
	
	
	/**
	 * 通过用户编号获取用户信息
	 * 1. 请求参数：POST JSON格式(application/json;charset=UTF-8)
	 * 2. Map对象接收参数
	 * @param reqMap
	 * @return
	 */
	@RequestMapping(value = "userByMap", method = RequestMethod.POST)
	//@PostMapping("userByMap")
	public User gerUserByMap(@RequestBody Map<String,Object> reqMap) {
		//打印日志输出请求参数信息
		log.info("reqMap:"+reqMap);
		log.info("id:"+reqMap.get("id"));
		User user=new User();
		user.setId((Long.valueOf(reqMap.get("id").toString())));
		return userService.getUesrById(user);
	}
	
	
	/**
     * 功能描述:通过HttpServletRequest 的方式来获取到json的数据
     * 1.通过Request获取:通过request的对象来获取到输入流，然后将输入流的数据写入到字符串里面，最后转化为JSON对象。
     * 2.post json可以不指定数据格式为：application/json;charset=UTF-8
     * @param request
     * @return
     */
    //@RequestMapping(value = "/getJson", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @PostMapping(value = "getJson",produces = "application/json;charset=UTF-8")
    public String getByRequest(HttpServletRequest request) {

        //获取到JSONObject
        JSONObject jsonParam = this.getJSONParam(request);
        User user=new User();
		user.setId(jsonParam.getLong("id"));
		
		// 将获取的json数据封装一层，然后在给返回
        JSONObject result = new JSONObject();
        
		result.put("code", "200");
		result.put("msg", "ok");
        result.put("method", request.getMethod());
        result.put("data", userService.getUesrById(user));
        return result.toJSONString();
    }
    
	
	
	/**
     * 功能描述:通过request来获取到json数据
     * @param request
     * @return
     */
    public JSONObject getJSONParam(HttpServletRequest request){
        JSONObject jsonParam = null;
        try {
            // 获取输入流
            InputStreamReader inputStream=new InputStreamReader(request.getInputStream(), "UTF-8");
            //字符流
            BufferedReader streamReader = new BufferedReader(inputStream);
            // 写入数据到StringBuilder
            StringBuilder sb = new StringBuilder();
            String line = null;
            //逐行读取字符流
            while ((line = streamReader.readLine()) != null) {
                sb.append(line);
            }
            jsonParam = JSONObject.parseObject(sb.toString());
            //Json信息输出
            log.info(jsonParam.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonParam;
    }
    
}
