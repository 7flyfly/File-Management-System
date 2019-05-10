package com.file.management.pojo.LibraryUse;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_registration")
public class RegistrationForm {
    //单号
    @Id
    @Column(name = "ODDNUMBERS")
    private String oddNumbers;

    //审批单号
    @Column(name = "APPROVENUMBER")
    private String approvalNumber;

    //审批意见
    @Column(name = "OPINION")
    private String opinion;

    //审批结果
    @Column(name = "RESULT")
    private String result;

    @Column(name = "REGISTRANT")
    private String registrant;

    //类型
    @Column(name = "TYPE")
    private String type;

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
    //出借日期
    @Column(name = "LOANDATE")
    private String loanDate;
    //出借天数
    @Column(name = "DAY")
    private String day;
    //归还经办人
    @Column(name = "TURN")
    private String turn;
    //归还日期
    @Column(name = "RETURNDATA")
    private String returnData;
    //操作
    @Column(name = "OPERATION")
    private String operation;

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

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTurn() {
        return turn;
    }

    public void setTurn(String aReturn) {
        turn = aReturn;
    }

    public String getReturnData() {
        return returnData;
    }

    public void setReturnData(String returnData) {
        this.returnData = returnData;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
