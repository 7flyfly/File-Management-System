package com.file.management.service;


import com.file.management.dao.UserInfoDao;
import com.file.management.pojo.UserInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoDao userInfoDao;
    @Override
    public UserInfo findByUsername(String username) {
        System.out.println("UserInfoServiceImpl.findByUsername()");
        return userInfoDao.findByUsername(username);
    }

    @Override
    public List<UserInfo> findAllInfo() {
        return userInfoDao.findAll();
    }

    @Override
    public void saveUserInfo(UserInfo userInfo) {
        userInfoDao.save(userInfo);
    }

    @Override
    public void insertInfo(String name, String phone, String department, String mail, String status, String account) {
        userInfoDao.insertInfo(name,phone,department,mail,status,account);
    }
}