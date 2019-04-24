package com.file.management.service.LibraryUse;

import com.file.management.pojo.RegistationExamine;
import com.file.management.pojo.RegistrationForm;

import java.util.List;

public interface ExamineService {
    //查询所有
    List<RegistationExamine> findAll();
}
