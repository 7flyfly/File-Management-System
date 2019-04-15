package com.file.management.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 *自定义拦截器
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginUser=request.getSession().getAttribute("username");
        if (loginUser!=null){
            //登录，放行
            return true;
        }
        //没有登录
        request.setAttribute("msg","没有权限，请先登录");
        //跳转到登录界面
        request.getRequestDispatcher("/index").forward(request,response);
        return false;
    }
}
