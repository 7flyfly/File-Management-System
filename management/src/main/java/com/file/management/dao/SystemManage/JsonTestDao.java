package com.file.management.dao.SystemManage;

import com.file.management.pojo.LibraryUse.RegistrationForm;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
public class JsonTestDao {
    @PersistenceContext
    EntityManager entityManager;

    /*
    * 预立库文书档案/文书/文件目录
     */
    public void getSQL(String sql0,String sql){
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("itcast");
//        EntityManager em =factory.createEntityManager();
        String jpql = "select " + sql0 + " from tb_ws where "+sql;
        Query query = (Query)entityManager.createNativeQuery(jpql);
        List resultList =  query.getResultList();
        for (Object r:resultList)
        {
            Object[] cells = (Object[])r;
            System.out.println(cells[0]);
        }
    }
}
