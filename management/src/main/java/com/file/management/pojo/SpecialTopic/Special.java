package com.file.management.pojo.SpecialTopic;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_special")
public class Special {

    @Id
    @Column(name = "NAME")
    private String name;

    @Column(name = "CONTANT")
    private String contant;

    @Column(name = "CREATER")
    private String creater;

    @Column(name = "CREATETIME")
    private String createtime;

    @Column(name = "PUBLISHER")
    private String publisher;

    @Column(name = "PUBLISHTIME")
    private String publishtime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContant() {
        return contant;
    }

    public void setContant(String contant) {
        this.contant = contant;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
    }
}
