package com.file.management.controller.metadata;


import com.file.management.pojo.Menu;
import com.file.management.pojo.metadata.Field;
import com.file.management.pojo.metadata.Tables;
import com.file.management.pojo.metadata.Template;
import com.file.management.service.MenuService;
import com.file.management.service.metadata.FieldService;
import com.file.management.service.metadata.TablesService;
import com.file.management.service.metadata.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("/metadata")
public class MetaDataController {
    @Autowired
    TemplateService templateService;

    @Autowired
    FieldService fieldService;

    @Autowired
    MenuService menuService;

    @Autowired
    TablesService tablesService;

    /*
   元数据
   */
    @RequestMapping("/Metadata")
    public String Metadata(){
        return "metadata/Metadata";
    }

    /*
    元数据模板
    */
    @RequestMapping("/MetadataTemplate")
    public String MetadataTemplate(Model model){
        model.addAttribute("templates",templateService.getAllTemplates());
        List<Field> fields = fieldService.getAllFields();
        Iterator<Field> it = fields.iterator();
        while(it.hasNext()){
            Field f = it.next();
            //序号和档案号已经默认添加，当遇到这样的字段时，移除出去，不显示
            if(f.getFieldName().equals("序号") || f.getFieldName().equals("档案号") || f.getFieldName().equals("档案号")){
                it.remove();
            }
        }
        model.addAttribute("fields",fields);
        return "metadata/MetadataTemplate";
    }

    /*
     元数据模板查看模板详情
    */
    @RequestMapping("/MetadataTemplate/{TemplateUuid}")
    public String MetadataTemplateDetails(@PathVariable("TemplateUuid") String TemplateUuid, Model model){
        Template template = templateService.getTemplateByTemplateUuid(TemplateUuid);
        model.addAttribute("template",template);
        return "metadata/TemplateDetails";
    }

    /*
      元数据模板添加模板
     */
    @RequestMapping("/postTemplateData")
    @ResponseBody
    public Map<String,String> returnTemplate(@RequestBody Map<String,Object> map, HttpServletResponse httpServletResponse){
        List<Field> fields = new ArrayList<>();
        Map<String,String> mapReturn = new HashMap<>();
        try {
            for (int i = 0; i < Integer.parseInt((String) map.get("stringA")); i++) {
                Field field = new Field();
                field.setFieldName(((ArrayList<String>) map.get("fieldNames")).get(i));
                field.setFieldEnglishName(((ArrayList<String>) map.get("fieldEnglishNames")).get(i));
                field.setFieldLength(Integer.parseInt(((ArrayList<String>) map.get("fieldLengths")).get(i)));
                field.setFieldType(((ArrayList<String>) map.get("fieldTypes")).get(i));
                field.setFieldIndex(Boolean.parseBoolean(((ArrayList<String>) map.get("fieldIndexes")).get(i)));
                field.setFieldIk(Boolean.parseBoolean(((ArrayList<String>) map.get("fieldIks")).get(i)));

                fieldService.saveOne(field);

                fields.add(field);
            }

            String str = (String) map.get("fieldSelected");
            String[] strArray = str.split("\\|\\|");

            for (String strEach : strArray) {
                fields.add(fieldService.getFieldByFieldName(strEach));
            }

            Field fieldPk = fieldService.getFieldByFieldName("档案号");
            fields.add(fieldPk);
            Field fieldNo = fieldService.getFieldByFieldName("序号");
            fields.add(fieldNo);
            Field filedLastMod = fieldService.getFieldByFieldName("最后修改时间");
            fields.add(filedLastMod);

            Template template = new Template();
            template.setTemplateName((String) map.get("templateName"));
            template.setTemplateDescription((String) map.get("templateDescription"));
            template.setFields(fields);
            template.setPrimaryKey(fieldPk);
            templateService.saveOne(template);
            mapReturn.put("msg", "新增模板成功！");
            return mapReturn;
        }catch(Exception ex){
            mapReturn.put("msg", "新增模板失败！");
            return mapReturn;
        }
    }

    /*
    元数据管理
    */
    @RequestMapping("/MetadataManagement")
    public String MetadataManagement(Model model){
        List<Template> templateList = templateService.getAllTemplates();
        model.addAttribute("templateList",templateList);

        List<Menu> menuList = menuService.getAllMenuByOrder();
        for(Menu menu:menuList){
            String string = "";
            for(int i=menu.getMenuLevel();i>1;i--){
                string += "│ ";
            }
            if(menu.getMenuLevel() >= 1){
                string += "├ ";
            }
            menu.setMenuName(string + menu.getMenuName());
        }
        model.addAttribute("menuList",menuList);
        return "metadata/MetadataManagement";
    }
    /*
      元数据管理删除菜单
     */
    @RequestMapping("/deleteMenu")
    @ResponseBody
    public Map<String,String> MetadataManagementRemoveMenu(@RequestBody Map<String,Object> map){
        String menuUuid = (String)map.get("uuid");
        int res = menuService.deleteMenuByMenuUuid(menuUuid);
        Map<String,String> mapReturn = new HashMap<>();
        if(res == 0){
            mapReturn.put("msg","删除失败：节点不存在。");
        }else if(res == 1){
            mapReturn.put("msg","删除失败：节点是根节点，无法删除。");
        }else if(res == 2){
            mapReturn.put("msg","删除失败：节点的子节点非空，无法删除。");
        }else{
            mapReturn.put("msg","节点删除成功。");
        }
        return mapReturn;
    }

    /*
    元数据管理查看表单详情
   */
    @RequestMapping("/MetadataManagement/{menuId}")
    public String MetadataTablesDetails(@PathVariable("menuId") int menuId, Model model){
        Menu menu = menuService.getMenuByMenuId(menuId);
        Tables tables = menu.getMenuTable();
        List<Field> fieldList = new ArrayList<>(tables.getFields());
        model.addAttribute("fieldList",fieldList);
        return "metadata/TableDetails";
    }

    /*
      下拉菜单添加成功
     */
    @RequestMapping("/addMenu")
    public void returnMenu(@RequestBody Map<String,Object> map, HttpServletResponse httpServletResponse){
        String menuName = (String)map.get("menuName");
        String menuDescription = (String)map.get("menuDescription");
        String menuClassification = (String)map.get("menuClassification");
        String menuParentUuid = (String)map.get("uuid");
        Menu menuParent = menuService.getMenuByMenuUUid(menuParentUuid);
        menuService.addMenu(menuParent,menuName,menuDescription,menuClassification);
    }

    /*
       修改菜单
     */
    @RequestMapping("/saveMenuEdit")
    public void saveMenuEdit(@RequestBody Map<String,Object> map, HttpServletResponse httpServletResponse){
        String menuDescription = (String)map.get("menuDescription");
        String menuClassification = (String)map.get("menuClassification");
        String menuUuid = (String)map.get("uuid");
        String menuOrder = (String)map.get("menuOrder");
        menuService.updateMenuDescription(menuDescription,menuUuid);
        if(!menuClassification.equals("库类别")) {
            menuService.updateMenuClassification(menuClassification, menuUuid);
        }
        menuService.editMenuOrder(Integer.parseInt(menuOrder),menuUuid);
    }

    /*
       引入模板
     */
    @RequestMapping("/addTemplate")
    @ResponseBody
    public Map<String,String> addTemplate(@RequestBody Map<String,String> map,HttpServletResponse httpServletResponse){
        String menuUuid = map.get("uuid");
        String templateName = map.get("templateName");
        String tableName = map.get("tableName");
        Template template = templateService.getTemplateByTemplateName(templateName);
        Menu menu = menuService.getMenuByMenuUUid(menuUuid);
        Map<String,String> mapReturn = new HashMap<>();

        if(menu.getMenuChildren() == null || menu.getMenuChildren().isEmpty()) {
            tablesService.generateTablesByTemplateId(template.getTemplateId(),tableName);
            Tables tables = tablesService.getTablesByTableName(tableName);
            menuService.updateMenuTableId(tables.getTableId(), menu.getMenuId());
            mapReturn.put("msg","引入模板成功！");
        }
        else{
            mapReturn.put("msg","引入模板失败：节点非叶子节点。");
        }
        return mapReturn;
    }
}
