package com.file.management.controller.Library;


import com.file.management.service.LibraryUse.RegistrationFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * 借阅利用
 */
@Controller
public class LibraryUse {

    @Autowired
    RegistrationFormService registrationFormService;
    @RequestMapping("/borrow")
    public String borrow(){
        return "Library/controlCenter";
    }

    @RequestMapping("/LibraryUse/Check")
    public String Check(Model model){

//        List<RegistrationForm> formList=registrationFormService.findAll();
//        model.addAttribute("contentList",formList);
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
