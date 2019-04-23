package com.file.management.pojo.metadata;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "tb_template")
public class Template implements Serializable {

    // 模板id，为模板类的主键，策略是自增
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="TEMPLATE_ID")
    private int templateId;

    // 模板名，且该字段在保存时必需有值
    @Column(name ="TEMPLATE_NAME",nullable = false)
    private String templateName;

    // 模板uuid，用户不可以修改
    @Column(name = "templateUuid",updatable=false)
    private String templateUuid;

    // 模板对应的字段
    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
        @JoinTable(
                name = "tb_template_field",
                joinColumns = {@JoinColumn(name="TEMPLATE_ID")},
                inverseJoinColumns = {@JoinColumn(name="FIELD_ID")}
    )
    private Set<Field> fields;

    // 模板的主键
    @OneToOne(cascade=CascadeType.MERGE,fetch=FetchType.EAGER)
    @JoinColumn(name = "FIELD_ID",nullable = false)
    private Field primaryKey;

    /*@OneToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "TEMPLATE_ID")*/

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateUuid() {
        return templateUuid;
    }

    public void setTemplateUuid(String templateUuid) {
        this.templateUuid = templateUuid;
    }

    public Set<Field> getFields() {
        return fields;
    }

    public void setFields(Set<Field> fields) {
        this.fields = fields;
    }

    public Field getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Field primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Override
    public String toString() {
        return "Template{" +
                "templateId=" + templateId +
                ", templateName='" + templateName + '\'' +
                ", templateUuid='" + templateUuid + '\'' +
                ", fields=" + fields +
                '}';
    }
}
