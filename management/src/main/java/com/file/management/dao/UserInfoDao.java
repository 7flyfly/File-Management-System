package com.file.management.dao;


import com.file.management.pojo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
public interface UserInfoDao extends JpaRepository<UserInfo,Long> {
    /**通过username查找用户信息;*/
    public UserInfo findByUsername(String username);
    List<UserInfo>findAll();
    @Modifying
    @Query(value = "update user_info t set t.NAME=?1,t.PHONE=?2,t.DEPARTMENT=?3, t.MAIL=?4, t.STATUS=?5 where t.USERNAME=?6",nativeQuery = true)
    void insertInfo(String name, String phone, String department, String mail, String status, String account);


}