package com.file.management.controller.SystemManage;

import com.alibaba.fastjson.JSONObject;
import com.file.management.dao.PermissionDao;
import com.file.management.dao.SysRoleDao;
import com.file.management.pojo.SysPermission;
import com.file.management.pojo.SysRole;
import com.file.management.pojo.UserInfo;
import com.file.management.pojo.UserManage;
import com.file.management.service.SystemManage.UsersManageService;
import com.file.management.service.UserInfoService;
import lombok.experimental.var;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class UsersManageController {

    @Autowired
    private UsersManageService usersManageService;
    @Autowired
    UserInfoService userInfoService;

    @ResponseBody
    @RequestMapping("/UserTable")
    public String searchAll(){
        JSONObject jsonObject=new JSONObject();
        //List<UserInfo> userInfoList = userInfoService.findAllInfo();
        List<UserManage> userManageList=usersManageService.findAll();
        if (userManageList !=null){
            jsonObject.put("rows",userManageList);
            jsonObject.put("total",userManageList.size());
            System.out.println(jsonObject);
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
        UserInfo userInfo = new UserInfo();
        //UserManage userManage = new UserManage();
        String name = (String) map.get("name");
        String account = (String) map.get("account");
        String department = (String) map.get("department");
        String mail = (String) map.get("mail");
        //String role =(String) map.get("role");
        String status = (String) map.get("status");
        String password = (String) map.get("password");
        String phone = (String)map.get("phone");

        userInfo.setUsername(account);
        userInfo.setDepartment(department);
        userInfo.setMail(mail);
        userInfo.setPassword(password);
        userInfo.setPhone(phone);
        userInfo.setState(status);
        userInfo.setName(name);
        //userManage.setRole(role);
        userInfoService.saveUserInfo(userInfo);

        UserManage userManage = new UserManage();
        userManage.setUsername(account);
        userManage.setName(name);
        userManage.setPhone(phone);
        userManage.setStatus(status);
        userManage.setMail(mail);
        userManage.setDepartment(department);
        userManage.setPassword(password);
        usersManageService.saveUserInfo(userManage);
        return "SystemManagement/UserManagement";
    }

    @RequestMapping("/saveEdit")
    public String insertInfo(@RequestBody Map<String,Object> map){

        String name = (String) map.get("name");
        String account = (String) map.get("account");
        String department = (String) map.get("department");
        String mail = (String) map.get("mail");
        //String role =(String) map.get("role");
        String status = (String) map.get("status");
        String phone = (String)map.get("phone");

        userInfoService.insertInfo(name,phone,department,mail,status,account);
        usersManageService.insertInfo(name,phone,department,mail,status,account);

        return "SystemManagement/UserManagement";
    }


    /*
    *获取权限
     */
    @ResponseBody
    @RequestMapping("/getAuthority")
    //@RequiresRoles("admin")//需要管理权限
    public List<String> getAuthority(@RequestBody Map<String,Object> map){
        List<String> authorityList = new ArrayList<String>();
        String username = (String) map.get("username");
        JSONObject jsonObject=new JSONObject();
        UserInfo userInfo = userInfoService.findByUsername(username);
        List<SysRole> sysRoles = userInfo.getRoleList();
        for (SysRole sysRole:sysRoles){
           List<SysPermission> sysPermissionList= sysRole.getPermissions();
           for (SysPermission permission:sysPermissionList){
               authorityList.add(permission.getName());
           }
        }
        return authorityList;
    }

    //获取js中设置权限
    @Autowired
    PermissionDao permissionDao;
    @Autowired
    SysRoleDao sysRoleDao;

    @ResponseBody
    @RequestMapping(value = "/saveAuthority",method = {RequestMethod.POST})
    //@RequiresRoles("admin")//需要管理权限
    public void saveAuthority(@RequestBody String[] array)throws IOException {
        String username = array[0];
        for (int k=0; k< array.length;k++)
            System.out.println(array[k]);
        UserInfo userInfo = userInfoService.findByUsername(username);
        Integer id = userInfo.getUid();
        //SysRole sysRole = sysRoleDao.findById(3);
        List<SysRole> roleList = userInfo.getRoleList(); //获取角色
        //roleList.add(sysRole);//添加角色
        userInfo.setRoleList(roleList);

        //System.out.println("id:"+userInfo.getUid());
//        for (SysRole role:roleList){
//            System.out.println("role:"+role.getId());
//            System.out.println(role.getDescription());
//        }

        List<SysPermission> permissions = new ArrayList<SysPermission>();
        for (int i=1;i<array.length;i++){
            String authorityName = array[i];
            SysPermission permission = permissionDao.findByName(authorityName);
            permissions.add(permission);
        }
        for (SysRole role:roleList){
            role.setPermissions(permissions);
        }
        userInfoService.saveUserInfo(userInfo);
    }
}
