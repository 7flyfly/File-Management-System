package com.file.management.service;

import com.file.management.dao.FieldRepository;
import com.file.management.pojo.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 字段的操作：
 *      1. 添加一个字段
 *      2. 批量添加字段
 *      3. 根据字段uuid查询表
 *      4. 根据字段名称做精确查询
 *      5. 根据字段名称做模糊查询
 * 用户不可以删除与修改字段。
 */
@Service
public class FieldService {

    @Autowired
    private FieldRepository fieldRepository;

    //获取当前的日期
    private Date date = new Date();
    //设置要获取到什么样的时间
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //获取String类型的时间
    private String createdate = sdf.format(date);

    /**
     * 添加一个字段
     * @param field
     */
    @Transactional
    public void saveOne(Field field) {
        field.setFieldUuid("Field" + "_" + field.getFieldEnglishName() + "_" + createdate);
        fieldRepository.saveAndFlush(field);
    }

    /**
     * 批量添加字段
     * @param fields
     */
    @Transactional
    public void saveAll(List<Field> fields){
        for(Field f: fields){
            f.setFieldUuid("Field" + "_" + f.getFieldEnglishName() + "_" + createdate);
        }
        fieldRepository.saveAll(fields);
    }

    /**
     * 根据字段uuid查询字段
     * @param filedUuid
     * @return
     */
    public Field getFieldByFieldUUid(String filedUuid){
        return fieldRepository.findByFieldUuid(filedUuid);
    }

    /**
     * 根据字段名称做精确查询
     * @param fieldName
     * @return
     */
    public Field getFieldByFieldName(String fieldName){
        return fieldRepository.findByFieldName(fieldName);
    }

    /**
     * 根据字段名称做模糊查询
     * @param fieldName
     * @return
     */
    public List<Field> getFieldByFieldNameLike(String fieldName){
        return fieldRepository.findByFieldNameLike(fieldName);
    }

    /**
     * 根据字段id删除字段
     * @param fieldId
     */
    /*public void deleteFieldByFieldId(int fieldId){
        fieldRepository.deleteFieldByFieldId(fieldId);
    }*/
}
