package com.file.management.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@XStreamAlias("entity")
public class SolrTableEntity {
    @XStreamAsAttribute
    @XStreamAlias("name")
    private String tableName;

    @XStreamAsAttribute
    @XStreamAlias("PK")
    private String primaryKey;

    @XStreamAsAttribute
    @XStreamAlias("dataSource")
    private String dataSourceName;

    @XStreamAsAttribute
    @XStreamAlias("query")
    private String fullImportQuery;

    @XStreamAsAttribute
    @XStreamAlias("deltaImportQuery")
    private String deltaImportQuery;

    @XStreamAsAttribute
    @XStreamAlias("deltaQuery")
    private String deltaQuery;

    @XStreamAsAttribute
    @XStreamAlias("deletedPkQuery")
    private String deletedPkQuery;

    @XStreamImplicit(itemFieldName="field")
    private List<SolrTableEntityColumn> solrTableEntityColumnList;

    public SolrTableEntity() {
    }

    public SolrTableEntity(String tableName, String primaryKey, String dataSourceName, String fullImportQuery,
                           String deltaImportQuery, String deltaQuery, String deletedPkQuery,
                           List<SolrTableEntityColumn> solrTableEntityColumnList) {
        this.tableName = tableName;
        this.primaryKey = primaryKey;
        this.dataSourceName = dataSourceName;
        this.fullImportQuery = fullImportQuery;
        this.deltaImportQuery = deltaImportQuery;
        this.deltaQuery = deltaQuery;
        this.deletedPkQuery = deletedPkQuery;
        this.solrTableEntityColumnList = solrTableEntityColumnList;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public String getFullImportQuery() {
        return fullImportQuery;
    }

    public void setFullImportQuery(String fullImportQuery) {
        this.fullImportQuery = fullImportQuery;
    }

    public String getDeltaImportQuery() {
        return deltaImportQuery;
    }

    public void setDeltaImportQuery(String deltaImportQuery) {
        this.deltaImportQuery = deltaImportQuery;
    }

    public String getDeltaQuery() {
        return deltaQuery;
    }

    public void setDeltaQuery(String deltaQuery) {
        this.deltaQuery = deltaQuery;
    }

    public String getDeletedPkQuery() {
        return deletedPkQuery;
    }

    public void setDeletedPkQuery(String deletedPkQuery) {
        this.deletedPkQuery = deletedPkQuery;
    }

    public List<SolrTableEntityColumn> getSolrTableEntityColumnList() {
        return solrTableEntityColumnList;
    }

    public void setSolrTableEntityColumnList(List<SolrTableEntityColumn> solrTableEntityColumnList) {
        this.solrTableEntityColumnList = solrTableEntityColumnList;
    }

    @Override
    public String toString() {
        return "SolrTableEntity{" +
                "tableName='" + tableName + '\'' +
                ", primaryKey='" + primaryKey + '\'' +
                ", dataSourceName='" + dataSourceName + '\'' +
                ", fullImportQuery='" + fullImportQuery + '\'' +
                ", deltaImportQuery='" + deltaImportQuery + '\'' +
                ", deltaQuery='" + deltaQuery + '\'' +
                ", deletedPkQuery='" + deletedPkQuery + '\'' +
                ", solrTableEntityColumnList=" + solrTableEntityColumnList +
                '}';
    }
}
