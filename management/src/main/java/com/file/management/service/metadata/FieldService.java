package com.file.management.service.metadata;

import com.file.management.dao.metadata.FieldRepository;
import com.file.management.pojo.metadata.Field;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

/*    //获取当前的日期
    private Date date = new Date();
    //设置要获取到什么样的时间
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
    //获取String类型的时间
    private String createdate = sdf.format(date);*/

    /**
     * 添加一个字段
     * @param field 需要添加的字段
     */
    @Transactional
    public void saveOne(Field field) {
        field.setFieldUuid("F" + "_" + RandomStringUtils.random(8, "abcdefghijklmnopqrstuvwxyz1234567890"));
        fieldRepository.saveAndFlush(field);
    }

    /**
     * 批量添加字段
     * @param fields 需要批量添加的字段列表
     */
    @Transactional
    public void saveAll(List<Field> fields){
        for(Field f: fields){
            f.setFieldUuid("F" + "_" + RandomStringUtils.random(8, "abcdefghijklmnopqrstuvwxyz1234567890"));
        }
        fieldRepository.saveAll(fields);
    }

    /**
     * 根据字段uuid查询字段
     * @param filedUuid 字段的uuid
     * @return 查询的字段结果
     */
    public Field getFieldByFieldUUid(String filedUuid){
        return fieldRepository.findByFieldUuid(filedUuid);
    }

    /**
     * 根据字段名称做精确查询
     * @param fieldName 字段名称
     * @return 查询的字段结果
     */
    public Field getFieldByFieldName(String fieldName){
        return fieldRepository.findByFieldName(fieldName);
    }

    public Field getFieldByFieldEnglishName(String fieldEnglishName){
        return fieldRepository.findByFieldEnglishName(fieldEnglishName);
    }

    /**
     * 根据字段名称做模糊查询
     * @param fieldName 字段模糊名称
     * @return 查询的字段结果
     */
    public List<Field> getFieldByFieldNameLike(String fieldName){
        return fieldRepository.findByFieldNameLike(fieldName);
    }
    
    /*public void deleteFieldByFieldId(int fieldId){
        fieldRepository.deleteFieldByFieldId(fieldId);
    }*/
}
