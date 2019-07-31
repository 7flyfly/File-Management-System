package com.file.management;

import com.alibaba.fastjson.JSONObject;
import com.file.management.dao.DynamicSQL;
import com.file.management.dao.LibraryUse.RegistrationFormRespository;
import com.file.management.dao.MenuRepository;
import com.file.management.dao.SystemManage.Dictionary.DictionaryDao;
import com.file.management.dao.SystemManage.JsonTestDao;
import com.file.management.pojo.LibraryUse.RegistrationForm;
import com.file.management.pojo.Menu;
import com.file.management.pojo.SystemManagement.Dictionary.DictionaryPojo;
import com.file.management.pojo.metadata.Field;
import com.file.management.pojo.metadata.Template;
import com.file.management.service.DictionaryService;
import com.file.management.service.MenuService;
import com.file.management.service.SystemManage.JsonService;
import com.file.management.service.metadata.FieldService;
import com.file.management.service.metadata.TablesService;
import com.file.management.service.metadata.TemplateService;
import com.google.gson.JsonObject;
import net.sf.json.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.*;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagementApplicationTests {
    @Autowired
    private FieldService fieldService;
    @Autowired
    private TablesService tablesService;
    @Autowired
    private TemplateService templateService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private DynamicSQL dynamicSQL;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private RegistrationFormRespository respository;

    @Autowired
    JsonService jsonService;

    @Test
    public void Test(){
        jsonService.getWS();
    }

    @Test
    public void getSQL(){

        dynamicSQL.selectQ();
    }

    @Test
    public void contextLoads() {
        JsonObject json = new JsonObject();
        json.addProperty("unit","计算机信息学院");
        json.addProperty("certificatetype","学生证,身份证");
        String sql = json.toString();
        JsonObject js = new JsonObject();
        System.out.println(sql);
        JSONObject jsonObject = JSONObject.parseObject(sql);
        System.out.println(jsonObject.getString("certificatetype"));

        List<String>registrationForms1 = respository.findINfo("计算机信息学院");
        for (String registrationForm:registrationForms1){
            System.out.println(registrationForm);
        }
        List<RegistrationForm> registrationForms = respository.findAll();
        List<RegistrationForm> registrationFormList =new ArrayList<RegistrationForm>();
        for (RegistrationForm registrationForm:registrationForms){
            if ((jsonObject.getString("certificatetype").contains(registrationForm.getCertificateType())) && (registrationForm.getUnit().equals(jsonObject.getString("unit")))){
                registrationFormList.add(registrationForm);
            }
        }
        for (RegistrationForm form:registrationFormList){
            System.out.println(form.getApprovalNumber());
        }


       /* HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("DocumentNo","123");
        hashMap.put("PartNo","10");
        hashMap.put("Title","xixi");
        tablesService.updateData("T_v5fqrpkd","2018-1WS0908.3-171",hashMap);*/
        /*Field field1 = new Field();
        field1.setFieldName("序号");
        field1.setFieldEnglishName("No");
        field1.setFieldType("int");
        field1.setFieldLength(11);
        field1.setFieldIndex(true);

        Field field2 = new Field();
        field2.setFieldName("档案号");
        field2.setFieldEnglishName("DocumentNo");
        field2.setFieldType("varchar");
        field2.setFieldLength(50);
        field2.setFieldIndex(true);

        Field field3 = new Field();
        field3.setFieldName("案卷号");
        field3.setFieldEnglishName("PartNo");
        field3.setFieldType("int");
        field3.setFieldLength(11);
        field3.setFieldIndex(false);

        Field field4 = new Field();
        field4.setFieldName("题名");
        field4.setFieldEnglishName("Title");
        field4.setFieldType("varchar");
        field4.setFieldLength(255);
        field4.setFieldIndex(true);
        field4.setFieldIk(true);

        Field field5 = new Field();
        field5.setFieldName("责任者");
        field5.setFieldEnglishName("PersonLiable");
        field5.setFieldType("varchar");
        field5.setFieldLength(50);
        field5.setFieldIndex(true);

        Field field6 = new Field();
        field6.setFieldName("校区代号");
        field6.setFieldEnglishName("CampusCode");
        field6.setFieldType("varchar");
        field6.setFieldLength(50);
        field6.setFieldIndex(true);

        Field field7 = new Field();
        field7.setFieldName("归档单位");
        field7.setFieldEnglishName("ArchivingDep");
        field7.setFieldType("varchar");
        field7.setFieldLength(50);
        field7.setFieldIndex(true);

        Field field8 = new Field();
        field8.setFieldName("实体分类号");
        field8.setFieldEnglishName("ClassificationNo");
        field8.setFieldType("varchar");
        field8.setFieldLength(50);
        field8.setFieldIndex(false);

        Field field9 = new Field();
        field9.setFieldName("页数");
        field9.setFieldEnglishName("PageNo");
        field9.setFieldType("int");
        field9.setFieldLength(11);
        field9.setFieldIndex(false);

        Field field10 = new Field();
        field10.setFieldName("附件");
        field10.setFieldEnglishName("Annex");
        field10.setFieldType("varchar");
        field10.setFieldLength(255);
        field10.setFieldIndex(false);

        Field field11 = new Field();
        field11.setFieldName("日期");
        field11.setFieldEnglishName("Date");
        field11.setFieldType("date");
        field11.setFieldIndex(true);

        Field field12 = new Field();
        field12.setFieldName("密级");
        field12.setFieldEnglishName("SecurityClassification");
        field12.setFieldType("varchar");
        field12.setFieldLength(50);
        field12.setFieldIndex(false);

        Field field13 = new Field();
        field13.setFieldName("备注");
        field13.setFieldEnglishName("Remarks");
        field13.setFieldType("varchar");
        field13.setFieldLength(100);
        field13.setFieldIndex(false);

        Field field14 = new Field();
        field14.setFieldName("最近修改时间");
        field14.setFieldEnglishName("LAST_MODIFIED");
        field14.setFieldType("TIMESTAMP");
        field14.setFieldLength(100);
        field14.setFieldIndex(false);

        Field field15 = new Field();
        field15.setFieldName("是否被删除");
        field15.setFieldEnglishName("IS_DEL");
        field15.setFieldType("int");
        field15.setFieldLength(11);
        field15.setFieldIndex(false);

        Field field16 = new Field();
        field16.setFieldName("表格号");
        field16.setFieldEnglishName("TABLE_ID");
        field16.setFieldType("int");
        field16.setFieldLength(11);
        field16.setFieldIndex(false);

        Field field17 = new Field();
        field17.setFieldName("文号");
        field17.setFieldEnglishName("ReferenceNo");
        field17.setFieldType("varchar");
        field17.setFieldLength(123);
        field17.setFieldIndex(true);

        Field field18 = new Field();
        field18.setFieldName("保管期限");
        field18.setFieldEnglishName("RetentionPeriod");
        field18.setFieldType("varchar");
        field18.setFieldLength(123);
        field18.setFieldIndex(true);

        Field field19 = new Field();
        field19.setFieldName("归档时间");
        field19.setFieldEnglishName("FilingTime");
        field19.setFieldType("varchar");
        field19.setFieldLength(123);
        field19.setFieldIndex(false);

        Field field20 = new Field();
        field20.setFieldName("姓名");
        field20.setFieldEnglishName("Name");
        field20.setFieldType("varchar");
        field20.setFieldLength(123);
        field20.setFieldIndex(true);

        Field field21 = new Field();
        field21.setFieldName("获奖名称");
        field21.setFieldEnglishName("Prize");
        field21.setFieldType("varchar");
        field21.setFieldLength(123);
        field21.setFieldIndex(true);

        Field field22 = new Field();
        field22.setFieldName("获奖时间");
        field22.setFieldEnglishName("PrizeTime");
        field22.setFieldType("varchar");
        field22.setFieldLength(123);
        field22.setFieldIndex(false);

        Field field23 = new Field();
        field23.setFieldName("收到时间");
        field23.setFieldEnglishName("ReceiveTime");
        field23.setFieldType("varchar");
        field23.setFieldLength(123);
        field23.setFieldIndex(false);

        Field field24 = new Field();
        field24.setFieldName("来源");
        field24.setFieldEnglishName("Source");
        field24.setFieldType("varchar");
        field24.setFieldLength(123);
        field24.setFieldIndex(false);

        Field field25 = new Field();
        field25.setFieldName("份数");
        field25.setFieldEnglishName("CopieNo");
        field25.setFieldType("int");
        field25.setFieldLength(11);
        field25.setFieldIndex(false);

        Field field26 = new Field();
        field26.setFieldName("名称");
        field26.setFieldEnglishName("Designation");
        field26.setFieldType("varchar");
        field26.setFieldLength(123);
        field26.setFieldIndex(false);

        Field field27 = new Field();
        field27.setFieldName("处理号");
        field27.setFieldEnglishName("TransactionNo");
        field27.setFieldType("int");
        field27.setFieldLength(11);
        field27.setFieldIndex(false);

        Field field28 = new Field();
        field28.setFieldName("合作者");
        field28.setFieldEnglishName("Collaborator");
        field28.setFieldType("varchar");
        field28.setFieldLength(123);
        field28.setFieldIndex(true);

        Field field29 = new Field();
        field29.setFieldName("规格");
        field29.setFieldEnglishName("Specifications");
        field29.setFieldType("varchar");
        field29.setFieldLength(123);
        field29.setFieldIndex(true);

        Field field30 = new Field();
        field30.setFieldName("主题词");
        field30.setFieldEnglishName("Subject");
        field30.setFieldType("varchar");
        field30.setFieldLength(123);
        field30.setFieldIndex(true);


        List<Field> list = new ArrayList<>();
        list.add(field1);
        list.add(field2);
        list.add(field3);
        list.add(field4);
        list.add(field5);
        list.add(field6);
        list.add(field7);
        list.add(field8);
        list.add(field9);
        list.add(field10);
        list.add(field11);
        list.add(field12);
        list.add(field13);
        list.add(field14);
        list.add(field15);
        list.add(field16);
        list.add(field17);
        list.add(field18);
        list.add(field19);
        list.add(field20);
        list.add(field21);
        list.add(field22);
        list.add(field23);
        list.add(field24);
        list.add(field25);
        list.add(field26);
        list.add(field27);
        list.add(field28);
        list.add(field29);
        list.add(field30);
        fieldService.saveAll(list);

        List<Field> set1 = new ArrayList<>();
        set1.add(field1);
        set1.add(field6);
        set1.add(field7);
        set1.add(field8);
        set1.add(field3);
        set1.add(field17);
        set1.add(field2);
        set1.add(field4);
        set1.add(field9);
        set1.add(field5);
        set1.add(field10);
        set1.add(field11);
        set1.add(field18);
        set1.add(field12);
        set1.add(field13);
        set1.add(field19);

        Template t1 = new Template();
        t1.setFields(set1);
        t1.setTemplateName("文书模板");
        t1.setPrimaryKey(field2);
        t1.setTemplateDescription("文书档案-文书-文件目录所使用的模板");
        templateService.saveOne(t1);

        List<Field> set2 = new ArrayList<>();
        set2.add(field1);
        set2.add(field6);
        set2.add(field2);
        set2.add(field3);
        set2.add(field4);
        set2.add(field5);
        set2.add(field20);

        Template t2 = new Template();
        t2.setFields(set2);
        t2.setTemplateName("已故人事模板");
        t2.setPrimaryKey(field2);
        t2.setTemplateDescription("文书档案-已故人事-文件目录所使用的模板");
        templateService.saveOne(t2);

        List<Field> set3 = new ArrayList<>();
        set3.add(field1);
        set3.add(field2);
        set3.add(field21);
        set3.add(field22);

        Template t3 = new Template();
        t3.setFields(set3);
        t3.setTemplateName("暂存文件模板");
        t3.setPrimaryKey(field2);
        t3.setTemplateDescription("文书档案-暂存文件-文件目录所使用的模板");
        templateService.saveOne(t3);

        List<Field> set4 = new ArrayList<>();
        set4.add(field1);
        set4.add(field2);
        set4.add(field8);
        set4.add(field23);
        set4.add(field24);
        set4.add(field25);
        set4.add(field26);

        Template t4 = new Template();
        t4.setFields(set4);
        t4.setTemplateName("河海资料/文件资料汇编模板");
        t4.setPrimaryKey(field2);
        t4.setTemplateDescription("文书档案-河海资料/文件资料汇编-文件目录所使用的模板");
        templateService.saveOne(t4);

        List<Field> set5 = new ArrayList<>();
        set5.add(field1);
        set5.add(field2);
        set5.add(field27);
        set5.add(field4);
        set5.add(field5);
        set5.add(field28);
        set5.add(field26);
        set5.add(field25);
        set5.add(field18);
        set5.add(field7);
        set5.add(field8);
        set5.add(field12);
        set5.add(field29);
        set5.add(field30);

        Template t5 = new Template();
        t5.setFields(set5);
        t5.setTemplateName("文书案卷");
        t5.setPrimaryKey(field2);
        t5.setTemplateDescription("文书案卷-案卷目录/卷内目录");
        templateService.saveOne(t5);


        Menu menu1 = menuService.addMenu(null,"预立库","","预立库");
        Menu menu2 = menuService.addMenu(menu1,"文书档案","文书档案","预立库");
        Menu menu3 = menuService.addMenu(menu2,"文书","","预立库");
        Menu menu4 = menuService.addMenu(menu3,"文件目录","","预立库");
        Menu menu5 = menuService.addMenu(menu2,"已故人事","","预立库");
        Menu menu6 = menuService.addMenu(menu5,"文件目录","","预立库");
        Menu menu7 = menuService.addMenu(menu2,"暂存文件","","预立库");
        Menu menu8 = menuService.addMenu(menu7,"文件目录","","预立库");
        Menu menu9 = menuService.addMenu(menu2,"河海资料","","预立库");
        Menu menu10 = menuService.addMenu(menu9,"文件目录","","预立库");
        Menu menu11 = menuService.addMenu(menu2,"文件资料汇编","","预立库");
        Menu menu12 = menuService.addMenu(menu11,"文件目录","","预立库");
        Menu menu13 = menuService.addMenu(menu2,"文书案卷","","预立库");
        Menu menu14 = menuService.addMenu(menu13,"案卷目录","","预立库");
        Menu menu15 = menuService.addMenu(menu13,"卷内目录","","预立库");
        Menu menu16 = menuService.addMenu(menu1,"教学档案","教学档案","预立库");
        Menu menu17 = menuService.addMenu(menu16,"学籍卡","","预立库");
        Menu menu18 = menuService.addMenu(menu17,"案卷目录","","预立库");
        Menu menu19 = menuService.addMenu(menu17,"卷内目录","","预立库");

        Menu menu20 = menuService.addMenu(null,"整理库","","整理库");
        Menu menu21 = menuService.addMenu(menu20,"文书档案","文书档案","整理库");
        Menu menu22 = menuService.addMenu(menu21,"文书","","整理库");
        Menu menu23 = menuService.addMenu(menu22,"文件目录","","整理库");
        Menu menu24 = menuService.addMenu(menu21,"已故人事","","整理库");
        Menu menu25 = menuService.addMenu(menu24,"文件目录","","整理库");
        Menu menu26 = menuService.addMenu(menu21,"暂存文件","","整理库");
        Menu menu27 = menuService.addMenu(menu26,"文件目录","","整理库");
        Menu menu28 = menuService.addMenu(menu21,"河海资料","","整理库");
        Menu menu29 = menuService.addMenu(menu28,"文件目录","","整理库");
        Menu menu30 = menuService.addMenu(menu21,"文件资料汇编","","整理库");
        Menu menu31 = menuService.addMenu(menu30,"文件目录","","整理库");
        Menu menu32 = menuService.addMenu(menu21,"文书案卷","","整理库");
        Menu menu33 = menuService.addMenu(menu32,"案卷目录","","整理库");
        Menu menu34 = menuService.addMenu(menu32,"卷内目录","","整理库");
        Menu menu35 = menuService.addMenu(menu20,"教学档案","教学档案","整理库");
        Menu menu36 = menuService.addMenu(menu35,"学籍卡","","整理库");
        Menu menu37 = menuService.addMenu(menu36,"案卷目录","","整理库");
        Menu menu38 = menuService.addMenu(menu36,"卷内目录","","整理库");

        Menu menu39 = menuService.addMenu(null,"档案库","","档案库");
        Menu menu40 = menuService.addMenu(menu39,"文书档案","文书档案","档案库");
        Menu menu41 = menuService.addMenu(menu40,"文书","","档案库");
        Menu menu42 = menuService.addMenu(menu41,"文件目录","","档案库");
        Menu menu43 = menuService.addMenu(menu40,"已故人事","","档案库");
        Menu menu44 = menuService.addMenu(menu43,"文件目录","","档案库");
        Menu menu45 = menuService.addMenu(menu40,"暂存文件","","档案库");
        Menu menu46 = menuService.addMenu(menu45,"文件目录","","档案库");
        Menu menu47 = menuService.addMenu(menu40,"河海资料","","档案库");
        Menu menu48 = menuService.addMenu(menu47,"文件目录","","档案库");
        Menu menu49 = menuService.addMenu(menu40,"文件资料汇编","","档案库");
        Menu menu50 = menuService.addMenu(menu49,"文件目录","","档案库");
        Menu menu51 = menuService.addMenu(menu40,"文书案卷","","档案库");
        Menu menu52 = menuService.addMenu(menu51,"案卷目录","","档案库");
        Menu menu53 = menuService.addMenu(menu51,"卷内目录","","档案库");
        Menu menu54 = menuService.addMenu(menu39,"教学档案","教学档案","档案库");
        Menu menu55 = menuService.addMenu(menu54,"学籍卡","","档案库");
        Menu menu56 = menuService.addMenu(menu55,"案卷目录","","档案库");
        Menu menu57 = menuService.addMenu(menu55,"卷内目录","","档案库");*/




        // 用模板tb_test2的使用的是field1 field2
        /*tablesService.generateTablesByTemplateId(1,"tb_test1");
        tablesService.generateTablesByTemplateId(1,"tb_test2");*/


        /*menuService.updateMenuTableId(1,4);
        menuService.updateMenuTableId(2,6);*/

//        System.out.println(tablesService.queryDataFromDatabase("1").toString());
        // menuService.editMenuOrder(2,"M_m1mhn6pf");

        /*List<Menu> menuList = menuService.getAllMenuByOrder();
        System.out.println(menuList);*/
        //System.out.println(menuService.getMenuRoot());

        /*menuService.deleteMenuByMenuId(14);*/

        /*Menu menu10 = new Menu();
        menu10.setMenuName("科研档案");
        menuService.addMenu(menu1,menu10);*/

        /*HashMap<String,String> map = new HashMap<>();
        map.put("No","10");
        map.put("DocumentNo","dasdsaas");
        map.put("Title","河海大学");
        tablesService.InsertData("Table_tb_test1_2019-04-24 10:13:26",map);*/

        /*HashMap<String,String> map = new HashMap<>();
        map.put("No","20");
        map.put("DocumentNo","1111");
        map.put("Title","河海大学test");
        tablesService.InsertData("Table_tb_test1_2019-04-23 10:34:26",map);*/

        /*tablesService.deleteDate("Table_tb_test1_2019-04-23 10:34:26","DocumentNo","1111");*/
    }
}
