package com.file.management.controller;

import com.file.management.pojo.User;
import com.file.management.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class UserLogin {
    @Autowired
    private UserService userService;
    @RequestMapping("/index")
    public String index(){
        return "login";
    }

    //@RequestMapping("/user/login")
    @RequestMapping("/login")
    public String Login(HttpServletRequest request, Map<String,Object>map, HttpServletResponse response, HttpSession session){

        System.out.println("UserLogin.login()");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //1.获取subject
        Subject subject = SecurityUtils.getSubject();
        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        //3.执行登录方法
        try{
            subject.login(token);
            Cookie cookie = new Cookie("username",username);
            cookie.setPath("/");
            cookie.setMaxAge(3600);
            session.setAttribute("username",username);
            response.addCookie(cookie);
            return "redirect:/mywork/homepage";
        }catch (UnknownAccountException e){
            map.put("msg", "用户名不存在");
            return "/index";
        }catch (IncorrectCredentialsException e){
            map.put("msg", "密码错误");
            return "/index";
        }

        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
//        String exception = (String) request.getAttribute("shiroLoginFailure");
//        System.out.println("exception=" + exception);
//        String msg = "";
//        if (exception != null) {
//            if (UnknownAccountException.class.getName().equals(exception)) {
//                System.out.println("UnknownAccountException -- > 账号不存在：");
//                msg = "UnknownAccountException -- > 账号不存在：";
//            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
//                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
//                msg = "IncorrectCredentialsException -- > 密码不正确：";
//            } else if ("kaptchaValidateFailed".equals(exception)) {
//                System.out.println("kaptchaValidateFailed -- > 验证码错误");
//                msg = "kaptchaValidateFailed -- > 验证码错误";
//            } else {
//                msg = "else >> "+exception;
//                System.out.println("else -- >" + exception);
//            }
//        }
//        map.put("msg", msg);
        // 此方法不处理登录成功,由shiro进行处理

//        try {
//             String username = request.getParameter("username");
//             String password = request.getParameter("password");
//
//            Cookie cookie = new Cookie("username",username);
//            cookie.setPath("/");
//            cookie.setMaxAge(3600);
//            response.addCookie(cookie);
//
//
//            User user=userService.findUser(username,password);
//            if (user != null){
//                session.setAttribute("username",username);
//                return "mywork/homepage";
//            }else {
//                map.put("msg","账户或密码错误");
//                return "redirect:/index";
//            }
//        }catch (Exception e){
//          System.out.println(e);
//        }
        //return "login";
    }


}
