package com.zhangs.springboot.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ZhangS
 * @create 2020-09-11-22:00
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

//	目标方法执行之前
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String loginUser = (String) request.getSession().getAttribute("loginUser");
		if(loginUser == null){
			//未登录,返回登录页面
			request.setAttribute("msg","没有权限查看！请先登录");
			request.getRequestDispatcher("/").forward(request,response);
			return false;
		}else{
			//已登录
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}
}
