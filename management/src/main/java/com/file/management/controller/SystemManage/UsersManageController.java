package com.file.management.controller.SystemManage;

import com.alibaba.fastjson.JSONObject;
import com.file.management.pojo.UserManage;
import com.file.management.service.SystemManage.UsersManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class UsersManageController {

    @Autowired
    private UsersManageService usersManageService;

    @ResponseBody
    @RequestMapping("/UserTable")
    public String searchAll(){
        JSONObject jsonObject=new JSONObject();
        List<UserManage> userManageList=usersManageService.findAll();
        if (userManageList !=null){
            jsonObject.put("rows",userManageList);
            jsonObject.put("total",userManageList.size());
            return jsonObject.toJSONString();
        }else {
            System.out.println("没数据");
        }
        return null;
    }

    /*
    *模态框保存用户信息
     */
    @RequestMapping("/saveUsers")
    public String saveUserInfo(@RequestBody Map<String,Object> map){
        UserManage userManage = new UserManage();
        String name = (String) map.get("name");
        String account = (String) map.get("account");
        String department = (String) map.get("department");
        String mail = (String) map.get("mail");
        String role =(String) map.get("role");
        String status = (String) map.get("status");
        String password = (String) map.get("password");
        String phone = (String)map.get("phone");
        userManage.setAccount(account);
        userManage.setDepartment(department);
        userManage.setMail(mail);
        userManage.setPassword(password);
        userManage.setPhone(phone);
        userManage.setStatus(status);
        userManage.setName(name);
        userManage.setRole(role);

        usersManageService.saveUserInfo(userManage);
        return "SystemManagement/UserManagement";
    }

    @RequestMapping("/saveEdit")
    public String insertInfo(@RequestBody Map<String,Object> map){

        String name = (String) map.get("name");
        String account = (String) map.get("account");
        String department = (String) map.get("department");
        String mail = (String) map.get("mail");
        String role =(String) map.get("role");
        String status = (String) map.get("status");
        String phone = (String)map.get("phone");

        usersManageService.insertInfo(name,phone,department,mail,role,status,account);

        return "SystemManagement/UserManagement";
    }

}
