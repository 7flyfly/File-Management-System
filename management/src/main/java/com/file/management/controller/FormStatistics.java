package com.file.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
*报表统计
 */
@Controller
public class FormStatistics {

    /*
     *汇总统计
     */
    @RequestMapping("/FormStatistics/TabulateStatistics")
    public String Tabulate(){
        return "FormStatistics/TabulateStatistics";
    }

    /*
     *分项统计
     */
    @RequestMapping("/FormStatistics/Breakdown")
    public String Breakdown(){
        return "/FormStatistics/Breakdown";
    }
}
