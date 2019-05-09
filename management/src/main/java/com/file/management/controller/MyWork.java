package com.file.management.controller;

import com.file.management.dao.FileResposity;
import com.file.management.pojo.ExpiredFile;
import com.file.management.pojo.state.Message;
import com.file.management.service.state.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;


/*
 我的工作
 */
@Controller
public class MyWork {

    @Autowired
    private MessageService messageService;
    @RequestMapping("/mywork")
    public String myWork( Map<String,Object> map){
        List<Message> lm = messageService.findAll();
        int num = lm.size()-5;
        for(int i=1;i<=5;i++){
            Message m = messageService.findById(num+i);
            if(m.getMessage()!=null){
                String str = "mes0"+i;
                map.put(str,m.getMessage());
            }
        }
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

    @RequestMapping("/register01")
    public  String register01(){
        return "register";
    }

    @RequestMapping("/MyWork/ExpiredFile")
    public  String ExpiredFile(){
        return "MyWork/ExpiredFile";
    }

    @RequestMapping("/MyWork/FileCount")
    public  String FileCount(){
        return "MyWork/FileCount";
    }


    @Autowired
    private FileResposity fileResposity;
    @RequestMapping(value = "/MyWork/MatureFile")
    public String FileManagement(Model model){
        List<ExpiredFile> list = fileResposity.findAll();
        model.addAttribute("filelist",list);
        return "/MyWork/MatureFile";
    }
}
