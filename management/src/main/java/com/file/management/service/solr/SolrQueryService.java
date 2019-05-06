package com.file.management.service.solr;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.file.management.dao.DynamicSQL;
import com.file.management.dao.metadata.TablesRepository;
import com.file.management.service.metadata.TablesService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SolrQueryService {
    @Autowired
    private TablesService tablesService;
    @Autowired
    private TablesRepository tablesRepository;
    @Autowired
    private DynamicSQL dynamicSQL;

    /**
     * 根据id查询索引
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
    public JSONObject queryKeywordbySolr(SolrClient solrClient, String keyword, String tableId, String pageSize,
                                         String offset){
        JSONObject jsonObject = new JSONObject();
        JSONObject result_jsonObject = new JSONObject();
        jsonObject.put("Keyword",keyword);
        jsonObject.put("TableId",tableId);
        jsonObject.put("PageSize",pageSize);
        jsonObject.put("Offset",offset);
        SolrDocumentList docs = null;
        JSONArray docsJsonArray = new JSONArray();
        try {
            SolrQuery solrQuery = this.buildSolrQuery4queryKeyword(jsonObject);
            //开始检索
            QueryResponse queryResponse = solrClient.query(solrQuery);
            docs = queryResponse.getResults();
            Long numFound = docs.getNumFound();
            docsJsonArray= (JSONArray)JSONArray.toJSON(docs);
            result_jsonObject.put("keyword",keyword);
            result_jsonObject.put("numFound",numFound);
            result_jsonObject.put("documentList",docsJsonArray);
            return result_jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            return result_jsonObject;
        }
    }

    /**
     * 高级搜索
     * @param solrClient
     * @param allKeyWord 包含以下全部关键字
     * @param keyword 包含以下的完整关键词
     * @param anyKeyWord 包含以下任意一个关键词
     * @param noKeyWord 不包括以下关键词
     * @param keyWordPosition 查询关键词位于
     * @param tableId 表ID
     * @param pageSize
     * @param offset
     * @return
     */
    public JSONObject ConditionQuerybySolr(SolrClient solrClient, String allKeyWord, String keyword, String anyKeyWord,
                                           String noKeyWord, String keyWordPosition, String tableId ,String pageSize, String offset){
        JSONObject jsonObject = new JSONObject();
        JSONObject result_jsonObject = new JSONObject();
        jsonObject.put("allKeyWord",allKeyWord);
        jsonObject.put("keyword",keyword);
        jsonObject.put("anyKeyWord",anyKeyWord);
        jsonObject.put("noKeyWord",noKeyWord);
        jsonObject.put("keyWordPosition",keyWordPosition);
        jsonObject.put("TableId",tableId);
        jsonObject.put("PageSize",pageSize);
        jsonObject.put("Offset",offset);
        SolrDocumentList docs = null;
        JSONArray docsJsonArray = new JSONArray();
        try {
            SolrQuery solrQuery = this.buildSolrQuery4ConditionQuery(jsonObject);
            //开始检索
            QueryResponse queryResponse = solrClient.query(solrQuery);
            docs = queryResponse.getResults();
            Long numFound = docs.getNumFound();
            docsJsonArray= (JSONArray)JSONArray.toJSON(docs);
            result_jsonObject.put("numFound",numFound);
            result_jsonObject.put("documentList",docsJsonArray);
            return result_jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            return result_jsonObject;
        }
    }

    /**
     * 根据document_number和Table_id从数据库中查询档案
     * @param table_id 表id
     * @param document_number 档案号
     * @param documentNumberDatabaseName 档案号在数据库中的字段名称
     * @param annexDatabaseName 附件在数据库中的字段名称
     * @param arrSplit 附件的分割符
     * @return
     */
    public JSONObject queryDocumentFromDatabase(String table_id, String document_number ,String documentNumberDatabaseName,
                                                String annexDatabaseName, String arrSplit){
        System.out.println(table_id +"\n"+ document_number);
        JSONObject result_js = new JSONObject();
        String tableName = tablesService.getTableNameByTableId(Integer.parseInt(table_id));
        if(tableName!=null&&!tableName.isEmpty()){
            List resultList = dynamicSQL.selectResultListByTableNameAndAttr(tableName,documentNumberDatabaseName,
                    document_number);
            if(resultList.size()==1){
                Object row = resultList.get(0);
                Object[] attValue = (Object[]) row;
                List AttrNameList = dynamicSQL.selectAttrNameByTableName(tableName);
                JSONObject jsonObject = tablesService.getAttrECNameByTableName(tableName);
                for(int i = 0; i<AttrNameList.size(); i++){
                    String attEName = AttrNameList.get(i) == null ? "" : (String)AttrNameList.get(i);
                    if(AttrNameList.get(i).toString().equals(annexDatabaseName)){
                        String fileNameStr = "";
                        String attCName = jsonObject.getString(attEName);
                        String[] fileNames = attValue[i].toString().split(arrSplit);
                        for(String fileName : fileNames){
                            fileNameStr = fileNameStr + fileName.substring(fileName.lastIndexOf("\\") + 1 )+ "; ";
                        }
                        result_js.put(attCName,fileNameStr);
                    }else if(attValue[i]!=null&&jsonObject.containsKey(attEName)){
                            String attCName = jsonObject.getString(attEName);
                            result_js.put(attCName,attValue[i]);
                    }
                }
            }else{
                result_js.put("error","该档号有多个结果");
            }
        }else{
            result_js.put("error","节点对应的表为空");
        }
        System.out.println(result_js);
        return result_js;
    }

    /**
     * 拼接查询字符串
     * @param keyword 关键词
     * @param table_id 数据所在表的id
     * @return 拼接好的查询字符串
     */
    public String buildQueryStr(String keyword, String table_id){
        String querystring = null;
        if(keyword.isEmpty()){
            querystring = "text:*";
            if(!table_id.isEmpty()){
                querystring = "table_id_s"+":"+table_id;
            }
        }else{
            StringBuffer buff = new StringBuffer();
            buff.append("("+"text:"+keyword+" "+"OR"+" "+"DOCUMENT_NUMBER:"+keyword+")");
            if(!table_id.isEmpty()){
                buff.append(" AND ");
                buff.append("table_id_s"+":"+table_id);
            }
            querystring = buff.toString();
        }
        return querystring;
    }

    /**
     * 构造solr的查询语句
     * @param jsonObject Keyword，TableId
     * @return
     */
    public SolrQuery buildSolrQuery4queryKeyword(JSONObject jsonObject) {
        String keyword = jsonObject.getString("Keyword");
        String tableId = jsonObject.getString("TableId");
        String querystring = null;
        SolrQuery solrQuery = new SolrQuery();
        //当输入为空时的特殊处理
        querystring = this.buildQueryStr(keyword,tableId);
        //分页
        int offset = jsonObject.containsKey("Offset")? Integer.parseInt(jsonObject.getString("Offset")):-1;
        int PageSize = jsonObject.containsKey("PageSize")? Integer.parseInt(jsonObject.getString("PageSize")):-1;
        if(offset!=-1 && PageSize!=-1 ){
            solrQuery.set("start", offset);
            solrQuery.set("rows", PageSize);
        }
        solrQuery.set("wt", "json");
        //高亮
//        if (!keyword.equals("")){
//            solrQuery.setHighlight(true);
//            solrQuery.set("hl.fl","keyword,idtitle,id");
//            solrQuery.setHighlightSnippets(1);
//            solrQuery.setHighlightSimplePre("<font color=\"red\">");
//            solrQuery.setHighlightSimplePost("</font>");
//            solrQuery.setHighlightFragsize(0);
//        }
        if(querystring==null)
            solrQuery.set("q", keyword);
        else
            solrQuery.set("q", querystring);
//        solrQuery.set("q.op", "AND");  //默认操作符
        System.out.println("solrQuery = " + solrQuery);
        return  solrQuery;
    }

    /**
     * 构造solr的查询语句
     * @param jsonObject allKeyWord，keyword，anyKeyWord，noKeyWord，keyWordPosition，TableId，Offset，PageSize
     * @return
     */
    public SolrQuery buildSolrQuery4ConditionQuery(JSONObject jsonObject) {
        String allKeyWord = jsonObject.getString("allKeyWord");
        String keyword = jsonObject.getString("keyword");
        String anyKeyWord = jsonObject.getString("anyKeyWord");
        String noKeyWord = jsonObject.getString("noKeyWord");
        String keyWordPosition = jsonObject.getString("keyWordPosition");
        String table_id = jsonObject.getString("TableId");
        String querystring = null;
        SolrQuery solrQuery = new SolrQuery();
        //当输入为空时的特殊处理
        StringBuffer buff = new StringBuffer();
        String param = "";
        if(keyWordPosition!=null&&!keyWordPosition.isEmpty()){
            if(keyWordPosition.equals("fullTextPosition")){ param = "text"; }
            if(keyWordPosition.equals("descriptionPosition")){ param = "file_content_iks";}//todo 增加copyfield
            if(keyWordPosition.equals("AnnexRetrieval")){ param = "file_content_iks";}
        }
        if(allKeyWord!=null&&!allKeyWord.isEmpty()){
            String[] keys = allKeyWord.split("\\+");
            String keyStr = "";
            for(int i = 0 ;i<keys.length-1; i++){
                keyStr = keyStr + keys[i] + " AND " ;
            }
            keyStr = keyStr + keys[keys.length-1];
            buff.append("("+param +":"+ keyStr +")");
        }
        if(keyword!=null&&!keyword.isEmpty()){
            solrQuery.set("defType","edismax");
            solrQuery.set("mm","100%");
            String[] keys = keyword.split("\\+");
            String keyStr = "";
            for(int i = 0 ;i<keys.length-1; i++){
                keyStr = keyStr + keys[i] + " AND " ;
            }
            keyStr = keyStr + keys[keys.length-1] ;
            buff.append("("+param +":"+ keyStr +")");
        }
        if(anyKeyWord!=null&&!anyKeyWord.isEmpty()){
            buff.append("("+param +":" + anyKeyWord +")");
        }
        if(noKeyWord!=null&&!noKeyWord.isEmpty()){
            String[] keys = noKeyWord.split("\\+");
            String keyStr = "";
            for(int i = 0 ;i<keys.length-1; i++){
                keyStr = keyStr + " NOT " + keys[i] ;
            }
            keyStr = keyStr +  " NOT " + keys[keys.length-1];
            buff.append(keyStr);
        }
        if(table_id!=null&&!table_id.isEmpty()){
            String[] keys = table_id.split("\\+");
            String keyStr = "";
            for(int i = 0 ;i<keys.length-1; i++){
                keyStr = keyStr + keys[i] + " AND " ;
            }
            keyStr = keyStr + keys[keys.length-1];
            buff.append("("+"table_id_s:"+ keyStr +")");
        }
        querystring = buff.toString();
        //分页
        int offset = jsonObject.containsKey("Offset")? Integer.parseInt(jsonObject.getString("Offset")):-1;
        int PageSize = jsonObject.containsKey("PageSize")? Integer.parseInt(jsonObject.getString("PageSize")):-1;
        if(offset!=-1 && PageSize!=-1 ){
            solrQuery.set("start", offset);
            solrQuery.set("rows", PageSize);
        }
        solrQuery.set("wt", "json");
        //高亮
//        if (!keyword.equals("")){
//            solrQuery.setHighlight(true);
//            solrQuery.set("hl.fl","keyword,idtitle,id");
//            solrQuery.setHighlightSnippets(1);
//            solrQuery.setHighlightSimplePre("<font color=\"red\">");
//            solrQuery.setHighlightSimplePost("</font>");
//            solrQuery.setHighlightFragsize(0);
//        }
        solrQuery.set("q", querystring);
        solrQuery.set("q.op", "AND");  //默认操作符
        System.out.println("solrQuery = " + solrQuery);
        return  solrQuery;
    }
}
