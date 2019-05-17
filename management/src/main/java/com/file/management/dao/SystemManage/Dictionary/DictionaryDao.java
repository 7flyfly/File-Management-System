package com.file.management.dao.SystemManage.Dictionary;

import com.file.management.pojo.SystemManagement.Dictionary.DictionaryPojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface DictionaryDao extends JpaRepository<DictionaryPojo,String> {

    @Modifying
    @Query(value = "update tb_dictionary t set t.NAME=?1,t.SEQUENCE=?2, t.COMMENT=?3 where t.CODE=?4 and t.DICTIONARY",nativeQuery = true)
    void insertInfo( String name, String order, String comment, String code,String dictionary);

    void deleteByCodeAndDictionary(String code,String dictionary);

    List<DictionaryPojo> findAllByDictionary(String dictionary);

}
