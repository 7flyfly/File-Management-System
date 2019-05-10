package com.file.management.service;

import com.file.management.dao.MenuRepository;
import com.file.management.pojo.Menu;
import com.file.management.pojo.metadata.Field;
import com.file.management.pojo.metadata.Tables;
import com.file.management.service.metadata.TablesService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

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


    public List<Menu> getMenuRoot(){
        return menuRepository.findMenuRoot();
    }

    /**
     * 添加前menu已经有id，name，url等信息
     * 在父节点菜单parent下添加一个名为menuName的子菜单，当父节点为null时，即要添加的节点m为根节点
     * @param parent 父节点
     * @param menuName 菜单名称
     * @return 返回添加好的menu
     */
    @Transactional
    public Menu addMenu  (Menu parent, String menuName,String menuDescription,String menuClassification) {
        Menu m = new Menu();
        m.setMenuName(menuName);
        m.setMenuDescription(menuDescription);
        m.setMenuClassification(menuClassification);
        m.setMenuUuid("M" + "_" + RandomStringUtils.random(8, "abcdefghijklmnopqrstuvwxyz1234567890"));
        // m.setMenuChildren(new ArrayList<Menu>());

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

    public void updateMenuTableId(int tableId,int menuId){
        menuRepository.updateMenuTableId(tableId,menuId);
    }

    public void updateMenuDescription(String menuDescription,String menuUuid){
        menuRepository.updateMenuDescription(menuDescription,menuUuid);
    }

    public void updateMenuClassification(String menuClassification,String menuUuid){
        menuRepository.updateMenuClassification(menuClassification,menuUuid);
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
     * @param menuId 菜单id
     * @return 如果节点不存在  ：无法删除，返回0；
     *         如果节点是根节点：无法删除，返回1；
     *         如果节点有子节点：无法删除，返回2；
     *         如果是叶子节点  ：直接删除，返回3。
     */
    public int deleteMenuByMenuId(int menuId){
        Menu menu = menuRepository.findByMenuId(menuId);
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
                    System.out.println("节点是叶子结点，可以删除。");

                    // 删除前所有在他后面的order需要自动-1
                    menuRepository.updateMenuOrderAfterDelete(menu.getMenuParent().getMenuId(),menu.getMenuOrder());
                    menuRepository.deleteMenuByMenuId(menuId);
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
     *
     * @param menuUuid 菜单Uuid
     * @return 如果节点不存在  ：无法删除，返回0；
     *         如果节点是根节点：无法删除，返回1；
     *         如果节点有子节点：无法删除，返回2；
     *         如果是叶子节点  ：直接删除，返回3。
     */
    public int deleteMenuByMenuUuid(String menuUuid){
        Menu menu = menuRepository.findByMenuUuid(menuUuid);
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
                    System.out.println("节点是叶子结点，可以删除。");

                    // 删除前所有在他后面的order需要自动-1
                    menuRepository.updateMenuOrderAfterDelete(menu.getMenuParent().getMenuId(),menu.getMenuOrder());
                    menuRepository.deleteMenuByMenuUuid(menuUuid);
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
     * @param menuUuid 菜单uuid
     * @param primaryKey 主键
     * @param fields 字段
     * @param tableName 表名
     */
    public void menuGenerateTablesByUser(String menuUuid,Field primaryKey, LinkedHashSet<Field> fields, String tableName){
        // 创建用户自定义表
        tablesService.generateTablesByUser(primaryKey,fields,tableName);

        // 获取这张表
        Tables tables = tablesService.getTablesByTableName(tableName);

        // 建立表与菜单的联系
        getMenuByMenuUUid(menuUuid).setMenuTable(tables);
    }

    /**
     *
     * @param menuUuid 菜单uuid
     * @param templateId 模板id
     * @param tableName 表名
     */
    public void menuGenerateTablesByTemplateId(String menuUuid,int templateId, String tableName){
        // 根据模板创建表
        tablesService.generateTablesByTemplateId(templateId,tableName);

        // 获取这张表
        Tables tables = tablesService.getTablesByTableName(tableName);

        // 建立表与菜单的联系
        getMenuByMenuUUid(menuUuid).setMenuTable(tables);
    }

    /**
     *  深度优先遍历treeview菜单树
     */
    public List<Menu> getAllMenuByOrder(){
        List<Menu> menuRootList = getMenuRoot();
        List<Menu> menuList = new ArrayList<>();
        for(Menu menuRoot:menuRootList) {
            menuList.addAll(getAllMenuByOrderRecusive(menuRoot));
        }
        return menuList;
    }

    private List<Menu> getAllMenuByOrderRecusive(Menu menuRoot){
        List<Menu> menuList = new ArrayList<>();
        if(menuRoot == null){
            return null;
        }
        Stack<Menu> myStack = new Stack<>();
        myStack.add(menuRoot);
        while(!myStack.isEmpty()){
            Menu menu = myStack.pop();
            menuList.add(menu);
            for(Menu child :menuRepository.findByMenuParentOrderByMenuOrder(menu.getMenuId())) {
                myStack.push(child);
            }
        }
        return menuList;
    }

    public void editMenuOrder(int newOrder,String menuUuid) {

        Menu menu = this.getMenuByMenuUUid(menuUuid);

        // 兄弟节点数量（包含自身）
        int menuBro = menu.getMenuParent()==null?getMenuRoot().size():menuRepository.findMenuIdGroup(menu.getMenuParent().getMenuId(),menu.getMenuId()).size()+1;

        // 如果新顺序号小于0或大于其父菜单的子菜单数
        if (menu.getMenuParent() != null) {
            if (newOrder < 0 || newOrder > menuBro) {
                System.out.println("输入数据有误");
            } else {
                // 第一步相当于删除该菜单，及所有大于其顺序号的菜单顺序号-1
                List<Menu> menuList = menuRepository.findByMenuParentGreaterThanOrder(menu.getMenuParent().getMenuId(), menu.getMenuOrder());
                for (Menu m : menuList) {
                    menuRepository.updateMenuOrder(m.getMenuOrder() - 1, m.getMenuUuid());
                }

                // 第二步插入该菜单，即让所有大于或等于该顺序号的菜单顺序号+1
                List<Menu> menuList2 = menuRepository.findByMenuParentGreaterThanOrder(menu.getMenuParent().getMenuId(), newOrder - 1);
                for (Menu m : menuList2) {
                    menuRepository.updateMenuOrder(m.getMenuOrder() + 1, m.getMenuUuid());
                }

                // 第三步将该菜单的顺序号设为newOrder
                menuRepository.updateMenuOrder(newOrder, menu.getMenuUuid());
            }
        }
    }
}
