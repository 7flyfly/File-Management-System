package com.file.management.service;

import com.file.management.dao.MenuRepository;
import com.file.management.pojo.Menu;
import com.file.management.pojo.metadata.Field;
import com.file.management.pojo.metadata.Tables;
import com.file.management.service.metadata.TablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private TablesService tablesService;

    //获取当前的日期
    private Date date = new Date();
    //设置要获取到什么样的时间
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
    //获取String类型的时间
    private String createdate = sdf.format(date);

    /**
     * 添加前menu已经有id，name，url等信息
     * 在父节点菜单parent下添加一个名为menuName的子菜单，当父节点为null时，即要添加的节点m为根节点
     * @param parent 父节点
     * @param menuName 菜单名称
     * @return 返回添加好的menu
     */
    @Transactional
    public Menu addMenu  (Menu parent, String menuName) {
        Menu m = new Menu();
        m.setMenuName(menuName);
        m.setMenuUuid("Menu" + "_" + m.getMenuName() + "_" + createdate);
        m.setMenuChildren(new ArrayList<Menu>());

        // 设置节点m的父节点为parent
        m.setMenuParent(parent);

        if(parent != null) {
            m.setMenuOrder(parent.getMenuChildren().size() + 1);
            m.setMenuLevel(parent.getMenuLevel() + 1);

            // 父节点parent下添加此子节点m
            List<Menu> MenuChildrenOfParent = parent.getMenuChildren();
            MenuChildrenOfParent.add(m);
            parent.setMenuChildren(MenuChildrenOfParent);
        }else{
            int menuLevelZeroNum = 0;
            for(Menu menu:menuRepository.findAll()){
                if(menu.getMenuLevel() == 0){
                    menuLevelZeroNum++;
                }
            }
            m.setMenuOrder(menuLevelZeroNum+1);
            m.setMenuLevel(0);
        }
        menuRepository.saveAndFlush(m);
        return m;
    }

    public Menu getMenuByMenuUUid(String menuUuid){
        return menuRepository.findByMenuUuid(menuUuid);
    }

    public Menu getMenuByMenuId(int menuId){
        return menuRepository.findByMenuId(menuId);
    }

    public Menu getMenuByMenuName(String menuName) { return menuRepository.findByMenuName(menuName); }

    /**
     *
     * @param menuName 菜单名称
     * @return 如果节点不存在  ：无法删除，返回0；
     *         如果节点是根节点：无法删除，返回1；
     *         如果节点有子节点：无法删除，返回2；
     *         如果是叶子节点  ：直接删除，返回3。
     */
    public int deleteMenuByMenuName(String menuName){
        Menu menu = menuRepository.findByMenuName(menuName);
        if(menu == null){
            // 菜单不存在
            System.out.println("节点不存在。");
            return 0;
        }else{
            // 节点是根节点
            if(menu.getMenuParent() == null){
                System.out.println("节点是根节点，无法删除。");
                return 1;
            }else{
                // 节点的子节点为空即为叶子节点
                if(menu.getMenuChildren().isEmpty()){
                    System.out.println("节点是叶子结点，直接删除。");
                    menuRepository.deleteMenuByMenuName(menuName);
                    return 3;
                }else{
                    // 该节点的子节点非空
                    System.out.println("节点的子节点非空，无法删除。");
                    return 2;
                }
            }
        }
    }

    /**
     * 为菜单uuid为menuUuid的菜单配置一张用户自定义的表
     * @param menuUuid
     * @param primaryKey
     * @param fields
     * @param tableName
     */
    public void menuGenerateTablesByUser(String menuUuid,Field primaryKey, Set<Field> fields, String tableName){
        // 创建用户自定义表
        tablesService.generateTablesByUser(primaryKey,fields,tableName);

        // 获取这张表
        Tables tables = tablesService.getTablesByTableName(tableName);

        // 建立表与菜单的联系
        getMenuByMenuUUid(menuUuid).setMenuTable(tables);
    }

    /**
     *
     * @param menuUuid
     * @param templateId
     * @param tableName
     */
    public void menuGenerateTablesByTemplateId(String menuUuid,int templateId, String tableName){
        // 根据模板创建表
        tablesService.generateTablesByTemplateId(templateId,tableName);

        // 获取这张表
        Tables tables = tablesService.getTablesByTableName(tableName);

        // 建立表与菜单的联系
        getMenuByMenuUUid(menuUuid).setMenuTable(tables);
    }
}
