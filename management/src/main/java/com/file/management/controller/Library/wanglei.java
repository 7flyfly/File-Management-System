package com.file.management.controller.Library;

import com.alibaba.fastjson.JSONObject;
import com.file.management.pojo.LibraryUse.RegistrationForm;
import com.file.management.service.LibraryUse.RegistrationFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class wanglei {
    @Autowired
    RegistrationFormService registrationFormService;

    //借阅申请查询
    @ResponseBody
    @RequestMapping("/getApplication")
    public String getApplication(HttpServletResponse response){
        response.setContentType("text/json");
        response.setCharacterEncoding("utf-8");
        List<RegistrationForm> examines=registrationFormService.findByStatus("待审批");
        int total=examines.size();
        JSONObject result= new JSONObject();
        result.put("rows",examines);
        result.put("total",total);
        return result.toJSONString();
    }
}
