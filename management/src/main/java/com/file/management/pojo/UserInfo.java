package com.file.management.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "UserInfo")
public class UserInfo implements Serializable {
    @Id
    @GeneratedValue
    private Integer uid;

    @Column(name = "USERNAME")
    private String username;//帐号
    @Column(name = "NAME")
    private String name;//名称（昵称或者真实姓名，不同系统不同定义）
    @Column(name = "PASSWORD")
    private String password; //密码;
    @Column(name = "DEPARTMENT")
    private String department;
    @Column(name = "MAIL")
    private String mail;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "STATE")
    private String state;

    //private String salt;//加密密码的盐
    //private byte state;//用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定.
    @ManyToMany(fetch= FetchType.EAGER)//立即从数据库中进行加载数据;
    @JoinTable(name = "SysUserRole", joinColumns = { @JoinColumn(name = "uid") }, inverseJoinColumns ={@JoinColumn(name = "roleId") })
    private List<SysRole> roleList;// 一个用户具有多个角色

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
//    public String getSalt() {
//        return salt;
//    }
//
//    public void setSalt(String salt) {
//        this.salt = salt;
//    }
//
//    public byte getState() {
//        return state;
//    }
//
//    public void setState(byte state) {
//        this.state = state;
//    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }

    /**
     * 密码盐.
     * @return
     */
//    public String getCredentialsSalt(){
//        return this.username+this.salt;
//    }
    //重新对盐重新进行了定义，用户名+salt，这样就更加不容易被破解
}