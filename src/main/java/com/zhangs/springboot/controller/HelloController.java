package com.zhangs.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author ZhangS
 * @create 2020-09-11-15:34
 */

@Controller
public class HelloController {

	@ResponseBody
	@RequestMapping("/hello")
	public String hello(){

		return "Hello World!";
	}

	@RequestMapping("/success")
	public String success(Map<String,Object> map){

		map.put("hello","hello");
		return "success";
	}


}
