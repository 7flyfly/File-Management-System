package com.file.management.dao.statemanage;

import com.file.management.pojo.state.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer>{
    //查询全部
    List<Message> findAll();
     //根据name查询Message
    Message findByName(String name);
    //ID
    Message findById(int id);

}
