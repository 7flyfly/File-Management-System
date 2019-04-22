package com.file.management.pojo;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tb_users")
public class User {
    @Id
    @Column(name ="USERNAME")
    private String userName;
    @Column(name ="PASSWORD")
    private String passWord;

}
