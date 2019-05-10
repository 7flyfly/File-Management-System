package com.file.management.dao.SystemManage;

import com.file.management.pojo.UserManage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface UsersManageDao extends JpaRepository<UserManage,String> {

    List<UserManage> findAll();

    @Modifying
    @Query(value = "update tb_usersmanage t set t.NAME=?1,t.PHONE=?2,t.DEPARTMENT=?3, t.MAIL=?4, t.ROLE=?5, t.STATUS=?6 where t.ACCOUNT=?7",nativeQuery = true)
    void insertInfo(String name, String phone, String department, String mail, String role, String status, String account);
}
