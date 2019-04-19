package com.file.management.controller.IntegratedQuery;

import com.file.management.service.solr.SolrService;
import com.file.management.utils.SolrUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/IntegratedQuery/IntelligentRetrieval")
public class IntelligentRetrievalController {
    @Autowired
    SolrService solrService;

    @RequestMapping(value = "/GetQueryRequest")
    @ResponseBody
    /**
     * 根据关键词查询结果 todo
     * 目前 根据DocumentNumber检索
     */
    public String getKeyWord(HttpServletRequest request){
        try {
            String keyword = request.getParameter("keyword");
            String optionsRadios = request.getParameter("optionsRadios");
            Map<String,Object> map = new HashMap<>();
            if("fullTextSearch".equals(optionsRadios)&&!(keyword==null&&"".equals(keyword))){
                System.out.println("keyword:"+keyword);
                SolrUtils solrUtils = new SolrUtils();
                SolrClient solrClient = solrUtils.createSolrClient();
                String result = solrService.getDoucmentByDocumentNumber(solrClient,keyword);
                solrClient.close();
                map.put("keyword",keyword);
                map.put("result",result);
                return map.toString();
            }else{
                map.put("result","失败！关键字不能为空");
                return map.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Map<String,Object> map = new HashMap<>();
            map.put("code","1");
            map.put("message",e.getMessage());
            return map.toString();
        }
    }

    @RequestMapping(value = "/fullImportTabel/{tableName}")
    @ResponseBody
    /**
     * 导入表，将表中的数据建立索引
     * @param tableName 表名
     * eg: http://localhost:8080/IntegratedQuery/IntelligentRetrieval/fullImportTabel/tb_3
     */
    public String fullImportTable2Solr(@PathVariable String tableName){
        try{
            Map<String,Object> map = new HashMap<>();
            if(tableName!=null&&!tableName.equals("")){
                System.out.println("tableName:"+tableName);
                SolrUtils solrUtils = new SolrUtils();
                SolrClient solrClient = solrUtils.createSolrClient();
                boolean bool = solrService.fullImportTableIntoSolr(solrClient,tableName);
                solrClient.close();
                if(bool){
                    map.put("result","成功导入表："+tableName);
                    return map.toString();
                }else{
                    map.put("result",tableName+"导入失败!");
                    return map.toString();
                }

            }else{
                map.put("result","失败! 表名不能为空");
                return map.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Map<String,Object> map = new HashMap<>();
            map.put("code","1");
            map.put("message",e.getMessage());
            return map.toString();
        }
    }

    @RequestMapping(value = "/deltaImportTabel/{tableName}")
    @ResponseBody
    /**
     * 增量导入表，将表中的数据建立索引，根据DocumentNumber修改索引，根据软删除标识删除索引
     * 更改软删除标识可以实现回滚
     * @param tableName 表名
     * eg: http://localhost:8080/IntegratedQuery/IntelligentRetrieval/deltaImportTabel/tb_3
     */
    public String deltaImportTable2Solr(@PathVariable String tableName){
        try{
            Map<String,Object> map = new HashMap<>();
            if(tableName!=null&&!tableName.equals("")){
                System.out.println("tableName:"+tableName);
                SolrUtils solrUtils = new SolrUtils();
                SolrClient solrClient = solrUtils.createSolrClient();
                boolean bool = solrService.deltaImportTableIntoSolr(solrClient,tableName);
                solrClient.close();
                if(bool){
                    map.put("result","成功增量导入表："+tableName);
                    return map.toString();
                }else{
                    map.put("result",tableName+"增量导入失败!");
                    return map.toString();
                }

            }else{
                map.put("result","失败! 表名不能为空");
                return map.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Map<String,Object> map = new HashMap<>();
            map.put("code","1");
            map.put("message",e.getMessage());
            return map.toString();
        }
    }

    @GetMapping("/insert")
    @ResponseBody
    public String solrInsert(){
        SolrUtils solrUtils = new SolrUtils();
        SolrClient solrClient = solrUtils.createSolrClient();
        System.out.println("123");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-hh:mm:ss");
        String dataString = sdf.format(new Date());
        try{
            SolrInputDocument doc = new SolrInputDocument();
            doc.addField("id",dataString);
            doc.addField("test_text","河海大学的前身可以追溯到1915年创建于南京的“河海工程专门学校”");
            doc.addField("test_owner","小明");
            doc.addField("test_owner","小明");
            solrClient.add(solrUtils.getCoreName(),doc);
            solrClient.commit(solrUtils.getCoreName());
        }catch(Exception e){
            e.printStackTrace();
            Map<String,Object> map = new HashMap<>();
            map.put("code","1");
            map.put("message",e.getMessage());
            return map.toString();
        }
        return "success";
    }

    @RequestMapping(value="/delete/{id}")
    @ResponseBody
    public String deleteDocumentById(@PathVariable String id){
        try {
            SolrUtils solrUtils = new SolrUtils();
            SolrClient solrClient = solrUtils.createSolrClient();
            solrClient.deleteById(solrUtils.getCoreName(),id);
            solrClient.commit(solrUtils.getCoreName());
            return "success delete:" + id;
        } catch (Exception e) {
            e.printStackTrace();
            Map<String,Object> map = new HashMap<>();
            map.put("code","3");
            map.put("message",e.getMessage());
            return map.toString();
        }
    }
}
