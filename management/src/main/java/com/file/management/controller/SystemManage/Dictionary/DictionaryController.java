package com.file.management.controller.SystemManage.Dictionary;

import com.alibaba.fastjson.JSONObject;
import com.file.management.dao.SystemManage.Dictionary.DictionaryDao;
import com.file.management.pojo.SystemManagement.Dictionary.DictionaryPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DictionaryController {
    @Autowired
    private DictionaryDao dictionaryDao;

    @ResponseBody
    @RequestMapping("/showCase")
    public String showCase(){
        System.out.println("宗号");
        JSONObject jsonObject = new JSONObject();
        List<DictionaryPojo> dictionaryPojoList=dictionaryDao.findAllByDictionary("1");
        if (dictionaryPojoList != null){
            jsonObject.put("rows",dictionaryPojoList);
            jsonObject.put("total",dictionaryPojoList.size());
            return jsonObject.toJSONString();
        }else {
            System.out.println("没数据");
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("/showClassfiy")
    public String showClassfiy(){
        JSONObject jsonObject = new JSONObject();
        List<DictionaryPojo> dictionaryPojoList=dictionaryDao.findAllByDictionary("2");
        if (dictionaryPojoList != null){
            jsonObject.put("rows",dictionaryPojoList);
            jsonObject.put("total",dictionaryPojoList.size());
            System.out.println(jsonObject.toJSONString());
            return jsonObject.toJSONString();
        }else {
            System.out.println("没数据");
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("/showArchive")
    public String showArchive(){
        JSONObject jsonObject = new JSONObject();
        List<DictionaryPojo> dictionaryPojoList=dictionaryDao.findAllByDictionary("3");
        if (dictionaryPojoList != null){
            jsonObject.put("rows",dictionaryPojoList);
            jsonObject.put("total",dictionaryPojoList.size());
            return jsonObject.toJSONString();
        }else {
            System.out.println("没数据");
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("/showType")
    public String showType(){
        JSONObject jsonObject = new JSONObject();
        List<DictionaryPojo> dictionaryPojoList=dictionaryDao.findAllByDictionary("4");
        if (dictionaryPojoList != null){
            jsonObject.put("rows",dictionaryPojoList);
            jsonObject.put("total",dictionaryPojoList.size());
            return jsonObject.toJSONString();
        }else {
            System.out.println("没数据");
        }
        return null;
    }

    //保存和查询
    @ResponseBody
    @RequestMapping("/saveboot")
    public  String saveInformation(String code, String name,String order,String comment,String dictionary){
        DictionaryPojo dictionaryPojo = new DictionaryPojo();
        dictionaryPojo.setCode(code);
        dictionaryPojo.setName(name);
        dictionaryPojo.setDictionary(dictionary);
        dictionaryPojo.setSequence(order);
        dictionaryPojo.setComment(comment);
        dictionaryDao.save(dictionaryPojo);

        JSONObject jsonObject=new JSONObject();
        List<DictionaryPojo> dictionaryPojoList=dictionaryDao.findAllByDictionary(dictionary);
        if (dictionaryPojoList != null){
            jsonObject.put("rows",dictionaryPojoList);
            jsonObject.put("total",dictionaryPojoList.size());
            return jsonObject.toJSONString();
        }else {
            System.out.println("没数据");
        }
        return null;
    }


    //保存和查询
    @ResponseBody
    @RequestMapping("/editboot")
    public  String editInformation(String code, String name,String order,String comment,String dictionary){
        dictionaryDao.insertInfo(name, order, comment, code,dictionary);
        JSONObject jsonObject=new JSONObject();
        List<DictionaryPojo> dictionaryPojoList=dictionaryDao.findAllByDictionary(dictionary);
        if (dictionaryPojoList != null){
            jsonObject.put("rows",dictionaryPojoList);
            jsonObject.put("total",dictionaryPojoList.size());
            return jsonObject.toJSONString();
        }else {
            System.out.println("没数据");
        }
        return null;
    }

    //删除
    @ResponseBody
    @RequestMapping("/delboot")
    public String delBoot(String code,String dictionary){
        dictionaryDao.deleteByCodeAndDictionary(code,dictionary);

        JSONObject jsonObject=new JSONObject();
        List<DictionaryPojo> dictionaryPojoList=dictionaryDao.findAllByDictionary(dictionary);
        if (dictionaryPojoList != null){
            jsonObject.put("rows",dictionaryPojoList);
            jsonObject.put("total",dictionaryPojoList.size());
            return jsonObject.toJSONString();
        }else {
            System.out.println("没数据");
        }
        return null;
    }
}
