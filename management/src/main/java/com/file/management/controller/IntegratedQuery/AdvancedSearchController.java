package com.file.management.controller.IntegratedQuery;

import com.alibaba.fastjson.JSONObject;
import com.file.management.service.solr.SolrDataConfigService;
import com.file.management.service.solr.SolrQueryService;
import com.file.management.service.solr.SolrService;
import com.file.management.utils.SolrUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/IntegratedQuery/AdvancedSearch")
public class AdvancedSearchController {
    @Autowired
    private SolrQueryService solrQueryService;

    @RequestMapping(value = "/conditionalSearch")
    @ResponseBody
    /**
     * 根据关键词查询结果,并返回给bootStrapTable
     */
    public String KeySerach(String allKeyWord, String documentNumber, String anyKeyWord, String noKeyWord,
                            String keyWordPosition, String nodeName ,String pageSize, String offset){
        SolrClient solrClient = null;
        JSONObject result_jsonObject = new JSONObject();
        try {
            if(nodeName==null) nodeName = "";
            SolrUtils solrUtils = new SolrUtils();
            solrClient = solrUtils.createSolrClient();
            JSONObject docsJsonObject = solrQueryService.ConditionQuerybySolr(solrClient,allKeyWord,documentNumber,anyKeyWord,
                    noKeyWord,keyWordPosition,nodeName,pageSize,offset,"annex_content");
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


}
