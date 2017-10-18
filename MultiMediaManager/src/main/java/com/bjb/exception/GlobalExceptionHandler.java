package com.bjb.exception;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjb.model.ApiResponse;

/**
 * 全局异常处理类
 * controller切面逻辑类
 * @author liuli
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	/**
	 * Log4j控制台输出
	 * 请查看Log4j配置文件，详细Log4j appender请查看log4j官方文档
	 */
	public static final Logger logger = Logger.getRootLogger();
	
	/**
	 * 异常处理
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ApiResponse defaultErrorHandler(Exception e){
		
		logger.error("全局异常处理开始......");
		//全局异常处理返回值类型更变 PENGSZ 20161102 STR
//		WhException errorInfo=new WhException();
//		errorInfo.setMsg(getErrorMessage(e));
//		errorInfo.setStatus(404);//自定义
		ApiResponse ret = null;
		ret = ApiResponse.error(getErrorMessage(e)).put("status", 404);
		return ret;
		//全局异常处理返回值类型更变 PENGSZ 20161102 END
	}
	
	/*
	 * 获取错误信息
	 */
	public String getErrorMessage(Exception ex){
		
		logger.error("全局异常明细分析......");
		String result="";
		if(ex instanceof SQLException){
			result="数据库操作错误："+ex.getMessage();
		}else if(ex instanceof NullPointerException){
			result="空指针异常："+ex.getMessage();
		}else if(ex instanceof NoSuchMethodException){
			result="方法名错误："+ex.getMessage();
		}else if(ex instanceof IOException){
			result="输入输出流错误："+ex.getMessage();
		}else if(ex instanceof ClassCastException){
			result="类型强制转化错误："+ex.getMessage();
		}else if(ex instanceof Exception){
			result="程序内部发生错误："+ex.getMessage();
		}
		logger.error("后台发生错误，错误信息：" + ex.getMessage());
		
		return result;
	}
}
