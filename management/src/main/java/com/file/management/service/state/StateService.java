package com.file.management.service.state;


import com.file.management.pojo.state.Action;
import com.file.management.pojo.state.State;

import java.util.List;

public interface StateService {
      //读取全部状态
      List<State> FindState();



      //根据name修改bool
      void  updateBoolByName(String name, String bool);

      //根据name读取action
      List<State> findByName(String name);

      State findStateByName(String name);

      //保存action
      void saveState(State state);

      //插入编辑后数据
      void insertInfo(String name, String source, String explain, String min,String max,String num,String less,String fit,String more,String bool);

      //删除
      void deleteInfo(String name);
}
