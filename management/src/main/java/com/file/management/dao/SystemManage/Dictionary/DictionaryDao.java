package com.file.management.dao.SystemManage.Dictionary;

import com.file.management.pojo.SystemManagement.Dictionary.DictionaryPojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface DictionaryDao extends JpaRepository<DictionaryPojo,String> {

//    @Modifying
//    @Query(value = "update tb_dictionary t set t.NAME=?1,t.SEQUENCE=?2, t.COMMENT=?3 where t.CODE=?4 and t.DICTIONARY",nativeQuery = true)
//    void insertInfo( String name, String order, String comment, String code,String dictionary);
//
//    void deleteByCodeAndDictionary(String code,String dictionary);
//
//    List<DictionaryPojo> findAllByDictionary(String dictionary);

    @Query(value = "select * from tb_dictionary t group by t.dictionaryname",nativeQuery = true)
    List<DictionaryPojo> findDictionary();

    List<DictionaryPojo> findAllByDictionaryname(String name);

    @Query(value = "select * from tb_dictionary t where t.dictionaryname=?1 and t.code is not null order by t.SEQUENCE",nativeQuery = true)
    List<DictionaryPojo> findAllInfo(String name);

    void deleteAllByDictionaryname(String name);

    void deleteByCode(String code);

    @Modifying
    @Query(value = "update tb_dictionary t set t.SEQUENCE=?1,t.COMMENT=?2 where t.CODE=?3",nativeQuery = true)
    void updateInfo(String sequence, String comment,String code);

}
