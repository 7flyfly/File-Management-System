package com.file.management.service.state;


import com.file.management.pojo.state.Message;

import java.util.List;

public interface MessageService {
      List<Message> findAll();
      Message findByName(String name);
      Message findById(int id);
      void save(Message m);
      void delete(Message m);
      void delectById(int i);
}
