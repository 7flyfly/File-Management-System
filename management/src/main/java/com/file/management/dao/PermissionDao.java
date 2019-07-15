package com.file.management.dao;

import com.file.management.pojo.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionDao extends JpaRepository<SysPermission,Integer> {

    SysPermission findByName(String name);
}
