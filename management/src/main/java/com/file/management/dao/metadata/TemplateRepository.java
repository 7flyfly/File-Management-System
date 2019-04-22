package com.file.management.dao.metadata;

import com.file.management.pojo.metadata.Template;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface TemplateRepository extends JpaRepository<Template,Integer> {
    // 根据模板id查询模板
    Template findByTemplateId(int templateId);

    // 根据模板uuid查询模板
    Template findByTemplateUuid(String templateUuid);

    // 根据模板id删除模板
    /*@Modifying
    @Query(value = "delete from tb_template where template_id=?1 ", nativeQuery = true)
    void deleteTemplateByTemplateId(int templateId);*/
}
