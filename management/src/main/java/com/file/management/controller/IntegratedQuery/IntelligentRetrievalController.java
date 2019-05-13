package com.file.management.controller.IntegratedQuery;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.file.management.pojo.*;
import com.file.management.service.ImageProcessing.ImagePHashService;
import com.file.management.service.solr.SolrDataConfigService;
import com.file.management.service.solr.SolrQueryService;
import com.file.management.service.solr.SolrService;
import com.file.management.utils.SolrUtils;
import com.file.management.utils.XsteamUtil;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.dom4j.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/IntegratedQuery/IntelligentRetrieval")
public class IntelligentRetrievalController {
    @Autowired
    private SolrService solrService;
    @Autowired
    private SolrQueryService solrQueryService;
    @Autowired
    private ImagePHashService imagePHashService;

    @RequestMapping(value = "/KeySearch")
    @ResponseBody
    /**
     * 根据关键词查询结果,并返回给bootStrapTable
     */
    public String keySerach(String keyword, String queryType, String tableId, String pageSize, String offset){
        SolrClient solrClient = null;
        JSONObject result_jsonObject = new JSONObject();
        try {
            if(tableId==null) tableId = "";
            SolrUtils solrUtils = new SolrUtils();
            solrClient = solrUtils.createSolrClient();
            System.out.println(keyword);
            JSONObject docsJsonObject = solrQueryService.queryKeywordbySolr(solrClient,keyword,tableId,pageSize,
                    offset);
            solrClient.close();
            result_jsonObject.put("rows",docsJsonObject.getJSONArray("documentList"));
            result_jsonObject.put("total",docsJsonObject.getString("numFound"));
            System.out.println(result_jsonObject);
            return result_jsonObject.toString();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                solrClient.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            result_jsonObject.put("result","error");
            result_jsonObject.put("message",e.getMessage());
            return result_jsonObject.toString();
        }
    }

    @RequestMapping(value = "/ImageUpload")
    @ResponseBody
    /**
     * 获取上传的图片信息，返回PHash编码
     */
    public String imageUpload(@RequestParam(value="upLoadImages",required=false) MultipartFile[] upLoadImages,
                              @RequestParam(value="keyword",required=false) String keyword, String queryType){
        JSONObject result_jsonObject = new JSONObject();
        try {
            if(upLoadImages.length>1){
                result_jsonObject.put("result","error");
                result_jsonObject.put("message","只能上传一张图片");
                return  result_jsonObject.toString();
            }else{
                MultipartFile upLoadImage = upLoadImages[0];
                String upLoadImagePHash = imagePHashService.getPHash(upLoadImage.getInputStream());
                result_jsonObject.put("result","success");
                result_jsonObject.put("upLoadImagePHash",upLoadImagePHash);
            }
        } catch (IOException e) {
            e.printStackTrace();
            result_jsonObject.put("result","error");
            result_jsonObject.put("message",e.getMessage());
            return  result_jsonObject.toString();
        }
        return  result_jsonObject.toString();
    }

    @RequestMapping(value = "/ImageSearch")
    @ResponseBody
    /**
     * 根据图片的PHash等信息查询档案，并返回给bootStrapTable
     */
    public String imageSearch(String keyword, String queryType, String upLoadImagePHash, String tableId,
                              String pageSize, String offset){
        SolrClient solrClient = null;
        JSONObject result_jsonObject = new JSONObject();
        try {
            if(tableId==null) tableId = "";
            SolrUtils solrUtils = new SolrUtils();
            solrClient = solrUtils.createSolrClient();
            System.out.println(keyword);
            JSONObject docsJsonObject = solrQueryService.imageSearchbySolr(solrClient,keyword,upLoadImagePHash,
                    tableId, pageSize, offset,"phash_numsplit");
            solrClient.close();
            result_jsonObject.put("rows",docsJsonObject.getJSONArray("documentList"));
            result_jsonObject.put("total",docsJsonObject.getString("numFound"));
            System.out.println(result_jsonObject);
            return result_jsonObject.toString();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                solrClient.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            result_jsonObject.put("result","error");
            result_jsonObject.put("message",e.getMessage());
            return result_jsonObject.toString();
        }
    }
    @RequestMapping(value = "/getDetail")
    @ResponseBody
    /**
     * 根据DocumentNumber查询档案详情
     */
    public String getDetail(String dataJson){
        JSONObject result_jsonObject = new JSONObject();
        try {
            JSONObject jsonObject = (JSONObject)JSONObject.parse(dataJson);
            System.out.println("getDetail/table_id+document_number:" + jsonObject);
            String table_id =  jsonObject.getString("table_id");
            String document_number =  jsonObject.getString("document_number");
            if(table_id!=null&&document_number!=null){
                result_jsonObject = solrQueryService.queryDocumentFromDatabase(table_id,document_number,
                        "DocumentNo","Annex",";");
                return result_jsonObject.toString();
            }else{
                result_jsonObject.put("result","error");
                result_jsonObject.put("message","失败！关键字不能为空");
                return result_jsonObject.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            result_jsonObject.put("result","error");
            result_jsonObject.put("message",e.getMessage());
            return result_jsonObject.toString();
        }
    }



//    @RequestMapping(value="/Delete/{id}")
//    @ResponseBody
//    /**
//     * 测试用，可删除
//     * 从solr中删除一条数据
//     */
//    public String deleteDocumentById(@PathVariable String id){
//        try {
//            SolrUtils solrUtils = new SolrUtils();
//            SolrClient solrClient = solrUtils.createSolrClient();
//            solrClient.deleteById(solrUtils.getCoreName(),id);
//            solrClient.commit(solrUtils.getCoreName());
//            return "success delete:" + id;
//        } catch (Exception e) {
//            e.printStackTrace();
//            Map<String,Object> map = new HashMap<>();
//            map.put("code","3");
//            map.put("message",e.getMessage());
//            return map.toString();
//        }
//    }

//    @GetMapping("/Insert")
//    @ResponseBody
//    /**
//     * 测试用，可删除
//     * 向solr中插入一条数据
//     */
//    public String solrInsert(){
//        SolrUtils solrUtils = new SolrUtils();
//        SolrClient solrClient = solrUtils.createSolrClient();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-hh:mm:ss");
//        String dataString = sdf.format(new Date());
//        try{
//            SolrInputDocument doc = new SolrInputDocument();
//            doc.addField("id",dataString);
//            doc.addField("test_text","河海大学的前身可以追溯到1915年创建于南京的“河海工程专门学校”");
//            doc.addField("test_owner","小明");
//            doc.addField("test_owner","小明");
//            solrClient.add(solrUtils.getCoreName(),doc);
//            solrClient.commit(solrUtils.getCoreName());
//        }catch(Exception e){
//            e.printStackTrace();
//            Map<String,Object> map = new HashMap<>();
//            map.put("code","1");
//            map.put("message",e.getMessage());
//            return map.toString();
//        }
//        return "success";
//    }

//    @RequestMapping(value = "/Test/FullImportTabel/{tableName}")
//    @ResponseBody
//    /**
//     * 测试用，可删除
//     * 导入表，将表中的数据建立索引
//     * @param tableName 表名
//     * eg: http://localhost:8080/IntegratedQuery/IntelligentRetrieval/fullImportTabel/tb_3
//     */
//    public String fullImportTable2Solr(@PathVariable String tableName){
//        try{
//            Map<String,Object> map = new HashMap<>();
//            if(tableName!=null&&!tableName.equals("")){
//                System.out.println("tableName:"+tableName);
//                SolrUtils solrUtils = new SolrUtils();
//                SolrClient solrClient = solrUtils.createSolrClient();
//                boolean bool = solrService.fullImportTable(solrClient,tableName);
//                solrClient.close();
//                if(bool){
//                    map.put("result","成功导入表："+tableName);
//                    return map.toString();
//                }else{
//                    map.put("result",tableName+"导入失败!");
//                    return map.toString();
//                }
//
//            }else{
//                map.put("result","失败! 表名不能为空");
//                return map.toString();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            Map<String,Object> map = new HashMap<>();
//            map.put("code","1");
//            map.put("message",e.getMessage());
//            return map.toString();
//        }
//    }

//    @RequestMapping(value = "/Test/DeltaImportTabel/{tableName}")
//    @ResponseBody
//    /**
//     * 测试 可删除
//     * 增量导入表，将表中的数据建立索引，根据DocumentNumber修改索引，根据软删除标识删除索引
//     * 更改软删除标识可以实现回滚
//     * @param tableName 表名
//     * eg: http://localhost:8080/IntegratedQuery/IntelligentRetrieval/deltaImportTabel/tb_3
//     */
//    public String deltaImportTable2Solr(@PathVariable String tableName){
//        try{
//            Map<String,Object> map = new HashMap<>();
//            if(tableName!=null&&!tableName.equals("")){
//                System.out.println("tableName:"+tableName);
//                SolrUtils solrUtils = new SolrUtils();
//                SolrClient solrClient = solrUtils.createSolrClient();
//                boolean bool = solrService.deltaImportTable(solrClient,tableName);
//                solrClient.close();
//                if(bool){
//                    map.put("result","成功增量导入表："+tableName);
//                    return map.toString();
//                }else{
//                    map.put("result",tableName+"增量导入失败!");
//                    return map.toString();
//                }
//            }else{
//                map.put("result","失败! 表名不能为空");
//                return map.toString();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            Map<String,Object> map = new HashMap<>();
//            map.put("code","1");
//            map.put("message",e.getMessage());
//            return map.toString();
//        }
//    }

//    @RequestMapping(value="/Test/AddDataSources2SolrDataConfig")
//    @ResponseBody
//    /**
//     * 测试用，可删除
//     * 新增需要将数据变为索引的数据库信息
//     */
//    public String addDataSources2SolrDataConfig(){
//        try {
//            Map<String,Object> map = new HashMap<>();
//            SolrDataConfig solrDataConfig = solrDataConfigService.getSolrDataConfig();
//            List<SolrDataSource> solrDataSourcesList = solrDataConfig.getSolrDataSourceList();
//            solrDataSourcesList = solrDataConfigService.addDataSource(solrDataSourcesList,"db_test","com.mysql.jdbc.Driver",
//                    "jdbc:mysql://localhost:3306/db_test","root","123456");
//            solrDataConfig.setSolrDataSourceList(solrDataSourcesList);  //覆盖原有solrDataSourcesList
//            boolean bool = solrDataConfigService.saveSolrDataConfig(solrDataConfig);
//            map.put("result",bool);
//            return map.toString();
//        }catch(Exception e){
//            Map<String,Object> map = new HashMap<>();
//            map.put("code","1");
//            map.put("message",e.getMessage());
//            return map.toString();
//        }
//    }

//    @RequestMapping(value="/Test/AddTableEntity2SolrDataConfig")
//    @ResponseBody
//    /**
//     * 测试用，可删除
//     * 新增需要将数据变为索引的数据库表的信息
//     */
//    public String addTableEntity2SolrDataConfig(){
//        try {
//            Map<String,Object> map = new HashMap<>();
//            SolrDataConfig solrDataConfig = solrDataConfigService.getSolrDataConfig();
//            //一个solrEntityDocumentList中只有一个元素
//            List<SolrEntityDocument>  solrEntityDocumentList = solrDataConfig.getSolrEntityDocumentList();
//            //solrEntityDocument中存放solrTableEntityList
//            List<SolrTableEntity> solrTableEntityList = solrEntityDocumentList.get(0).getSolrTableEntityList();
//            if(solrTableEntityList==null) solrTableEntityList = new ArrayList<SolrTableEntity>();
//            //确定数据库表中各字段在solr中对应的性质
//            String documentNumber = "FILE_NUMBER";
//            List<String> solrStringList = new ArrayList<String>();
//            solrStringList.add("ID");
//            solrStringList.add("PIECE_NUMBER");
//            solrStringList.add("NOTE1");
//            solrStringList.add("TABLE_ID");
//            List<String> solrIKList = new ArrayList<String>();
//            solrIKList.add("PERSON_LIABLE");
//            List<String> solrIKCopyList = new ArrayList<String>();
//            solrIKCopyList.add("TITLE");
//            solrIKCopyList.add("NOTE2");
//            List<String> solrDateList = new ArrayList<String>();
//            solrDateList.add("LAST_MODIFIED");
//
//            List<SolrTableEntityColumn> solrTableEntityColumnList = solrDataConfigService.createSolrTableEntityColumnList(
//                    documentNumber,solrStringList,null, null, null,
//                    solrIKList,solrIKCopyList,null, null,solrDateList,
//                    null,null,null);
//
//            solrTableEntityList = solrDataConfigService.addSolrTableEntity(solrTableEntityList,"tb_4","FILE_NUMBER",
//                    "db_test",solrTableEntityColumnList);
//
//            solrEntityDocumentList.get(0).setSolrTableEntityList(solrTableEntityList);
//            solrDataConfig.setSolrEntityDocumentList(solrEntityDocumentList);
//            boolean bool = solrDataConfigService.saveSolrDataConfig(solrDataConfig);
//            map.put("result",bool);
//            return map.toString();
//        }catch(Exception e){
//            Map<String,Object> map = new HashMap<>();
//            map.put("code","1");
//            map.put("message",e.getMessage());
//            return map.toString();
//        }
//    }

    @RequestMapping(value="/test/fulltest")
    @ResponseBody
    /**
     * 测试用，可删除
     */
    public String fullImportTable2Solr(){
        Map<String,Object> map = new HashMap<>();
        HashMap<Boolean,String> hashMap = solrService.fullImportTable2Solr("tb_test3",
                null,null,null,null,null);
        map.put("result",hashMap);
        return map.toString();
    }

    @RequestMapping(value="/test/deltaTest")
    @ResponseBody
    /**
     * 测试用，可删除
     */
    public String deltaImportTable2Solr(){
        Map<String,Object> map = new HashMap<>();
        HashMap<Boolean,String> hashMap = solrService.deltaImportTable2Solr("tb_test2",
                null,null,null,null,null);
        map.put("result",hashMap);
        return map.toString();
    }

    @RequestMapping(value="/test/oneTest")
    @ResponseBody
    /**
     * 测试用，可删除
     */
    public String oneImportTable2Solr(){
        Map<String,Object> map = new HashMap<>();
        HashMap<Boolean,String> hashMap = solrService.refreshOneDocument2Solr("tb_test2","2017-1WS0908.2-172",
                null,null,null,null,null);
        map.put("result",hashMap);
        return map.toString();
    }

//    @RequestMapping(value="/test/pHashTest")
//    @ResponseBody
//    /**
//     * 测试用，可删除
//     */
//    public String pHash(){
//        Map<String,Object> map = new HashMap<>();
//        for(int i = 1;i<=16; i++){
//            for(int j = 1;j<=16; j++){
//                int dif = imagePHashService.hammingDistance(imagePHashService.getPHash("http://192.168.0.104/documents/hh"+ i +".jpg"),
//                        imagePHashService.getPHash("http://192.168.0.104/documents/hh"+ j +".jpg"));
//                System.out.print(i + "-" + j + ":" + dif + "\n");
////                map.put("D:\\web\\documents\\hh"+ i +".jpg", i + "-" + j + ":" + dif );
//
//            }
////            String imagePHashStr = imagePHashService.getPHash("D:\\web\\documents\\hh"+ i +".jpg");
////            map.put("D:\\web\\documents\\hh"+ i +".jpg", imagePHashStr);
//        }
//        return map.toString();
//    }
}
