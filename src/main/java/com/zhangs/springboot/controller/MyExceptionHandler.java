package com.zhangs.springboot.controller;

import com.zhangs.springboot.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhangS
 * @create 2020-09-12-18:53
 */
@ControllerAdvice
public class MyExceptionHandler {

	//第一种方式：用这种方式处理异常，浏览器和客户端则都会返回json数据
//	@ResponseBody
//	@ExceptionHandler(UserNotExistException.class)
//	public Map<String, Object> handleException(Exception e){
//
//		Map<String,Object> map = new HashMap<>();
//		map.put("code","user.notexist");
//		map.put("message",e.getMessage());
//
//		return map;
//
//	}

	@ExceptionHandler(UserNotExistException.class)
	public String handleException(Exception e, HttpServletRequest request){

		Map<String,Object> map = new HashMap<>();
//		传入我们自己的错误状态码
		request.setAttribute("javax.servlet.error.status_code",400);
		map.put("code","user.notexist");
		map.put("message",e.getMessage());

//		转发到error文件下
		return "forward:/error";

	}




}
