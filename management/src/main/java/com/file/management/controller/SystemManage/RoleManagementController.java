package com.file.management.controller.SystemManage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.file.management.dao.PermissionDao;
import com.file.management.dao.SysRoleDao;
import com.file.management.pojo.SysRole;
import com.file.management.pojo.SystemManagement.Dictionary.DictionaryPojo;
import com.file.management.pojo.UserInfo;
import com.file.management.service.DictionaryService;
import com.file.management.service.SystemManage.UsersManageService;
import com.file.management.service.UserInfoService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RoleManagementController {

    @Autowired
    private UsersManageService usersManageService;
    @Autowired
    UserInfoService userInfoService;
    //获取js中设置权限
    @Autowired
    PermissionDao permissionDao;
    @Autowired
    SysRoleDao sysRoleDao;

    /*
     * 用户角色表
     */
    @RequestMapping("/UserRoleTable")
    @ResponseBody
    public String UserRoleTable(){

       List<UserRoleInformation> userRoleList = new ArrayList<UserRoleInformation>();
        List<UserInfo> userInfoList = userInfoService.findAllInfo();
        for (UserInfo userInfo:userInfoList){
            String roleStr="";
            UserRoleInformation userRoleInformation = new UserRoleInformation();
            List<SysRole> roleList = userInfo.getRoleList();
            //加载出一个用户所有角色
            for (SysRole role : roleList){
                roleStr += role.getRole();
                roleStr +='/';
            }
            userRoleInformation.setName(userInfo.getName());
            userRoleInformation.setUsername(userInfo.getUsername());
            userRoleInformation.setDepartment(userInfo.getDepartment());
            userRoleInformation.setRole(roleStr);
            userRoleList.add(userRoleInformation);
        }
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("rows",userRoleList);
        jsonObject.put("total",userRoleList.size());
        return jsonObject.toJSONString();
    }

    /*
     * 获取所有角色
     */
    @Autowired
    DictionaryService dictionaryService;

    @RequestMapping("/UserRole")
    @ResponseBody
    public String userRole(){
       List<DictionaryPojo> dictionaryPojoList = dictionaryService.getDictionary("用户角色");
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("rows",dictionaryPojoList);
        jsonObject.put("total",dictionaryPojoList.size());
        return jsonObject.toJSONString();
    }

    /*
      保存角色
     */
    @RequestMapping("/saveRole")
    public String saveRole(@RequestParam("role")String role,String username){
        //System.out.println(role);
        UserInfo userInfo = userInfoService.findByUsername(username);
        //System.out.println(username);
        JSONArray array = JSONArray.fromObject(role);
        List<SysRole> sysRoleList = new ArrayList<SysRole>();
        if (array.size()>0){
            for (int i =0; i<array.size();i++){
                net.sf.json.JSONObject job = array.getJSONObject(i);
                String user_role=(String) job.get("code");
                System.out.println(user_role);
                SysRole sysRole= sysRoleDao.findByRole(user_role);
                sysRoleList.add(sysRole);
            }
            userInfo.setRoleList(sysRoleList);
            userInfoService.saveUserInfo(userInfo);
        }
        return "SystemManagement/RoleManagement";
    }

    /*
     向页面中显示拥有角色
     */

}
