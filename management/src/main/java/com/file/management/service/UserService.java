package com.file.management.service;

import com.file.management.pojo.User;

public interface UserService {
      //查找登录用户是否存在
      User findUser(String username,String password);

      //注册
      void addUser(User user);
}
