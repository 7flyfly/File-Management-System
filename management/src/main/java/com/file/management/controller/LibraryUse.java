package com.file.management.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * 借阅利用
 */
@Controller
public class LibraryUse {

    @RequestMapping("/borrow")
    public String borrow(){
        return "LibraryUse/controlCenter";
    }

    @RequestMapping("/check")
    public String Check(){
        return "LibraryUse/check";
    }
    @RequestMapping("/examine")
    public String Examine(){
        return "LibraryUse/examine";
    }

    @RequestMapping("/query")
    public String Query(){
        return "LibraryUse/query";
    }
}
