package com.file.management.service.SystemManage;

import com.file.management.pojo.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> findAll();

    void save(Department department);

    List<Department> findByName(String name);

    //插入编辑后数据
    void insertInfo(String code, String phone, String fax, String parent, String comment, String name, String charger);

    //删除
    void deleteInfo(String name, String charger);
}
