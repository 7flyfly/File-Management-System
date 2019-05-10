package com.file.management.service.SystemManage;

import com.file.management.pojo.UserManage;

import java.util.List;

public interface UsersManageService {

    List<UserManage> findAll();

    void saveUserInfo(UserManage userManage);
    //插入编辑后数据
    void insertInfo(String name, String phone, String department, String mail, String role, String status, String account);
}
