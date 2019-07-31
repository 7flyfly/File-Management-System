package com.file.management.controller.SystemManage;


import com.file.management.dao.SysRoleDao;
import com.file.management.pojo.SysRole;
import com.file.management.pojo.UserInfo;
import com.file.management.service.UserInfoService;
import com.file.management.service.metadata.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/*
  系统管理
 */
@Controller
public class SystemManagement {

    @Autowired
    TemplateService templateService;
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    SysRoleDao sysRoleDao;

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

//    @RequestMapping("/SystemManagement/DepartmentManage")
//    public String departmentManagement(){
//        return "SystemManagement/DepartmentManagement";
//    }


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
    public String UserManagement(Model model){
        List<UserInfo> userInfoList = userInfoService.findAllInfo();
        model.addAttribute("list",userInfoList);
        return "SystemManagement/UserManagement";
    }

    /*
    角色管理
   */
    @RequestMapping("/SystemManagement/RoleManagement")
    public String UserRoleManagement(Model model){
        List<SysRole> roleList = sysRoleDao.findAll();
        model.addAttribute("roleList",roleList);
        return "SystemManagement/RoleManagement";
    }

    @RequestMapping("/SystemManagement/DataBaseManagement")
    public String dataBaseManagement(Model model){
        List<SysRole> roles = sysRoleDao.findAll();
        model.addAttribute("roles",roles);
        return "SystemManagement/DataBaseManagement";
    }
}
