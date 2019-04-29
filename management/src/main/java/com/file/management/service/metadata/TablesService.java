package com.file.management.service.metadata;

import com.file.management.dao.metadata.TablesRepository;
import com.file.management.pojo.metadata.Field;
import com.file.management.pojo.metadata.Tables;
import com.file.management.pojo.metadata.Template;
import com.file.management.service.solr.SolrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 表的操作：
 * 1. 添加一张表
 * 2. 批量添加表
 * 3. 根据表id查询表
 * 4. 根据表uuid查询表
 * 5. 根据表id删除表【仅删除表，不删除字段】
 * 6. 根据表uuid删除表【仅删除表，不删除字段】
 * 7. 根据用户设置的字段生成表
 * 8. 根据模板直接生成表
 * 9. 表中添加一条数据
 * 10.表中批量添加数据
 * 11.表中删除一条数据
 * 12.表格批量删除数据
 */
@Service
public class TablesService {
    @Autowired
    private TablesRepository tablesRepository;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SolrService solrService;

    //获取当前的日期
    private Date date = new Date();
    //设置要获取到什么样的时间
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
    //获取String类型的时间
    private String createdate = sdf.format(date);

    /**
     * 添加一张表
     *
     * @param table 需要添加的表
     */
    @Transactional
    public void saveOne(Tables table) {
        // 将该表放入数据库中
        table.setTableUuid("Table" + "_" + table.getTableName() + "_" + createdate);
        tablesRepository.saveAndFlush(table);
    }

    /**
     * 批量添加表
     *
     * @param tables 需要批量添加的表的list
     */
    @Transactional
    public void saveAll(List<Tables> tables) {
        for (Tables t : tables) {
            saveOne(t);
        }
    }

    /**
     * 根据表格id查询表格
     *
     * @param tableId 表的id
     * @return 查询的表结果
     */
    public Tables getTablesByTableId(int tableId) {
        return tablesRepository.findByTableId(tableId);
    }

    /**
     * 根据表格uuid查询表格
     *
     * @param tableUuid 表的uuid
     * @return 查询的表结果
     */
    public Tables getTablesByTableUuid(String tableUuid) {
        return tablesRepository.findByTableUuid(tableUuid);
    }

    /**
     * 根据表格id删除表格
     *
     * @param tableId 表的id
     */
    public boolean deleteTablesByTableId(int tableId) {
        if (getTablesByTableId(tableId) == null) {
            return false;
        }
        tablesRepository.deleteTableByTableId(tableId);
        return true;
    }

    /**
     * 根据TableId获得tableName
     * @param tableId 表的id
     */
    public String getTableNameByTableId(int tableId){ return getTablesByTableId(tableId).getTableName();}

    /**
     * 根据tableName获得table
     * @param tableName 表名
     */
    public Tables getTablesByTableName(String tableName){return tablesRepository.findByTableName(tableName);}

    /**
     * 根据表格uuid删除表格
     *
     * @param tableUuid 表的uuid
     */
    public boolean deleteTablesByTableUuid(String tableUuid) {
        if (getTablesByTableUuid(tableUuid) == null) {
            return false;
        }
        tablesRepository.deleteTableByTableUuid(tableUuid);
        return true;
    }

    /*public void updateTablesByTemplateId(int templateId,int tableId) {
        tablesRepository.updateTableByTemplateId(templateId,tableId);
    }*/

    /**
     * 生成表函数
     *
     * @param primaryKey 该表的主键
     * @param fields     表字段
     * @param tableName  表名
     * @param isTemplate 是否使用模板生成
     */
    public void generateTables(Field primaryKey,Set<Field> fields, String tableName, boolean isTemplate) {

        //根据一些字段生成一张名为tableName的表
        String sqlCreateTable = "";
        sqlCreateTable += "CREATE TABLE " + tableName + "(\n";

        // 添加所需字段
        for (Field f : fields) {
            if (f.getFieldLength() == 0) {
                sqlCreateTable += f.getFieldEnglishName() + " " + f.getFieldType() + ",\n";
            } else {
                sqlCreateTable += f.getFieldEnglishName() + " " + f.getFieldType() + "(" + f.getFieldLength() + ")" + ",\n";
            }
        }

        // 设置表的主键
        sqlCreateTable += "PRIMARY KEY (" + primaryKey.getFieldEnglishName() + "),\n";

        // 添加最后修改时间并设置默认值
        sqlCreateTable += "LAST_MODIFIED TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,\n";

        // 添加软删除标识
        sqlCreateTable += "IS_DEL int(11) DEFAULT 0,\n";

        // 添加tableid
        sqlCreateTable += "TABLE_ID int(11));";

        // 执行建表语句
        jdbcTemplate.execute(sqlCreateTable);

        // 将新生成的表放入tb_tables中
        Tables tables = new Tables();
        tables.setTableName(tableName);
        tables.setPrimaryKey(primaryKey);

        // 如果不是模板，即用户自定义字段生成的模板建立table-field关联
        if (!isTemplate) {
            tables.setFields(fields);
        }
        saveOne(tables);
    }

    /**
     * 使用用户自定义字段生成表
     *
     * @param primaryKey 该表的主键
     * @param fields    表字段
     * @param tableName 表名
     */
    public void generateTablesByUser(Field primaryKey,Set<Field> fields, String tableName) {
        // 根据用户定义字段生成表
        generateTables(primaryKey,fields, tableName, false);

        // 将表插入solr中
        addTableToSolr(getTablesByTableName(tableName));
    }

    /**
     * 根据模板直接生成表
     *
     * @param templateId 模板id
     * @param tableName  表名
     */
    public void generateTablesByTemplateId(int templateId, String tableName) {
        Template template = templateService.getTemplateByTemplateId(templateId);
        generateTables(template.getPrimaryKey(),template.getFields(), tableName, true);

        // 修改tb_table的模板id的sql语句
        String sqlUpdateTable = "";
        sqlUpdateTable += "UPDATE TB_TABLE SET TEMPLATE_ID = " + templateId + " WHERE TABLE_NAME = \"" + tableName + "\"";

        // 执行修改表的模板id的sql语句
        jdbcTemplate.execute(sqlUpdateTable);

        // 将表加入solr中
        addTableToSolr(getTablesByTableName(tableName));

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
     * @param tableUuid 表uuid
     * @param map:      key为属性名称，value为属性值。
     */
    public void InsertData(String tableUuid, HashMap<String, String> map) {
        Tables tables = getTablesByTableUuid(tableUuid);
        String tableName = tables.getTableName();
        String keys = "";
        String values = "";
        for (String key : map.keySet()) {
            keys += key + ",";
            values += "\'" + map.get(key) + "\'" + ",";
        }
        // 删除最后一个逗号
        keys = keys.substring(0, keys.length() - 1);
        values = values.substring(0, values.length() - 1);

        // 拼接字符串完成插入数据的sql语句
        String sqlInsert = "INSERT INTO " + tableName + "(" + keys + ")" + " VALUES" + "(" + values + ")";
        jdbcTemplate.execute(sqlInsert);
        solrService.deltaImportTable2Solr(tableName);
    }

    /**
     * 表中批量添加数据
     *
     * @param tableUuid 表uuid
     * @param list      数据list
     */
    public void InsertDatas(String tableUuid, ArrayList<HashMap<String, String>> list) {
        for (HashMap<String, String> map : list) {
            InsertData(tableUuid, map);
        }
    }

    /**
     * 删除一条数据
     *
     * @param tableUuid tableuuid
     * @param key       主键值
     * @param value     主键value
     * @return 返回删除是否成功信息
     */
    public String deleteDate(String tableUuid, String key, String value) {
        // 获取table名称
        Tables tables = getTablesByTableUuid(tableUuid);
        String tableName = tables.getTableName();

        // 更新数据表的软链接由默认的0变为1
        String sqlUpdate = "UPDATE " + tableName + " SET IS_DEL = 1 WHERE " + key + "=" + "\'" + value + "\'";
        jdbcTemplate.execute(sqlUpdate);

        // 调用solrService的方法，看是否可以删除数据
        HashMap<Boolean, String> hashMap = solrService.deltaImportTable2Solr(tableName);
        String res = "";

        for (boolean flag : hashMap.keySet()) {
            if (flag) {
                // 如果可以删除，则直接删除表中的数据
                String sqlDelete = "DELETE FROM " + tableName + " WHERE " + key + "=" + "\'" + value + "\'";
                jdbcTemplate.execute(sqlDelete);
            }
            res += hashMap.get(flag) + "\n";
        }
        return res;
    }

    /**
     * 批量删除数据
     *
     * @param tableUuid tableuuid
     * @param key       主键值
     * @param values    主键值
     * @return 返回删除是否成功信息
     */
    public String deleteDates(String tableUuid, String key, String[] values) {
        StringBuffer sb = new StringBuffer();
        for (String value : values) {
            sb.append(deleteDate(tableUuid, key, value));
        }
        return sb.toString();
    }

    /**
     *
     * 将表放入solr中
     * @param table 要放入solr的表
     */
    public void addTableToSolr(Tables table) {
        // 在综合查询中不可被检索的字段list
        ArrayList<String> solrStringList = new ArrayList<>();

        // 综合查询中可以被检索的字段list
        ArrayList<String> solrStringCopyTextList = new ArrayList<>();

        // 在综合查询中可以被检索并可以被分词的字段list
        ArrayList<String> solrIKCopyTextList = new ArrayList<>();

        for (Field f : table.getFields()) {

            // DocumentNo 无需加入
            if(!f.getFieldEnglishName().equals("DocumentNo")) {
                if (!f.getFieldIndex()) {
                    solrStringList.add(f.getFieldEnglishName());
                } else {
                    if (!f.getFieldIk()) {
                        solrStringCopyTextList.add(f.getFieldEnglishName());
                    } else {
                        solrIKCopyTextList.add(f.getFieldEnglishName());
                    }
                }
            }
        }

        // 将表放入solr中
        solrService.addTableEntity2SolrDataConfig("db_fileManagement", table.getTableName(), table.getPrimaryKey().getFieldEnglishName(), "DocumentNo", solrStringList, solrStringCopyTextList, null, null, null, solrIKCopyTextList, null, null, null, null, null, null);
    }
}
