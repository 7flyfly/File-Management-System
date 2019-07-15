package com.file.management.service;

import com.file.management.pojo.User;

public interface UserService {
      //查找登录用户是否存在
      User findUser(String username,String password);

     public User findUsername(String username);

      //注册
      void addUser(User user);
}
