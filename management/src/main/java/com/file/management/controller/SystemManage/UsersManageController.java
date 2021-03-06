package com.file.management.controller.SystemManage;

import com.alibaba.fastjson.JSONObject;
import com.file.management.dao.PermissionDao;
import com.file.management.dao.SysRoleDao;
import com.file.management.dao.UserInfoDao;
import com.file.management.pojo.SysPermission;
import com.file.management.pojo.SysRole;
import com.file.management.pojo.UserInfo;
import com.file.management.pojo.UserManage;
import com.file.management.service.SystemManage.UsersManageService;
import com.file.management.service.UserInfoService;
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
    @Autowired
    SysRoleDao sysRoleDao;
    @Autowired
    UserInfoDao userInfoDao;


    @ResponseBody
    @RequestMapping("/UserTable")
    public String searchAll(){
        JSONObject jsonObject=new JSONObject();
        //List<UserManage> userManageList=usersManageService.findAll();
        List<SysRole> sysRoleList = sysRoleDao.findAll();
        List<SysRole> sysRoles = new ArrayList<SysRole>();
        for (SysRole r : sysRoleList){
            SysRole sysRole = new SysRole();
            sysRole.setId(r.getId());
            sysRole.setPermissions(r.getPermissions());
            sysRole.setRole(r.getRole());
            sysRole.setDescription(r.getDescription());
            sysRoles.add(sysRole);
        }
        if (sysRoles !=null){
            jsonObject.put("rows",sysRoles);
            jsonObject.put("total",sysRoles.size());
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
        String role = (String) map.get("role");
        System.out.println(role);
        JSONObject jsonObject=new JSONObject();
        //UserInfo userInfo = userInfoService.findByUsername(username);
        SysRole sysRole = sysRoleDao.findByRole(role);
        List<SysPermission> sysPermissionList= sysRole.getPermissions();
        for (SysPermission permission:sysPermissionList){
               authorityList.add(permission.getName());
           }
        //List<SysRole> sysRoles = userInfo.getRoleList();
//        for (SysRole sysRole:sysRoles){
//           List<SysPermission> sysPermissionList= sysRole.getPermissions();
//           for (SysPermission permission:sysPermissionList){
//               authorityList.add(permission.getName());
//           }
//        }
        return authorityList;
    }

    //获取js中设置权限
    @Autowired
    PermissionDao permissionDao;

    @ResponseBody
    @RequestMapping(value = "/saveAuthority",method = {RequestMethod.POST})
    //@RequiresRoles("admin")//需要管理权限
    public void saveAuthority(@RequestBody String[] array)throws IOException {
        String role = array[0];
        SysRole sysRole = sysRoleDao.findByRole(role);
        //List<SysPermission> permissions = new ArrayList<SysPermission>();
        List<SysPermission> permissions = sysRole.getPermissions();
        ArrayList<String> permissionName = new ArrayList<String>();
        for (SysPermission p : permissions){
            permissionName.add(p.getName());
        }
        for (int i=1;i<array.length;i++){
            String authorityName = array[i];
            System.out.println(authorityName);
            SysPermission permission = permissionDao.findByName(authorityName);
            List<SysRole> pms_role = permission.getRoles();
            if (permissionName.contains(authorityName)==true){
                continue;
            }else {
                pms_role.add(sysRole);
            }
            permission.setRoles(pms_role);
            //permissions.add(permission);
            permissionDao.save(permission);

        }
//        SysRole role1 =  new SysRole();
//        role1.setId(sysRole.getId());
//        role1.setRole(sysRole.getRole());
//        role1.setDescription(sysRole.getDescription());
//        role1.setAvailable(sysRole.getAvailable());
//        role1.setPermissions(permissions);

        //sysRole.setPermissions(permissions);
        //sysRoleDao.saveAndFlush(sysRole);
//        String username = array[0];
//        for (int k=0; k< array.length;k++)
//            System.out.println(array[k]);
//        UserInfo userInfo = userInfoService.findByUsername(username);
//        Integer id = userInfo.getUid();
//        List<SysRole> roleList = userInfo.getRoleList(); //获取角色
//        userInfo.setRoleList(roleList);
//
//        List<SysPermission> permissions = new ArrayList<SysPermission>();
//        for (int i=1;i<array.length;i++){
//            String authorityName = array[i];
//            SysPermission permission = permissionDao.findByName(authorityName);
//            permissions.add(permission);
//        }
//        for (SysRole role:roleList){
//            role.setPermissions(permissions);
//        }
//        userInfoService.saveUserInfo(userInfo);
    }

    /*
    * 保存用户角色
     */
    @ResponseBody
    @RequestMapping(value ="/saveUserRole",method = {RequestMethod.POST})
    public void saveUserRole(@RequestBody String[] array){
        String username = array[0];
        UserInfo userInfo = userInfoService.findByUsername(username);
        List<SysRole>roleList = new ArrayList<>();
        for (int i=1;i<array.length;i++){
            SysRole role = sysRoleDao.findByRole(array[i]);
            roleList.add(role);
        }
        userInfo.setRoleList(roleList);
        userInfoDao.save(userInfo);
    }

    /*
    *向模态框传递参数，显示用户角色
     */
    @ResponseBody
    @RequestMapping("/getRole")
    //@RequiresRoles("admin")//需要管理权限
    public List<String> getRole(@RequestBody Map<String,Object> map){
        List<String> roleList = new ArrayList<String>();
        String username = (String) map.get("username");
        JSONObject jsonObject=new JSONObject();
        UserInfo userInfo = userInfoService.findByUsername(username);
        List<SysRole> sysRoleList= userInfo.getRoleList();
        for (SysRole role:sysRoleList){
            roleList.add(role.getRole());
        }
        return roleList;
    }

    /*
    *添加用户，saveUser
     */
    @RequestMapping("/saveUser")
    public String saveUser(@RequestBody Map<String,Object> map){
        UserInfo userInfo = new UserInfo();
        String username = (String) map.get("username");
        String name = (String) map.get("name");
        String department = (String) map.get("department");
        String mail = (String) map.get("mail");
        String status = (String) map.get("status");
        String password = (String) map.get("password");
        String phone = (String)map.get("phone");

        userInfo.setUsername(username);
        userInfo.setDepartment(department);
        userInfo.setMail(mail);
        userInfo.setPassword(password);
        userInfo.setPhone(phone);
        userInfo.setState(status);
        userInfo.setName(name);
        userInfoService.saveUserInfo(userInfo);

//        UserManage userManage = new UserManage();
//        userManage.setUsername(account);
//        userManage.setName(name);
//        userManage.setPhone(phone);
//        userManage.setStatus(status);
//        userManage.setMail(mail);
//        userManage.setDepartment(department);
//        userManage.setPassword(password);
//        usersManageService.saveUserInfo(userManage);
        return "SystemManagement/UserManagement";
    }
}
