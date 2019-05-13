package com.file.management.service.solr;

import com.file.management.pojo.SolrDataConfig;
import com.file.management.pojo.SolrDataSource;
import com.file.management.pojo.SolrTableEntity;
import com.file.management.pojo.SolrTableEntityColumn;
import com.file.management.utils.SolrUtils;
import com.file.management.utils.XsteamUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 对Solr配置文件SolrDataConfig的配置
 */
@Service
public class SolrDataConfigService {

    /**
     * 获得SolrDataConfig对象，即solr-data-config文件,并转换为SolrDataConfig对象
     * @return SolrDataConfig对象
     */
    public SolrDataConfig getSolrDataConfig(){
        SolrUtils solrUtils = new SolrUtils();
        Document document = this.loadSolrDataConfig(solrUtils.getSolrDataConfigPath());
        String xmlStr = XsteamUtil.xmlDocument2String(document);
        SolrDataConfig solrDataConfig = (SolrDataConfig)XsteamUtil.toBean(SolrDataConfig.class,xmlStr);
        return solrDataConfig;
    }

    /**
     * 将SolrDataConfig对象转换为xml字符串，保存SolrDataConfig
     * @param solrDataConfig 需要保存的SolrDataConfig对象
     * @return
     */
    public boolean saveSolrDataConfig(SolrDataConfig solrDataConfig){
        SolrUtils solrUtils = new SolrUtils();
        String xmlStr = XsteamUtil.toXml(solrDataConfig);
        boolean bool = this.rewriteSolrDataConfig(solrUtils.getSolrDataConfigPath(),xmlStr);
        return bool;
    }

    /**
     * 获得solr中数据的DataImportProperty文件，即solr索引的更新时间
     * @return
     */
    public Properties getDataImportProperty(){
        Properties p = new Properties();
        try {
            SolrUtils solrUtils = new SolrUtils();
            InputStream in = new BufferedInputStream(new FileInputStream(solrUtils.getSolrDataimportPath()));
            p.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return p;
    }
    /**
     * 获得solr数据库的配置文件，即solr-data-config文件
     * @param solrDataConfigPath solr数据库配置文件的绝对位置
     * @return
     */
    private Document loadSolrDataConfig(String solrDataConfigPath){
        Document doc = null;
        SAXReader reader = new SAXReader();
        try{
            doc = reader.read(solrDataConfigPath);
        }catch(DocumentException e){
            e.printStackTrace();
            System.out.println("读取SolrDataConfig失败！或文件不存在");
        }
        return doc;
    }

    /**
     * 保存修改后的solr数据库配置文件
     * @param solrDataConfigPath solr数据库配置文件的绝对位置
     * @param xmlStr solr数据库配置文件(xml)的String
     * @return
     */
    private boolean rewriteSolrDataConfig(String solrDataConfigPath,String xmlStr){
        FileWriter fileWritter = null;
        try {
            File xmlFile = new File(solrDataConfigPath);
            fileWritter = new FileWriter(xmlFile);
            Document document = XsteamUtil.string2XMLDocument(xmlStr);
//            Element root = document.getRootElement();
//            Element data = root.element("document");//获取子节点
//            Element entity = data.element("entity");//获取子节点
            //使用dom4j的XMLWriter,&gt;无法转换为>，所以先转换为String
            String documentStr = XsteamUtil.xmlDocument2String(document);
            String documentStrReplace = documentStr.replaceAll("&gt;",">");
            fileWritter.write(documentStrReplace);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("保存SolrDataConfig失败！");
        }finally{
            if (fileWritter!=null) {
                try {
                    fileWritter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("保存SolrDataConfig失败！");
                }
            }
        }
        return false;
    }

    /**
     * 新增数据库连接
     * @param solrDataSourceList 数据库链接列表
     * @param dataSourceName 数据库别名
     * @param driverName 数据库驱动名称
     * @param dataSourceUrl 数据库url
     * @param userName 数据库用户名
     * @param password 数据库用户密码
     * @return
     */
    public List<SolrDataSource> addDataSource(List<SolrDataSource> solrDataSourceList, String dataSourceName, String driverName,
                                 String dataSourceUrl, String userName, String password){
        SolrDataSource solrDataSource = this.getDataSourece(solrDataSourceList,dataSourceName,dataSourceUrl);
        if(solrDataSource!=null){
            System.out.println(dataSourceName+"已经存在，请重新命名");
            System.out.println(dataSourceUrl+"已经存在");
        }else{
            String type = "JdbcDataSource";
            solrDataSource = new SolrDataSource(dataSourceName,type,driverName,dataSourceUrl,userName,password);
            solrDataSourceList.add(solrDataSource);
        }
        return solrDataSourceList;
    }

    /**
     * 删除数据库连接
     * @param solrDataSourceList 数据库链接列表
     * @param dataSourceName 要删除的数据库别名
     * @param dataSourceUrl 要删除的数据库url
     * @return
     */
    public boolean deleteDataSource(List<SolrDataSource> solrDataSourceList, String dataSourceName, String dataSourceUrl){
        SolrDataSource solrDataSource = this.getDataSourece(solrDataSourceList, dataSourceName, dataSourceUrl);
        if (solrDataSource == null) {
            System.out.println("不存在满足条件的数据源");
            return false;
        }else{
            solrDataSourceList.remove(solrDataSource);
            return true;
        }
    }

    /**
     * 获得指定的SolrDataSource
     * @param solrDataSourceList 现有的solrDataSourceList
     * @param dataSourceName 数据库别名
     * @param dataSourceUrl 数据库url
     * @return 存在返回指定的SolrDataSource，不存在返回null;
     */
    public SolrDataSource getDataSourece(List<SolrDataSource> solrDataSourceList, String dataSourceName, String dataSourceUrl){
        for(SolrDataSource solrDataSource: solrDataSourceList){
            if(solrDataSource.getDataSourceName()!=null&&solrDataSource.getDataSourceName().equals(dataSourceName)){
                return solrDataSource;
            }else if(solrDataSource.getDataSourceUrl().equals(dataSourceUrl)){
                return solrDataSource;
            }
        }
        return null;
    }

    /**
     * 新建solr与数据库新建表之间的配置信息
     * @param dataSourceName 所在数据库别名
     * @param tableEntityName 数据库表名
     * @param primaryKey 数据库表中主键名称(有且只有一个主键，suggest：档号的英文名)
     * @param solrTableEntityColumnList 数据库表中列名的list ()
     * @return
     */
    public List<SolrTableEntity> addSolrTableEntity(List<SolrTableEntity> solrTableEntityList,
                                           String tableEntityName, String primaryKey,String dataSourceName,
                                         List<SolrTableEntityColumn> solrTableEntityColumnList){
        SolrTableEntity solrTableEntity = this.getSolrTableEntity(solrTableEntityList,dataSourceName,tableEntityName);
        if(solrTableEntity!=null){
            System.out.println("在该数据源或其他数据源下已经存在相同名称的表");
        }else{
            String fullImportQuery = "select * from " + tableEntityName + " where IS_DEL = '0'";
            String deltaImportQuery = "select * from " + tableEntityName + " where " + primaryKey +
                    " = '${dih.delta." + primaryKey + "}'";
            String deltaQuery = "select " + primaryKey + " from " + tableEntityName +
                    " where LAST_MODIFIED > '${dataimporter." + tableEntityName + ".last_index_time}'";
            String deletedPkQuery = "select " + primaryKey + " from " + tableEntityName + " where IS_DEL = '1'";
            solrTableEntity = new SolrTableEntity(tableEntityName, primaryKey, dataSourceName, fullImportQuery,
                    deltaImportQuery, deltaQuery, deletedPkQuery,solrTableEntityColumnList);
            solrTableEntityList.add(solrTableEntity);
        }
        return solrTableEntityList;
    }

    /**
     * 需要删除的solr数据库配置文件中表的名称
     * @param dataSourceName 所在数据库别名
     * @param tableEntityName 数据库中表的名称
     * @return
     */
    public boolean deleteSolrTableEntity(List<SolrTableEntity> solrTableEntityList,
                                         String dataSourceName, String tableEntityName){
        SolrTableEntity solrTableEntity = this.getSolrTableEntity(solrTableEntityList,dataSourceName,tableEntityName);
        if(solrTableEntity!=null){
            solrTableEntityList.remove(solrTableEntity);
            return true;
        }else{
            System.out.println("不存在满足条件的表");
            return false;
        }
    }

    /**
     * 获得相同表名的SolrTableEntity
     * 无论数据源相同否，数据库表名都应不同
     * @param solrTableEntityList SolrTableEntityList
     * @param dataSourceName 数据源别名
     * @param tableEntityName 数据库表名
     * @return
     */
    public SolrTableEntity getSolrTableEntity(List<SolrTableEntity> solrTableEntityList,
                                              String dataSourceName, String tableEntityName){
//        for(SolrTableEntity solrTableEntity: solrTableEntityList){
//            if(solrTableEntity.getDataSourceName()!=null&&solrTableEntity.getDataSourceName().equals(dataSourceName)
//                    &&solrTableEntity.getTableName().equals(tableEntityName)){
//                return solrTableEntity;
//            }else if(solrTableEntity.getDataSourceName()==null&&dataSourceName==null
//                    &&solrTableEntity.getTableName().equals(tableEntityName)){
//                return solrTableEntity;
//            }
//        }
        for(SolrTableEntity solrTableEntity: solrTableEntityList){
            if(solrTableEntity.getTableName().equals(tableEntityName)){
                return solrTableEntity;
            }
        }
        return null;
    }

    /**
     * 将数据库表字段按不同的需求转换为solr中dynamicField的格式
     * @param documentNumber 指定的uniqueKey，档号的字段名称；
     * @param solrStringList string型，字段在综合查询中不可被检索；
     * @param solrStringCopyTextList string型，字段在综合查询中可以被检索；
     * @param solrStringArrList string型，允许该字段有多个值(即组成数组)，字段在综合查询中不可被检索；
     * @param solrStringArrCopyTextList string型，允许该字段有多个值(即组成数组)，字段在综合查询中可以被检索；
     * @param solrIKList 可被分词类型，字段在综合查询中不可被检索；(不建议，用solrStringList)
     * @param solrIKCopyTextList 可被分词类型，字段在综合查询中可以被检索；
     * @param solrIKArrList 可被分词类型，允许该字段有多个值(即组成数组)，字段在综合查询中不可被检索；(不建议，用solrStringArrList)
     * @param solrIKArrCopyTextList 可被分词类型，允许该字段有多个值(即组成数组)，字段在综合查询中可以被检索；
     * @param solrDateList date型，字段在综合查询中不可被检索；
     * @param solrDateCopyTextList date型，字段在综合查询中可以被检索；
     * @param solrDateArrList date型，允许该字段有多个值(即组成数组)，字段在综合查询中不可被检索；
     * @param solrDateArrCopyTextList date型，字段在综合查询中不可被检索；
     * @return 构造好的SolrTableEntityColumnList，可直接用 于构造TableEntity
     */
    public List<SolrTableEntityColumn> createSolrTableEntityColumnList(String documentNumber,List<String> solrStringList,
           List<String> solrStringCopyTextList, List<String> solrStringArrList, List<String> solrStringArrCopyTextList,
           List<String> solrIKList, List<String> solrIKCopyTextList, List<String> solrIKArrList,
           List<String> solrIKArrCopyTextList, List<String> solrDateList, List<String> solrDateCopyTextList,
           List<String> solrDateArrList, List<String> solrDateArrCopyTextList){
        List<SolrTableEntityColumn> SolrTableEntityColumnList = new ArrayList<SolrTableEntityColumn>();
        //档号为solr中的uniqueKey
        if(documentNumber!=null){
            SolrTableEntityColumn solrTableEntityColumn = new SolrTableEntityColumn(documentNumber,"DOCUMENT_NUMBER");
            SolrTableEntityColumnList.add(solrTableEntityColumn);
        }
        //变量名不建议改变
        SolrTableEntityColumnList = this.createSolrTableEntityColumn(SolrTableEntityColumnList,solrStringList,"solrStringList");
        SolrTableEntityColumnList = this.createSolrTableEntityColumn(SolrTableEntityColumnList,solrStringCopyTextList,"solrStringCopyTextList");
        SolrTableEntityColumnList = this.createSolrTableEntityColumn(SolrTableEntityColumnList,solrStringArrList,"solrStringArrList");
        SolrTableEntityColumnList = this.createSolrTableEntityColumn(SolrTableEntityColumnList,solrStringArrCopyTextList,"solrStringArrCopyTextList");
        SolrTableEntityColumnList = this.createSolrTableEntityColumn(SolrTableEntityColumnList,solrIKList,"solrIKList");
        SolrTableEntityColumnList = this.createSolrTableEntityColumn(SolrTableEntityColumnList,solrIKCopyTextList,"solrIKCopyTextList");
        SolrTableEntityColumnList = this.createSolrTableEntityColumn(SolrTableEntityColumnList,solrIKArrList,"solrIKArrList");
        SolrTableEntityColumnList = this.createSolrTableEntityColumn(SolrTableEntityColumnList,solrIKArrCopyTextList,"solrIKArrCopyTextList");
        SolrTableEntityColumnList = this.createSolrTableEntityColumn(SolrTableEntityColumnList,solrDateList,"solrDateList");
        SolrTableEntityColumnList = this.createSolrTableEntityColumn(SolrTableEntityColumnList,solrDateCopyTextList,"solrDateCopyTextList");
        SolrTableEntityColumnList = this.createSolrTableEntityColumn(SolrTableEntityColumnList,solrDateArrList,"solrDateArrList");
        SolrTableEntityColumnList = this.createSolrTableEntityColumn(SolrTableEntityColumnList,solrDateArrCopyTextList,"solrDateArrCopyTextList");
        return SolrTableEntityColumnList;
    }

    /**
     * 根据list的名称，将数据库表字段组装为solr中dynamicField的格式
     * @param SolrTableEntityColumnList 构造好的SolrTableEntityColumnList，可直接用于构造TableEntity
     * @param tableColumnList 需要转换的数据库表的list
     * @param switchFlag list变量名的String，当做switch的flag(变量名不建议改变)
     * @return
     */
    public List<SolrTableEntityColumn> createSolrTableEntityColumn(List<SolrTableEntityColumn> SolrTableEntityColumnList,
                                                                   List<String> tableColumnList, String switchFlag){
        String SOLR_STRING = "_s";  //string型
        String SOLR_STRING_COPYTEXT = "_s_c";  //string型，可被综合查询查询到
        String SOLR_STRING_MULTIVALUED = "_ss";  //string型，允许该字段有多个值
        String SOLR_STRING_MULTIVALUED_COPYTEXT = "_ss_c";  //string型，允许有改字段有多个值，可被综合查询查询到

        String SOLR_IK = "_ik";  //可被分词类型
        String SOLR_IK_COPYTEXT = "_ik_c";  //可被分词类型，可被综合查询查询到
        String SOLR_IK_MULTIVALUED = "_iks";  //可被分词类型，允许该字段有多个值
        String SOLR_IK_MULTIVALUED_COPYTEXT = "_iks_c";  //可被分词类型，允许该字段有多个值，可被综合查询查询到

        String SOLR_DATE = "_dt";  //date型
        String SOLR_DATE_COPYTEXT = "_dt_c";  //date型，可被综合查询查询到
        String SOLR_DATE_MULTIVALUED = "_dts";  //date型，允许该字段有多个值
        String SOLR_DATE_MULTIVALUED_COPYTEXT = "_dts_c";  //date型，允许该字段有多个值，可被综合查询查询到

        if(tableColumnList==null) return SolrTableEntityColumnList;
        String suffixString = "";
        switch(switchFlag){
            case "solrStringList":
                suffixString = SOLR_STRING;
                break;
            case "solrStringCopyTextList":
                suffixString = SOLR_STRING_COPYTEXT;
                break;
            case "solrStringArrList":
                suffixString = SOLR_STRING_MULTIVALUED;
                break;
            case "solrStringArrCopyTextList":
                suffixString = SOLR_STRING_MULTIVALUED_COPYTEXT;
                break;
            case "solrIKList":
                suffixString = SOLR_IK;
                break;
            case "solrIKCopyTextList":
                suffixString = SOLR_IK_COPYTEXT;
                break;
            case "solrIKArrList":
                suffixString = SOLR_IK_MULTIVALUED;
                break;
            case "solrIKArrCopyTextList":
                suffixString = SOLR_IK_MULTIVALUED_COPYTEXT;
                break;
            case "solrDateList":
                suffixString = SOLR_DATE;
                break;
            case "solrDateCopyTextList":
                suffixString = SOLR_DATE_COPYTEXT;
                break;
            case "solrDateArrList":
                suffixString = SOLR_DATE_MULTIVALUED;
                break;
            case "solrDateArrCopyTextList":
                suffixString = SOLR_DATE_MULTIVALUED_COPYTEXT;
                break;
            default:
                suffixString = SOLR_IK_COPYTEXT;
        }
        for(String tableColumn : tableColumnList) {
            String solrColumnName = tableColumn.toLowerCase()+suffixString;  //将数据库字段同一转换为小写
            SolrTableEntityColumn solrTableEntityColumn = new SolrTableEntityColumn(tableColumn,solrColumnName);
            SolrTableEntityColumnList.add(solrTableEntityColumn);
        }
        return SolrTableEntityColumnList;
    }
}
