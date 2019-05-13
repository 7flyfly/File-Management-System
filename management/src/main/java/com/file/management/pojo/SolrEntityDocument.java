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
@XStreamAlias("document")
/**
 * 对应Solr-data-config文件中的document
 */
public class SolrEntityDocument {
    //具体的表的信息
    @XStreamImplicit(itemFieldName="entity")
    private List<SolrTableEntity> solrTableEntityList;

    public List<SolrTableEntity> getSolrTableEntityList() {
        return solrTableEntityList;
    }

    public void setSolrTableEntityList(List<SolrTableEntity> solrTableEntityList) {
        this.solrTableEntityList = solrTableEntityList;
    }

    @Override
    public String toString() {
        return "SolrEntityDocument{" +
                "solrTableEntityList=" + solrTableEntityList +
                '}';
    }
}
