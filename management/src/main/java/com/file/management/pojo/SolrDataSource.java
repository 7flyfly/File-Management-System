package com.file.management.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@XStreamAlias("dataSource")
public class SolrDataSource {
    @XStreamAsAttribute
    @XStreamAlias("name")
    private String dataSourceName;

    @XStreamAsAttribute
    @XStreamAlias("type")
    private String dataSourceType;

    @XStreamAsAttribute
    @XStreamAlias("driver")
    private String dataSourceDriver;

    @XStreamAsAttribute
    @XStreamAlias("url")
    private String dataSourceUrl;

    @XStreamAsAttribute
    @XStreamAlias("user")
    private String dataSourceUser;

    @XStreamAsAttribute
    @XStreamAlias("password")
    private String dataSourcePassword;

    public SolrDataSource() {
    }

    public SolrDataSource(String dataSourceName, String dataSourceType, String dataSourceDriver, String dataSourceUrl,
                          String dataSourceUser, String dataSourcePassword) {
        this.dataSourceName = dataSourceName;
        this.dataSourceType = dataSourceType;
        this.dataSourceDriver = dataSourceDriver;
        this.dataSourceUrl = dataSourceUrl;
        this.dataSourceUser = dataSourceUser;
        this.dataSourcePassword = dataSourcePassword;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public String getDataSourceType() {
        return dataSourceType;
    }

    public void setDataSourceType(String dataSourceType) {
        this.dataSourceType = dataSourceType;
    }

    public String getDataSourceDriver() {
        return dataSourceDriver;
    }

    public void setDataSourceDriver(String dataSourceDriver) {
        this.dataSourceDriver = dataSourceDriver;
    }

    public String getDataSourceUrl() {
        return dataSourceUrl;
    }

    public void setDataSourceUrl(String dataSourceUrl) {
        this.dataSourceUrl = dataSourceUrl;
    }

    public String getDataSourceUser() {
        return dataSourceUser;
    }

    public void setDataSourceUser(String dataSourceUser) {
        this.dataSourceUser = dataSourceUser;
    }

    public String getDataSourcePassword() {
        return dataSourcePassword;
    }

    public void setDataSourcePassword(String dataSourcePassword) {
        this.dataSourcePassword = dataSourcePassword;
    }

    @Override
    public String toString() {
        return "SolrDataSource{" +
                "dataSourceName='" + dataSourceName + '\'' +
                ", dataSourceType='" + dataSourceType + '\'' +
                ", dataSourceDriver='" + dataSourceDriver + '\'' +
                ", dataSourceUrl='" + dataSourceUrl + '\'' +
                ", dataSourceUser='" + dataSourceUser + '\'' +
                ", dataSourcePassword='" + dataSourcePassword + '\'' +
                '}';
    }
}
