package com.file.management.service.SystemManage;

import com.alibaba.fastjson.JSONObject;
import com.file.management.dao.SystemManage.JsonTestDao;
import com.file.management.pojo.LibraryUse.RegistrationForm;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.List;
@Service
public class JsonServiceImpl implements JsonService {
    @Autowired
    JsonTestDao jsonTestDao;

    @Override
    public List<RegistrationForm> getWS() {
        //行权限
        JsonObject json = new JsonObject();
        json.addProperty("CampusCode","1.南京校区");
        json.addProperty("ArchivingDep","科技处");
        json.addProperty("ClassificationNo","");
        json.addProperty("PartNo","");
        json.addProperty("ReferenceNo","");
        json.addProperty("DocumentNo","");
        json.addProperty("Title","");
        json.addProperty("PageNo","");
        json.addProperty("PersonLiable","河海大学");
        json.addProperty("Annex","");
        json.addProperty("Date","");
        json.addProperty("RetentionPeriod","");
        json.addProperty("SecurityClassification","");
        json.addProperty("Remarks","");
        json.addProperty("FilingTime","");
        json.addProperty("LAST_MODIFIED","");
        json.addProperty("IS_DEL","");
        json.addProperty("TABLE_ID","");
        String sql = json.toString();
        JsonObject js = new JsonObject();
        System.out.println(sql);
        String sql0="";
        JSONObject jsonObject = JSONObject.parseObject(sql);
        //System.out.println(jsonObject.getString("certificatetype"));
        if (StringUtils.isEmpty(jsonObject.getString("CampusCode"))!=true){
            String s ="";
            if (sql0==""){
                 s = "CampusCode="+"'"+jsonObject.getString("CampusCode")+"'";
            }else {
                 s =" and "+ "CampusCode="+"'"+jsonObject.getString("CampusCode")+"'";
            }

            sql0  = sql0+s;
        }
        if (StringUtils.isEmpty(jsonObject.getString("ArchivingDep"))!=true){
            String s = "";
            if (sql0==""){
                s = "ArchivingDep="+"'"+jsonObject.getString("ArchivingDep")+"'";
            }else {
                s =" and "+"ArchivingDep="+"'"+jsonObject.getString("ArchivingDep")+"'";
            }
            sql0  = sql0+s;
        }
        if (StringUtils.isEmpty(jsonObject.getString("ClassificationNo"))!=true){
            String s = "";
            if (sql0==""){
                s = "ClassificationNo="+"'"+jsonObject.getString("ClassificationNo")+"'";
            }else {
                s =" and "+"ClassificationNo="+"'"+jsonObject.getString("ClassificationNo")+"'";
            }
            sql0  = sql0+s;
        }
        if (StringUtils.isEmpty(jsonObject.getString("PartNo"))!=true){
            String s = "";
            if (sql0==""){
                s = "PartNo="+"'"+jsonObject.getString("PartNo")+"'";
            }else {
                s =" and "+"PartNo="+"'"+jsonObject.getString("PartNo")+"'";
            }
            sql0  = sql0+s;
        }
        if (StringUtils.isEmpty(jsonObject.getString("ReferenceNo"))!=true){
            String s = "";
            if (sql0==""){
                s = "ReferenceNo="+"'"+jsonObject.getString("ReferenceNo")+"'";
            }else {
                s =" and "+"ReferenceNo="+"'"+jsonObject.getString("ReferenceNo")+"'";
            }
            sql0  = sql0+s;
        }
        if (StringUtils.isEmpty(jsonObject.getString("DocumentNo"))!=true){
            String s = "";
            if (sql0==""){
                s = "DocumentNo="+"'"+jsonObject.getString("DocumentNo")+"'";
            }else {
                s =" and "+"DocumentNo="+"'"+jsonObject.getString("DocumentNo")+"'";
            }
            sql0  = sql0+s;
        }
        if (StringUtils.isEmpty(jsonObject.getString("Title"))!=true){
            String s = "";
            if (sql0==""){
                s = "Title="+"'"+jsonObject.getString("Title")+"'";
            }else {
                s =" and "+"Title="+"'"+jsonObject.getString("Title")+"'";
            }
            sql0  = sql0+s;
        }
        if (StringUtils.isEmpty(jsonObject.getString("PageNo"))!=true){
            String s = "";
            if (sql0==""){
                s = "PageNo="+"'"+jsonObject.getString("PageNo")+"'";
            }else {
                s =" and "+"PageNo="+"'"+jsonObject.getString("PageNo")+"'";
            }
            sql0  = sql0+s;
        }
        if (StringUtils.isEmpty(jsonObject.getString("PersonLiable"))!=true){
            String s = "";
            if (sql0==""){
                s = "PersonLiable="+"'"+jsonObject.getString("PersonLiable")+"'";
            }else {
                s =" and "+"PersonLiable="+"'"+jsonObject.getString("PersonLiable")+"'";
            }
            sql0  = sql0+s;
        }
        if (StringUtils.isEmpty(jsonObject.getString("Annex"))!=true){
            String s = "";
            if (sql0==""){
                s = "Annex="+"'"+jsonObject.getString("Annex")+"'";
            }else {
                s =" and "+"Annex="+"'"+jsonObject.getString("Annex")+"'";
            }
            sql0  = sql0+s;
        }
        if (StringUtils.isEmpty(jsonObject.getString("Date"))!=true){
            String s = "";
            if (sql0==""){
                s = "Date="+"'"+jsonObject.getString("Date")+"'";
            }else {
                s =" and "+"Date="+"'"+jsonObject.getString("Date")+"'";
            }
            sql0  = sql0+s;
        }
        if (StringUtils.isEmpty(jsonObject.getString("RetentionPeriod"))!=true){
            String s = "";
            if (sql0==""){
                s = "RetentionPeriod="+"'"+jsonObject.getString("RetentionPeriod")+"'";
            }else {
                s =" and "+"RetentionPeriod="+"'"+jsonObject.getString("RetentionPeriod")+"'";
            }
            sql0  = sql0+s;
        }
        if (StringUtils.isEmpty(jsonObject.getString("SecurityClassification"))!=true){
            String s = "";
            if (sql0==""){
                s = "SecurityClassification="+"'"+jsonObject.getString("SecurityClassification")+"'";
            }else {
                s =" and "+"SecurityClassification="+"'"+jsonObject.getString("SecurityClassification")+"'";
            }
            sql0  = sql0+s;
        }
        if (StringUtils.isEmpty(jsonObject.getString("Remarks"))!=true){
            String s = "";
            if (sql0==""){
                s = "Remarks="+"'"+jsonObject.getString("Remarks")+"'";
            }else {
                s =" and "+"Remarks="+"'"+jsonObject.getString("Remarks")+"'";
            }
            sql0  = sql0+s;
        }
        if (StringUtils.isEmpty(jsonObject.getString("FilingTime"))!=true){
            String s = "";
            if (sql0==""){
                s = "FilingTime="+"'"+jsonObject.getString("FilingTime")+"'";
            }else {
                s =" and "+"FilingTime="+"'"+jsonObject.getString("FilingTime")+"'";
            }
            sql0  = sql0+s;
        }
        if (StringUtils.isEmpty(jsonObject.getString("LAST_MODIFIED"))!=true){
            String s = "";
            if (sql0==""){
                s = "LAST_MODIFIED="+"'"+jsonObject.getString("LAST_MODIFIED")+"'";
            }else {
                s =" and "+"LAST_MODIFIED="+"'"+jsonObject.getString("LAST_MODIFIED")+"'";
            }
            sql0  = sql0+s;
        }
        if (StringUtils.isEmpty(jsonObject.getString("IS_DEL"))!=true) {
            String s = "";
            if (sql0==""){
                s = "IS_DEL="+"'"+jsonObject.getString("IS_DEL")+"'";
            }else {
                s =" and "+"IS_DEL="+"'"+jsonObject.getString("IS_DEL")+"'";
            }
            sql0 = sql0 + s;
        }
        if (StringUtils.isEmpty(jsonObject.getString("TABLE_ID"))!=true){
            String s = "";
            if (sql0==""){
                s = "TABLE_ID="+"'"+jsonObject.getString("TABLE_ID")+"'";
            }else {
                s =" and "+"TABLE_ID="+"'"+jsonObject.getString("TABLE_ID")+"'";
            }
            sql0  = sql0+s;
        }
        System.out.println(sql0);

        //列权限
        JsonObject json1 = new JsonObject();
        json1.addProperty("CampusCode","true");
        json1.addProperty("ArchivingDep","");
        json1.addProperty("ClassificationNo","");
        json1.addProperty("PartNo","true");
        json1.addProperty("ReferenceNo","");
        json1.addProperty("DocumentNo","");
        json1.addProperty("Title","");
        json1.addProperty("PageNo","");
        json1.addProperty("PersonLiable","true");
        json1.addProperty("Annex","");
        json1.addProperty("Date","");
        json1.addProperty("RetentionPeriod","");
        json1.addProperty("SecurityClassification","");
        json1.addProperty("Remarks","");
        json1.addProperty("FilingTime","");
        json1.addProperty("LAST_MODIFIED","");
        json1.addProperty("IS_DEL","");
        json1.addProperty("TABLE_ID","");
        String sql_lie = json1.toString();
        String sql1="";
        JSONObject jsonObject_lie = JSONObject.parseObject(sql_lie);
        if (StringUtils.isEmpty(jsonObject.getString("CampusCode"))!=true){
            String s = "";
            if (sql1==""){
                s = "CampusCode";
            }else {
                s = ",CampusCode";
            }
            sql1  = sql1+s;
        }
        if (StringUtils.isEmpty(jsonObject.getString("ArchivingDep"))!=true){
            String s = "";
            if (sql1==""){
                s = "ArchivingDep";
            }else {
                s = ",ArchivingDep";
            }
            sql1  = sql1+s;
        }
        if (StringUtils.isEmpty(jsonObject.getString("ClassificationNo"))!=true){
            String s = "";
            if (sql1==""){
                s = "ClassificationNo";
            }else {
                s = ",ClassificationNo";
            }
            sql1  = sql1+s;
        }
        if (StringUtils.isEmpty(jsonObject.getString("PartNo"))!=true){
            String s = "";
            if (sql1==""){
                s = "PartNo";
            }else {
                s = ",PartNo";
            }
            sql1  = sql1+s;
        }
        if (StringUtils.isEmpty(jsonObject.getString("ReferenceNo"))!=true){
            String s = "";
            if (sql1==""){
                s = "ReferenceNo";
            }else {
                s = ",ReferenceNo";
            }
            sql1  = sql1+s;
        }
        if (StringUtils.isEmpty(jsonObject.getString("DocumentNo"))!=true){
            String s = "";
            if (sql1==""){
                s = "DocumentNo";
            }else {
                s = ",DocumentNo";
            }
            sql1  = sql1+s;
        }
        if (StringUtils.isEmpty(jsonObject.getString("Title"))!=true){
            String s = "";
            if (sql1==""){
                s = "Title";
            }else {
                s = ",Title";
            }
            sql1  = sql1+s;
        }
        if (StringUtils.isEmpty(jsonObject.getString("PageNo"))!=true){
            String s = "";
            if (sql1==""){
                s = "PageNo";
            }else {
                s = ",PageNo";
            }
            sql1  = sql1+s;
        }
        if (StringUtils.isEmpty(jsonObject.getString("PersonLiable"))!=true){
            String s = "";
            if (sql1==""){
                s = "PersonLiable";
            }else {
                s = ",PersonLiable";
            }
            sql1  = sql1+s;
        }
        if (StringUtils.isEmpty(jsonObject.getString("Annex"))!=true){
            String s = "";
            if (sql1==""){
                s = "Annex";
            }else {
                s = ",Annex";
            }
            sql1  = sql1+s;
        }
        if (StringUtils.isEmpty(jsonObject.getString("Date"))!=true){
            String s = "";
            if (sql1==""){
                s = "Date";
            }else {
                s = ",Date";
            }
            sql1  = sql1+s;
        }
        if (StringUtils.isEmpty(jsonObject.getString("RetentionPeriod"))!=true){
            String s = "";
            if (sql1==""){
                s = "RetentionPeriod";
            }else {
                s = ",RetentionPeriod";
            }
            sql1  = sql1+s;
        }
        if (StringUtils.isEmpty(jsonObject.getString("SecurityClassification"))!=true){
            String s = "";
            if (sql1==""){
                s = "SecurityClassification";
            }else {
                s = ",SecurityClassification";
            }
            sql1  = sql1+s;
        }
        if (StringUtils.isEmpty(jsonObject.getString("Remarks"))!=true){
            String s = "";
            if (sql1==""){
                s = "Remarks";
            }else {
                s = ",Remarks";
            }
            sql1  = sql1+s;
        }
        if (StringUtils.isEmpty(jsonObject.getString("FilingTime"))!=true){
            String s = "";
            if (sql1==""){
                s = "FilingTime";
            }else {
                s = ",FilingTime";
            }
            sql1  = sql1+s;
        }
        if (StringUtils.isEmpty(jsonObject.getString("LAST_MODIFIED"))!=true){
            String s = "";
            if (sql1==""){
                s = "LAST_MODIFIED";
            }else {
                s = ",LAST_MODIFIED";
            }
            sql1  = sql1+s;
        }
        if (StringUtils.isEmpty(jsonObject.getString("IS_DEL"))!=true) {
            String s = "";
            if (sql1==""){
                s = "IS_DEL";
            }else {
                s = ",IS_DEL";
            }
            sql1  = sql1+s;
        }
        if (StringUtils.isEmpty(jsonObject.getString("TABLE_ID"))!=true){
            String s = "";
            if (sql1==""){
                s = "TABLE_ID";
            }else {
                s = ",TABLE_ID";
            }
            sql1  = sql1+s;
        }
        System.out.println(sql1);
        jsonTestDao.getSQL(sql1,sql0);
        return null;
    }
}
