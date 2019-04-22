package com.file.management.dao;

import com.file.management.pojo.RegistrationForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegistrationFormRespository extends JpaRepository<RegistrationForm, Integer> {
    //查找所有
      List<RegistrationForm> findAll();



}
