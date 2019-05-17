package com.file.management.controller.Library;

import com.alibaba.fastjson.JSONObject;
import com.file.management.dao.LibraryUse.DatabasesDao;
import com.file.management.dao.SystemManage.Dictionary.DictionaryDao;
import com.file.management.pojo.LibraryUse.DatabasesPojo;
import com.file.management.pojo.LibraryUse.RegistrationForm;
import com.file.management.pojo.Menu;
import com.file.management.pojo.SystemManagement.Dictionary.DictionaryPojo;
import com.file.management.service.LibraryUse.RegistrationFormService;
import com.file.management.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class RegistrationFormController {
    @Autowired
    RegistrationFormService registrationFormService;

    @ResponseBody
    @RequestMapping("/registTable")
    public String serchAll(){
        JSONObject jsonObject=new JSONObject();
        List<RegistrationForm> registrationFormList = registrationFormService.findAll();
        if (registrationFormList != null){
            jsonObject.put("rows",registrationFormList);
            jsonObject.put("total",registrationFormList.size());
            //System.out.println(jsonObject.toJSONString());
            return jsonObject.toJSONString();
        }else {
            System.out.println("没数据");
        }
        return null;
    }


    @RequestMapping("/postRegist")
    public String saveData(@RequestBody Map<String,Object> map,HttpServletRequest request) {
        //随机生成6位数字,用于订单号
        int randomNum = (int)((Math.random()*9 + 1)*100000);
        String num = String.valueOf(randomNum);
        RegistrationForm registration = registrationFormService.findByOddnum(num);
        while (registration!=null){
            randomNum = (int)((Math.random()*9 + 1)*100000);
            num = String.valueOf(randomNum);
            registration = registrationFormService.findByOddnum(num);
        }

        //随机生成8位数，用于审批单号
        int randomNum2 = (int)((Math.random()*9+1)*10000000);
        String num2= String.valueOf(randomNum2);
        RegistrationForm registration2 = registrationFormService.findByApprovenum(num2);
        while (registration2!=null){
            randomNum = (int)((Math.random()*9 + 1)*100000);
            num = String.valueOf(randomNum);
            registration2 = registrationFormService.findByApprovenum(num);
        }
        //获取当前日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String recordDate="";
        try {
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            recordDate = sdf.format(date);
            System.out.println(recordDate);
        }catch (Exception e){
            System.out.println(e);
        }

        String registrant ="";
        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
            for (Cookie cookie: cookies){
                if (cookie.getName().equals("username")){
                   registrant = cookie.getValue();
                }
            }
        }

        RegistrationForm registrationForm = new RegistrationForm();
        String type = (String) map.get("type");
        String name = (String) map.get("name");

        String beginDate = (String) map.get("beginDate");
        String unit = (String) map.get("unit");
        String certificateType = (String) map.get("certificateType");
        String certificateNumber = (String) map.get("certificateNumber");
        String day = (String) map.get("day");
        String purpose = (String) map.get("purpose");
        String phone = (String)map.get("phone");
        String way = (String) map.get("way");
        String state = "待审批";
        String contant = (String)map.get("contant");

        registrationForm.setOddNumbers(num);
        registrationForm.setApprovalNumber(num2);
        registrationForm.setType(type);
        registrationForm.setName(name);
        registrationForm.setRegistrant(registrant);
        registrationForm.setRecordDate(recordDate);
        registrationForm.setUnit(unit);
        registrationForm.setCertificateType(certificateType);
        registrationForm.setCertificateNumber(certificateNumber);
        registrationForm.setDate(beginDate);
        registrationForm.setDay(day);
        registrationForm.setPurpose(purpose);
        registrationForm.setRecordDate(recordDate);
        registrationForm.setTelphone(phone);
        registrationForm.setWay(way);
        registrationForm.setState(state);
        registrationForm.setStloanAgentate(registrant);
        registrationForm.setContant(contant);
        registrationFormService.saveAll(registrationForm);

        return "LibraryUse/Check";
    }
    //利用查询
    @ResponseBody
    @RequestMapping("/queryTable")
    public String queryTable(){
        JSONObject jsonObject=new JSONObject();
        List<RegistrationForm> registrationFormList = registrationFormService.findAll();
        if (registrationFormList != null){
            jsonObject.put("rows",registrationFormList);
            jsonObject.put("total",registrationFormList.size());
            return jsonObject.toJSONString();
        }else {
            System.out.println("没数据");
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("/deleteRegister")
    public String deleteRegister(String num){
        registrationFormService.deleteForm(num);

        JSONObject jsonObject=new JSONObject();
        List<RegistrationForm> registrationFormList = registrationFormService.findAll();
        if (registrationFormList != null){
            jsonObject.put("rows",registrationFormList);
            jsonObject.put("total",registrationFormList.size());
            return jsonObject.toJSONString();
        }else {
            System.out.println("没数据");
        }
        return null;

    }

    //查询登记
    @ResponseBody
    @RequestMapping("/searchRegist")
    public String searchRegist(String query_type,String status, String query_keward){
        JSONObject jsonObject=new JSONObject();
        System.out.println(query_type);
        System.out.println(status);
        System.out.println(query_keward);
        List<RegistrationForm> registrationFormList = registrationFormService.findByTypeAndNameAndStatus(query_type,query_keward,status);
        if (registrationFormList != null){
            jsonObject.put("rows",registrationFormList);
            jsonObject.put("total",registrationFormList.size());
            System.out.println(jsonObject.toJSONString());
            return jsonObject.toJSONString();
        }else {
            System.out.println("没数据");
        }
        return null;
    }

    //归还操作
    @ResponseBody
    @RequestMapping("/backFile")
    public String backFile(String num,HttpServletRequest request){
        String registrant ="";
        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
            for (Cookie cookie: cookies){
                if (cookie.getName().equals("username")){
                    registrant = cookie.getValue();
                }
            }
        }
        //获取当前日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String recordDate="";
        try {
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            recordDate = sdf.format(date);
        }catch (Exception e){
            System.out.println(e);
        }

        String status="归还";
        JSONObject jsonObject=new JSONObject();
        registrationFormService.updateState(status,registrant,recordDate,num);//更新状态
        List<RegistrationForm> registrationFormList = registrationFormService.findAll();
        if (registrationFormList != null){
            jsonObject.put("rows",registrationFormList);
            jsonObject.put("total",registrationFormList.size());
            return jsonObject.toJSONString();
        }else {
            System.out.println("没数据");
        }
        return null;
    }

    /*
    * 选择库
     */
    @Autowired
    DatabasesDao databasesDao;

    @ResponseBody
    @RequestMapping("/getDatabase")
    public String getDatabases(){
        JSONObject jsonObject=new JSONObject();
        List<DatabasesPojo> databasesPojoList = databasesDao.findAll();
        if (databasesPojoList != null){
            jsonObject.put("rows",databasesPojoList);
            jsonObject.put("total",databasesPojoList.size());
            return jsonObject.toJSONString();
        }else {
            System.out.println("没数据");
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("/showDatabase")
    public String showDatabase(String documentNo,String database){
        JSONObject jsonObject=new JSONObject();
        List<DatabasesPojo> databasesPojoList = databasesDao.findAllByDocumentNoAndFilestore(documentNo,database);
        if (databasesPojoList != null){
            jsonObject.put("rows",databasesPojoList);
            jsonObject.put("total",databasesPojoList.size());
            return jsonObject.toJSONString();
        }else {
            System.out.println("没数据");
        }
        return null;
    }


    @Autowired
    private DictionaryDao dictionaryDao;

    @RequestMapping("/getCertType")
    @ResponseBody
    public List<DictionaryPojo> getcertType(){
        List<DictionaryPojo> dictionaryPojoList = dictionaryDao.findAllByDictionary("4");
        return dictionaryPojoList;
    }

    //向前台发送状态下拉框数据
    @RequestMapping("/getStatus")
    @ResponseBody
    public List<DictionaryPojo> getStatus(){
        List<DictionaryPojo> dictionaryPojoList = dictionaryDao.findAllByDictionary("5");
        return dictionaryPojoList;
    }

    //向前台发送单位下拉框数据
    @RequestMapping("/getUnit")
    @ResponseBody
    public List<DictionaryPojo> getUnit(){
        List<DictionaryPojo> dictionaryPojoList = dictionaryDao.findAllByDictionary("3");
        return dictionaryPojoList;
    }

    @Autowired
    private MenuService menuService;
    //向前台发送档案库下拉框数据
    @RequestMapping("/getData")
    @ResponseBody
    public List<String> getData(){
        List<String> str = new ArrayList<String>();
        List<Menu> menuList =  menuService.getMenuRoot();
        for (Menu menu:menuList){
            str.add(menu.getMenuName());
        }
        return str;
    }
}



