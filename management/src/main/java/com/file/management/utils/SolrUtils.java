package com.file.management.utils;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

import javax.persistence.Entity;

public class SolrUtils {
    private  SolrClient solrClient; //Solr服务器链接的
    //创建solr客户端
    public SolrClient createSolrClient(){
        String url = ConstantString.solrURL + ConstantString.coreName;
        solrClient = new HttpSolrClient.Builder(url).build();
        return solrClient;
    }

    //创建solr客户端
    public void SolrConnection(){
        String url = ConstantString.solrURL + ConstantString.coreName;
        solrClient = new HttpSolrClient.Builder(ConstantString.solrURL).build();
    }

    //获取solr客户端
    public SolrClient getSolrClient(){
        return solrClient;
    }

    public String getSolrURL() {
        return ConstantString.solrURL;
    }

    public String getCoreName() {
        return ConstantString.coreName;
    }

    public String getSolrDataConfigPath(){return ConstantString.solrDataConfigPath;}

    public String getSolrDataimportPath() {
        return ConstantString.solrDataimportPath;
    }

}
