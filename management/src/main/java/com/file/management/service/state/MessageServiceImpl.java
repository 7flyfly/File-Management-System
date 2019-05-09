package com.file.management.service.state;


import com.file.management.dao.statemanage.MessageRepository;
import com.file.management.pojo.state.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Override
    public Message findById(int id) {
        Message m = messageRepository.findById(id);
        return m;
    }

    @Override
    public void save(Message m) {
        messageRepository.save(m);
    }

    @Override
    public void delete(Message m) {
        messageRepository.delete(m);
    }

    @Override
    public void delectById(int i) {
        messageRepository.deleteById(i);
    }


    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public Message findByName(String name){
        Message m = messageRepository.findByName(name);
        return  m ;
    }


}
