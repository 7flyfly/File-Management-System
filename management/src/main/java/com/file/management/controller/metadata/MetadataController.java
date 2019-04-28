package com.file.management.controller.metadata;


import com.file.management.pojo.metadata.Field;
import com.file.management.pojo.metadata.Template;
import com.file.management.service.metadata.FieldService;
import com.file.management.service.metadata.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/SystemManagement")
public class MetadataController {
    @Autowired
    TemplateService templateService;

    @Autowired
    FieldService fieldService;

    /*
   元数据
   */
    @RequestMapping("/Metadata")
    public String Metadata(){
        return "SystemManagement/Metadata";
    }

    /*
    元数据模板
    */
    @RequestMapping("/MetadataTemplate")
    public String MetadataTemplate(Model model){
        model.addAttribute("templates",templateService.getAllTemplates());
        return "SystemManagement/MetadataTemplate";
    }

    /*
     元数据模板查看模板详情
    */
    @RequestMapping("/MetadataTemplate/{TemplateUuid}")
    public String MetadataTemplateDetails(@PathVariable("TemplateUuid") String TemplateUuid, Model model){
        Template template = templateService.getTemplateByTemplateUuid(TemplateUuid);
        model.addAttribute("template",template);
        return "SystemManagement/TemplateDetails";
    }

    /*
      元数据管理添加模板成功
     */
    @RequestMapping("/postTemplateData")
    public String returnTemplate(@RequestBody Map<String,Object> map, HttpServletResponse httpServletResponse){
        Field fieldPk = new Field();
        fieldPk.setFieldName((String)map.get("fieldName"));
        fieldPk.setFieldEnglishName((String)map.get("fieldEnglishName"));
        fieldPk.setFieldLength(Integer.parseInt((String)map.get("fieldLength")));
        fieldPk.setFieldType((String)map.get("fieldType"));
        fieldPk.setFieldIndex(Boolean.parseBoolean((String)map.get("fieldIndex")));
        fieldPk.setFieldIk(Boolean.parseBoolean((String)map.get("fieldIk")));
        fieldService.saveOne(fieldPk);

        Set<Field> fields = new HashSet<>();
        fields.add(fieldPk);

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine se = manager.getEngineByName("js");

        for(int i=0;i<Integer.parseInt((String)map.get("stringA"));i++){
            Field field = new Field();
            field.setFieldName(((ArrayList<String>)map.get("fieldNames")).get(i));
            field.setFieldEnglishName(((ArrayList<String>)map.get("fieldEnglishNames")).get(i));
            field.setFieldLength(Integer.parseInt(((ArrayList<String>)map.get("fieldLengths")).get(i)));
            field.setFieldType(((ArrayList<String>)map.get("fieldTypes")).get(i));
            field.setFieldIndex(Boolean.parseBoolean(((ArrayList<String>)map.get("fieldIndexs")).get(i)));
            field.setFieldIk(Boolean.parseBoolean(((ArrayList<String>)map.get("fieldIks")).get(i)));
            fieldService.saveOne(field);
            fields.add(field);
        }

        Template template = new Template();
        template.setTemplateName((String)map.get("templateName"));
        template.setTemplateDescription((String)map.get("templateDescription"));
        template.setFields(fields);
        template.setPrimaryKey(fieldPk);
        templateService.saveOne(template);

        return "SystemManagement/MetadataManagement";
    }

    /*
    元数据管理
    */
    @RequestMapping("/MetadataManagement")
    public String MetadataManagement(Model model){
        return "SystemManagement/MetadataManagement";
    }
}
