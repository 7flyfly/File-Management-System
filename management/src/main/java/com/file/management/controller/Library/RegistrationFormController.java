package com.file.management.controller.Library;

import com.file.management.pojo.RegistrationForm;
import com.file.management.service.LibraryUse.RegistrationFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RegistrationFormController {
    @Autowired
    RegistrationFormService registrationFormService;

    @ResponseBody
    @RequestMapping("/registration")
    public void postRegistration(Model model){
        List<RegistrationForm>  formList= registrationFormService.findAll();
        model.addAttribute("contentList",formList);

    }
}
