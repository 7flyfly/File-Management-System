package com.file.management.dao.statemanage;


import com.file.management.pojo.state.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ActionRepository extends JpaRepository<Action,String> {
     //查询全部动作
     List<Action> findAll();

    //根据name查询state
    List<Action> findByName(String name);

     //根据name查询state
    Action findActionByName(String name);

    //插入编辑后数据
    @Modifying
    @Query(value = "update tb_action t set t.TYPE=?2,t.EXP=?3, t.MESSAGE=?4 where t.NAME=?1 ",nativeQuery = true)
    void insertInfo(String name, String type, String explain, String message);

    //改变名称
    @Modifying
    @Query(value = "update tb_action t set t.NAME=?2 where t.NAME=?1 ",nativeQuery = true)
    void changeName(String oldname, String newname);

    //删除
    void deleteByName(String name);
}
