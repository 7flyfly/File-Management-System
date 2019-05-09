package com.file.management.service.state;

import com.file.management.dao.statemanage.ActionRepository;
import com.file.management.pojo.state.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActionServiceImpl implements ActionService {
    public ActionServiceImpl() {
    }

    @Autowired
    private ActionRepository actionRepository;

    @Override
    public List<Action> findAction() {
        return actionRepository.findAll();
    }

    @Override
    public void insertInfo(String name, String type, String explain, String message) {
        actionRepository.insertInfo(name,type,explain,message);
    }

    @Override
    public void deleteInfo(String name ) {
        actionRepository.deleteByName(name);
    }

    @Override
    public Action findActionByName(String name) {
        Action list = actionRepository.findActionByName(name);
        return list;
    }

    @Override
    public void changeName(String oldname, String newname) {
        actionRepository.changeName(oldname,newname);
    }

    @Override
    public List<Action> findByName(String name) {
        List<Action> list = actionRepository.findByName(name);
        return list;
    }

    @Override
    public void saveAction(Action action) {
        actionRepository.save(action);
    }
}
