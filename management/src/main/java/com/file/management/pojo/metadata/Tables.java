package com.file.management.pojo.metadata;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "tb_table")
public class Tables implements Serializable{

    // 表id，为表类的主键，策略是自增
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="TABLE_ID")
    private int tableId;

    // 表名，且该字段在保存时必需有值
    @Column(name ="TABLE_Name",nullable = false)
    private String tableName;

    // 表uuid，用户不可以修改
    @Column(name ="TABLE_UUID",updatable = false)
    private String tableUuid;

    // 表对应的模板，如果是通过模板生成则该属性存储模板，否则为null
    @ManyToOne(cascade=CascadeType.MERGE,fetch=FetchType.EAGER)
    @JoinColumn(name = "TEMPLATE_ID")
    private Template template;

    // 表的主键
    @OneToOne(cascade=CascadeType.MERGE,fetch=FetchType.EAGER)
    @JoinColumn(name = "FIELD_ID",nullable = false)
    private Field primaryKey;


    // 表对应的字段
    @ManyToMany(cascade = CascadeType.MERGE,fetch=FetchType.LAZY)
    @JoinTable(
            name = "tb_table_field",
            joinColumns = {@JoinColumn(name="TABLE_ID")},
            inverseJoinColumns = {@JoinColumn(name="FIELD_ID")}
    )
    private List<Field> fields = new ArrayList<>();

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableUuid() {
        return tableUuid;
    }

    public void setTableUuid(String tableUuid) {
        this.tableUuid = tableUuid;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public List<Field> getFields() {
        if(this.getTemplate() == null) {
            return fields;
        }else{
            return this.getTemplate().getFields();
        }
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public Field getPrimaryKey() {
        if(this.getTemplate() == null) {
            return primaryKey;
        }else{
            return this.getTemplate().getPrimaryKey();
        }
    }

    public void setPrimaryKey(Field primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Override
    public String toString() {
        /*return "Tables{" +
                "tableId=" + tableId +
                ", tableName='" + tableName + '\'' +
                ", tableUuid='" + tableUuid + '\'' +
                ", template=" + template +
                ", fields=" + fields +
                '}';*/
        return tableName;
    }
}
