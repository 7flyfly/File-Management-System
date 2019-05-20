package com.file.management.service.state;
/**
 * 定时任务
 *
 */

import com.file.management.pojo.state.Action;
import com.file.management.pojo.state.Message;
import com.file.management.pojo.state.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SchedulerTask {
    @Autowired
    private StateService stateService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private ActionService actionRepository;
    //cron表达式：每隔10秒执行一次
    @Scheduled(cron="0/10 * *  * * ? ")
    //线程任务，定时刷新数据库，获取最新状态
    public void scheduled(){
        List<State> states = stateService.FindState();

        for (State s :states) {
            //实际数值
            int num = Integer.parseInt(s.getNum());
            //范围下限
            int min = Integer.parseInt(s.getMin());
            //范围上限
            int max = Integer.parseInt(s.getMax());
            //bool是否发送过消息
            int bool = Integer.parseInt(s.getBool());

            //0~n范围，如申请数目，到期档案数目
            if (min == 0 && s.getFit() != null && s.getFit().equals("")==false && bool == 0) {
                String action = s.getFit();
                Action a = actionRepository.findActionByName(action);
                String type = a.getType();
                String message = a.getMessage();
                Submit(type, message);
                stateService.updateBoolByName(s.getName(), "1");

            }
            //0~100%的状态，例如进度，完整度，只关心100%的状态
            if (min == 0 && s.getMore() != null && s.getMore().equals("")==false && num == max && bool == 0) {
                String action = s.getMore();
                Action a = actionRepository.findActionByName(action);
                String type = a.getType();
                String message = a.getMessage();
                Submit(type, message);
                stateService.updateBoolByName(s.getName(), "1");

            }
            //有固定的适宜范围，如温度，湿度
            if (min != 0 && max != 0) {
                if (num <= min && bool == 0) {
                    String action = s.getLess();
                    Action a = actionRepository.findActionByName(action);
                    String type = a.getType();
                    String message = a.getMessage();
                    Submit(type, message);
                    stateService.updateBoolByName(s.getName(), "1");

                }
                if (min < num && num < max && bool == 1) {
                    String action = s.getFit();
                    Action a = actionRepository.findActionByName(action);
                    String type = a.getType();
                    String message = a.getMessage();
                    Submit(type, message);
                    stateService.updateBoolByName(s.getName(), "0");

                }
                if (num >= max && bool == 0) {
                    String action = s.getMore();
                    Action a = actionRepository.findActionByName(action);
                    String type = a.getType();
                    String message = a.getMessage();
                    Submit(type, message);
                    stateService.updateBoolByName(s.getName(), "1");
                    
                }
            }
        }
    }

    //状态交换，发送不同类型的提示
    public  void Submit(String ConcreteState, String information){
        String select = ConcreteState;
        switch (select){
            //提醒
            case "Remind":
                {
                    List<Message> lm = messageService.findAll();
                    int max = lm.size();
                    Message mes = new Message();
                    mes.setId(max+1);
                    mes.setName("Remind");
                    mes.setMessage("提示:"+information);
                    messageService.save(mes);
                }break;
            //警报
            case "Alarm":{
                List<Message> lm = messageService.findAll();
                int max = lm.size();
                Message mes = new Message();
                mes.setId(max+1);
                mes.setName("Alarm");
                mes.setMessage("提示:"+information);
                messageService.save(mes);
            }break;
            //需要执行动作
            case "Action":{
                List<Message> lm = messageService.findAll();
                int max = lm.size();
                Message mes = new Message();
                mes.setId(max+1);
                mes.setName("Action");
                mes.setMessage("提示:"+information);
                messageService.save(mes);
            }break;
            //新的信息
            case "Message":{
                List<Message> lm = messageService.findAll();
                int max = lm.size();
                Message mes = new Message();
                mes.setId(max+1);
                mes.setName("Message");
                mes.setMessage("提示:"+information);
                messageService.save(mes);
            }break;
        }
    }
}
