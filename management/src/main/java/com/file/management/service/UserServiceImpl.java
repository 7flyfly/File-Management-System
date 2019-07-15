package com.file.management.service;

import com.file.management.dao.UserResposity;
import com.file.management.dao.AddUserRepository;
import com.file.management.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserResposity userResposity;
    @Autowired
    private AddUserRepository addUserRepository;

    @Override
    public User findUser(String username, String password) {
        System.out.println("s:"+username);
        User user=userResposity.findByUsernameAndPassword(username,password);
        return user;
    }

    @Override
    public User findUsername(String username) {
        System.out.println("UserServiceImpl.findByUsername()");
        return userResposity.findByUsername(username);
    }

    //添加用户
    @Override
    public void addUser(User user) {
        addUserRepository.save(user);
    }
}
