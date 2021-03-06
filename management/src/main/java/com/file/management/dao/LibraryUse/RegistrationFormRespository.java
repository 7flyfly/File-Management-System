package com.file.management.dao.LibraryUse;

import com.file.management.pojo.LibraryUse.RegistrationForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface RegistrationFormRespository extends JpaRepository<RegistrationForm, String> {
    //查找所有
      List<RegistrationForm> findAll();

      //利用查询

    //查询单号
    RegistrationForm findByOddNumbers(String oddNumber);

    //查询审批单号
    RegistrationForm findByApprovalNumber(String approvalNum);

    //审核
    @Modifying
    @Query(value = "update tb_registration t set t.OPINION=?1, t.RESULT=?2, t.STATE=?3 where t.APPROVENUMBER=?4 ",nativeQuery = true)
    void updateOpinion(String opinion, String result, String state, String approvalNumber);

    //删除登记单
    void deleteByOddNumbers(String num);

    //查询利用登记
    List<RegistrationForm> findAllByTypeAndNameAndState(String type,String name,String status);

    List<RegistrationForm>findAllByTypeAndState(String type,String status);

    //查询利用审批
    List<RegistrationForm> findAllByState(String status);

    //更改状态
    @Modifying
    @Query(value = "update tb_registration t set t.STATE=?1, t.TURN=?2, t.RETURNDATA=?3 where t.ODDNUMBERS=?4 ",nativeQuery = true)
    void updateStatus(String state, String person, String date, String num);

    //json数据测试
    @Modifying
    @Query(value = "select t.oddnumbers, t.type,t.way,t.telphone from tb_registration t where t.unit=?1",nativeQuery = true)
    List<String> findINfo(String sql);
}
