package com.file.management.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("dataConfig")
/**
 * 对应Solr-data-config文件
 */
public class SolrDataConfig {
    //solr中数据库源
    @XStreamImplicit(itemFieldName="dataSource")
    private List<SolrDataSource> solrDataSourceList;

    //数据库中需要导入solr中的表
    @XStreamImplicit(itemFieldName="document")
    private List<SolrEntityDocument>  solrEntityDocumentList;

    public List<SolrDataSource> getSolrDataSourceList() {
        return solrDataSourceList;
    }

    public void setSolrDataSourceList(List<SolrDataSource> solrDataSourceList) {
        this.solrDataSourceList = solrDataSourceList;
    }

    public List<SolrEntityDocument> getSolrEntityDocumentList() {
        return solrEntityDocumentList;
    }

    public void setSolrEntityDocumentList(List<SolrEntityDocument> solrEntityDocumentList) {
        this.solrEntityDocumentList = solrEntityDocumentList;
    }

    @Override
    public String toString() {
        return "SolrDataConfig{" +
                "solrDataSourceList=" + solrDataSourceList +
                ", solrEntityDocumentList=" + solrEntityDocumentList +
                '}';
    }
}
