package com.file.management.dao;

import com.file.management.pojo.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysRoleDao extends JpaRepository<SysRole,Integer> {

    SysRole findById(int id);
    SysRole findByRole(String role);
}
