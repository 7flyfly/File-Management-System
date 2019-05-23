package com.file.management.controller.SystemManage.Dictionary;

import com.alibaba.fastjson.JSONObject;
import com.file.management.dao.SystemManage.Dictionary.DictionaryDao;
import com.file.management.pojo.SystemManagement.Dictionary.DictionaryPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class DictionaryController {
    @Autowired
    private DictionaryDao dictionaryDao;

    @ResponseBody
    @RequestMapping("/getDictionary")
    public String getDictionary() {
        System.out.println("1");
        JSONObject jsonObject = new JSONObject();
        List<DictionaryPojo> dictionaryPojoList = dictionaryDao.findDictionary();
        if (dictionaryPojoList != null) {
            jsonObject.put("rows", dictionaryPojoList);
            jsonObject.put("total", dictionaryPojoList.size());
            return jsonObject.toJSONString();
        } else {
            System.out.println("没数据");
        }
        return null;
    }

    //返回词典具体内容
    @ResponseBody
    @RequestMapping("/getDetail")
    public String getDetail(String name){
        JSONObject jsonObject = new JSONObject();
        List<DictionaryPojo> dictionaryPojoList = dictionaryDao.findAllByDictionaryname(name);
        if (dictionaryPojoList != null) {
            jsonObject.put("rows", dictionaryPojoList);
            jsonObject.put("total", dictionaryPojoList.size());
            return jsonObject.toJSONString();
        } else {
            System.out.println("没数据");
        }
        return null;
    }
    /*
     *删除信息
     */
    @ResponseBody
    @RequestMapping("/delDictionary")
    public String deleteInfo(String name){
        dictionaryDao.deleteAllByDictionaryname(name);
        JSONObject jsonObject = new JSONObject();
        List<DictionaryPojo> dictionaryPojoList = dictionaryDao.findDictionary();
        if (dictionaryPojoList != null) {
            jsonObject.put("rows", dictionaryPojoList);
            jsonObject.put("total", dictionaryPojoList.size());
            return jsonObject.toJSONString();
        } else {
            System.out.println("没数据");
        }
        return null;
    }

    //删除字段
    @ResponseBody
    @RequestMapping("/deleteField")
    public String deleteField(String code,String name){
        dictionaryDao.deleteByCode(code);
        JSONObject jsonObject = new JSONObject();
        List<DictionaryPojo> dictionaryPojoList = dictionaryDao.findAllInfo(name);
        if (dictionaryPojoList != null) {
            jsonObject.put("rows", dictionaryPojoList);
            jsonObject.put("total", dictionaryPojoList.size());
            return jsonObject.toJSONString();
        } else {
            System.out.println("没数据");
        }
        return null;
    }
    //添加
    @ResponseBody
    @RequestMapping("/addDictionary")
    public String addDictionary(String name,String code){
        DictionaryPojo dictionaryPojo = new DictionaryPojo();
        dictionaryPojo.setDictionarycode(code);
        dictionaryPojo.setDictionaryname(name);
        dictionaryDao.save(dictionaryPojo);

        JSONObject jsonObject = new JSONObject();
        List<DictionaryPojo> dictionaryPojoList = dictionaryDao.findDictionary();
        if (dictionaryPojoList != null) {
            jsonObject.put("rows", dictionaryPojoList);
            jsonObject.put("total", dictionaryPojoList.size());
            return jsonObject.toJSONString();
        } else {
            System.out.println("没数据");
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("/addContent")
    public String addContent(String dictionary_name,String dictionary_code, String name,String code,String parent,String comment,String sequence){
        DictionaryPojo dictionaryPojo = new DictionaryPojo();
        dictionaryPojo.setCode(code);
        dictionaryPojo.setName(name);
        dictionaryPojo.setParent(parent);
        dictionaryPojo.setComment(comment);
        dictionaryPojo.setSequence(sequence);
        dictionaryPojo.setDictionaryname(dictionary_name);
        dictionaryPojo.setDictionarycode(dictionary_code);
        dictionaryDao.save(dictionaryPojo);

        JSONObject jsonObject = new JSONObject();
        List<DictionaryPojo> dictionaryPojoList = dictionaryDao.findAllInfo(dictionary_name);
        if (dictionaryPojoList != null) {
            jsonObject.put("rows", dictionaryPojoList);
            jsonObject.put("total", dictionaryPojoList.size());
            return jsonObject.toJSONString();
        } else {
            System.out.println("没数据");
        }
        return null;
    }

    //editInfo修改
    @ResponseBody
    @RequestMapping("/editInfo")
    public String editInfo(String code, String name,String sequence,String comment){
        dictionaryDao.updateInfo(sequence,comment,code);
        JSONObject jsonObject = new JSONObject();
        List<DictionaryPojo> dictionaryPojoList = dictionaryDao.findAllInfo(name);
        if (dictionaryPojoList != null) {
            jsonObject.put("rows", dictionaryPojoList);
            jsonObject.put("total", dictionaryPojoList.size());
            return jsonObject.toJSONString();
        } else {
            System.out.println("没数据");
        }
        return null;
    }
}
