package com.file.management.controller.SpecialTopic;

import com.alibaba.fastjson.JSONObject;
import com.file.management.dao.SpecialTopic.SpecialDao;
import com.file.management.pojo.Department;
import com.file.management.pojo.SpecialTopic.Special;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/*
  专题编研
 */
@Controller
public class SpecialController {

    @Autowired
    SpecialDao specialDao;

    @RequestMapping("/SpecialTopic/Special")
    public String specialPage(){
        return "SpecialTopic/Special";
    }

    @ResponseBody
    @RequestMapping("/topic")
    public String serchAll(){
        JSONObject jsonObject=new JSONObject();
        List<Special> specialList=specialDao.findAll();
        if (specialList != null){
            jsonObject.put("rows",specialList);
            jsonObject.put("total",specialList.size());
            return jsonObject.toJSONString();
        }else {
            System.out.println("没数据");
        }
        return null;
    }

    @RequestMapping("/postSpecial")
    public String saveInfo(@RequestBody Map<String,Object> map){
        String name = (String)map.get("name");
        String contant = (String)map.get("contant");
        String creater= (String)map.get("creater");
        //获取当前日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String creattime="";
        try {
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            creattime = sdf.format(date);
        }catch (Exception e){
            System.out.println(e);
        }

        String publisher = (String)map.get("publisher");
        String publishertime= (String)map.get("time");

        Special special = new Special();
        special.setName(name);
        special.setContant(contant);
        special.setCreater(creater);
        special.setCreatetime(creattime);
        special.setPublisher(publisher);
        special.setPublishtime(publishertime);
        specialDao.save(special);
        return "SpecialTopic/Special";
    }
}
