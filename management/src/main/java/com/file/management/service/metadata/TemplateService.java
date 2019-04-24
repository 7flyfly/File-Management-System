package com.file.management.service.metadata;

import com.file.management.dao.metadata.TemplateRepository;
import com.file.management.pojo.metadata.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
    //获取String类型的时间
    private String createdate = sdf.format(date);

    /**
     * 添加一个模板
     * @param template 需要添加的模板
     */
    @Transactional
    public void saveOne(Template template) {
        template.setTemplateUuid("Template" + "_" + template.getTemplateName() + "_" + createdate);
        templateRepository.saveAndFlush(template);
    }

    /**
     * 批量添加模板
     * @param templates 需要批量添加的模板列表
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
     * @param templateId 模板id
     * @return 查询的模板结果
     */
    public Template getTemplateByTemplateId(int templateId){
        return templateRepository.findByTemplateId(templateId);
    }

    /**
     * 根据模板uuid查询模板
     * @param templateUuid 模板uuid
     * @return 查询的模板结果
     */
    public Template getTemplateByTemplateUuid(String templateUuid){
        return templateRepository.findByTemplateUuid(templateUuid);
    }

    public ArrayList<Template> getAllTemplates(){
        return templateRepository.findAllTemplates();
    }

    /*public void deleteTemplateByTemplateId(int templateId) {
        templateRepository.deleteTemplateByTemplateId(templateId);
    }*/
}
