package com.file.management.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/*
  综合查询
 */
@Controller
public class IntegratedQuery {



    @RequestMapping("/AdvancedSearch")
    public String AdvancedSearch(){
        return "IntegratedQuery/AdvancedSearch";
    }

    @RequestMapping("/IntelligentRetrieval")
    public String IntelligentRetrieval(){
        return "IntegratedQuery/IntelligentRetrieval";
    }




}
