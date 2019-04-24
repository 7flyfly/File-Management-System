package com.file.management.controller.Library;

import com.alibaba.fastjson.JSONObject;
import com.file.management.pojo.RegistationExamine;
import com.file.management.service.LibraryUse.ExamineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
/*
 利用审批
 */
@Controller
public class ExamineController {
    @Autowired
    ExamineService examineService;

    @ResponseBody
    @RequestMapping("/registrat")
    public String findAll(HttpServletResponse response){
        response.setContentType("texy/json");
        response.setCharacterEncoding("utf-8");
        List<RegistationExamine> examines=examineService.findAll();
        int total=examines.size();
        JSONObject result= new JSONObject();
        result.put("rows",examines);
        result.put("total",total);
        return result.toJSONString();
    }
}
