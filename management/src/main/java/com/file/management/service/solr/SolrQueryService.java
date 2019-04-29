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
            SolrQuery solrQuery = this.buildSolrQuery(jsonObject);
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


    public JSONObject queryDocumentFromDatabase(String table_id, String document_number){
        System.out.println(table_id +"\n"+ document_number);
        JSONObject result_js = new JSONObject();
        String tableName = tablesService.getTableNameByTableId(Integer.parseInt(table_id));
        if(tableName!=null&&!tableName.isEmpty()){
            List resultList = dynamicSQL.selectResultListByTableNameAndAttr(tableName,"DocumentNo",
                    document_number);
            if(resultList.size()==1){
                Object row = resultList.get(0);
                Object[] attValue = (Object[]) row;
                List AttrNameList = dynamicSQL.selectAttrNameByTableName(tableName);
                JSONObject jsonObject = tablesService.getAttrECNameByTableName(tableName);
                for(int i = 0; i<AttrNameList.size(); i++){
                    String attEName = AttrNameList.get(i) == null ? "" : (String)AttrNameList.get(i);
                    if(attValue[i]!=null&&jsonObject.containsKey(attEName)){
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

    public SolrQuery buildSolrQuery(JSONObject jsonObject) {
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
        if(querystring!=null)
            solrQuery.set("q", keyword);
//        solrQuery.set("q.op", "AND");  //默认操作符
        System.out.println("solrQuery = " + solrQuery);
        return  solrQuery;
    }

}
