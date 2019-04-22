package com.file.management.service;

import com.file.management.dao.TablesRepository;
import com.file.management.pojo.Field;
import com.file.management.pojo.Tables;
import com.file.management.pojo.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 表的操作：
 *      1. 添加一张表
 *      2. 批量添加表
 *      3. 根据表id查询表
 *      4. 根据表uuid查询表
 *      5. 根据表id删除表【仅删除表，不删除字段】
 *      6. 根据表uuid删除表【仅删除表，不删除字段】
 *      7. 根据用户设置的字段生成表
 *      8. 根据模板直接生成表
 *      9. 表中添加一条数据
 *      10. 表中批量添加数据
 */
@Service
public class TablesService {
    @Autowired
    private TablesRepository tablesRepository;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //获取当前的日期
    private Date date = new Date();
    //设置要获取到什么样的时间
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //获取String类型的时间
    private String createdate = sdf.format(date);

    /**
     * 添加一张表
     * @param table
     */
    @Transactional
    public void saveOne(Tables table){
        table.setTableUuid("Table" + "_" + table.getTableName() + "_" + createdate);
        tablesRepository.saveAndFlush(table);
    }

    /**
     * 批量添加表
     * @param tables
     */
    @Transactional
    public void saveAll(List<Tables> tables){
        for(Tables t: tables){
            t.setTableUuid("Table" + "_" + t.getTableName() + "_" + createdate);
        }
        tablesRepository.saveAll(tables);
    }

    /**
     * 根据表格id查询表格
     * @param tableId
     * @return
     */
    public Tables getTablesByTableId(int tableId){
        return tablesRepository.findByTableId(tableId);
    }

    /**
     * 根据表格uuid查询表格
     * @param tableUuid
     * @return
     */
    public Tables getTablesByTableUuid(String tableUuid){
        return tablesRepository.findByTableUuid(tableUuid);
    }

    /**
     * 根据表格id删除表格
     * @param tableId
     */
    public boolean deleteTablesByTableId(int tableId) {
        if(getTablesByTableId(tableId) == null){
            return false;
        }
        tablesRepository.deleteTableByTableId(tableId);
        return true;
    }

    /**
     * 根据表格uuid删除表格
     * @param tableUuid
     */
    public boolean deleteTablesByTableUuid(String tableUuid) {
        if(getTablesByTableUuid(tableUuid) == null){
            return false;
        }
        tablesRepository.deleteTableByTableUuid(tableUuid);
        return true;
    }

    /**
     * 修改指定表格的模板id
     * @param templateId
     * @param tableId
     */
    /*public void updateTablesByTemplateId(int templateId,int tableId) {
        tablesRepository.updateTableByTemplateId(templateId,tableId);
    }*/

    /**
     * 生成表函数
     * @param fields
     * @param tableName
     * @param isTemplate ：是否使用模板生成
     */
    public void generateTables(Set<Field> fields, String tableName,boolean isTemplate) {

        /**
         * 根据一些字段生成一张名为tableName的表
         */
        String sqlCreateTable = "";
        sqlCreateTable += "CREATE TABLE " + tableName + "(\n";
        Field keyField = null;

        // 添加所需字段
        for(Field f : fields){
            sqlCreateTable += f.getFieldEnglishName() + " " + f.getFieldType() + "(" + f.getFieldLength() + ")" + ",\n";
            if(f.isFieldPrimaryKey()){
                keyField = f;
            }
        }

        // 添加最后修改时间并设置默认值
        sqlCreateTable += "LAST_MODIFIED TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,\n";

        // 添加软删除标识
        sqlCreateTable += "IS_DEL int(11) DEFAULT 0,\n";

        // 添加tableid
        sqlCreateTable += "TABLE_ID int(11),\n";

        // 设置表的主键
        sqlCreateTable += "PRIMARY KEY (" + keyField.getFieldEnglishName() + ")\n";
        sqlCreateTable += ");";

        // 执行建表语句
        jdbcTemplate.execute(sqlCreateTable);

        // 将新生成的表放入tb_tables中
        Tables tables = new Tables();
        tables.setTableName(tableName);
        // 如果不是模板，即用户自定义字段生成的模板建立table-field关联
        if(!isTemplate) {
            tables.setFields(fields);
        }
        saveOne(tables);
    }

    /**
     * 使用用户自定义字段生成表
     * @param fields
     * @param tableName
     */
    public void generateTablesByUser(Set<Field> fields, String tableName){
        generateTables(fields,tableName,false);
    }

    /**
     * 根据模板直接生成表
     * @param templateId
     * @param tableName
     */
    public void generateTablesByTemplateId(int templateId,String tableName) {
        Template template = templateService.getTemplateByTemplateId(templateId);
        generateTables(template.getFields(),tableName,true);

        // 修改tb_table的模板id的sql语句
        String sqlUpdateTable = "";
        sqlUpdateTable += "UPDATE TB_TABLE SET TEMPLATE_ID = " + templateId + " WHERE TABLE_NAME = \"" + tableName + "\"";

        // 执行修改表的模板id的sql语句
        jdbcTemplate.execute(sqlUpdateTable);
        templateService.saveOne(template);

        /*String sqlUpdateTemplateField = "";
        for(Field f:templateService.getTemplateByTemplateId(templateId).getFields()){
            int fieldId = f.getFieldId();
            sqlUpdateTemplateField += "INSERT INTO TB_TEMPLATE_FIELD(TEMPLATE_ID,FIELD_ID) VALUES(" + Integer.toString(templateId) +"," + Integer.toString(fieldId) + ")";
            jdbcTemplate.execute(sqlUpdateTemplateField);
        }*/

        /*String sqlUpdateField = "";
        sqlUpdateField += "UPDATE TB_FIELD F JOIN TB_TABLE T ON F.TABLE_ID = T.TABLE_ID SET F.TEMPLATE_ID = T.TEMPLATE_ID WHERE TABLE_NAME = \""  + tableName + "\"";
        jdbcTemplate.execute(sqlUpdateField);*/
    }

    /**
     * 表中添加一条数据
     * @param tableUuid
     * @param map: key为属性名称，value为属性值。
     */
    public void InsertData(String tableUuid, HashMap<String, String> map){
        Tables tables = getTablesByTableUuid(tableUuid);
        String tableName = tables.getTableName();
        String keys = "";
        String values = "";
        for (String key:map.keySet()) {
             keys += key + ",";
             values += "\'" + map.get(key) + "\'" + ",";
        }
        // 删除最后一个逗号
        keys = keys.substring(0,keys.length() - 1);
        values = values.substring(0,values.length() - 1);

        // 拼接字符串完成插入数据的sql语句
        String sqlInsert = "INSERT INTO " + tableName + "(" + keys + ")" + " VALUES" + "(" + values + ")";
        jdbcTemplate.execute(sqlInsert);
    }

    /**
     * 表中批量添加数据
     * @param tableUuid
     * @param list
     */
    public void InsertDatas(String tableUuid, ArrayList<HashMap<String,String>> list){
        for(HashMap<String,String> map :list){
            InsertData(tableUuid,map);
        }
    }

    /**
     * 表中删除一条数据
     */
    //public boolean DeleteDate(String tableUuid,)
}
