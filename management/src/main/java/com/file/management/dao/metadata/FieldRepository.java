package com.file.management.dao.metadata;

import com.file.management.pojo.metadata.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface FieldRepository extends JpaRepository<Field,Integer> {

    // 根据字段名查询字段
    Field findByFieldName(String fieldName);

    // 根据字段uuid查询字段
    Field findByFieldUuid(String fieldUuid);

    // 根据字段英文名查询字段
    Field findByFieldEnglishName(String fieldEnglishName);

    // 根据模糊字段名查询字段
    @Query(value = "select * from tb_field where field_name like %?1% ", nativeQuery = true)
    List<Field> findByFieldNameLike(String fieldName);

    // 根据字段号删除字段
    /*@Modifying
    @Query(value = "delete from tb_field where field_id=?1 ", nativeQuery = true)
    void deleteFieldByFieldId(int fieldId);*/

}
