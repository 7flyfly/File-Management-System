package com.file.management.service.LibraryUse;

import com.file.management.dao.RegistationExamineDao;
import com.file.management.pojo.RegistationExamine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ExamineServiceImpl implements ExamineService {
    @Autowired
    RegistationExamineDao registationExamineDao;
    @Override
    public List<RegistationExamine> findAll() {

        return registationExamineDao.findAll();
    }
}
