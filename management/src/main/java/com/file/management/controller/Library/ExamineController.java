package com.file.management.controller.Library;

import com.alibaba.fastjson.JSONObject;
import com.file.management.dao.SystemManage.Dictionary.DictionaryDao;
import com.file.management.pojo.LibraryUse.RegistrationForm;
import com.file.management.pojo.SystemManagement.Dictionary.DictionaryPojo;
import com.file.management.service.LibraryUse.RegistrationFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/*
 利用审批
 */
@Controller
public class ExamineController {

    @Autowired
    RegistrationFormService registrationFormService;

    @ResponseBody
    @RequestMapping("/registrat")
    public String findAll(HttpServletResponse response){
        response.setContentType("text/json");
        response.setCharacterEncoding("utf-8");
        List<RegistrationForm> examines=registrationFormService.findAll();
        int total=examines.size();
        JSONObject result= new JSONObject();
        result.put("rows",examines);
        result.put("total",total);
        return result.toJSONString();
    }

    //审核
    @RequestMapping("/examine")
    public String examine(@RequestBody Map<String,Object> map){

       String approveNum = (String)map.get("approvalNumber");
       String opinion = (String)map.get("opinion");
       String result = (String)map.get("result");

       String state = "通过";

       registrationFormService.updateOpinion(opinion,result,state,approveNum);
        return "LibraryUse/Examine";
    }

    @RequestMapping("/unexamine")
    public String unexamine(@RequestBody Map<String,Object> map){

        String approveNum = (String)map.get("approvalNumber");
        String opinion = (String)map.get("opinion");
        String result = (String)map.get("result");

        String state = "未通过";

        registrationFormService.updateOpinion(opinion,result,state,approveNum);
        return "LibraryUse/Examine";
    }

    //查询登记
    @ResponseBody
    @RequestMapping("/searchExamine")
    public String searchRegist(String query_status){
        JSONObject jsonObject=new JSONObject();
        List<RegistrationForm> registrationFormLists = registrationFormService.findByStatus(query_status);
        if (registrationFormLists != null){
            jsonObject.put("rows",registrationFormLists);
            jsonObject.put("total",registrationFormLists.size());
            return jsonObject.toJSONString();
        }else {
            System.out.println("没数据");
        }
        return null;
    }

    @Autowired
    private DictionaryDao dictionaryDao;
    //向前台发送状态下拉框数据
    @RequestMapping("/getExamineStatus")
    @ResponseBody
    public List<DictionaryPojo> getExamineStatus(){
        List<DictionaryPojo> dictionaryPojoList = dictionaryDao.findAllInfo("状态");
        return dictionaryPojoList;
    }
}
