package com.file.management.service.solr;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.file.management.dao.DynamicSQL;
import com.file.management.dao.MenuRepository;
import com.file.management.pojo.Menu;
import com.file.management.service.ImageProcessing.ImagePHashService;
import com.file.management.service.metadata.TablesService;
import com.file.management.utils.ConstantString;
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

/**
 * 针对solr查询的一些操作
 */
@Service
public class SolrQueryService {
    @Autowired
    private TablesService tablesService;
    @Autowired
    private DynamicSQL dynamicSQL;
    @Autowired
    private SolrService solrService;
    @Autowired
    private ImagePHashService imagePHashService;
    @Autowired
    private MenuRepository menuRepository;
    /**
     * 根据id查询索引
     * @param solrClient：solr客户端
     * @param DocumentNumber: 档号
     */
    public String getDoucmentByDocumentNumber(SolrClient solrClient, String DocumentNumber){
        SolrDocument document = null;
        try {
            document = solrService.getDoucmentByDocumentNumber(solrClient,DocumentNumber);
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
     * 对solr进行检索 全文检索
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
     * @param searchConditionArr 搜索项，word，operation
     * @param searchArea 全文检索、附件检索、目录检索
     * @param searchOptions 模糊检索、精确检索
     * @param pageSize 每页结果的数量
     * @param offset 偏移值
     * @return
     */
    public JSONObject ConditionQuerybySolr(SolrClient solrClient, String searchConditionArr, String searchArea, String searchOptions,
                                           String pageSize,String offset, String fileContentSolrName){
        JSONObject jsonObject = new JSONObject();
        JSONObject result_jsonObject = new JSONObject();
        jsonObject.put("searchConditionArr",searchConditionArr);
        jsonObject.put("searchArea",searchArea);
        jsonObject.put("searchOptions",searchOptions);
        jsonObject.put("PageSize",pageSize);
        jsonObject.put("Offset",offset);
        SolrDocumentList docs = null;
        JSONArray docsJsonArray = new JSONArray();
        try {
            SolrQuery solrQuery = this.buildSolrQuery4ConditionQuery(jsonObject,fileContentSolrName);
            if(solrQuery==null){
                //开始检索
                result_jsonObject.put("numFound","0");
                result_jsonObject.put("documentList","");
                return result_jsonObject;
            }else{
                //开始检索
                QueryResponse queryResponse = solrClient.query(solrQuery);
                docs = queryResponse.getResults();
                Long numFound = docs.getNumFound();
                docsJsonArray= (JSONArray)JSONArray.toJSON(docs);
                result_jsonObject.put("numFound",numFound);
                result_jsonObject.put("documentList",docsJsonArray);
                return result_jsonObject;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return result_jsonObject;
        }
    }

    /**
     * 根据Phash值等信息去solr中进行查询 图片检索
     * @param solrClient
     * @param keyword 关键词
     * @param upLoadImagePHash 上传的图片的PHash值
     * @param tableId 表ID
     * @param pageSize 每一页结果的数量
     * @param offset 便宜值
     * @param imagePHashSolrName PHash在solr中字段的名称
     * @return
     */
    public JSONObject imageSearchbySolr(SolrClient solrClient, String keyword, String upLoadImagePHash,
                                        String tableId, String pageSize,String offset, String imagePHashSolrName){
        JSONObject jsonObject = new JSONObject();
        JSONObject result_jsonObject = new JSONObject();
        jsonObject.put("Keyword",keyword);
        jsonObject.put("UpLoadImagePHash",upLoadImagePHash);
        jsonObject.put("TableId",tableId);
        jsonObject.put("PageSize",pageSize);
        jsonObject.put("Offset",offset);
        SolrDocumentList docs = null;
        JSONArray docsJsonArray = new JSONArray();
        try {
            SolrQuery solrQuery = this.buildSolrQuery4ImageSearch(jsonObject,imagePHashSolrName);
            //开始检索
            QueryResponse queryResponse = solrClient.query(solrQuery);
            docs = queryResponse.getResults();
            Long numFound = docs.getNumFound();
            docsJsonArray= (JSONArray)JSONArray.toJSON(docs);
            docsJsonArray = imagePHashService.getSimilarImages(upLoadImagePHash,docsJsonArray,imagePHashSolrName,3);
            result_jsonObject.put("keyword",keyword);
            result_jsonObject.put("numFound",docsJsonArray.size());
            result_jsonObject.put("documentList",docsJsonArray);
            return result_jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            return result_jsonObject;
        }
    }

    /**
     * 根据document_number和Table_id从数据库中查询档案,并未展示做准备
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
                        String fileAddressStr = "";
                        String attCName = jsonObject.getString(attEName);
                        String[] fileNames = attValue[i].toString().split(arrSplit);
                        int sum = 0; //附件数
                        for(String filename : fileNames){
                            if(!filename.contains("http")){
                                continue;
                            }
                            sum ++;
                            if(!solrService.getPictureTypeType(filename)){
                                fileAddressStr = fileAddressStr + "../../imgs/" +
                                        filename.substring(filename.lastIndexOf(".") + 1) +".jpg"+ "; "; //根据后缀获得富文本对应的图片
                            }else{
                                fileAddressStr = fileAddressStr + filename + "; ";
                            }
                            fileNameStr = fileNameStr + filename.substring(filename.lastIndexOf("\\") + 1 )+ "; ";
                        }
                        result_js.put(attCName,"<button type=\"button\" class=\"btn btn-xm btn-default\" " +
                                "onclick=\"IntelligentRetrieval.pictureView(\'"+document_number+"\',\'"+ fileAddressStr+"\',\'"+fileNameStr+"\')\"> " +
                                "<span class=\"badge badge-warning\">"+sum+"</span> </button>");
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
     * 对于全文检索 构造solr的查询语句
     * @param jsonObject Keyword，TableId
     * @return
     */
    private SolrQuery buildSolrQuery4queryKeyword(JSONObject jsonObject) {
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
//            solrQuery.set("hl.fl","title_ik_c");
//            solrQuery.setHighlightSnippets(1);
//            solrQuery.setHighlightSimplePre("<font color=\"red\">");
//            solrQuery.setHighlightSimplePost("</font>");
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
     * 对于全文检索 拼接查询字符串
     * @param keyword 关键词
     * @param table_id 数据所在表的id
     * @return 拼接好的查询字符串
     */
    private String buildQueryStr(String keyword, String table_id){
        String querystring = null;
        if(keyword.isEmpty()){
            querystring = "all_text:*";
            if(!table_id.isEmpty()){
                querystring = "table_id_s"+":"+table_id;
            }
        }else{
            StringBuffer buff = new StringBuffer();
            buff.append("("+"all_text:"+keyword+" "+"OR"+" "+"document_number:"+keyword+")");
            if(!table_id.isEmpty()){
                buff.append(" AND ");
                buff.append("table_id_s"+":"+table_id);
            }
            querystring = buff.toString();
        }
        return querystring;
    }

    /**
     * 对于高级检索 构造solr的查询语句
     * @param jsonObject allKeyWord，keyword，anyKeyWord，noKeyWord，keyWordPosition，TableId，Offset，PageSize
     * @param fileContentSolrName 附件在solr中的字段名称
     * @return
     */
    private SolrQuery buildSolrQuery4ConditionQuery(JSONObject jsonObject,String fileContentSolrName) {
        JSONArray searchConditionJSONArray = jsonObject.getJSONArray("searchConditionArr");
        String searchArea = jsonObject.getString("searchArea");
        String searchOptions = jsonObject.getString("searchOptions");
        String querystring = "";
        SolrQuery solrQuery = new SolrQuery();
        //当输入为空时的特殊处理
        ArrayList<StringBuffer> buffList = new ArrayList<>();
        StringBuffer stringBuffer = new StringBuffer();
        String param = "all_text";
        for(int i=0;i<searchConditionJSONArray.size();i++){
            JSONObject searchConditionJSONObject = searchConditionJSONArray.getJSONObject(i);
            String searchCondition = searchConditionJSONObject.getString("searchCondition");
            String searchOperation = searchConditionJSONObject.getString("searchOperation");
            String searchText = searchConditionJSONObject.getString("searchText");
            String searchConditionSolrName = this.getSolrName(searchCondition);
            if("table_name".equals(searchCondition)){
                StringBuffer stringBuffer2 = new StringBuffer();
                List<Menu> menuList = menuRepository.findMenuByMenuName(searchText);
                if(menuList.size()==0) continue;
                if(i!=0){stringBuffer.append(" "+this.getOperation(searchOperation)+" ");}
                stringBuffer2.append("(");
                for(int j = 0; j < menuList.size()-1; j++){
                    stringBuffer2.append(searchConditionSolrName + ":" + menuList.get(j).getMenuTable().getTableId());
                    stringBuffer2.append(" OR ");
                }
                stringBuffer2.append(searchConditionSolrName + ":" + menuList.get(menuList.size()-1).getMenuTable().getTableId());
                stringBuffer2.append(")");
                stringBuffer.append(stringBuffer2);
            }else{
                if(i!=0){stringBuffer.append(" "+this.getOperation(searchOperation)+" ");}
                stringBuffer.append(this.getSearchArea(searchArea,searchOptions,searchConditionSolrName));
                stringBuffer.append(":");
                stringBuffer.append(searchText);
            }
        }
        querystring = stringBuffer.toString();
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

    /**
     * 对于高级检索 构造solr的查询语句
     * @param jsonObject allKeyWord，keyword，anyKeyWord，noKeyWord，keyWordPosition，TableId，Offset，PageSize
     * @param fileContentSolrName 附件在solr中的字段名称
     * @return
     */
    private SolrQuery buildSolrQuery4ConditionQuery2(JSONObject jsonObject,String fileContentSolrName) {
        String allKeyWord = jsonObject.getString("allKeyWord");
        String documentNumber = jsonObject.getString("documentNumber");
        String anyKeyWord = jsonObject.getString("anyKeyWord");
        String noKeyWord = jsonObject.getString("noKeyWord");
        String keyWordPosition = jsonObject.getString("keyWordPosition");
        String table_id = jsonObject.getString("TableId");
        String querystring = "";
        SolrQuery solrQuery = new SolrQuery();
        //当输入为空时的特殊处理
        ArrayList<StringBuffer> buffList = new ArrayList<>();
        String param = "all_text";
        //判断搜索范围
        if(keyWordPosition!=null&&!keyWordPosition.isEmpty()){
            if(keyWordPosition.equals("fullTextPosition")){ param = "all_text";}
            if(keyWordPosition.equals("descriptionPosition")){ param = "text"; }
            if(keyWordPosition.equals("AnnexRetrieval")){ param = fileContentSolrName;}
        }
        //判断搜索关键词
        //包含以下全部关键字
        if(allKeyWord!=null&&!allKeyWord.isEmpty()){
            StringBuffer buff = new StringBuffer();
            String[] keys = allKeyWord.split("\\+");
            String keyStr = "";
            for(int i = 0 ;i<keys.length-1; i++){
                keyStr = keyStr + keys[i] + " AND " ;
            }
            keyStr = keyStr + keys[keys.length-1];
            buff.append("("+param +":"+ keyStr +")");
            buffList.add(buff);
        }
        //包含以下任意一个关键词
        if(anyKeyWord!=null&&!anyKeyWord.isEmpty()){
            StringBuffer buff = new StringBuffer();
            String[] keys = anyKeyWord.split("\\+");
            String keyStr = "";
            for(int i = 0 ;i<keys.length-1; i++){
                keyStr = keyStr + keys[i] + " OR " ;
            }
            keyStr = keyStr + keys[keys.length-1];
            buff.append("("+param +":"+ keyStr +")");
            buffList.add(buff);
        }

        //判断搜索限制
        //档案号
        if(documentNumber!=null&&!documentNumber.isEmpty()){
            String[] keys = documentNumber.split("\\+");
            String keyStr = "";
            for(int i = 0 ;i<keys.length-1; i++){
                keyStr = keyStr + keys[i] + " AND " ;
            }
            keyStr = keyStr + keys[keys.length-1] ;
            StringBuffer buff = new StringBuffer();
            buff.append(" (document_number:"+ keyStr +")");
            buffList.add(buff);
        }
        //节点名称
        if(table_id!=null&&!table_id.isEmpty()){
            String[] keys = table_id.split("\\+");
            String keyStr = "";
            for(int i = 0 ;i<keys.length-1; i++){
                keyStr = keyStr + keys[i] + " AND " ;
            }
            keyStr = keyStr + keys[keys.length-1];
            StringBuffer buff = new StringBuffer();
            buff.append("("+"table_id_s:"+ keyStr +")");
            buffList.add(buff);
        }

        if(buffList.size()!=0){
            StringBuffer buffall = new StringBuffer();
            for(int i = 0; i < buffList.size()-1 ; i++){
                buffall.append(buffList.get(i));
                buffall.append(" AND ");
            }
            buffall.append(buffList.get(buffList.size()-1));
            StringBuffer buff = new StringBuffer();
            //不包含一些关键词
            if(noKeyWord!=null&&!noKeyWord.isEmpty()){
                String[] keys = noKeyWord.split("\\+");
                String keyStr = "";
                for(int i = 0 ;i<=keys.length-1; i++){
                    keyStr = keyStr + " NOT "+param+":" + keys[i] ;
                }
                buff.append(keyStr);
            }
            buffall.append(buff);
            querystring = querystring + buffall.toString();
        }else{
            return null;
        }

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

    /**
     * 构造图片查询的solr语句
     * @param jsonObject  Keyword，UpLoadImagePHash，TableId，PageSize，Offset
     * @param imagePHashSolrName PHash在solr中field的名称
     * @return
     */
    private SolrQuery buildSolrQuery4ImageSearch(JSONObject jsonObject,String imagePHashSolrName) {
        String keyword = jsonObject.getString("Keyword");
        String tableId = jsonObject.getString("TableId");
        String upLoadImagePHash = jsonObject.getString("UpLoadImagePHash");
        String querystring = null;
        SolrQuery solrQuery = new SolrQuery();
        //当输入为空时的特殊处理
        StringBuffer buff = new StringBuffer();
        if(keyword.isEmpty()){
//            buff.append(imagePHashSolrName + ":*");
            buff.append(imagePHashSolrName + ":"+upLoadImagePHash);
            if(!tableId.isEmpty()){
                buff.append(" AND ");
                buff.append("table_id_s"+":"+tableId);
            }
        }else{
            buff.append("all_text:" + keyword);
            buff.append(" AND ");
            buff.append(imagePHashSolrName + ":*");
            if(!tableId.isEmpty()){
                buff.append(" AND ");
                buff.append("table_id_s"+":"+tableId);
            }
        }
        querystring = buff.toString();
        //分页
//        int offset = jsonObject.containsKey("Offset")? Integer.parseInt(jsonObject.getString("Offset")):-1;
//        int PageSize = jsonObject.containsKey("PageSize")? Integer.parseInt(jsonObject.getString("PageSize")):-1;
//        if(offset!=-1 && PageSize!=-1 ){
//            solrQuery.set("start", offset);
//            solrQuery.set("rows", PageSize);
//        }
        //todo 优化查询结果
        solrQuery.set("rows", "100000");
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

    /**
     * 获取对应的运算符
     */
    private String getOperation(String operationStr) {
        String operation = "";
        switch(operationStr) {
            case "notOperation":
                operation = "NOT";
                break;
            case "orOperation":
                operation = "OR";
                break;
            case "andOperation":
                operation = "AND";
                break;
            default:
                operation = "AND";
        }
        return operation;
    }

    /**
     * 获得字段对应的solrName
     * @param searchCondition 字段的名称
     * @return
     */
    private String getSolrName(String searchCondition){
        String solrName = "";
        switch(searchCondition) {
            case "allText":
                solrName = "all_text";
                break;
            case "catalog":
                solrName = "text";
                break;
            case "annex":
                solrName = ConstantString.AnnexContentSolrName;
                break;
            case "table_name":
                solrName = "table_id_s";
                break;
            case "title":
                solrName = "title_ik_c";
                break;
            case "documentNumber":
                solrName = "document_number";
                break;
            case "author":
                solrName = "personliable_s_c";
                break;
            default:
                solrName = "all_text";
        }
        return solrName;
    }

    /**
     * 获得搜索范围
     * @param searchArea 搜索范围
     * @param searchOptions 搜索精度
     * @param searchCondition 搜索字段
     * @return
     */
    private String getSearchArea(String searchArea,String searchOptions,String searchCondition){
        String SearchArea = "";
        if("all_text".equals(searchCondition)||"text".equals(searchCondition)||"annex_content".equals(searchCondition)){
            if(searchArea!=null&&!searchArea.isEmpty()&&searchOptions!=null&&!searchOptions.isEmpty()){
                if(searchOptions.equals("accurateSearch")&&searchArea.equals("fullTextArea")){
                    SearchArea = "all_exact_text";
                }else if(searchOptions.equals("accurateSearch")&&searchArea.equals("catalogArea")){
                    SearchArea = "exact_text";
                }else if(searchOptions.equals("accurateSearch")&&searchArea.equals("annexArea")){
                    SearchArea = ConstantString.AnnexContentSolrName + "_text_exact";
                }else if(searchOptions.equals("fuzzySearch")&&searchArea.equals("fullTextArea")){
                    SearchArea = "all_text";
                }else if(searchOptions.equals("fuzzySearch")&&searchArea.equals("catalogArea")){
                    SearchArea = "text";
                }else if(searchOptions.equals("fuzzySearch")&&searchArea.equals("annexArea")){
                    SearchArea = ConstantString.AnnexContentSolrName;
                }else{
                    SearchArea = "all_text";
                }
            }
        }else{
            SearchArea = searchCondition;
        }
        return SearchArea;
    }
}
