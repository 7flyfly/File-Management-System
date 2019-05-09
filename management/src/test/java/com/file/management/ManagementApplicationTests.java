package com.file.management;

import com.file.management.dao.MenuRepository;
import com.file.management.pojo.Menu;
import com.file.management.pojo.metadata.Field;
import com.file.management.pojo.metadata.Template;
import com.file.management.service.MenuService;
import com.file.management.service.metadata.FieldService;
import com.file.management.service.metadata.TablesService;
import com.file.management.service.metadata.TemplateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    private MenuRepository menuRepository;

    @Test
    public void contextLoads() {

        Field field1 = new Field();
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
        field3.setFieldName("案卷号/件号");
        field3.setFieldEnglishName("PartNo");
        field3.setFieldType("int");
        field3.setFieldLength(11);
        field3.setFieldIndex(false);

        Field field4 = new Field();
        field4.setFieldName("题名");
        field4.setFieldEnglishName("Title");
        field4.setFieldType("varchar");
        field4.setFieldLength(100);
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
        field10.setFieldLength(50);
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
        fieldService.saveAll(list);

       /* Set<Field> set = new LinkedHashSet<>();
        set.add(field1);
        set.add(field2);
        set.add(field3);
        set.add(field4);
        set.add(field5);

        Template t1 = new Template();
        t1.setFields(set);
        t1.setTemplateName("demo1");
        t1.setPrimaryKey(field2);
        t1.setTemplateDescription("第一个模板demo");
        templateService.saveOne(t1);

        Template t2 = new Template();
        t2.setFields(set);
        t2.setTemplateName("demo2");
        t2.setPrimaryKey(field2);
        t2.setTemplateDescription("第二个模板demo");
        templateService.saveOne(t2);

        Template t3 = new Template();
        t3.setFields(set);
        t3.setTemplateName("demo3");
        t3.setPrimaryKey(field2);
        t3.setTemplateDescription("第三个模板demo");
        templateService.saveOne(t3);

        Template t4 = new Template();
        t4.setFields(set);
        t4.setTemplateName("demo4");
        t4.setPrimaryKey(field2);
        t4.setTemplateDescription("第四个模板demo");
        templateService.saveOne(t4);

        Template t5 = new Template();
        t5.setFields(set);
        t5.setTemplateName("demo5");
        t5.setPrimaryKey(field2);
        t5.setTemplateDescription("第五个模板demo");
        templateService.saveOne(t5);

        Template t6 = new Template();
        t6.setFields(set);
        t6.setTemplateName("demo6");
        t6.setPrimaryKey(field2);
        t6.setTemplateDescription("第六个模板demo");
        templateService.saveOne(t6);

        Template t7 = new Template();
        t7.setFields(set);
        t7.setTemplateName("demo7");
        t7.setPrimaryKey(field2);
        t7.setTemplateDescription("第七个模板demo");
        templateService.saveOne(t7);

        Template t8 = new Template();
        t8.setFields(set);
        t8.setTemplateName("demo8");
        t8.setPrimaryKey(field2);
        t8.setTemplateDescription("第八个模板demo");
        templateService.saveOne(t8);

        Template t9 = new Template();
        t9.setFields(set);
        t9.setTemplateName("demo9");
        t9.setPrimaryKey(field2);
        t9.setTemplateDescription("第九个模板demo");
        templateService.saveOne(t9);

        Template t10 = new Template();
        t10.setFields(set);
        t10.setTemplateName("demo10");
        t10.setPrimaryKey(field2);
        t10.setTemplateDescription("第十个模板demo");
        templateService.saveOne(t10);

        Template t11 = new Template();
        t11.setFields(set);
        t11.setTemplateName("demo11");
        t11.setPrimaryKey(field2);
        t11.setTemplateDescription("第十一个模板demo");
        templateService.saveOne(t11);


        // 用模板tb_test2的使用的是field1 field2
        tablesService.generateTablesByUser(field2,set,"tb_test3");
        tablesService.generateTablesByTemplateId(1,"tb_test1");
        tablesService.generateTablesByTemplateId(1,"tb_test2");

        Menu menu1 = menuService.addMenu(null,"预立卷","","预立卷库");
        Menu menu2 = menuService.addMenu(menu1,"文书档案","文书档案","预立卷库");
        Menu menu3 = menuService.addMenu(menu2,"文书","","预立卷库");
        Menu menu4 = menuService.addMenu(menu3,"文件目录","","预立卷库");
        Menu menu5 = menuService.addMenu(menu2,"已故人事","","预立卷库");
        Menu menu6 = menuService.addMenu(menu5,"文件目录","","预立卷库");
        Menu menu7 = menuService.addMenu(menu2,"暂存文件","","预立卷库");
        Menu menu8 = menuService.addMenu(menu7,"文件目录","","预立卷库");
        Menu menu9 = menuService.addMenu(menu2,"河海资料","","预立卷库");
        Menu menu10 = menuService.addMenu(menu9,"文件目录","","预立卷库");
        Menu menu11 = menuService.addMenu(menu2,"文件资料汇编","","预立卷库");
        Menu menu12 = menuService.addMenu(menu11,"文件目录","","预立卷库");
        Menu menu13 = menuService.addMenu(menu2,"文书案卷","","预立卷库");
        Menu menu14 = menuService.addMenu(menu13,"案卷目录","","预立卷库");
        Menu menu15 = menuService.addMenu(menu13,"卷内目录","","预立卷库");
        Menu menu16 = menuService.addMenu(menu1,"教学档案","教学档案","预立卷库");
        Menu menu17 = menuService.addMenu(menu16,"学籍卡","","预立卷库");
        Menu menu18 = menuService.addMenu(menu17,"案卷目录","","预立卷库");
        Menu menu19 = menuService.addMenu(menu17,"卷内目录","","预立卷库");

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

        menuService.updateMenuTableId(1,4);
        menuService.updateMenuTableId(2,6);*/

        System.out.println(tablesService.queryDataFromDatabase("1").toString());
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
