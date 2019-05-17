package com.file.management.controller.SystemManage;


import com.alibaba.fastjson.JSONObject;
import com.file.management.pojo.state.Action;
import com.file.management.service.state.ActionService;
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
public class ActionManage {

    @Autowired
    private ActionService actionService;

    @RequestMapping("/SystemManagement/ActionManagement")
    public String ActionManage(HttpServletResponse response, Model model){
        return "SystemManagement/ActionManagement";
    }

    @ResponseBody
    @RequestMapping("/actionSearch")
    public String searchAllaction(){
        JSONObject jsonObject=new JSONObject();
        List<Action> listact = actionService.findAction();
        if (listact != null){
            jsonObject.put("rows",listact);
            jsonObject.put("total",listact.size());
            return jsonObject.toJSONString();
        }else {
            System.out.println("没数据");
        }
       return null;
    }

    /*
    *添加数据
     */
    @RequestMapping("/postActionData")
    public String saveactionData(@RequestBody Map<String,Object>map){
        Action action = new Action();
        String name = (String) map.get("name");
        String type = (String) map.get("type");
        String explain = (String) map.get("explain");
        String message =(String) map.get("message");

       action.setName(name);
       action.setType(type);
       action.setExp(explain);
       action.setMessage(message);
        try {
            actionService.saveAction(action);
        }catch (Exception e){
            System.out.println(e);
        }
       return  "SystemManagement/ActionManagement";
    }

    /*
    *按要求查询。通过名称查询
     */
    @ResponseBody
    @RequestMapping("/searchAction")
    public String searchaction(String text){
        String name=text;
        System.out.println(name);
        List<Action> actions = actionService.findByName(name);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("rows",actions);
        jsonObject.put("total",actions.size());
        return jsonObject.toJSONString();
    }


    @RequestMapping("/saveActionEdit")
    public String insertActionInfo(@RequestBody Map<String,Object> map){

        String name = (String) map.get("name");
        String type = (String) map.get("type");
        String explain = (String) map.get("explain");
        String message =(String) map.get("message");

        actionService.insertInfo(name,type,explain,message);
        return "SystemManagement/ActionManagement";
    }

    /*
    *删除信息
     */
    @RequestMapping("/deleteActionInfo")
    public String deleteActionInfo(@RequestBody Map<String,Object> map){
        String name=(String)map.get("name");
        actionService.deleteInfo(name);
        return "SystemManagement/ActionManagement";
    }
}

