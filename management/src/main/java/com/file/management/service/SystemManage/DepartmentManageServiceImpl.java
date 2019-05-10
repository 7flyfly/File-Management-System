package com.file.management.service.SystemManage;

import com.file.management.dao.SystemManage.DepartmentDao;
import com.file.management.pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentManageServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentDao departmentDao;
    @Override
    public List<Department> findAll() {
        List<Department> departmentLists=departmentDao.findAll();
        return departmentLists;
    }

    @Override
    public void save(Department department) {
        departmentDao.save(department);
    }

    @Override
    public List<Department> findByName(String name) {
        List<Department> departmentLists=departmentDao.findByName(name);
        return departmentLists;
    }

    @Override
    public void insertInfo(String code, String phone, String fax, String parent, String comment, String name, String charger) {
        departmentDao.insertInfo(code, phone, comment, fax, parent, name, charger);
    }

    @Override
    public void deleteInfo(String name, String charger) {
        departmentDao.deleteByNameAndCharger(name,charger);
    }
}
