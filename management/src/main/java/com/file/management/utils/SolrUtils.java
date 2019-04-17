package com.file.management.utils;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

import javax.persistence.Entity;

public class SolrUtils {
    private static SolrClient solrClient; //Solr服务器链接的
    private static String SolrURL = "http://127.0.0.1:8983/solr/" ; //SolrCloud所使用的数据集地址
    private static CloudSolrClient cloudSolrClient;//Zookeeper服务器连接
    private static String zkHostURL;//zookeeper连接地址
    private static String cloudCollection;//云端数据集
    private static String coreName = "test_core";//core名称

    //创建solr客户端
    public SolrClient createSolrClient(){
        solrClient = new HttpSolrClient.Builder(this.SolrURL).build();
        return solrClient;
    }

    //创建solr客户端
    public void SolrConnection(){
        solrClient = new HttpSolrClient.Builder(this.SolrURL).build();
    }

    //获取solr客户端
    public SolrClient getSolrClient(){
        return solrClient;
    }

    public String getSolrURL() {
        return SolrURL;
    }

    public void setSolrURL(String setSolrURL) {
        this.SolrURL = setSolrURL;
    }

    public String getCoreName() {
        return coreName;
    }

    public void setCoreName(String coreName) {
        this.coreName = coreName;
    }
}
