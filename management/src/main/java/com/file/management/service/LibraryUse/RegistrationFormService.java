package com.file.management.service.LibraryUse;

import com.file.management.pojo.RegistrationForm;

import java.util.List;

public interface RegistrationFormService {
    //查询登记表中所有登记信息
    List<RegistrationForm> findAll();

}
