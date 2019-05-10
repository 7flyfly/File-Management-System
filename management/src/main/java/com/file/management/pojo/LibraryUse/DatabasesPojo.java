package com.file.management.pojo.LibraryUse;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//从库中选择
@Entity
@Table(name = "tb_databases")
public class DatabasesPojo {

    @Id
    @Column(name = "DOCUMENTNO")
    String documentNo; //档号

    @Column(name = "FILESTORE")
    String filestore; //档案库

    @Column(name = "DOCUNMENTNUM")
    String docunmentNum;//文件数

    @Column(name = "CASENO")
    String caseNo; // 全宗号

    @Column(name = "RYEAR")
    String ryear; //归档年度

    @Column(name = "CLASSIFICATION")
    String classification; //分类号

    @Column(name = "TITLE")
    String title; //题名

    @Column(name = "SUBJECT")
    String subject; //主题词

    @Column(name = "UNIT")
    String unit; //归档单位

    @Column(name = "RDATE")
    String rdate; //归档日期

    @Column(name = "BEGINDATE")
    String begindate;//开始日期

    @Column(name = "ENDDATE")
    String enddate; //结束日期

    @Column(name = "SECURITY")
    String security; //保密

    @Column(name = "DEADLINE")
    String deadline; // 保管期限

    @Column(name = "DESCRIPTION")
    String description; //描述

    @Column(name = "REEL")
    String reel; //盘号

    @Column(name = "EADDRESS")
    String eaddress; //电子存址

    @Column(name = "BOXNO")
    String boxno; //盒号

    @Column(name = "PADDRESS")
    String paddress; // 纸质存址

    @Column(name = "STATUS")
    String status; //业务状态

    @Column(name = "CREATOR")
    String creator; //'创建者'

    @Column(name = "CREATETIME")
    String createtime; //'创建时间'

    public String getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    public String getFilestore() {
        return filestore;
    }

    public void setFilestore(String filestore) {
        this.filestore = filestore;
    }

    public String getDocunmentNum() {
        return docunmentNum;
    }

    public void setDocunmentNum(String docunmentNum) {
        this.docunmentNum = docunmentNum;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getRyear() {
        return ryear;
    }

    public void setRyear(String ryear) {
        this.ryear = ryear;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getRdate() {
        return rdate;
    }

    public void setRdate(String rdate) {
        this.rdate = rdate;
    }

    public String getBegindate() {
        return begindate;
    }

    public void setBegindate(String begindate) {
        this.begindate = begindate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReel() {
        return reel;
    }

    public void setReel(String reel) {
        this.reel = reel;
    }

    public String getEaddress() {
        return eaddress;
    }

    public void setEaddress(String eaddress) {
        this.eaddress = eaddress;
    }

    public String getBoxno() {
        return boxno;
    }

    public void setBoxno(String boxno) {
        this.boxno = boxno;
    }

    public String getPaddress() {
        return paddress;
    }

    public void setPaddress(String paddress) {
        this.paddress = paddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
