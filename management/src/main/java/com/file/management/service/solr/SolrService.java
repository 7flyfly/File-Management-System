package com.file.management.service.solr;

import com.alibaba.fastjson.JSON;
import com.file.management.pojo.*;
import com.file.management.utils.SolrUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.swing.text.Document;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SolrService {
    @Autowired
    SolrDataConfigService solrDataConfigService;

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
     * 对solr进行检索
     * @param solrClient solr客户端
     * @param keyword 关键词
     * @return
     */
    public SolrDocumentList querySolrbyKeyword(SolrClient solrClient, String keyword){
        SolrDocumentList docs = null;
        try {
            SolrQuery query = new SolrQuery();
            query.setQuery("text:*");
            //开始检索
            QueryResponse queryResponse = solrClient.query(query);
            docs = queryResponse.getResults();
            solrClient.commit();
            return docs;
        } catch (Exception e) {
            e.printStackTrace();
            return docs;
        }
    }
    /**
     * 新增需要将数据变为索引的数据库信息
     * @param dataSourceName 数据库名 "db_test"
     * @param driveName 驱动名 "com.mysql.jdbc.Driver"
     * @param dataSourceUrl 数据库地址 "jdbc:mysql://localhost:3306/db_test"
     * @param userName 用户名 "root"
     * @param password 密码 "123456"
     * @return
     */
    public HashMap<Boolean,String> addDataSources2SolrDataConfig(String dataSourceName,String driveName,String dataSourceUrl,
                                                                 String userName,String password){
        try {
            HashMap<Boolean,String> hashMap = new HashMap<Boolean,String>();
            SolrDataConfig solrDataConfig = solrDataConfigService.getSolrDataConfig();
            List<SolrDataSource> solrDataSourcesList = solrDataConfig.getSolrDataSourceList();
            solrDataSourcesList = solrDataConfigService.addDataSource(solrDataSourcesList,dataSourceName,driveName,
                    dataSourceUrl,userName,password);
            solrDataConfig.setSolrDataSourceList(solrDataSourcesList);  //覆盖原有solrDataSourcesList
            boolean bool = solrDataConfigService.saveSolrDataConfig(solrDataConfig);
            hashMap.put(true,"新增数据源成功");
            return hashMap;
        }catch(Exception e){
            HashMap<Boolean,String> hashMap = new HashMap<Boolean,String>();
            hashMap.put(false,e.getMessage());
            return hashMap;
        }
    }

    /**
     * 新增需要将数据变为索引的数据库表的信息
     * @param dataSourceName 所在数据库名(默认配置文件中的数据库名，先指定db_fileManagement todo 读取数据库配置文件)
     * @param tableName 数据库表名
     * @param primaryKey 数据库表中主键名称(有且只有一个主键，suggest：档号字段的英文名)
     * @param documentNumber 指定的uniqueKey，档号的字段名称；(suggest：档号字段的英文名)
     * @param solrStringList string型，字段在综合查询中不可被检索；可以为null [1]
     * @param solrStringCopyTextList string型，字段在综合查询中可以被检索；可以为null [2]
     * @param solrStringArrList string型，允许该字段有多个值(即组成数组)，字段在综合查询中不可被检索；可以为null
     * @param solrStringArrCopyTextList string型，允许该字段有多个值(即组成数组)，字段在综合查询中可以被检索；可以为null
     * @param solrIKList 可被分词类型，字段在综合查询中不可被检索；(不建议，用solrStringList)；可以为null
     * @param solrIKCopyTextList 可被分词类型，字段在综合查询中可以被检索；可以为null [3]
     * @param solrIKArrList 可被分词类型，允许该字段有多个值(即组成数组)，字段在综合查询中不可被检索；(不建议，用solrStringArrList)；可以为null
     * @param solrIKArrCopyTextList 可被分词类型，允许该字段有多个值(即组成数组)，字段在综合查询中可以被检索；可以为null
     * @param solrDateList date型，字段在综合查询中不可被检索；可以为null
     * @param solrDateCopyTextList date型，字段在综合查询中可以被检索；可以为null
     * @param solrDateArrList date型，允许该字段有多个值(即组成数组)，字段在综合查询中不可被检索；可以为null
     * @param solrDateArrCopyTextList date型，字段在综合查询中不可被检索；可以为null
     */
    public HashMap<Boolean,String> addTableEntity2SolrDataConfig(String dataSourceName, String tableName, String primaryKey,
             String documentNumber,List<String> solrStringList, List<String> solrStringCopyTextList,
             List<String> solrStringArrList, List<String> solrStringArrCopyTextList, List<String> solrIKList,
             List<String> solrIKCopyTextList, List<String> solrIKArrList, List<String> solrIKArrCopyTextList,
             List<String> solrDateList, List<String> solrDateCopyTextList, List<String> solrDateArrList,
             List<String> solrDateArrCopyTextList){
        try {
            HashMap<Boolean,String> hashMap = new HashMap<Boolean,String>();
            SolrDataConfig solrDataConfig = solrDataConfigService.getSolrDataConfig();
            //一个solrEntityDocumentList中只有一个元素
            List<SolrEntityDocument>  solrEntityDocumentList = solrDataConfig.getSolrEntityDocumentList();
            //solrEntityDocument中存放solrTableEntityList
            List<SolrTableEntity> solrTableEntityList = solrEntityDocumentList.get(0).getSolrTableEntityList();
            //构建表
            List<SolrTableEntityColumn> solrTableEntityColumnList = solrDataConfigService.createSolrTableEntityColumnList(
                    documentNumber,solrStringList,solrStringCopyTextList, solrStringArrList, solrStringArrCopyTextList,
                    solrIKList,solrIKCopyTextList,solrIKArrList, solrIKArrCopyTextList,solrDateList,
                    solrDateCopyTextList,solrDateArrList,solrDateArrCopyTextList);
            //增加表
            solrTableEntityList = solrDataConfigService.addSolrTableEntity(solrTableEntityList,tableName,primaryKey,
                    dataSourceName,solrTableEntityColumnList);
            solrEntityDocumentList.get(0).setSolrTableEntityList(solrTableEntityList);
            solrDataConfig.setSolrEntityDocumentList(solrEntityDocumentList);
            boolean bool = solrDataConfigService.saveSolrDataConfig(solrDataConfig);
            String boolMessage = bool ? "添加成功" : "添加失败";
            return hashMap;
        }catch(Exception e){
            HashMap<Boolean,String> hashMap = new HashMap<Boolean,String>();
            hashMap.put(false,e.getMessage());
            return hashMap;
        }
    }

    /**
     * 导入表，将表中的数据建立索引
     * @param tableName 表名，非空
     */
    public HashMap<Boolean,String> fullImportTable2Solr(String tableName){
        try{
            HashMap<Boolean,String> hashMap = new HashMap<Boolean,String>();
            if(tableName!=null&&!tableName.equals("")){
                System.out.println("tableName:"+tableName);
                SolrUtils solrUtils = new SolrUtils();
                SolrClient solrClient = solrUtils.createSolrClient();
                boolean bool = this.fullImportTableIntoSolr(solrClient,tableName);
                solrClient.close();
                if(bool){
                    hashMap.put(true,"成功导入表："+tableName);
                    return hashMap;
                }else{
                    hashMap.put(false,tableName+"导入失败!");
                    return hashMap;
                }

            }else{
                hashMap.put(false,"失败! 表名不能为空");
                return hashMap;
            }
        } catch (IOException e) {
            e.printStackTrace();
            HashMap<Boolean,String> hashMap = new HashMap<Boolean,String>();
            hashMap.put(false,e.getMessage());
            return hashMap;
        }
    }

    /**
     * 增量导入表，将表中的数据建立索引，根据DocumentNumber修改索引，根据软删除标识删除索引
     * 更改软删除标识可以实现回滚
     * @param tableName 表名
     */
    public HashMap<Boolean,String> deltaImportTable2Solr(String tableName){
        try{
            HashMap<Boolean,String> hashMap = new HashMap<Boolean,String>();
            if(tableName!=null&&!tableName.equals("")){
                System.out.println("tableName:"+tableName);
                SolrUtils solrUtils = new SolrUtils();
                SolrClient solrClient = solrUtils.createSolrClient();
                boolean bool = this.deltaImportTableIntoSolr(solrClient,tableName);
                solrClient.close();
                if(bool){
                    hashMap.put(true,"成功增量导入表："+tableName);
                    return hashMap;
                }else{
                    hashMap.put(false,tableName+"增量导入失败!");
                    return hashMap;
                }
            }else{
                hashMap.put(false,"失败! 表名不能为空");
                return hashMap;
            }
        } catch (IOException e) {
            e.printStackTrace();
            HashMap<Boolean,String> hashMap = new HashMap<Boolean,String>();
            hashMap.put(false,e.getMessage());
            return hashMap;
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
