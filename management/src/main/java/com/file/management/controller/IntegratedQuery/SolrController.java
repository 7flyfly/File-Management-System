package com.file.management.controller.IntegratedQuery;

import com.file.management.utils.SolrUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/IntegratedQuery/IntelligentRetrieval")
public class SolrController {

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

    @GetMapping("/get/{id}")
    @ResponseBody
    public String getDoucmentById(@PathVariable String id){
        SolrUtils solrUtils = new SolrUtils();
        SolrClient solrClient = solrUtils.createSolrClient();
        SolrDocument document = null;
        try {
            document = solrClient.getById(solrUtils.getCoreName(),id);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String,Object> map = new HashMap<>();
            map.put("code","2");
            map.put("message",e.getMessage());
            return map.toString();
        }
        System.out.println(document);
        return document.toString();
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
