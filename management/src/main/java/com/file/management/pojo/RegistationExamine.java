package com.file.management.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/*
* 利用审批
 */
@Entity
@Table(name = "tb_RegistExamine")
public class RegistationExamine {
    //序号
    @Id
    @Column(name = "ID")
    private int id;
    //审批单号
    @Column(name = "APPROVENUMBER")
    private String approvalNumber;
    //利用单号
    @Column(name = "ODDNUMBERS")
    private String oddNumbers;
    //类型
    @Column(name = "TYPE")
    private String type;
    //登记人
    @Column(name = "REGISTRANT")
    private String registrant;
    //登记日期
    @Column(name = "RECORDDATE")
    private String recordDate;
    //单位
    @Column(name = "UNIT")
    private String unit;
    //姓名
    @Column(name = "NAME")
    private String name;
    //日期
    @Column(name = "DATE")
    private String date;
    //联系电话
    @Column(name = "TELPHONE")
    private String telphone;
    //证件类型
    @Column(name = "CERTIFICATETYPE")
    private String certificateType;
    //证件号码
    @Column(name = "CERTIFICATENUMBER")
    private String certificateNumber;
    //目的
    @Column(name = "PURPOSE")
    private String purpose;
    //方式
    @Column(name = "WAY")
    private String way;
    //内容
    @Column(name = "CONTANT")
    private String contant;
    //状态
    @Column(name = "STATE")
    private String state;
    //出借经办人
    @Column(name = "STLOANAGENTATE")
    private String stloanAgentate;
    //出借提交日期
    @Column(name = "LOANUBMISSIONDATE")
    private String loanUbmissionDate;
    //审批环节
    @Column(name = "EXAMINE")
    private String examine;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public String getOddNumbers() {
        return oddNumbers;
    }

    public void setOddNumbers(String oddNumbers) {
        this.oddNumbers = oddNumbers;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegistrant() {
        return registrant;
    }

    public void setRegistrant(String registrant) {
        this.registrant = registrant;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getContant() {
        return contant;
    }

    public void setContant(String contant) {
        this.contant = contant;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStloanAgentate() {
        return stloanAgentate;
    }

    public void setStloanAgentate(String stloanAgentate) {
        this.stloanAgentate = stloanAgentate;
    }

    public String getLoanUbmissionDate() {
        return loanUbmissionDate;
    }

    public void setLoanUbmissionDate(String loanUbmissionDate) {
        this.loanUbmissionDate = loanUbmissionDate;
    }

    public String getExamine() {
        return examine;
    }

    public void setExamine(String examine) {
        this.examine = examine;
    }
}
