package com.file.management.service.metadata;

import com.alibaba.fastjson.JSONObject;
import com.file.management.dao.DynamicSQL;
import com.file.management.dao.metadata.TablesRepository;
import com.file.management.pojo.metadata.Field;
import com.file.management.pojo.metadata.Tables;
import com.file.management.pojo.metadata.Template;
import com.file.management.service.solr.SolrService;
import com.file.management.utils.ConstantString;
import net.bytebuddy.asm.Advice;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    private FieldService fieldService;

    @Autowired
    private TablesRepository tablesRepository;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SolrService solrService;

    @Autowired
    private DynamicSQL dynamicSQL;

    /*//获取当前的日期
    private Date date = new Date();
    //设置要获取到什么样的时间
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
    //获取String类型的时间
    private String createdate = sdf.format(date);*/

    /**
     * 添加一张表
     *
     * @param table 需要添加的表
     */
    @Transactional
    public void saveOne(Tables table) {
        // 将该表放入数据库中
        table.setTableUuid("T" + "_" + RandomStringUtils.random(8, "abcdefghijklmnopqrstuvwxyz1234567890"));
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
     *
     * @param tableId
     * @return
     */
    public String getTableNameByTableId(int tableId){ return getTablesByTableId(tableId).getTableName();}

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
     *
     * @param tableName
     * @return
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
     * @param fields     表字段
     * @param tableName  表名
     * @param template   模板
     */
    @Transactional
    public void generateTables(List<Field> fields, String tableName, Template template) {

        //根据一些字段生成一张名为tableName的表
        String sqlCreateTable = "";
        sqlCreateTable += "CREATE TABLE " + tableName + "(\n";

        Field primaryKey = new Field();
        for(Field f:fields){
            if(f.getFieldEnglishName().equals("No")){
                primaryKey = f;
                sqlCreateTable += f.getFieldEnglishName() + " " + f.getFieldType() + " "  + "PRIMARY KEY AUTO_INCREMENT,\n";
            }
        }

        // 添加所需字段
        for (Field f : fields) {
            if(!(f.getFieldEnglishName().equals("No"))) {
                if (f.getFieldLength() == 0) {
                    sqlCreateTable += f.getFieldEnglishName() + " " + f.getFieldType() + ",\n";
                } else {
                    sqlCreateTable += f.getFieldEnglishName() + " " + f.getFieldType() + "(" + f.getFieldLength() + ")" + ",\n";
                }
            }
        }

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
        if (template == null) {
            tables.setFields(fields);
        }else{
            tables.setTemplate(template);
        }
        saveOne(tables);
    }
    /*@Transactional
    public void generateTables(Field primaryKey, List<Field> fields, String tableName, Template template) {

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
        if (template == null) {
            tables.setFields(fields);
        }else{
            tables.setTemplate(template);
        }
        saveOne(tables);
    }*/

    /**
     * 使用用户自定义字段生成表
     *
     * @param fields    表字段
     * @param tableName 表名
     */
    public void generateTablesByUser(List<Field> fields, String tableName) {
        // 根据用户定义字段生成表
        generateTables(fields, tableName, null);

        // 将表插入solr中
        addTableToSolr(getTablesByTableName(tableName));
    }

    /**
     * 根据模板直接生成表
     *
     * @param templateId 模板id
     * @param tableName  表名
     */
    @Transactional
    public void generateTablesByTemplateId(int templateId, String tableName) {
        Template template = templateService.getTemplateByTemplateId(templateId);
        generateTables(template.getFields(), tableName, template);

        /*// 修改tb_table的模板id的sql语句
        String sqlUpdateTable = "";
        sqlUpdateTable += "UPDATE TB_TABLE SET TEMPLATE_ID = " + templateId + " WHERE TABLE_NAME = \"" + tableName + "\"";

        // 执行修改表的模板id的sql语句
        jdbcTemplate.execute(sqlUpdateTable);*/
        tablesRepository.updateTemplateId(templateId,tableName);

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
     *
     * @param tableUuid 表uuid
     * @param map:      key为属性名称，value为属性值。
     */
    public String InsertData(String tableUuid, HashMap<String, String> map) {
        Tables tables = getTablesByTableUuid(tableUuid);
        String tableName = tables.getTableName();
        String keys = "";
        String values = "";
        for (String key : map.keySet()) {
            if(map.get(key) != null && map.get(key) != "") {
                keys += key + ",";
                values += "\'" + map.get(key) + "\'" + ",";
            }
        }
        // 删除最后一个逗号
        keys = keys.substring(0, keys.length() - 1);
        values = values.substring(0, values.length() - 1);

        // 拼接字符串完成插入数据的sql语句
        String sqlInsert = "INSERT INTO " + tableName + "(" + keys + ")" + " VALUES" + "(" + values + ")";
        jdbcTemplate.execute(sqlInsert);
        HashMap<Boolean, String> hashMap = solrService.deltaImportTable2Solr(tableName,null,null,null,null,null);
        String str = "";
        for(String s:hashMap.values()){
            str += s;
        }
        return str;
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
     * @param documentNo
     * @return 返回删除是否成功信息
     */
    public String deleteData(String tableUuid, String documentNo) {
        // 获取table名称
        Tables tables = getTablesByTableUuid(tableUuid);
        String tableName = tables.getTableName();

        // 更新数据表的软链接由默认的0变为1
        String sqlUpdate = "UPDATE " + tableName + " SET IS_DEL = 1 WHERE DOCUMENTNO =" + "\'" + documentNo + "\'";
        jdbcTemplate.execute(sqlUpdate);

        // 调用solrService的方法，看是否可以删除数据
        HashMap<Boolean, String> hashMap = solrService.deleteDocumentTable2Solr(tableName,null,null,null,null,null);
        String res = "";

        for (boolean flag : hashMap.keySet()) {
            if (flag) {
                // 如果可以删除，则直接删除表中的数据
                String sqlDelete = "DELETE FROM " + tableName + " WHERE DOCUMENTNO = " + "\'" + documentNo + "\'";
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
     * @param documentNos
     * @return 返回删除是否成功信息
     */
    public String deleteDatas(String tableUuid,String[] documentNos) {
        StringBuffer sb = new StringBuffer();
        for (String documentNo : documentNos) {
            sb.append(deleteData(tableUuid, documentNo));
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

    public String updateData(String tableUuid,String documentNo,HashMap<String,String> map){
        Tables tables = getTablesByTableUuid(tableUuid);
        String tableName = tables.getTableName();
        String str = "";

        for (String key : map.keySet()) {
            str += key + " = '" + map.get(key) + "', ";
        }
        // 删除最后一个逗号
        str = str.substring(0, str.length() - 2);

        // 执行修改语句
        String sqlUpdate = "UPDATE " + tableName + " SET " + str + " WHERE DOCUMENTNO = '" + documentNo + "'";
        jdbcTemplate.execute(sqlUpdate);

        // 更改solr数据
        HashMap<Boolean, String> hashMap = solrService.deltaImportTable2Solr(tableName,null,null,null,null,null);
        String res = "";

        for (boolean flag : hashMap.keySet()) {
            res += hashMap.get(flag) + "\n";
        }
        return res;
    }

    public void addAnnex(String tableUuid,String documentNo,String annex){
        Tables tables = getTablesByTableUuid(tableUuid);
        String tableName = tables.getTableName();
        String sqlUpdate = "UPDATE " + tableName + " SET ANNEX = CONCAT(IFNULL(`Annex`,''), '" + annex + ";') WHERE DOCUMENTNO = '" + documentNo + "'";
        jdbcTemplate.execute(sqlUpdate);
        solrService.deltaImportTable2Solr(tableName,"DocumentNo","Annex",";", ConstantString.AnnexContentSolrName,ConstantString.ImageContentSolrName);
    }

    public boolean deleteAnnex(Tables tables,String documentNo,String annexName){
        String tableName = tables.getTableName();
        String sqlQuery = "SELECT ANNEX FROM " + tableName + " WHERE DOCUMENTNO = ?";
        String annex = (String)jdbcTemplate.queryForObject(sqlQuery,String.class,new Object[]{documentNo});
        String[] strs = annex.split(";");
        boolean result = Arrays.asList(strs).contains(annexName);
        if(result){
            // 即包含所输入的附件
            String ret = "";
            for(String str:strs){
                if(!str.equals(annexName)){
                    ret += str + ";";
                }
            }
            jdbcTemplate.execute("UPDATE " + tableName + " SET ANNEX = '" + ret + "' WHERE DOCUMENTNO = '" + documentNo + "'");
            solrService.deltaImportTable2Solr(tableName,"DocumentNo","Annex",";", ConstantString.AnnexContentSolrName,ConstantString.ImageContentSolrName);
            return true;
        }else{
            return false;
        }
    }

    /**
     * 获得表中属性的中英文名称
     * @param tableName 表名
     * @return
     */
    public JSONObject getAttrECNameByTableName(String tableName){
        JSONObject jsonObject = new JSONObject();
        Tables tables = this.getTablesByTableName(tableName);
        List<Field> EAttrNameset = tables.getFields();
        for (Field field : EAttrNameset) {
            jsonObject.put(field.getFieldEnglishName(),field.getFieldName());
        }
        return jsonObject;
    }

    public List<String> queryTitleFromDatabase(String table_id){
        String tableName = getTableNameByTableId(Integer.parseInt(table_id));
        List AttrNameList = dynamicSQL.selectAttrNameByTableName(tableName);
        for(int i=0;i<AttrNameList.size();i++){
            String attrNameStr = (String)AttrNameList.get(i);
            Field field = fieldService.getFieldByFieldEnglishName(attrNameStr);
            AttrNameList.set(i,field.getFieldName());
        }
        return AttrNameList;
    }
    public List<Map<String,String>> queryDataFromDatabase(String table_id){
        List<Map<String,String>> listMap = new ArrayList<>();
        String tableName = getTableNameByTableId(Integer.parseInt(table_id));
        /*List AttrNameList = dynamicSQL.selectAttrNameByTableName(tableName);
        for(int i=0;i<AttrNameList.size();i++){
            String attrNameStr = (String)AttrNameList.get(i);
            Field field = fieldService.getFieldByFieldEnglishName(attrNameStr);
            AttrNameList.set(i,field.getFieldName());
        }
        System.out.print(AttrNameList);*/
        //listList.add(AttrNameList);
        List resultList = dynamicSQL.selectAllByTableName(tableName);
        for (Object item : resultList) {
            Object[] obj = (Object[]) item;
            Map<String,String> map = new HashMap<>();
            for(int i=0;i<obj.length;i++){
                if (obj[i] != null) {
                    map.put(String.valueOf(i),obj[i].toString());
                }
            }
            listMap.add(map);
        }
        return listMap;
    }

    public HashMap<String,String> queryDataByTableIdAndDocumentNo(String tableId,String documentNo){
        String tableName = getTableNameByTableId(Integer.parseInt(tableId));
        List resultList = dynamicSQL.selectAllByTableNameAndDocumentNo(tableName,documentNo);
        HashMap<String,String> hashMap = new HashMap<>();
        // 查询结果只有一个
        for(Object item:resultList){
            Object[] obj = (Object[]) item;
            for(int i=0;i<obj.length;i++){
                if (obj[i] != null) {
                    hashMap.put(String.valueOf(i),obj[i].toString());
                }
            }
        }
        return hashMap;
    }

}
