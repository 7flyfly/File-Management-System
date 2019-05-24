package com.file.management.pojo.SystemManagement.Dictionary;

import javax.persistence.*;

@Entity
@Table(name = "tb_dictionary")
public class DictionaryPojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "DICTIONARYNAME")
    private String dictionaryname; //词典

    @Column(name = "DICTIONARYCODE")
    private String dictionarycode;

    @Column(name = "NAME")
    private String name;//

    @Column(name = "PARENT")
    private String parent;

    @Column(name = "SEQUENCE")
    private String sequence;//

    @Column(name = "COMMENT")
    private String comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDictionaryname() {
        return dictionaryname;
    }

    public void setDictionaryname(String dictionaryname) {
        this.dictionaryname = dictionaryname;
    }

    public String getDictionarycode() {
        return dictionarycode;
    }

    public void setDictionarycode(String dictionarycode) {
        this.dictionarycode = dictionarycode;
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
