package com.file.management.dao;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class DynamicSQL {
    @PersistenceContext
    EntityManager entityManager;

    /**
     * 根据表名，属性，属性值拼接原生sql进行查询
     * @param tableName 表名
     * @param attr 属性名
     * @param attrVal 属性值
     * @return 抽象对象
     */
    public Object selectSingleResultByTableNameAndAttr(String tableName, String attr, String attrVal){
        String sql = "select * from " + tableName + " where " + attr + "= \"" + attrVal + "\"";
        Object object = entityManager.createNativeQuery(sql).getSingleResult();
        return  object;
    }

    /**
     * 根据表名，属性，属性值拼接原生sql进行查询
     * @param tableName
     * @param attr
     * @param attrVal
     * @return 返回抽象list
     */
    public List selectResultListByTableNameAndAttr(String tableName, String attr, String attrVal){
        String sql = "select * from " + tableName + " where " + attr + "= \"" + attrVal + "\"";
        Object object = entityManager.createNativeQuery(sql).getSingleResult();
        List resultList = entityManager.createNativeQuery(sql).getResultList();
        return  resultList;
    }

    /**
     * 获取数据库表中的列的列名
     * @param tableName
     * @return
     */
    public List selectAttrNameByTableName(String tableName){
        String sql = "select COLUMN_NAME from information_schema.`COLUMNS` where table_name = \"" + tableName + "\"";
        List resultList = entityManager.createNativeQuery(sql).getResultList();
        return  resultList;
    }

//    public String getAttrCNameByTableId(String TableId, String colEName){
//
//    }
}
