package com.mooc.house.exception;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

/**
 * @package: com.mooc.house.exception
 * @file: GlobalExceptionHandler.java
 * @description: 使用@ExceptionHandler和@ControllerAdvice能够集中异常，使异常处理与业务逻辑分离
 * @author: empathy
 * @date: 2018年10月5日 下午10:17:38
 * @version: v1.0
 */

//开启全局捕获异常
@ControllerAdvice
public class GlobalExceptionHandler extends Exception {
	
	/**
	 * spring boot ControllerAdvice+ExceptionHandler全局捕获异常
	 */
	private static final long serialVersionUID = -3207375931176191878L;
	
	private final Logger logger= LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	/**
	 * 可以直接写@ExceptionHandler,不指明异常类，会自动映射,//还可以声明接收其他任意参数
	 * @param exception
	 * @return
	 */
 	@ExceptionHandler(CustomException.class)
    public String customGenericExceptionHnadler(CustomException exception){ 
 		JSONObject json=new JSONObject();
 		json.put("errCode",exception.getErrCode());
 		json.put("errMsg",exception.getErrMsg());
 		logger.info("customer msg:-----"+json.toJSONString());
        return null;
    }

 	
 	/**
 	 * 可以直接写@EceptionHandler，IOExeption继承于Exception
 	 * @param exception
 	 * @return
 	 */
    @ExceptionHandler(BusinessException.class)
    public String allExceptionHandler(BusinessException exception){
    	JSONObject json=new JSONObject();
 		json.put("errCode",exception.getErrCode());
 		json.put("errMsg",exception.getErrMsg());
 		logger.info("bussiness msg:-----"+json.toJSONString());
        return null;
    }
    
    
    /**
     * 未知异常处理
     * @param exception
     * @return
     */
    @ExceptionHandler(value={Exception.class,RuntimeException.class})
    public String allExceptionHandler(Exception exception){
    	JSONObject json=new JSONObject();
 		json.put("errCode","404");
 		json.put("errMsg",exception.getMessage());
 		logger.info("exception msg:-----"+json.toJSONString());
        return null;
    }
    

    /**
     * 如果是跳转到错误页面就直接写页面地址，返回String跳转到页面。
     * @return
     */
    @ExceptionHandler(IOException.class)//拦截所有运行时异常
    //@ResponseBody //ResponseBody返回json。
    public Map<String,Object> errorMap(){
        Map<String,Object> result=new HashMap<String ,Object>();
        result.put("errorCode","500");
        result.put("errorMsg","系统异常");
        logger.info("exception info:-----"+result);
        return result;
    }
 
}
