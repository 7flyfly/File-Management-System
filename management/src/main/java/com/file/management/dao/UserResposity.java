package com.file.management.dao;

import com.file.management.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserResposity extends JpaRepository<User,Long> {
       //查询用户
      User findByUserNameAndPassWord(String username,String password);

     public User findByUserName(String name);
}
