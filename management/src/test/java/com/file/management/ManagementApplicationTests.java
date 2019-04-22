package com.file.management;

import com.file.management.pojo.Field;
import com.file.management.pojo.Template;
import com.file.management.service.FieldService;
import com.file.management.service.TablesService;
import com.file.management.service.TemplateService;
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
    FieldService fieldService;
    @Autowired
    TablesService tablesService;
    @Autowired
    TemplateService templateService;

    @Test
    public void contextLoads() {
        /*Field field1 = new Field();
        field1.setFieldType("int");
        field1.setFieldLength(11);
        field1.setFieldPrimaryKey(true);
        field1.setFieldIndex(true);
        field1.setFieldEnglishName("No");
        field1.setFieldName("序号");

        Field field2 = new Field();
        field2.setFieldType("varchar");
        field2.setFieldLength(255);
        field2.setFieldPrimaryKey(false);
        field2.setFieldIndex(true);
        field2.setFieldEnglishName("DocumentNo");
        field2.setFieldName("档案号");

        Set<Field> set = new HashSet<>();
        set.add(field1);
        set.add(field2);

        Field field3 = new Field();
        field3.setFieldType("int");
        field3.setFieldLength(11);
        field3.setFieldPrimaryKey(true);
        field3.setFieldIndex(true);
        field3.setFieldEnglishName("NoTest");
        field3.setFieldName("序号Test");

        Field field4 = new Field();
        field4.setFieldType("varchar");
        field4.setFieldLength(255);
        field4.setFieldPrimaryKey(false);
        field4.setFieldIndex(true);
        field4.setFieldEnglishName("DocumentNoTest");
        field4.setFieldName("档案号Test");
        Set<Field> set2 = new HashSet<>();
        set2.add(field3);
        set2.add(field4);

        List<Field> list = new ArrayList<>();
        list.add(field1);
        list.add(field2);
        list.add(field3);
        list.add(field4);
        fieldService.saveAll(list);

        Template t = new Template();
        t.setFields(set);
        t.setTemplateName("demo1");
        templateService.saveOne(t);

        // 用模板tb_test2的使用的是field1 field2
        tablesService.generateTablesByTemplateId(1,"tb_test2");
        tablesService.generateTablesByTemplateId(1,"tb_test4");
        tablesService.generateTablesByTemplateId(1,"tb_test5");
        // 不用模板的tb_test3使用的是field3 field4
        tablesService.generateTablesByUser(set2,"tb_test3");*/

        HashMap<String,String> map = new HashMap<>();
        map.put("No","10");
        map.put("DocumentNo","dasdsaas");
        tablesService.InsertData("Table_tb_test2_2019-04-22 09:10:21",map);
    }
}
