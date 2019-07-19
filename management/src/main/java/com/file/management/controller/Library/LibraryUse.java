package com.file.management.controller.Library;


import com.file.management.dao.SystemManage.Dictionary.DictionaryDao;
import com.file.management.service.LibraryUse.RegistrationFormService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
    @Autowired
    private DictionaryDao dictionaryDao;

    @RequestMapping("/borrow")
    public String borrow(){
        return "Library/controlCenter";
    }

    @RequestMapping("/LibraryUse/Check")
    @RequiresPermissions("LibraryUse:Check")//权限管理;
    public String Check(Model model){

//        List<RegistrationForm> formList=registrationFormService.findAll();
//        model.addAttribute("contentList",formList);
        return "LibraryUse/Check";
    }
    @RequestMapping("/LibraryUse/Examine")
    @RequiresPermissions("LibraryUse:Examine")//权限管理;
    public String Examine(){
        return "LibraryUse/Examine";
    }

    @RequestMapping("/LibraryUse/Query")
    @RequiresPermissions("LibraryUse:Query")//权限管理;
    public String Query(){
        return "LibraryUse/Query";
    }
}
