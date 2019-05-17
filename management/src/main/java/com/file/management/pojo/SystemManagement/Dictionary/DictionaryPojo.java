package com.file.management.pojo.SystemManagement.Dictionary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_dictionary")
public class DictionaryPojo {
    @Id
    @Column(name = "CODE")
    private String code;

    @Column(name = "DICTIONARY")
    private String dictionary; //词典

    @Column(name = "NAME")
    private String name;

    @Column(name = "PARENT")
    private String parent;

    @Column(name = "SEQUENCE")
    private String sequence;

    @Column(name = "COMMENT")
    private String comment;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDictionary() {
        return dictionary;
    }

    public void setDictionary(String dictionary) {
        this.dictionary = dictionary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
