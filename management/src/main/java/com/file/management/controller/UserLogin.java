package com.file.management.controller;

import com.file.management.pojo.User;
import com.file.management.service.UserService;
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

    @RequestMapping("/user/login")
    public String Login(HttpServletRequest request, Map<String,Object>map, HttpServletResponse response, HttpSession session){

        try {
             String username = request.getParameter("username");
             String password = request.getParameter("password");

            Cookie cookie = new Cookie("username",username);
            cookie.setPath("/");
            cookie.setMaxAge(3600);
            response.addCookie(cookie);

            User user=userService.findUser(username,password);
            if (user != null){
                session.setAttribute("username",username);
                return "mywork/homepage";
            }else {
                map.put("msg","账户或密码错误");
                return "redirect:/index";
            }
        }catch (Exception e){
          System.out.println(e);
        }
        return "login";
    }


}
