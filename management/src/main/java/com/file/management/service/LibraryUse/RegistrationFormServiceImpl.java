package com.file.management.service.LibraryUse;

import com.file.management.dao.LibraryUse.RegistrationFormRespository;
import com.file.management.pojo.LibraryUse.RegistrationForm;
import com.file.management.pojo.UserInfo;
import com.file.management.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationFormServiceImpl implements RegistrationFormService {
    @Autowired
    RegistrationFormRespository respository;
    @Autowired
    UserInfoService userInfoService;
    /*
     *根据用户所属部门进行查询
     */
    @Override
    public List<RegistrationForm> findAll(HttpServletRequest request) {

        //获取cookie值
        Cookie[] cookies = request.getCookies();
        Cookie cookie= null;
        String username = null;
        for (int i=0;i<cookies.length;i++){
            cookie = cookies[i];
            if ("username".equals(cookie.getName())){
                username = cookie.getValue();
            }
        }
        UserInfo userInfo = userInfoService.findByUsername(username);
        String department = userInfo.getDepartment();
        System.out.println(department);
        List<RegistrationForm> registrationForms= respository.findAll();
        List<RegistrationForm> registrationFormList = new ArrayList<RegistrationForm>();
        //行权限
        for(RegistrationForm registrationForm : registrationForms){
            if (department.equals("管理员")){
                return registrationForms;
            }else if((registrationForm.getUnit().contains(department))){
                registrationFormList.add(registrationForm);
            }
        }
        return registrationFormList;
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
    public List<RegistrationForm> findByTypeAndNameAndStatus(String type, String name, String status) {
        return respository.findAllByTypeAndNameAndState(type,name,status);
    }

    @Override
    public List<RegistrationForm> findByTypeAndStatus(String type, String status) {
        return respository.findAllByTypeAndState(type,status);
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
