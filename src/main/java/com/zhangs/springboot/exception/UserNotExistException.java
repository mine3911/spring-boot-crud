package com.zhangs.springboot.exception;

/**
 * @author ZhangS
 * @create 2020-09-12-18:48
 */
public class UserNotExistException extends RuntimeException{

	public UserNotExistException(){
		super("用户不存在");
	}

}
