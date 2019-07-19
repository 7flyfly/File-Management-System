package com.file.management.service;

import com.file.management.dao.SystemManage.Dictionary.DictionaryDao;
import com.file.management.pojo.SystemManagement.Dictionary.DictionaryPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DictionaryService {
    @Autowired
    DictionaryDao dictionaryDao;

    public List<String> getListByName(String name){
        List<DictionaryPojo> dictionaryPojos = dictionaryDao.findAllInfo(name);
        List<String> nameList = new ArrayList<>();
        for(DictionaryPojo d:dictionaryPojos){
            nameList.add(d.getName());
        }
        return nameList;
    }

    public List<DictionaryPojo> getDictionary(String name){
        List<DictionaryPojo> dictionaryPojos = dictionaryDao.findAllInfo(name);
        return dictionaryPojos;
    }
}
