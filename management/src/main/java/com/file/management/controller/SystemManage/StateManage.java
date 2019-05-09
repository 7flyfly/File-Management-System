package com.file.management.controller.SystemManage;


import com.alibaba.fastjson.JSONObject;
import com.file.management.pojo.state.Action;
import com.file.management.pojo.state.State;
import com.file.management.service.state.ActionService;
import com.file.management.service.state.StateService;
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
public class StateManage {

    @Autowired
    private StateService stateService;
    @Autowired
    private ActionService actionService;

    @RequestMapping("/SystemManagement/StateManagement")
    public String StateManage(HttpServletResponse response, Model model){
        return "SystemManagement/StateManagement";
    }

    @ResponseBody
    @RequestMapping("/stateSearch")
    public String searchAllaction(){
        JSONObject jsonObject=new JSONObject();
        List<State> liststa = stateService.FindState();
        if (liststa != null){
            jsonObject.put("rows",liststa);
            jsonObject.put("total",liststa.size());
            return jsonObject.toJSONString();
        }else {
            System.out.println("没数据");
        }
       return null;
    }

    /*
    *添加数据
     */
    @RequestMapping("/postStateData")
    public String savestateData(@RequestBody Map<String,Object>map){
        State state = new State();
        Action action = new Action();
        String name = (String) map.get("name");
        String sonrce = (String) map.get("source");
        String explain = (String) map.get("explain");
        String min =(String) map.get("min");
        String max =(String) map.get("max");
        String num =(String) map.get("num");
        String less = (String) map.get("less");
        String fit = (String) map.get("fit");
        String more = (String) map.get("more");
        String bool =(String) map.get("bool");
        if(less != null && less.equals("")==false){
            action.setName(less);
            try {
                actionService.saveAction(action);
            }catch (Exception e){
                System.out.println(e);
            }
        }
        if(fit != null && fit.equals("")==false){
            action.setName(fit);
            try {
                actionService.saveAction(action);
            }catch (Exception e){
                System.out.println(e);
            }
        }
        if(more != null &&more.equals("")==false){
            action.setName(more);
            try {
                actionService.saveAction(action);
            }catch (Exception e){
                System.out.println(e);
            }
        }
        state.setName(name);
        state.setExp(explain);
        state.setSource(sonrce);
        state.setMin(min);
        state.setMax(max);
        state.setNum(num);
        state.setLess(less);
        state.setFit(fit);
        state.setMore(more);
        state.setBool(bool);

        try {
            stateService.saveState(state);
        }catch (Exception e){
            System.out.println(e);
        }
        return  "SystemManagement/StateManagement";
    }

    /*
    *按要求查询。通过名称查询
     */
    @ResponseBody
    @RequestMapping("/searchState")
    public String searchstate(String text){
        String name=text;
        System.out.println(name);
        List<State> states = stateService.findByName(name);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("rows",states);
        jsonObject.put("total",states.size());
        return jsonObject.toJSONString();
    }


    @RequestMapping("/saveStateEdit")
    public String insertStateInfo(@RequestBody Map<String,Object> map){

        String name = (String) map.get("name");
        String sonrce = (String) map.get("source");
        String explain = (String) map.get("explain");
        String min =(String) map.get("min");
        String max =(String) map.get("max");
        String num =(String) map.get("num");
        String less = (String) map.get("less");
        String fit = (String) map.get("fit");
        String more = (String) map.get("more");
        String bool =(String) map.get("bool");

        State state = stateService.findStateByName(name);
        if(state.getLess() != null && state.getLess().equals("")==false){
            String oldname = state.getLess();
            String newname = less;
            if(oldname.equals(newname)==false){
                try {
                    actionService.changeName(oldname,newname);
                }catch (Exception e){
                    System.out.println(e);
                }
            }
        }
        if(state.getFit() != null && state.getFit().equals("")==false){
            String oldname = state.getFit();
            String newname = fit;
            if(oldname.equals(newname)==false){
                try {
                    actionService.changeName(oldname,newname);
                }catch (Exception e){
                    System.out.println(e);
                }
            }
        }
        if(state.getMore() != null && state.getMore().equals("")==false){
            String oldname = state.getMore();
            String newname = more;
            if(oldname.equals(newname)==false){
                try {
                    actionService.changeName(oldname,newname);
                }catch (Exception e){
                    System.out.println(e);
                }
            }
        }
        stateService.insertInfo(name,sonrce,explain,min,max,num,less,fit,more,bool);
        return "SystemManagement/StateManagement";
    }

    /*
    *删除信息
     */
    @RequestMapping("/deleteStateInfo")
    public String deleteActionInfo(@RequestBody Map<String,Object> map){
        String name=(String)map.get("name");
        stateService.deleteInfo(name);
        return "SystemManagement/StateManagement";
    }
}

