package com.file.management.dao;

import com.file.management.pojo.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
public interface MenuRepository extends JpaRepository<Menu,Integer>{

    // 根据菜单名称查询菜单
    Menu findByMenuName(String menuName);

    // 根据菜单id查询菜单
    Menu findByMenuId(int menuId);

    // 根据菜单uuid查询菜单
    Menu findByMenuUuid(String menuUuid);

    // 根据菜单名称删除菜单
    void deleteMenuByMenuName(String menuName);

    // 根据菜单id删除菜单
    void deleteMenuByMenuId(int menuId);

    // 根据菜单uuid删除菜单
    void deleteMenuByMenuUuid(String menuUuid);

    // 更新所有与此节点父节点一致 且 order>此节点的节点list
   // @Query(value = "SELECT * FROM TB_MENU WHERE PARENT_ID = ?1 AND MENU_ORDER > ?2 ", nativeQuery = true)
    @Modifying
    @Query(value = "UPDATE TB_MENU SET MENU_ORDER=MENU_ORDER-1 WHERE PARENT_ID = ?1 AND MENU_ORDER > ?2", nativeQuery = true)
    void updateMenuOrderAfterDelete(int parentId,int menuOrder);

    // 查询所有的模板
    ArrayList<Menu> findAll();

    // 更新设置menu的tableId
    @Modifying
    @Query(value = "UPDATE TB_MENU SET TABLE_ID = ?1 WHERE MENU_ID = ?2",nativeQuery = true)
    void updateMenuTableId(int tableId,int menuId);

    // 更新设置menu的menuOrder
    @Modifying
    @Query(value = "UPDATE TB_MENU SET MENU_ORDER = ?1 WHERE MENU_UUID = ?2",nativeQuery = true)
    void updateMenuOrder(int menuOrder,String menuUuid);

    // 更新设置menu的menuDescription
    @Modifying
    @Query(value = "UPDATE TB_MENU SET MENU_DESCRIPTION = ?1 WHERE MENU_UUID = ?2",nativeQuery = true)
    void updateMenuDescription(String menuDescription,String menuUuid);

    // 更新设置menu的menuClassification
    @Modifying
    @Query(value = "UPDATE TB_MENU SET MENU_CLASSIFICATION = ?1 WHERE MENU_UUID = ?2",nativeQuery = true)
    void updateMenuClassification(String menuClassification,String menuUuid);

    // 获取与menuId一致父节点的其他menu
    @Query(value = "SELECT * FROM TB_MENU WHERE PARENT_ID = ?1 AND MENU_ID <> ?2", nativeQuery = true)
    List<Menu> findMenuIdGroup(int parentId,int menuId);

    // 获取与menuId一致父节点且顺序号大于此节点的所有菜单
    @Query(value = "SELECT * FROM TB_MENU WHERE PARENT_ID = ?1 AND MENU_ORDER > ?2", nativeQuery = true)
    List<Menu> findByMenuParentGreaterThanOrder(int parentId,int menuOrder);

    // 获取menuId下的所有子节点并按照order倒序排序
    @Query(value = "SELECT * FROM TB_MENU WHERE PARENT_ID = ?1 ORDER BY MENU_ID DESC", nativeQuery = true)
    List<Menu> findByMenuParentOrderByMenuOrder(int menuId);

    // 按照顺序号获取根节点的menu
    @Query(value = "SELECT * FROM TB_MENU WHERE PARENT_ID IS NULL ORDER BY MENU_ORDER", nativeQuery = true)
    List<Menu> findMenuRoot();

    // 根据tableid查询menu
    @Query(value = "SELECT * FROM TB_MENU WHERE TABLE_ID = ?1",nativeQuery = true)
    Menu findMenuByTableId(int tableId);

    // 根据菜单名称和分类查询菜单
    @Query(value = "SELECT * FROM TB_MENU WHERE MENU_NAME = ?1 AND MENU_CLASSIFICATION = ?2",nativeQuery = true)
    List<Menu> findMenuByMenuNameAndMenuClassification(String menuName,String menuClassification);

}
