package com.file.management.service;

import com.file.management.dao.UserResposity;
import com.file.management.dao.addUserRepository;
import com.file.management.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserResposity userResposity;
    @Autowired
    private addUserRepository addUserRepository;

    @Override
    public User findUser(String username, String password) {
        System.out.println("s:"+username);
        User user=userResposity.findByUserNameAndPassWord(username,password);
        return user;
    }

    //添加用户
    @Override
    public void addUser(User user) {
        addUserRepository.save(user);
    }
}
