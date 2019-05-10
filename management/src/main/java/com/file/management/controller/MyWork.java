package com.file.management.controller;

import com.alibaba.fastjson.JSONObject;
import com.file.management.pojo.Menu;
import com.file.management.pojo.metadata.Tables;
import com.file.management.service.MenuService;
import com.file.management.service.metadata.FieldService;
import com.file.management.service.metadata.TablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
 我的工作
 */
@Controller
public class MyWork {
    @Autowired
    private MenuService menuService;

    @Autowired
    private TablesService tablesService;

    @Autowired
    private FieldService fieldService;

    @RequestMapping("/mywork")
    public String myWork(Model model){
        List<Menu> menuRoot = menuService.getMenuRoot();
        Menu menuYlj = new Menu();
        for(int i=0;i<menuRoot.size();i++){
            if(menuRoot.get(i).getMenuName().equals("预立卷")){
                menuYlj = menuRoot.get(i);
                break;
            }
        }
        model.addAttribute("jsondata",menuYlj.toString());
        return "mywork";
    }

    @RequestMapping("/main")
    public String main(){
        return  "main";
    }

    @RequestMapping("/emps")
    public String emps(){
        return  "emps";
    }

    @RequestMapping("/mywork/index")
    public String myworkIndex(){
        return "myworkIndex";
    }

    /*
      查看表格数据
     */
    @RequestMapping("/mywork/{tableId}")
    public String tableData(@PathVariable("tableId") String tableId, Model model) {
        JSONObject result_jsonObject = new JSONObject();
        List<Map<String,String>> listMap = tablesService.queryDataFromDatabase(tableId);
        model.addAttribute("tableData",listMap);
        List<String> titleList = tablesService.queryTitleFromDatabase(tableId);
        int documentNoIndex = -1;
        for(int i=0;i<titleList.size();i++){
            if(titleList.get(i).equals("档案号")){
                documentNoIndex = i;
            }
        }
        model.addAttribute("documentNoIndex",documentNoIndex);
        model.addAttribute("tableTitle",titleList);
        model.addAttribute("tableId",tableId);
        return "TableData";
    }

    /*
      添加数据
     */
    @RequestMapping("/addData")
    public void addData(@RequestBody Map<String,Object> map, HttpServletResponse httpServletResponse){
        String tableId = (String)map.get("tableId");
        int length = (Integer)map.get("length");
        ArrayList<String> value = (ArrayList<String>)map.get("value");
        HashMap<String,String> hashMap = new HashMap<>();
        for(int i=0;i<length;i++){
            hashMap.put(fieldService.getFieldByFieldName(value.get(2*i)).getFieldEnglishName(),value.get(2*i+1));
        }
        tablesService.InsertData(tablesService.getTablesByTableId(Integer.parseInt(tableId)).getTableUuid(),hashMap);
    }

    /*
      删除数据
     */
    @RequestMapping("/deleteData")
    public void deleteData(@RequestBody Map<String,Object> map, HttpServletResponse httpServletResponse){
        String tableId = (String)map.get("tableId");
        String documentNo = (String)map.get("documentNo");
        tablesService.deleteData(tablesService.getTablesByTableId(Integer.parseInt(tableId)).getTableUuid(),documentNo);
    }

    /*
      编辑数据
     */
    @RequestMapping("/editData")
    public void editData(@RequestBody Map<String,Object> map, HttpServletResponse httpServletResponse){
        String tableId = (String)map.get("tableId");
        String documentNo = (String)map.get("documentNo");
        int length = (Integer)map.get("length");
        ArrayList<String> value = (ArrayList<String>)map.get("value");
        HashMap<String,String> hashMap = new HashMap<>();
        for(int i=0;i<length;i++){
            if(value.get(2*i+1) != "") {
                hashMap.put(fieldService.getFieldByFieldName(value.get(2 * i)).getFieldEnglishName(), value.get(2 * i + 1));
            }
        }
        tablesService.updateData(tablesService.getTablesByTableId(Integer.parseInt(tableId)).getTableUuid(),documentNo,hashMap);
    }
}
