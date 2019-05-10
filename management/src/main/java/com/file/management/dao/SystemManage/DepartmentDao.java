package com.file.management.dao.SystemManage;


import com.file.management.pojo.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface DepartmentDao extends JpaRepository<Department, Integer> {

    List<Department> findAll();
    //通过名称查询
    List<Department> findByName(String name);

    //查询单号
    Department findByCode(String code);


    @Modifying
    @Query(value = "update tb_department t set t.CODE=?1,t.PHONE=?2,t.COMMENT=?3, t.FAX=?4, t.PARENT=?5 where t.NAME=?6 and t.CHARGER=?7",nativeQuery = true)
    void insertInfo(String code, String phone, String comment, String fax, String parent, String name, String charger);

    void deleteByNameAndCharger(String name, String charger);
}
