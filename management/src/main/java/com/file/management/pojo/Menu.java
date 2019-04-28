package com.file.management.pojo;

import com.file.management.pojo.metadata.Tables;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_menu")
public class Menu implements Serializable{

    // 菜单id，为字段类的主键，策略是自增
    // 字段id自动生成，用户不需要自行输入
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="MENU_ID")
    private int menuId;

    // 菜单uuid，用户不可以修改
    // 菜单uuid自动生成，用户不需要自行输入
    @Column(name ="MENU_UUID",updatable = false)
    private String menuUuid;

    // 菜单层级，且该字段在保存时必需有值,默认根节点层级为0，依次递增
    @Column(name = "MENU_LEVEL",nullable = false)
    private int menuLevel;

    // 菜单名称，且该字段在保存时必需有值
    @Column(name = "MENU_NAME",nullable = false)
    private String menuName;

    /*// 菜单网址
    @Column(name = "MENU_URL",nullable = false)
    private String menuUrl;*/

    // 同一层级下菜单的顺序
    @Column(name = "MENU_ORDER")
    private int menuOrder;

    // 菜单的父节点菜单
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    private Menu menuParent;

    // 菜单的子节点菜单,默认懒加载
    @OneToMany(targetEntity = Menu.class, cascade = { CascadeType.ALL }, mappedBy = "menuParent")
    @Fetch(FetchMode.SUBSELECT)
    @OrderBy("menuOrder")
    private List<Menu> menuChildren = new ArrayList<>();

    // 菜单对应的表单
    @OneToOne(cascade=CascadeType.MERGE,fetch=FetchType.EAGER)
    @JoinColumn(name = "TABLE_ID")
    private Tables menuTable;

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getMenuUuid() {
        return menuUuid;
    }

    public void setMenuUuid(String menuUuid) {
        this.menuUuid = menuUuid;
    }

    public int getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(int menuLevel) {
        this.menuLevel = menuLevel;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

//    public String getMenuUrl() {
//        return menuUrl;
//    }
//
//    public void setMenuUrl(String menuUrl) {
//        this.menuUrl = menuUrl;
//    }

    public int getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(int menuOrder) {
        this.menuOrder = menuOrder;
    }

    public Menu getMenuParent() {
        return menuParent;
    }

    public void setMenuParent(Menu menuParent) {
        this.menuParent = menuParent;
    }

    public List<Menu> getMenuChildren() {
        return menuChildren;
    }

    public void setMenuChildren(List<Menu> menuChildren) {
        this.menuChildren = menuChildren;
    }

    public Tables getMenuTable() {
        return menuTable;
    }

    public void setMenuTable(Tables menuTable) {
        this.menuTable = menuTable;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuId=" + menuId +
                ", menuUuid='" + menuUuid + '\'' +
                ", menuLevel=" + menuLevel +
                ", menuName='" + menuName + '\'' +
//                ", menuUrl='" + menuUrl + '\'' +
                ", menuOrder=" + menuOrder +
                ", menuParent=" + menuParent +
                ", menuChildren=" + menuChildren +
                ", menuTable=" + menuTable +
                '}';
    }
}
