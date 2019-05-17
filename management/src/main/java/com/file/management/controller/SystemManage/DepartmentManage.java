package com.file.management.controller.SystemManage;


import com.alibaba.fastjson.JSONObject;
import com.file.management.pojo.Department;
import com.file.management.service.SystemManage.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
public class DepartmentManage {

    @Autowired
    private DepartmentService departmentService;
    @RequestMapping("/SystemManagement/DepartmentManage")
    public String returnTable(HttpServletResponse response, Model model){
        return "SystemManagement/DepartmentManagement";
    }

    @ResponseBody
    @RequestMapping("/departmentSerach")
    public String serchAll(){
        JSONObject jsonObject=new JSONObject();
        List<Department> departmentLists=departmentService.findAll();
        if (departmentLists != null){
            jsonObject.put("rows",departmentLists);
            jsonObject.put("total",departmentLists.size());
            return jsonObject.toJSONString();
        }else {
            System.out.println("没数据");
        }
        return null;
    }

    /*
    *添加数据
     */
    @RequestMapping("/postDepartementData")
    public String saveData(@RequestBody Map<String,Object>map){
        Department department=new Department();
        String code = (String) map.get("code");
        String name = (String) map.get("name");
        String parent = (String) map.get("parent");
        String charger = (String) map.get("charger");
        String phone =(String) map.get("phone");
        String fax = (String) map.get("fax");
        String comment = (String) map.get("comment");
        department.setCode(code);
        department.setName(name);
        department.setParent(parent);
        department.setCharger(charger);
        department.setPhone(phone);
        department.setFax(fax);
        department.setComment(comment);
        System.out.println(code);
        try {
            departmentService.save(department);
        }catch (Exception e){
            System.out.println(e);
        }
       return  "SystemManagement/DepartmentManagement";
    }

    /*
    *按要求查询。通过名称查询
     */
    @ResponseBody
    @RequestMapping("/search")
    public String search(String text){
        String name=text;
        List<Department> departmentLists=departmentService.findByName(name);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("rows",departmentLists);
        jsonObject.put("total",departmentLists.size());
        return jsonObject.toJSONString();
    }


    @RequestMapping("/saveDepartmentEdit")
    public String insertInfo(@RequestBody Map<String,Object> map){

        String code = (String) map.get("code");
        String phone = (String) map.get("phone");
        String comment = (String) map.get("comment");
        String fax = (String) map.get("fax");
        String parent =(String) map.get("parent");
        String name = (String) map.get("name");
        String charger = (String)map.get("charger");

        departmentService.insertInfo(code,phone,comment,fax,parent,name,charger);

        return "SystemManagement/DepartmentManagement";
    }

    /*
    *删除信息
     */
    @RequestMapping("/deleteInfo")
    public String deleteInfo(@RequestBody Map<String,Object> map){
        String name=(String)map.get("name");
        String charger = (String)map.get("charger");
        System.out.println(name);
        departmentService.deleteInfo(name,charger);
        return "SystemManagement/DepartmentManagement";
    }
}

