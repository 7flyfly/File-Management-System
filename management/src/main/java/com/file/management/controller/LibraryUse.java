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

    @RequestMapping("/LibraryUse/Check")
    public String Check(){
        return "LibraryUse/Check";
    }
    @RequestMapping("/LibraryUse/Examine")
    public String Examine(){
        return "LibraryUse/Examine";
    }

    @RequestMapping("/LibraryUse/Query")
    public String Query(){
        return "LibraryUse/Query";
    }
}
