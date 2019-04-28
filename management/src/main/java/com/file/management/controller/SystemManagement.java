package com.file.management.controller;


import com.file.management.pojo.metadata.Template;
import com.file.management.service.metadata.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/*
  系统管理
 */
@Controller
public class SystemManagement {

    @Autowired
    TemplateService templateService;

    /*
      权限管理
     */
    @RequestMapping("/SystemManagement/AuthorityManagement")
    public String AuthorityManagement(){
        return "SystemManagement/AuthorityManagement";
    }

    /*
     数据库备份
    */
    @RequestMapping("/SystemManagement/DatabaseBackup")
    public String DatabaseBackup(){
        return "SystemManagement/DatabaseBackup";
    }

    /*
     数据字典
    */
    @RequestMapping("/SystemManagement/DataDictionary")
    public String DataDictionary(){
        return "SystemManagement/DataDictionary";
    }

    /*
     部门管理
    */
    @RequestMapping("/SystemManagement/DepartmentManage")
    public String departmentManagement(){
        return "SystemManagement/DepartmentManage";
    }


    /*
    流程管理
   */
    @RequestMapping("/SystemManagement/ProcessManagement")
    public String ProcessManagement(){
        return "SystemManagement/ProcessManagement";
    }

    /*
    用户管理
   */
    @RequestMapping("/SystemManagement/UserManagement")
    public String UserManagement(){
        return "SystemManagement/UserManagement";
    }
}
