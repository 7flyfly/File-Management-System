package com.file.management.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;


/*
  综合查询
 */
@Controller
@RequestMapping("/IntegratedQuery")
public class IntegratedQuery {

    @RequestMapping("/QueryHomePage")
    public String integratedQuery(){
        return "IntegratedQuery/ControlCenter";
    }

    @RequestMapping("/IntelligentRetrieval")
    public String intelligentRetrieval(){
        return "IntegratedQuery/IntelligentRetrieval";
    }

    @RequestMapping("/AdvancedSearch")
    public String advancedSearch(){
        return "IntegratedQuery/AdvancedSearch";
    }

    @RequestMapping("/GetKeyWord")
    @ResponseBody
    public String getKeyWord(String keyword){
        System.out.println("keyword:"+keyword);
//        String keyword1 = request.getParameter("keyword");
//        System.out.println("keyword:"+keyword1);
        return keyword;
    }



}
