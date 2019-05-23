package com.file.management.service.LibraryUse;

import com.file.management.pojo.LibraryUse.RegistrationForm;

import java.util.List;

public interface RegistrationFormService {
    //查询登记表中所有登记信息
    List<RegistrationForm> findAll();

    void saveAll(RegistrationForm registrationForm);

    //查询单号
    RegistrationForm findByOddnum(String num);
    //查询审批单号
    RegistrationForm findByApprovenum(String approvenum);

    //审批
    void updateOpinion(String opinion, String result, String state, String approvalNumber);

    //删除登记单
    void deleteForm(String num);

    //查询利用登记
    List<RegistrationForm> findByTypeAndNameAndStatus(String type,String name,String status);

    List<RegistrationForm> findByTypeAndStatus(String type,String status);

    //查询利用审批
    List<RegistrationForm>findByStatus(String status);

    //更改状态
    void updateState(String state, String person, String date, String num);
}
