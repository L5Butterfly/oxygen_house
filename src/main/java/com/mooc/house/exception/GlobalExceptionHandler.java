package com.mooc.house.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alibaba.fastjson.JSONObject;

/**
 * @package: com.mooc.house.exception
 * @file: GlobalExceptionHandler.java
 * @description: 使用@ExceptionHandler和@ControllerAdvice能够集中异常，使异常处理与业务逻辑分离
 * @author: empathy
 * @date: 2018年10月5日 下午10:17:38
 * @version: v1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler extends Exception {
	
	/**
	 * spring boot ControllerAdvice+ExceptionHandler全局捕获异常
	 */
	private static final long serialVersionUID = -3207375931176191878L;
	
	private final Logger logger= LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	//可以直接写@ExceptionHandler,不指明异常类，会自动映射,//还可以声明接收其他任意参数
 	@ExceptionHandler(CustomException.class)
    public String customGenericExceptionHnadler(CustomException exception){ 
 		JSONObject json=new JSONObject();
 		json.put("errCode",exception.getErrCode());
 		json.put("errMsg",exception.getErrMsg());
 		logger.info("customer msg:-----"+json.toJSONString());
        return null;
    }

 	
 	//可以直接写@EceptionHandler，IOExeption继承于Exception
    @ExceptionHandler(BusinessException.class)
    public String allExceptionHandler(BusinessException exception){
    	JSONObject json=new JSONObject();
 		json.put("errCode",exception.getErrCode());
 		json.put("errMsg",exception.getErrMsg());
 		logger.info("bussiness msg:-----"+json.toJSONString());
        return null;
    }
    
    
    //未知异常处理
    @ExceptionHandler(value={Exception.class,RuntimeException.class})
    public String allExceptionHandler(Exception exception){
    	JSONObject json=new JSONObject();
 		json.put("errCode","404");
 		json.put("errMsg",exception.getMessage());
 		logger.info("exception msg:-----"+json.toJSONString());
        return null;
    }
}
