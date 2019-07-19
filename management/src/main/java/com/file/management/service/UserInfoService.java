package com.file.management.service;


import com.file.management.pojo.UserInfo;

import java.util.List;

public interface UserInfoService {
    /**通过username查找用户信息;*/
    public UserInfo findByUsername(String username);
    List<UserInfo> findAllInfo();
    void saveUserInfo(UserInfo userInfo);
    void insertInfo(String name, String phone, String department, String mail, String status, String account);
}