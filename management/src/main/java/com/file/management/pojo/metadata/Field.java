package com.file.management.pojo.metadata;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_field")
public class Field implements Serializable {

    // 字段id，为字段类的主键，策略是自增
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="FIELD_ID")
    private int fieldId;

    // 字段uuid，用户不可以修改
    @Column(name ="FIELD_UUID",updatable = false)
    private String fieldUuid;

    // 字段名，且该字段在保存时必需有值
    @Column(name ="FIELD_NAME",nullable = false)
    private String fieldName;

    // 字段英文名，且该字段在保存时必需有值
    @Column(name ="FIELD_ENGLISH_NAME",nullable = false)
    private String fieldEnglishName;

    // 字段类型，且该字段在保存时必需有值
    @Column(name ="FIELD_TYPE",nullable = false)
    private String fieldType;

    // 字段长度，且该字段在保存时必需有值
    @Column(name ="FIELD_LENGTH",nullable = false)
    private int fieldLength;

    // 字段是否为主键，且该字段在保存时必需有值
    @Column(name ="FIELD_PRIMARY_KEY",nullable = false)
    private boolean fieldPrimaryKey;

    // 字段是否为索引，即该字段是否可以被检索，且该字段在保存时必需有值
    @Column(name ="FIELD_INDEX",nullable = false)
    private Boolean fieldIndex;

    // 字段是否可被分词
    @Column(name ="FIELD_IK",columnDefinition="bit default 0")
    private Boolean fieldIk;

    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public String getFieldUuid() {
        return fieldUuid;
    }

    public void setFieldUuid(String fieldUuid) {
        this.fieldUuid = fieldUuid;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldEnglishName() {
        return fieldEnglishName;
    }

    public void setFieldEnglishName(String fieldEnglishName) {
        this.fieldEnglishName = fieldEnglishName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public int getFieldLength() {
        return fieldLength;
    }

    public void setFieldLength(int fieldLength) {
        this.fieldLength = fieldLength;
    }

    public boolean isFieldPrimaryKey() {
        return fieldPrimaryKey;
    }

    public void setFieldPrimaryKey(boolean fieldPrimaryKey) {
        this.fieldPrimaryKey = fieldPrimaryKey;
    }

    public Boolean getFieldIndex() {
        return fieldIndex;
    }

    public void setFieldIndex(Boolean fieldIndex) {
        this.fieldIndex = fieldIndex;
    }

    public Boolean getFieldIk() {
        return fieldIk;
    }

    public void setFieldIk(Boolean fieldIk) {
        this.fieldIk = fieldIk;
    }

    @Override
    public String toString() {
        return "Field{" +
                "fieldId=" + fieldId +
                ", fieldUuid='" + fieldUuid + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", fieldEnglishName='" + fieldEnglishName + '\'' +
                ", fieldType='" + fieldType + '\'' +
                ", fieldLength=" + fieldLength +
                ", fieldPrimaryKey=" + fieldPrimaryKey +
                ", fieldIndex=" + fieldIndex +
                '}';
    }
}
