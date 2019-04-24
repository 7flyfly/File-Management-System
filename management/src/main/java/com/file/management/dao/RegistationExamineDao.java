package com.file.management.dao;

import com.file.management.pojo.RegistationExamine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistationExamineDao extends JpaRepository<RegistationExamine,Integer> {
    //查询所有
    List<RegistationExamine> findAll();
}
