package com.file.management.service.SystemManage;

import com.file.management.pojo.LibraryUse.RegistrationForm;

import java.util.List;

public interface JsonService {
    /*
    *根据用户权限获取文书列表
     */
    List<RegistrationForm> getWS();

}
