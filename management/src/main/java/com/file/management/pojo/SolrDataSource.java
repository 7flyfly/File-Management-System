package com.file.management.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@XStreamAlias("dataSource")
/**
 * 对应Solr-data-config文件中的dataSource
 */
public class SolrDataSource {
    //用户名
    @XStreamAsAttribute
    @XStreamAlias("name")
    private String dataSourceName;

    //数据库类型
    @XStreamAsAttribute
    @XStreamAlias("type")
    private String dataSourceType;

    //数据库驱动类型
    @XStreamAsAttribute
    @XStreamAlias("driver")
    private String dataSourceDriver;

    //数据库链接地址
    @XStreamAsAttribute
    @XStreamAlias("url")
    private String dataSourceUrl;

    //用户名称
    @XStreamAsAttribute
    @XStreamAlias("user")
    private String dataSourceUser;

    //用户密码
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
