package com.file.management;

import com.file.management.pojo.metadata.Field;
import com.file.management.pojo.metadata.Template;
import com.file.management.service.metadata.FieldService;
import com.file.management.service.metadata.TablesService;
import com.file.management.service.metadata.TemplateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        fieldService.saveAll(list);

        Set<Field> set = new HashSet<>();
        set.add(field1);
        set.add(field2);
        set.add(field3);
        set.add(field4);
        set.add(field5);

        Template t = new Template();
        t.setFields(set);
        t.setTemplateName("demo");
        t.setPrimaryKey(field2);
        templateService.saveOne(t);


        // 用模板tb_test2的使用的是field1 field2
        tablesService.generateTablesByUser(field2,set,"tb_test3");
        tablesService.generateTablesByTemplateId(1,"tb_test1");
        tablesService.generateTablesByTemplateId(1,"tb_test2");

        // 不用模板的tb_test3使用的是field3 field4
        // tablesService.generateTablesByUser(set2,"tb_test3");

        /*HashMap<String,String> map = new HashMap<>();
        map.put("No","10");
        map.put("DocumentNo","dasdsaas");
        map.put("Title","河海大学");
        tablesService.InsertData("Table_tb_test1_2019-04-22 22:28:34",map);*/

        // tablesService.deleteDate("Table_tb_test1_2019-04-22 22:28:34","DocumentNo","dasdsaas");
    }
}
