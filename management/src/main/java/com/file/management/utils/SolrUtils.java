package com.file.management.utils;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

import javax.persistence.Entity;

public class SolrUtils {
    private  SolrClient solrClient; //Solr服务器链接的
    private  String solrURL = "http://127.0.0.1:8983/solr/" ; //SolrCloud所使用的数据集地址
    private  CloudSolrClient cloudSolrClient;//Zookeeper服务器连接
    private  String zkHostURL;//zookeeper连接地址
    private  String cloudCollection;//云端数据集
//    private static String coreName = "file_core";//core名称
    private  String coreName = "test_core";//core名称
//    private  String solrDataConfigPath = "D:\\solr\\solr-data-config.xml";
    private  String solrDataConfigPath = "D:\\solr\\solr\\server\\solr\\test_core\\conf\\solr-data-config.xml";
    private  String solrDataimportPath = "D:\\solr\\solr\\server\\solr\\test_core\\conf\\dataimport.properties";
    //创建solr客户端
    public SolrClient createSolrClient(){
        String url = this.solrURL + coreName;
        solrClient = new HttpSolrClient.Builder(url).build();
        return solrClient;
    }

    //创建solr客户端
    public void SolrConnection(){
        String url = this.solrURL + coreName;
        solrClient = new HttpSolrClient.Builder(this.solrURL).build();
    }

    //获取solr客户端
    public SolrClient getSolrClient(){
        return solrClient;
    }

    public String getSolrURL() {
        return solrURL;
    }

    public void setSolrURL(String setSolrURL) {
        this.solrURL = setSolrURL;
    }

    public String getCoreName() {
        return coreName;
    }

    public void setCoreName(String coreName) {
        this.coreName = coreName;
    }

    public String getSolrDataConfigPath(){return this.solrDataConfigPath;}

    public void setSolrDataConfigPath(String solrDataConfigPath){
        this.solrDataConfigPath = solrDataConfigPath;
    }

    public String getSolrDataimportPath() {
        return solrDataimportPath;
    }

    public void setSolrDataimportPath(String solrDataimportPath) {
        this.solrDataimportPath = solrDataimportPath;
    }
}
