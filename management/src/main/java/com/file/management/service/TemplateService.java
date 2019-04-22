package com.file.management.service;

import com.file.management.dao.TemplateRepository;
import com.file.management.pojo.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 模板的操作：
 *      1. 添加一个模板
 *      2. 批量添加模板
 *      3. 根据模板id查询模板
 *      4. 根据模板uuid查询模板
 * 用户不可以修改删除模板。
 */
@Service
public class TemplateService {
    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //获取当前的日期
    private Date date = new Date();
    //设置要获取到什么样的时间
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //获取String类型的时间
    private String createdate = sdf.format(date);

    /**
     * 添加一个模板
     * @param template
     */
    @Transactional
    public void saveOne(Template template) {
        template.setTemplateUuid("Template" + "_" + template.getTemplateName() + "_" + createdate);
        templateRepository.saveAndFlush(template);
    }

    /**
     * 批量添加模板
     * @param templates
     */
    @Transactional
    public void saveAll(List<Template> templates){
        for(Template t: templates){
            t.setTemplateUuid("Template" + "_" + t.getTemplateName() + "_" + createdate);
        }
        templateRepository.saveAll(templates);
    }

    /**
     * 根据模板id查询模板
     * @param templateId
     * @return
     */
    public Template getTemplateByTemplateId(int templateId){
        return templateRepository.findByTemplateId(templateId);
    }

    /**
     * 根据模板uuid查询模板
     * @param templateUuid
     * @return
     */
    public Template getTemplateByTemplateUuid(String templateUuid){
        return templateRepository.findByTemplateUuid(templateUuid);
    }

    /**
     * 根据表格id删除表格
     * @param templateId
     */
    /*public void deleteTemplateByTemplateId(int templateId) {
        templateRepository.deleteTemplateByTemplateId(templateId);
    }*/
}
