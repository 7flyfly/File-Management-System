package com.file.management.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@XStreamAlias("field")
/**
 * 对应Solr-data-config文件中的document中的entity中的field
 */
public class SolrTableEntityColumn {
    //数据库中字段的名称
    @XStreamAsAttribute
    @XStreamAlias("column")
    private String columnName;

    //solr中对应的field名称
    @XStreamAsAttribute
    @XStreamAlias("name")
    private String solrColumnName;

    public SolrTableEntityColumn() {
    }

    public SolrTableEntityColumn(String columnName, String solrColumnName) {
        this.columnName = columnName;
        this.solrColumnName = solrColumnName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getSolrColumnName() {
        return solrColumnName;
    }

    public void setSolrColumnName(String solrColumnName) {
        this.solrColumnName = solrColumnName;
    }

    @Override
    public String toString() {
        return "SolrTableEntityColumn{" +
                "columnName='" + columnName + '\'' +
                ", solrColumnName='" + solrColumnName + '\'' +
                '}';
    }
}
