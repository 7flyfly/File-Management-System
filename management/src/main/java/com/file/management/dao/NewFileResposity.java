package com.file.management.dao;


import com.file.management.pojo.NewFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewFileResposity extends JpaRepository<NewFile,Integer> {
       //查询所有档案
    List<NewFile>  findAllByFinish(String finish);
}
