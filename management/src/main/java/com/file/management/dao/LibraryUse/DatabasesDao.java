package com.file.management.dao.LibraryUse;

import com.file.management.pojo.LibraryUse.DatabasesPojo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DatabasesDao extends JpaRepository<DatabasesPojo,String> {

    List<DatabasesPojo> findAllByDocumentNoAndFilestore(String num , String file);
}
