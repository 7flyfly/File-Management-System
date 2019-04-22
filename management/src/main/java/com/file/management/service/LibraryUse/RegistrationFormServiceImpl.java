package com.file.management.service.LibraryUse;

import com.file.management.dao.RegistrationFormRespository;
import com.file.management.pojo.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RegistrationFormServiceImpl implements RegistrationFormService {
    @Autowired
    RegistrationFormRespository respository;
    @Override
    public List<RegistrationForm> findAll() {
        List<RegistrationForm> registrationForms= respository.findAll();
        return registrationForms;
    }

}
