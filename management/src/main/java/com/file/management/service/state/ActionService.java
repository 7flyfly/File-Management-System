package com.file.management.service.state;


import com.file.management.pojo.state.Action;

import java.util.List;

public interface ActionService {
      //读取全部action
      List<Action> findAction();

      //根据name读取action
      Action findActionByName(String name);

      List<Action> findByName(String name);

      void changeName(String oldname,String newname);
      //保存action
      void saveAction(Action action);

      //插入编辑后数据
      void insertInfo(String name, String type, String explain, String message);

      //删除
      void deleteInfo(String name);

}
