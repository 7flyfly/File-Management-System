package com.file.management.service.state;

import com.file.management.dao.statemanage.StateRepository;
import com.file.management.pojo.state.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateServiceImpl implements StateService {

    @Autowired
    private StateRepository stateRepository;
    //查找全部状态
    @Override
    public List<State> FindState() {
            return stateRepository.findAll();
    }

    @Override
    public State findStateByName(String name) {
        return stateRepository.findStateByName(name);
    }

    @Override
    public List<State> findByName(String name) {
        return stateRepository.findByName(name);
    }

    @Override
    public void saveState(State state) {
        stateRepository.save(state);
    }

    @Override
    public void insertInfo(String name, String source, String explain, String min, String max, String num, String less, String fit, String more, String bool,String plug) {
        stateRepository.insertInfo(name,source,explain,min,max,num,less,fit,more,bool,plug);
    }

    @Override
    public void deleteInfo(String name) {
        stateRepository.deleteByName(name);
    }


    @Override
    public void updateBoolByName(String name, String bool) {
        stateRepository.updateNameById(name,bool);
    }

}
