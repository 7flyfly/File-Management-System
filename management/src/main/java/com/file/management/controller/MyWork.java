package com.file.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/*
 我的工作
 */
@Controller
public class MyWork {

    @RequestMapping("/mywork")
    public String myWork(){
        return "mywork";
    }

    @RequestMapping("/main")
    public String main(){
        return  "main";
    }

    @RequestMapping("/emps")
    public String emps(){
        return  "emps";
    }
}
