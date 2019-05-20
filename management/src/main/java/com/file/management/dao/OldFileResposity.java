package com.file.management.dao;

import com.file.management.pojo.ExpiredFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OldFileResposity extends JpaRepository<ExpiredFile,Integer> {
       //查询所有档案
    List<ExpiredFile>  findAll();
}
