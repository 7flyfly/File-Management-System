package com.file.management.controller;

import com.alibaba.fastjson.JSONObject;
import com.drew.tools.FileUtil;
import com.file.management.dao.DynamicSQL;
import com.file.management.pojo.Menu;
import com.file.management.pojo.metadata.Tables;
import com.file.management.service.DictionaryService;
import com.file.management.service.MenuService;
import com.file.management.service.metadata.FieldService;
import com.file.management.service.metadata.TablesService;
import com.file.management.utils.uploadFileUtils;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import net.sf.ehcache.util.TimeUtil;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.solr.common.util.Hash;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sun.security.pkcs11.wrapper.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileLockInterruptionException;
import java.util.*;


/*
 我的工作
 */
@Controller
@RequestMapping("/mywork")
public class MyWork {
    @Autowired
    private MenuService menuService;

    @Autowired
    private TablesService tablesService;

    @Autowired
    private FieldService fieldService;

    @Autowired
    private DictionaryService dictionaryService;

    @RequestMapping("/homepage")
    //@RequiresPermissions("mywork:homepage")//权限管理;
    public String myWork(Model model){
        List<Menu> menuRoot = menuService.getMenuRoot();
        Menu menuYlj = new Menu();
        Menu menuZlk = new Menu();
        Menu menuDak = new Menu();
        for(int i=0;i<menuRoot.size();i++){
            if(menuRoot.get(i).getMenuName().equals("预立库")){
                menuYlj = menuRoot.get(i);
            }else if(menuRoot.get(i).getMenuName().equals("整理库")){
                menuZlk = menuRoot.get(i);
            }else{
                menuDak = menuRoot.get(i);
            }
        }

        model.addAttribute("jsondataYlj",menuYlj.toString());
        model.addAttribute("jsondataZlk",menuZlk.toString());
        model.addAttribute("jsondataDak",menuDak.toString());
        return "mywork/homepage";
    }

    @RequestMapping("/main")
    public String main(){
        return  "main";
    }

    @RequestMapping("/emps")
    public String emps(){
        return  "emps";
    }

    @RequestMapping("/index")
    public String myworkIndex(){
        return "mywork/myworkIndex";
    }

    /*
      查看表格数据
     */
    @RequestMapping("/{tableId}")
    public String tableData(@PathVariable("tableId") String tableId, Model model) {
        JSONObject result_jsonObject = new JSONObject();
        List<Map<String,String>> listMap = tablesService.queryDataFromDatabase(tableId);
        model.addAttribute("tableData",listMap);
        List<String> titleList = tablesService.queryTitleFromDatabase(tableId);
        List<List<String>> listList = new ArrayList<>();
        for(String title:titleList){
            listList.add(dictionaryService.getListByName(title));
        }
        int documentNoIndex = -1;
        for(int i=0;i<titleList.size();i++){
            if(titleList.get(i).equals("档案号")){
                documentNoIndex = i;
            }
        }
        model.addAttribute("documentNoIndex",documentNoIndex);
        model.addAttribute("tableTitle",titleList);
        model.addAttribute("tableId",tableId);
        model.addAttribute("listList",listList);
        return "mywork/TableData";
    }

    /*
      添加数据
     */
    @RequestMapping("/addData")
    @ResponseBody
    public Map<String,String> addData(@RequestBody Map<String,Object> map, HttpServletResponse httpServletResponse){
        String tableId = (String)map.get("tableId");
        int length = (Integer)map.get("length");
        ArrayList<String> value = (ArrayList<String>)map.get("value");
        HashMap<String,String> hashMap = new HashMap<>();
        Map<String,String> mapReturn = new HashMap<>();
        for(int i=0;i<length;i++){
            if(fieldService.getFieldByFieldName(value.get(2*i)).getFieldName().equals("档案号")&&value.get(2*i+1).equals("")){
                mapReturn.put("msg","添加失败：档案号不能为空！");
                return mapReturn;
            }
            hashMap.put(fieldService.getFieldByFieldName(value.get(2*i)).getFieldEnglishName(),value.get(2*i+1));
        }
        String messageValue = tablesService.InsertData(tablesService.getTablesByTableId(Integer.parseInt(tableId)).getTableUuid(),hashMap);
        mapReturn.put("msg","成功添加数据！");
        return mapReturn;
    }

    /*
      删除数据
     */
    @RequestMapping("/deleteData")
    @ResponseBody
    public Map<String,String> deleteData(@RequestBody Map<String,Object> map, HttpServletResponse httpServletResponse){
        String tableId = (String)map.get("tableId");
        String documentNo = (String)map.get("documentNo");
        Map<String,String> mapReturn = new HashMap<>();
        String messageValue = tablesService.deleteData(tablesService.getTablesByTableId(Integer.parseInt(tableId)).getTableUuid(),documentNo);
        mapReturn.put("msg", "成功删除该数据！");
        return mapReturn;
    }

    /*
      批量删除数据
     */
    @RequestMapping("/deleteDatas")
    @ResponseBody
    public Map<String,String> deleteDatas(@RequestBody Map<String,Object> map, HttpServletResponse httpServletResponse){
        String tableId = (String)map.get("tableId");
        ArrayList<String> documentNos = (ArrayList<String>)map.get("documentNos");
        Map<String,String> mapReturn = new HashMap<>();
        String messageValue = "";
        if(documentNos.size()==0) {
            mapReturn.put("msg","请至少选择一条数据！");
            return mapReturn;
        }else {
            for (String documentNo : documentNos) {
                messageValue += tablesService.deleteData(tablesService.getTablesByTableId(Integer.parseInt(tableId)).getTableUuid(), documentNo);
            }
            mapReturn.put("msg", "成功批量删除数据！");
            return mapReturn;
        }
    }

    /*
      编辑数据
     */
    @RequestMapping("/editData")
    @ResponseBody
    public Map<String,String> editData(@RequestBody Map<String,Object> map, HttpServletResponse httpServletResponse){
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
        Map<String,String> mapReturn = new HashMap<>();
        String messageValue = tablesService.updateData(tablesService.getTablesByTableId(Integer.parseInt(tableId)).getTableUuid(),documentNo,hashMap);
        mapReturn.put("msg","成功编辑数据！");
        return mapReturn;
    }


    /**
     * 上传附件
     */
    @RequestMapping(value = "/addAnnex")
    @ResponseBody
    public String imageUpload(@RequestParam(value="upLoadFiles",required=false) MultipartFile[] upLoadFiles,
                              @RequestParam(value="tableId",required=false) String tableId,
                              @RequestParam(value="documentNo",required=false) String documentNo,
                              MultipartHttpServletRequest request) {
        try {

            if (upLoadFiles != null && upLoadFiles.length > 0) {

                String fileUrl = ""; //用来接收拼接各个图片的名字，并保存到数据库。
                for (int i = 0; i < upLoadFiles.length; i++) {
                    if (!upLoadFiles[i].isEmpty()) {
                        String rootUrl = "C:\\Users\\Administrator.PC-20190422MXKW.000\\Desktop\\documents";
                        fileUrl = fileUrl + "http://192.168.0.105/" + uploadFileUtils.uploadImage(rootUrl,request, upLoadFiles[i]);
                    }
                }
                //上传成功
                if (fileUrl != null && fileUrl.length() > 0) {
                    System.out.println("上传成功！" + fileUrl); //
                    tablesService.addAnnex(tablesService.getTablesByTableId(Integer.parseInt(tableId)).getTableUuid(), documentNo, fileUrl);
                } else {
                    System.out.println("上传失败！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

    /*
      删除附件
     */
    @RequestMapping("/deleteAnnex")
    @ResponseBody
    public Map<String,String> deleteAnnex(@RequestBody Map<String,Object> map, HttpServletResponse httpServletResponse){
        String tableId = (String)map.get("tableId");
        String documentNo = (String)map.get("documentNo");
        String annexName = (String)map.get("annexName");
        Map<String,String> mapReturn = new HashMap<>();

        Tables tables = tablesService.getTablesByTableId(Integer.parseInt(tableId));
        boolean flag = tablesService.deleteAnnex(tables,documentNo,annexName);
        if(flag){
            mapReturn.put("msg","删除附件成功！");
        }else{
            mapReturn.put("msg","删除失败：没有此附件");
        }

        return mapReturn;
    }

    /*
      移交归档
    */
    @RequestMapping("/archiving")
    @ResponseBody
    public Map<String,String> archiving(@RequestBody Map<String,Object> map, HttpServletResponse httpServletResponse){
        String tableId = (String)map.get("tableId");
        String documentNo = (String)map.get("documentNo");
        int length = (Integer)map.get("length");
        String value = (String)map.get("value");
        String[] values = value.split("\\|\\|");
        String menuClassification;
        Map<String,String> mapReturn = new HashMap<>();

        Menu menu = menuService.getMenuByTableId(Integer.parseInt(tableId));
        String menuName = menu.getMenuName();
        if(menu.getMenuClassification().equals("预立库")){
            menuClassification = "整理库";
        }else if(menu.getMenuClassification().equals("整理库")){
            menuClassification = "档案库";
        }else{
            mapReturn.put("msg","已在档案库，无法移库！");
            return mapReturn;
        }

        List<Menu> menuList = menuService.getMenuByMenuNameAndMenuClassification(menuName,menuClassification);

        // 不同类别的同种菜单
        Menu menuOtherClassification = new Menu();
        for(Menu m :menuList){
            if(m.getMenuParent().getMenuName().equals(menu.getMenuParent().getMenuName())){
                menuOtherClassification = m;
                break;
            }
        }

        // 在menuOtherClassification中添加这条数据

        HashMap<String,String> hashMap = new HashMap<>();
        for(int i=0;i<length;i++){
            if(!values[2*i].equals("序号") && !values[2*i].equals("最近修改时间") && !values[2*i].equals("操作")&&!values[2*i+1].equals("")) {
                hashMap.put(fieldService.getFieldByFieldName(values[2 * i]).getFieldEnglishName(), values[2 * i + 1]);
            }
        }
        String msg1 = tablesService.InsertData(menuOtherClassification.getMenuTable().getTableUuid(),hashMap);

        // 删除menu中的这条数据
        String msg2 = tablesService.deleteData(tablesService.getTablesByTableId(Integer.parseInt(tableId)).getTableUuid(),documentNo);
        mapReturn.put("msg", "移交归档成功！");
        return mapReturn;
    }



    /*
      批量移交归档
    */
    @RequestMapping("/archivings")
    @ResponseBody
    public Map<String,String> archivings(@RequestBody Map<String,Object> map, HttpServletResponse httpServletResponse){
        String tableId = (String)map.get("tableId");
        ArrayList<String> documentNos = (ArrayList<String>)map.get("documentNos");
        ArrayList<String> values = (ArrayList<String>)map.get("values");
        int length = (Integer)map.get("length");
        String menuClassification;
        Map<String,String> mapReturn = new HashMap<>();
        if(documentNos.size()==0) {
            mapReturn.put("msg","请至少选择一条数据！");
            return mapReturn;
        }else {
            Menu menu = menuService.getMenuByTableId(Integer.parseInt(tableId));
            String menuName = menu.getMenuName();
            if (menu.getMenuClassification().equals("预立库")) {
                menuClassification = "整理库";
            } else if (menu.getMenuClassification().equals("整理库")) {
                menuClassification = "档案库";
            } else {
                mapReturn.put("msg", "已在档案库，无法移库！");
                return mapReturn;
            }
            List<Menu> menuList = menuService.getMenuByMenuNameAndMenuClassification(menuName, menuClassification);

            // 不同类别的同种菜单
            Menu menuOtherClassification = new Menu();
            for (Menu m : menuList) {
                if (m.getMenuParent().getMenuName().equals(menu.getMenuParent().getMenuName())) {
                    menuOtherClassification = m;
                    break;
                }
            }

            String msg = "";
            // 在menuOtherClassification中添加这条数据
            for (int i = 0; i < values.size() / length / 2; i++) {
                HashMap<String, String> hashMap = new HashMap<>();
                for (int j = i * length; j < (i + 1) * length; j++) {
                    if (!values.get(2 * j).equals("序号") && !values.get(2 * j).equals("最近修改时间") && !values.get(2 * j).equals("操作") && !values.get(2 * j + 1).equals("")) {
                        hashMap.put(fieldService.getFieldByFieldName(values.get(2 * j)).getFieldEnglishName(), values.get(2 * j + 1));
                    }
                }
                String msg1 = tablesService.InsertData(menuOtherClassification.getMenuTable().getTableUuid(), hashMap);

                // 删除menu中的这条数据
                String msg2 = tablesService.deleteData(tablesService.getTablesByTableId(Integer.parseInt(tableId)).getTableUuid(), documentNos.get(i));

                msg += msg1 + msg2;
            }
            mapReturn.put("msg", "批量移交归档成功！");
            return mapReturn;
        }
    }

}
