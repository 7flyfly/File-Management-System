package com.file.management.dao;

import com.file.management.pojo.SysPermission;
import com.file.management.pojo.SysRole;
import org.apache.poi.hwpf.model.ListData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
public interface PermissionDao extends JpaRepository<SysPermission,Integer> {

    SysPermission findByName(String name);

//    @Modifying
//    @Query(value = "insert ",nativeQuery = true)
//    void Insert(int id1 , int id2);
}
