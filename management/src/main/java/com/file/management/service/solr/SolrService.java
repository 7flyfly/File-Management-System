package com.file.management.service.solr;

import com.alibaba.fastjson.JSON;
import com.file.management.utils.SolrUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class SolrService {
    //根据id查询索引
    /**
     * 将表中数据导入solr
     * @param solrClient：solr客户端
     * @param DocumentNumber: 档号
     */
    public String getDoucmentByDocumentNumber(SolrClient solrClient, String DocumentNumber){
        SolrDocument document = null;
        try {
            document = solrClient.getById(DocumentNumber);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String,Object> map = new HashMap<>();
            map.put("code","2");
            map.put("message",e.getMessage());
            return map.toString();
        }
        //处理输出
        if(document==null){
            return "未查询到相关结果";
        }else{
            System.out.println(document);
            return document.toString();
        }
    }

    /**
     * 将表中数据导入solr，建立索引
     * @param solrClient：solr客户端
     * @param tableName: 表名
     */
    public boolean fullImportTableIntoSolr(SolrClient solrClient, String tableName){
        if(solrClient!=null&&tableName!=null&&!tableName.equals("")){
            try {
                SolrQuery solrQuery = new SolrQuery();
                solrQuery.setRequestHandler("/dataimport");//指定RequestHandler,默认为select
                solrQuery.setParam("command","full-import");//指定导入方式
                solrQuery.setParam("clean","false");//是否删除core中的索引，默认为true ！！！
                solrQuery.setParam("optimize","false");//操作结束后是否对索引进行优化
//                solrQuery.setParam("dataSource","db_fileManagement");//指定需要导入的table
                solrQuery.setParam("entity",tableName);//指定需要导入的table
                // 以档号为档案的唯一标识，相同档号的记录会覆盖
                solrQuery.setParam("commit","true");//是否提交
                QueryResponse queryResponse = solrClient.query(solrQuery);
//                System.out.println(queryResponse);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }else{
            System.out.println("solrClient或tableName不能为空");
            return false;
        }
    }

    //
    /**
     * 增量导入表，将表中数据导入solr；根据DocumentNumber修改索引，根据软删除标识删除索引
     * 更改软删除标识可以实现回滚
     * @param solrClient：solr客户端
     * @param tableName: 表名
     */
    public boolean deltaImportTableIntoSolr(SolrClient solrClient, String tableName){
        if(solrClient!=null&&tableName!=null&&!tableName.equals("")){
            try {
                SolrQuery solrQuery = new SolrQuery();
                solrQuery.setRequestHandler("/dataimport");//指定RequestHandler,默认为select
                solrQuery.setParam("command","delta-import");//指定导入方式
                solrQuery.setParam("clean","false");//是否删除core中的索引，默认为true ！！！
                solrQuery.setParam("optimize","false");//操作结束后是否对索引进行优化
                solrQuery.setParam("entity",tableName);//指定需要导入的table
                // 以档号为档案的唯一标识，相同档号的记录会覆盖
                solrQuery.setParam("commit","true");//是否提交
                QueryResponse queryResponse = solrClient.query(solrQuery);
//                System.out.println(queryResponse);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }else{
            System.out.println("solrClient或tableName不能为空");
            return false;
        }
    }

    /**
     * 重新载入配置文件
     * @param solrClient solr客户端
     */
    public boolean reloadConfig(SolrClient solrClient){
        if(solrClient!=null){
            try {
                SolrQuery solrQuery = new SolrQuery();
                solrQuery.setRequestHandler("/dataimport");//指定RequestHandler,默认为select
                solrQuery.setParam("command","reload-config");
                QueryResponse queryResponse = solrClient.query(solrQuery);
                System.out.println("重新载入配置文件");
//                System.out.println(queryResponse);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }else{
            System.out.println("solrClient不能为空");
            return false;
        }
    }

    /**
     * 获得导入状态
     * @param solrClient solr客户端
     */
    public boolean getImportInfo(SolrClient solrClient){
        if(solrClient!=null){
            try {
                SolrQuery solrQuery = new SolrQuery();
                solrQuery.setRequestHandler("/dataimport");//指定RequestHandler,默认为select
                solrQuery.setParam("command","dataimport");
                QueryResponse queryResponse = solrClient.query(solrQuery);
                System.out.println("获取导入状态");
                System.out.println(queryResponse);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }else{
            System.out.println("solrClient不能为空");
            return false;
        }
    }

    /**
     * 对索引进行优化，提高查询性能
     * @param solrClient solr客户端
     */
    public boolean optimizeSolrIndex(SolrClient solrClient){
        if(solrClient!=null){
            try {
                UpdateResponse updateResponse = solrClient.optimize();
                System.out.println("优化索引");
                System.out.println(updateResponse);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }else{
            System.out.println("solrClient不能为空");
            return false;
        }
    }
}
