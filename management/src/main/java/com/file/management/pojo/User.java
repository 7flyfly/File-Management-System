package com.file.management.pojo;


import lombok.Data;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "tb_users")
public class User  {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name ="USERNAME" ,unique = true)
    private String userName;

    @Column(name ="PASSWORD")
    private String passWord;


}
