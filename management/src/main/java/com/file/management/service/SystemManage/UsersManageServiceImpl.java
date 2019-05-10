package com.file.management.service.SystemManage;

import com.file.management.dao.SystemManage.UsersManageDao;
import com.file.management.pojo.UserManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersManageServiceImpl implements UsersManageService {
    @Autowired
    UsersManageDao usersManageDao;

    @Override
    public List<UserManage> findAll() {
        List<UserManage> userManageList= usersManageDao.findAll();
        return userManageList;
    }

    @Override
    public void saveUserInfo(UserManage userManage) {
        usersManageDao.save(userManage);
    }

    @Override
    public void insertInfo(String name, String phone, String department, String mail, String role, String status, String account) {
        usersManageDao.insertInfo(name,phone,department,mail,role,status,account);
    }
}
