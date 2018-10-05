package com.mooc.house.exception;

/**
 * @package: com.mooc.house.exception
 * @file: BusinessException.java
 * @description: 业务异常（@ExceptionHandler和@ControllerAdvice能够集中异常，使异常处理与业务逻辑分离）
 * @author: empathy
 * @date: 2018年10月5日 下午10:19:04
 * @version: v1.0
 */
public class BusinessException extends Exception {
	
	private static final long serialVersionUID = 1L;

    private String errCode;
    
    private String errMsg;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public BusinessException(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

}
