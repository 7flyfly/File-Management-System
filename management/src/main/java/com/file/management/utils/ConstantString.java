package com.file.management.utils;

import org.springframework.stereotype.Service;

/**
 * 保存solr的访问信息
 * 保存solr中的关键字段的名称
 */
public class ConstantString {
    //DocumentNumber在数据库中所对应的字段名称
    public final static String DocumentNumberDatabaseName= "DocumentNo";
    //Annex在数据库中所对应的字段名称
    public final static String AnnexDatabaseName= "Annex";
    //Annex在数据库中所对应的字段的分隔符
    public final static String AnnexDatabaseSplitChar= ";";
    //Annex在solr中对应的field名称
    public final static String AnnexContentSolrName= "annex_content";
    //图片phash在solr中对应的field名称
    public final static String ImageContentSolrName= "phash_numsplit";

    //SolrCloud所使用的数据集地址
    final static String solrURL = "http://127.0.0.1:8983/solr/" ;
    //core名称
    final static String coreName = "test_core";
    //solr数据库配置文件的地址
    final static String solrDataConfigPath = "D:\\solr\\solr\\server\\solr\\test_core\\conf\\solr-data-config.xml";
    //solr数据导入信息的地址
    final static String solrDataimportPath = "D:\\solr\\solr\\server\\solr\\test_core\\conf\\dataimport.properties";
}
