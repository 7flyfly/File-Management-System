package com.file.management.service.LibraryUse;

import com.file.management.dao.LibraryUse.RegistrationFormRespository;
import com.file.management.pojo.LibraryUse.RegistrationForm;
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

    @Override
    public void saveAll(RegistrationForm registrationForm) {
        respository.save(registrationForm);
    }

    @Override
    public RegistrationForm findByOddnum(String num) {
        RegistrationForm registrationForm = respository.findByOddNumbers(num);
        return registrationForm;
    }

    @Override
    public RegistrationForm findByApprovenum(String approvenum) {
        return respository.findByApprovalNumber(approvenum);
    }

    @Override
    public void updateOpinion(String opinion, String result,String state, String approvalNumber) {
        respository.updateOpinion(opinion,result,state,approvalNumber);
    }

    @Override
    public void deleteForm(String num) {
        respository.deleteByOddNumbers(num);
    }

    @Override
    public List<RegistrationForm> findByTypeAndName(String type, String name) {
        return respository.findAllByTypeAndRegistrant(type,name);
    }

    @Override
    public List<RegistrationForm> findByStatus(String status) {
        return respository.findAllByState(status);
    }

    @Override
    public void updateState(String state, String person, String date, String num) {
        respository.updateStatus(state,person,date,num);
    }
}
