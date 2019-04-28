package com.file.management.dao;

import com.file.management.pojo.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Transactional
public interface MenuRepository extends JpaRepository<Menu,Integer>{

    // 根据菜单名称查询菜单
    Menu findByMenuName(String menuName);

    // 根据菜单id查询菜单
    Menu findByMenuId(int menuId);

    // 根据菜单uuid查询菜单
    Menu findByMenuUuid(String menuUuid);

    // 根据菜单名称删除菜单
    int deleteMenuByMenuName(String menuName);

    // 查询所有的模板
    ArrayList<Menu> findAll();

}
