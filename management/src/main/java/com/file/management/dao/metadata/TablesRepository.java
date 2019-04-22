package com.file.management.dao.metadata;

import com.file.management.pojo.metadata.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

@Transactional
public interface TablesRepository extends JpaRepository<Tables,Integer> {
    // 根据表id查询表
    Tables findByTableId(int tableId);

    // 根据表uuid查询表
    Tables findByTableUuid(String tableUuid);

    // 根据表id删除表
    @Modifying
    @Query(value = "delete from tb_table where table_id=?1 ", nativeQuery = true)
    void deleteTableByTableId(int tableId);

    // 根据表uuid删除表
    @Modifying
    @Query(value = "delete from tb_table where table_uuid=?1 ", nativeQuery = true)
    void deleteTableByTableUuid(String tableUuid);

    /*// 修改指定表格的模板
    @Modifying
    @Query(value = "update tb_table t set t.template_id=?1 where t.table_id=?2", nativeQuery = true)
    void updateTableByTemplateId(int templateId,int tableId);*/
}
