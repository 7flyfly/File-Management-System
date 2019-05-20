package com.file.management.dao.statemanage;


import com.file.management.pojo.state.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface StateRepository extends JpaRepository<State,String> {
     //查询全部状态
     List<State> findAll();

     //修改数据库bool
     @Modifying
     @Query(value = "update tb_state set bool = :bool where name = :name",nativeQuery = true)
     void updateNameById(@Param("name") String name, @Param("bool") String bool);


    //根据name查询state集合
    List<State> findByName(String name);
    //根据name查询state
    State findStateByName(String name);


    //插入编辑后数据
    @Modifying
    @Query(value = "update tb_state t set t.SOURCE=?2,t.EXP=?3, t.MIN=?4 , t.MAX=?5 , t.NUM=?6 , t.LESS=?7 , t.FIT=?8 , t.MORE=?9 , t.BOOL=?10,t.PLUG=?11 where t.NAME=?1 ",nativeQuery = true)
    void insertInfo(String name, String source, String explain, String min, String max, String num, String less, String fit, String more, String bool, String plug);

    //删除
    void deleteByName(String name);
}
