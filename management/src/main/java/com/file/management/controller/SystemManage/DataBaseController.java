package com.file.management.controller.SystemManage;

import com.file.management.dao.SysRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class DataBaseController {
   @Autowired
   private SysRoleDao sysRoleDao;

   //保存查询过滤
   @RequestMapping("/saveSerach")
    public void saveSerach(@RequestBody Map<String,Object> map){
       String roleName = (String)map.get("roleName");
       String type = (String) map.get("type");
       String department = (String) map.get("department");
       String purpose = (String)map.get("purpose");

   }
}
