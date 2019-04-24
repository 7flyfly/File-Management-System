package com.file.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/*
  专题编研
 */
@Controller
public class SpecialTopic {

    @RequestMapping("/SpecialTopic/Special")
    public String specialPage(){
        return "SpecialTopic/Special";
    }
}
