package com.file.management.service.solr;

import com.file.management.dao.DynamicSQL;
import com.file.management.pojo.*;
import com.file.management.service.ImageProcessing.ImagePHashService;
import com.file.management.utils.SolrUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.*;


import org.apache.tika.Tika;
import org.springframework.util.StringUtils;

/**
 * 对solr的一些操作
 */
@Service
public class SolrService {
    @Autowired
    private SolrDataConfigService solrDataConfigService;

    @Autowired
    private DynamicSQL dynamicSQL;

    @Autowired
    private ImagePHashService imagePHashService;
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
        SolrClient solrClient = null;
        try {
            HashMap<Boolean,String> hashMap = new HashMap<Boolean,String>();
            SolrDataConfig solrDataConfig = solrDataConfigService.getSolrDataConfig();
            List<SolrDataSource> solrDataSourcesList = solrDataConfig.getSolrDataSourceList();
            solrDataSourcesList = solrDataConfigService.addDataSource(solrDataSourcesList,dataSourceName,driveName,
                    dataSourceUrl,userName,password);
            solrDataConfig.setSolrDataSourceList(solrDataSourcesList);  //覆盖原有solrDataSourcesList
            boolean bool1 = solrDataConfigService.saveSolrDataConfig(solrDataConfig);
            SolrUtils solrUtils = new SolrUtils();
            solrClient = solrUtils.createSolrClient();
            boolean bool2 = this.reloadConfig(solrClient);
            String boolMessage = bool1&&bool2 ? "新增数据源成功" : "新增数据源失败";
            hashMap.put(true,boolMessage);
            solrClient.close();
            return hashMap;
        }catch(Exception e){
            if(solrClient!=null){
                try {
                    solrClient.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
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
        SolrClient solrClient = null;
        try {
            HashMap<Boolean,String> hashMap = new HashMap<Boolean,String>();
            SolrDataConfig solrDataConfig = solrDataConfigService.getSolrDataConfig();
            //一个solrEntityDocumentList中只有一个元素
            List<SolrEntityDocument>  solrEntityDocumentList = solrDataConfig.getSolrEntityDocumentList();
            //solrEntityDocument中存放solrTableEntityList
            List<SolrTableEntity> solrTableEntityList = solrEntityDocumentList.get(0).getSolrTableEntityList();
            if(solrTableEntityList==null) solrTableEntityList = new ArrayList<SolrTableEntity>();
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
            boolean bool1 = solrDataConfigService.saveSolrDataConfig(solrDataConfig);
            SolrUtils solrUtils = new SolrUtils();
            solrClient = solrUtils.createSolrClient();
            boolean bool2 = this.reloadConfig(solrClient);
            String boolMessage = bool1&&bool2 ? "添加成功" : "添加失败";
            hashMap.put(true,boolMessage);
            solrClient.close();
            return hashMap;
        }catch(Exception e){
            if(solrClient!=null){
                try {
                    solrClient.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            HashMap<Boolean,String> hashMap = new HashMap<Boolean,String>();
            hashMap.put(false,e.getMessage());
            return hashMap;
        }
    }

    /**
     * 针对表中的某一条档案进行更新
     * @param tableName 表名
     * @param document_number 档案号
     * @param documentNumberDatabaseName  档案号在库表中对应的字段名
     * @param annexDatabaseName 附件在库表中对应的字段名
     * @param arrSplit 附件的分割符
     * @param fileContentSolrName 附件在solr中的字段名称
     * @return
     */
    public HashMap<Boolean,String> refreshOneDocument2Solr(String tableName, String document_number, String documentNumberDatabaseName,
                                                           String annexDatabaseName, String arrSplit, String fileContentSolrName){
        HashMap<Boolean,String> hashMap = new HashMap<Boolean,String>();
        if(documentNumberDatabaseName==null) documentNumberDatabaseName = "DocumentNo";
        if(annexDatabaseName==null) annexDatabaseName = "Annex";
        if(arrSplit==null) arrSplit = ";";
        if(fileContentSolrName==null) fileContentSolrName = "annex_content";
        if(tableName!=null&&!tableName.equals("")){
            List AttrNameList = dynamicSQL.selectAttrNameByTableName(tableName);
            if(!(AttrNameList.contains(annexDatabaseName)&&AttrNameList.contains(documentNumberDatabaseName))){
                hashMap.put(false,"失败! 属性名不存在");
                return hashMap;
            }
            List resultList = dynamicSQL.selectResultListByTableNameAndAttr(tableName,documentNumberDatabaseName,
                    document_number);
            int documentNumber_index = AttrNameList.indexOf(documentNumberDatabaseName);
            int Annex_index = AttrNameList.indexOf(annexDatabaseName);
            try {
                SolrUtils solrUtils = new SolrUtils();
                SolrClient solrClient = solrUtils.createSolrClient();
                Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
                for(Object row : resultList){
                    Object[] attValue = (Object[]) row;
                    String fileName =(String)attValue[Annex_index];
                    String documentNumber =(String)attValue[documentNumber_index];
                    SolrDocument document = this.getDoucmentByDocumentNumber(solrClient,documentNumber);
                    if(document==null){
//                        hashMap.put(false,"档案" + documentNumber + "不存在!");
                        System.out.println("档案" + documentNumber + "不存在!");
                    }
                    SolrInputDocument solrInputDocument = new SolrInputDocument();
                    //复制 solrInputDocument
                    for(String field : document.getFieldNames()){
                        if(!field.equals("_version_")){
                            solrInputDocument.setField(field,document.getFieldValue(field));
                        }
                    }
                    //重写 solrInputDocument
                    if(solrInputDocument.containsKey(fileContentSolrName)){
                        solrInputDocument.removeField(fileContentSolrName);

                    }
                    for(String field : document.getFieldNames()){
                        for(int i = 0; i<AttrNameList.size(); i++){
                            if(field.contains(((String)AttrNameList.get(i)).toLowerCase())){
                                solrInputDocument.setField(field,attValue[i]);
                            }
                        }
                    }
                    boolean flag = true;
                    if(fileName!=null){ //文件存在
                        String[] fileNameArr = fileName.split(arrSplit);
                        for(String filePath : fileNameArr){
                            File f = new File(filePath);
                            if(!f .exists()) { flag = false; }  //文件不存在
                            if(!this.getFileContentType(filePath)) { flag = false;}  //文件类型不是富文本类型
                            if(!flag){
//                                hashMap.put(false,filePath + "：文件导入失败！");
                                System.out.println(filePath + "：文件导入失败！");
                                continue;
                            }
                            Tika tika = new Tika();
//                    System.out.println("filetype:"+tika.detect(filePath));  //利用Tika的detect方法检测文件的实际类型
                            String fileContent = tika.parseToString(f);  //利用Tika的parseToString()方法读取文件的文本内容
                            solrInputDocument.addField(fileContentSolrName,fileContent);
                        }
                    }
                    docs.add(solrInputDocument);
                }
                solrClient.add(docs);
                solrClient.commit();
                hashMap.put(true,"成功导入表："+tableName);
                return hashMap;
            } catch (Exception e) {
                e.printStackTrace();
                hashMap.put(false,tableName+"导入失败!");
                return hashMap;
            }
        }else{
            hashMap.put(false,"失败! 表名不能为空");
            return hashMap;
        }
    }

    /**
     * 导入表，将表中的数据建立索引
     * @param tableName 表名，非空
     */
    public HashMap<Boolean,String> fullImportTable2Solr(String tableName, String documentNumberDatabaseName,
                                                        String annexDatabaseName, String arrSplit, String fileContentSolrName,
                                                        String imageContentSolrName){
        try{
            HashMap<Boolean,String> hashMap = new HashMap<Boolean,String>();
            if(documentNumberDatabaseName==null) documentNumberDatabaseName = "DocumentNo";
            if(annexDatabaseName==null) annexDatabaseName = "Annex";
            if(arrSplit==null) arrSplit = ";";
            if(fileContentSolrName==null) fileContentSolrName = "annex_content";
            if(imageContentSolrName==null) imageContentSolrName = "phash_numsplit";
            if(tableName!=null&&!tableName.equals("")){
                System.out.println("tableName:"+tableName);
                SolrUtils solrUtils = new SolrUtils();
                SolrClient solrClient1 = solrUtils.createSolrClient();
                boolean bool1 = this.fullImportTable(solrClient1,tableName);
                Thread.sleep(1000);
                SolrClient solrClient2 = solrUtils.createSolrClient();
                boolean bool2 = this.fullImportFiles(solrClient2,tableName,documentNumberDatabaseName,annexDatabaseName,
                        arrSplit,fileContentSolrName,imageContentSolrName);
                solrClient1.close();
                solrClient2.close();
                if(bool1&&bool2){
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
        } catch (Exception e) {
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
    public HashMap<Boolean,String> deltaImportTable2Solr(String tableName, String documentNumberDatabaseName,
                                                         String annexDatabaseName, String arrSplit, String fileContentSolrName,
                                                         String imageContentSolrName){
        try{
            HashMap<Boolean,String> hashMap = new HashMap<Boolean,String>();
            if(documentNumberDatabaseName==null) documentNumberDatabaseName = "DocumentNo";
            if(annexDatabaseName==null) annexDatabaseName = "Annex";
            if(arrSplit==null) arrSplit = ";";
            if(fileContentSolrName==null) fileContentSolrName = "annex_content";
            if(imageContentSolrName==null) imageContentSolrName = "phash_numsplit";
            if(tableName!=null&&!tableName.equals("")){
                System.out.println("tableName:"+tableName);
                SolrUtils solrUtils = new SolrUtils();
                SolrClient solrClient1 = solrUtils.createSolrClient();
                boolean bool1 = this.deltaImportTable(solrClient1,tableName);
                Thread.sleep(1000);
                SolrClient solrClient2 = solrUtils.createSolrClient();
                boolean bool2 = this.deltaImportFiles(solrClient2,tableName,documentNumberDatabaseName,annexDatabaseName,
                        arrSplit,fileContentSolrName,imageContentSolrName);
                solrClient1.close();
                solrClient2.close();
                if(bool1&&bool2){
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
        } catch (Exception e) {
            e.printStackTrace();
            HashMap<Boolean,String> hashMap = new HashMap<Boolean,String>();
            hashMap.put(false,e.getMessage());
            return hashMap;
        }
    }

// ----------------以下是对solr的基本操作------------------------------

    /**
     * 将表中数据导入solr，建立索引
     * @param solrClient：solr客户端
     * @param tableName: 表名
     */
    private boolean fullImportTable(SolrClient solrClient, String tableName){
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
    private boolean deltaImportTable(SolrClient solrClient, String tableName){
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
     * 读取数据库中附件所在的地址，读取文件内容构建索引
     * @param solrClient solr客户端
     * @param tableName 表名
     * @param documentNumberDatabaseName Documentnumber在数据库中字段的名称
     * @param annexDatabaseName Annex在数据库中字段的名称
     * @param arrSplit 附件的分割符
     * @param fileContentSolrName 附件内容在solr中字段的名称
     */
    private boolean fullImportFiles(SolrClient solrClient, String tableName, String documentNumberDatabaseName,
                                    String annexDatabaseName, String arrSplit, String fileContentSolrName,
                                    String imageContentSolrName){
        Properties properties = solrDataConfigService.getDataImportProperty();
        String lastModified = properties.getProperty(tableName + ".last_index_time");
        List AttrNameList = dynamicSQL.selectAttrNameByTableName(tableName);
        List resultList = dynamicSQL.selectByTableName(tableName);
        int documentNumber_index = -1,Annex_index = -1;
        Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
        try {
            if(!AttrNameList.contains(documentNumberDatabaseName)){
                return false;
            }else if(AttrNameList.contains(annexDatabaseName)){
                Annex_index = AttrNameList.indexOf(annexDatabaseName);
            }
            documentNumber_index= AttrNameList.indexOf(documentNumberDatabaseName);
            for(Object row : resultList){
                Object[] attValue = (Object[]) row;
                String documentNumber =(String)attValue[documentNumber_index];
                SolrDocument document = this.getDoucmentByDocumentNumber(solrClient,documentNumber);
                SolrInputDocument solrInputDocument = new SolrInputDocument();
                for(String field : document.getFieldNames()){
                    if(!field.equals("_version_")&&!field.equals(fileContentSolrName)&&!field.equals(imageContentSolrName))
                        solrInputDocument.setField(field,document.getFieldValue(field));
                }
                if(Annex_index!=-1){
                    String fileNameStr =(String)attValue[Annex_index];
                    String[] fileUrlArr = fileNameStr.split(arrSplit);
                    for(String fileUrl : fileUrlArr){
                        if(!fileUrl.contains("http")){
                            System.out.println(fileUrl + "：文件导入失败！");
                            continue;
                        }
                        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
                        String urlHead = fileUrl.replace(fileName,"");
                        URL url= new URL(urlHead + URLEncoder.encode(fileName,"utf-8")); //直接使用会报400错误
                        URLConnection con = url.openConnection();
                        String message = con.getHeaderField(0);
                        if (StringUtils.hasText(message) && message.startsWith("HTTP/1.1 404")) {  //网络资源不存在
                            System.out.println(fileName+":不存在!");
                            continue;
                        }
                        if(this.getFileContentType(fileUrl)){ //富文本类型
                            Tika tika = new Tika();
//                    System.out.println("filetype:"+tika.detect(filePath));  //利用Tika的detect方法检测文件的实际类型
                            String fileContent = tika.parseToString(con.getInputStream());  //利用Tika的parseToString()方法读取文件的文本内容
                            solrInputDocument.addField(fileContentSolrName,fileContent);
                        }else if(this.getPictureTypeType(fileUrl)){ //图片类型
                            String imagePHash = imagePHashService.getPHash(con.getInputStream());
                            solrInputDocument.addField(imageContentSolrName,imagePHash);
                        }else{
                            System.out.println(fileUrl + "：既不是富文本类型！也不是图片类型");
                        }
                    }
                }
                docs.add(solrInputDocument);
            }
            solrClient.add(docs);
            solrClient.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 读取数据库中附件所在的地址，增量读取文件内容构建索引（根据solr配置文件中的last_index_time选择更新）
     * @param solrClient solr客户端
     * @param tableName 表名
     * @param documentNumberDatabaseName Documentnumber在数据库中字段的名称
     * @param annexDatabaseName Annex在数据库中字段的名称
     * @param arrSplit 附件的分割符
     * @param fileContentSolrName 附件内容在solr中字段的名称
     * @param imageContentSolrName 附件图片在solr中字段的名称
     */
    private boolean deltaImportFiles(SolrClient solrClient, String tableName, String documentNumberDatabaseName,
                                     String annexDatabaseName, String arrSplit, String fileContentSolrName,
                                     String imageContentSolrName){
        Properties properties = solrDataConfigService.getDataImportProperty();
        String lastModified = properties.getProperty(tableName + ".last_index_time");
        List AttrNameList = dynamicSQL.selectAttrNameByTableName(tableName);
        List resultList = dynamicSQL.selectLastModifiedByTableName(tableName,lastModified);
        int documentNumber_index = -1,Annex_index = -1;
        Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
        try {
            if(!AttrNameList.contains(documentNumberDatabaseName)){
                return false;
            }else if(AttrNameList.contains(annexDatabaseName)){
                Annex_index = AttrNameList.indexOf(annexDatabaseName);
            }
            documentNumber_index= AttrNameList.indexOf(documentNumberDatabaseName);
            for(Object row : resultList){
                Object[] attValue = (Object[]) row;
                String documentNumber =(String)attValue[documentNumber_index];
                SolrDocument document = this.getDoucmentByDocumentNumber(solrClient,documentNumber);
                SolrInputDocument solrInputDocument = new SolrInputDocument();
                for(String field : document.getFieldNames()){
                    if(!field.equals("_version_")&&!field.equals(fileContentSolrName)&&!field.equals(imageContentSolrName))
                        solrInputDocument.setField(field,document.getFieldValue(field));
                }
                if(Annex_index!=-1){
                    String fileNameStr =(String)attValue[Annex_index];
                    String[] fileUrlArr = fileNameStr.split(arrSplit);
                    for(String fileUrl : fileUrlArr){
                        if(!fileUrl.contains("http")){
                            System.out.println(fileUrl + "：文件导入失败！");
                            continue;
                        }
                        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
                        String urlHead = fileUrl.replace(fileName,"");
                        URL url= new URL(urlHead + URLEncoder.encode(fileName,"utf-8")); //直接使用会报400错误
                        URLConnection con = url.openConnection();
                        String message = con.getHeaderField(0);
                        if (StringUtils.hasText(message) && message.startsWith("HTTP/1.1 404")) {  //网络资源不存在
                            System.out.println(fileName+":不存在!");
                            continue;
                        }
                        if(this.getFileContentType(fileUrl)){ //富文本类型
                            Tika tika = new Tika();
//                    System.out.println("filetype:"+tika.detect(filePath));  //利用Tika的detect方法检测文件的实际类型
                            String fileContent = tika.parseToString(con.getInputStream());  //利用Tika的parseToString()方法读取文件的文本内容
                            solrInputDocument.addField(fileContentSolrName,fileContent);
                        }else if(this.getPictureTypeType(fileUrl)){ //图片类型
                            String imagePHash = imagePHashService.getPHash(con.getInputStream());
                            solrInputDocument.addField(imageContentSolrName,imagePHash);
                        }else{
                            System.out.println(fileUrl + "：既不是富文本类型！也不是图片类型");
                        }
                    }
                }
                docs.add(solrInputDocument);
            }
            if(docs.size()==0){
                return false;
            }else{
                solrClient.add(docs);
                solrClient.commit();
            }
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据id查询索引
     * @param solrClient：solr客户端
     * @param DocumentNumber: 档号
     */
    public SolrDocument getDoucmentByDocumentNumber(SolrClient solrClient, String DocumentNumber){
        SolrDocument document = null;
        try {
            document = solrClient.getById(DocumentNumber);
            return document;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("cannot find document:" + DocumentNumber);
            return document;
        }
    }

    /**
     * 根据id从solr中删除一条数据
     * @param solrClient：solr客户端
     * @param DocumentNumber: 档号
     */
    public boolean deleteDocumentByDocumentNumber(SolrClient solrClient, String DocumentNumber){
        try {
            SolrUtils solrUtils = new SolrUtils();
            solrClient.deleteById(solrUtils.getCoreName(),DocumentNumber);
            solrClient.commit(solrUtils.getCoreName());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("cannot delete document:" + DocumentNumber);
            return false;
        }
    }

    /**
     * 重新载入配置文件
     * @param solrClient solr客户端
     */
    private boolean reloadConfig(SolrClient solrClient){
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
    private boolean getImportInfo(SolrClient solrClient){
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

    /**
     * 根据文件名获取文件的ContentType类型,是否可以被解析 富文本
     */
    public boolean getFileContentType(String filename) {
        boolean bool = false;
        String prefix = filename.substring(filename.lastIndexOf(".") + 1);
        switch(prefix) {
            case "xlsx":
                bool = true;
                break;
            case "pdf":
                bool = true;
                break;
            case "doc":
                bool = true;
                break;
            case "txt":
                bool = true;
                break;
            case "xls":
                bool = true;
                break;
            case "docx":
                bool = true;
                break;
            case "ppt":
                bool = true;
                break;
            case "pptx":
                bool = true;
                break;
            default:
                bool = false;
        }
        return bool;
    }

    /**
     * 判断文件是否是图片类型
     * @param filename 文件名称
     * @return
     */
    public boolean getPictureTypeType(String filename) {
        boolean bool = false;
        String prefix = filename.substring(filename.lastIndexOf(".") + 1);
        switch(prefix) {
            case "bmp":
                bool = true;
                break;
            case "jpeg":
                bool = true;
                break;
            case "gif":
                bool = true;
                break;
            case "psd":
                bool = true;
                break;
            case "png":
                bool = true;
                break;
            case "tiff":
                bool = true;
                break;
            case "tga":
                bool = true;
                break;
            case "eps":
                bool = true;
                break;
            case "jpg":
                bool = true;
                break;
            default:
                bool = false;
        }
        return bool;
    }
}
