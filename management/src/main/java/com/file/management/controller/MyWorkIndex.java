package com.file.management.controller;

import com.alibaba.fastjson.JSONObject;
import com.file.management.dao.FileResposity;
import com.file.management.dao.NewFileResposity;
import com.file.management.dao.VideoDao;
import com.file.management.pojo.ExpiredFile;
import com.file.management.pojo.LibraryUse.RegistrationForm;
import com.file.management.pojo.NewFile;
import com.file.management.pojo.Video;
import com.file.management.pojo.state.Message;
import com.file.management.pojo.state.State;
import com.file.management.service.LibraryUse.RegistrationFormService;
import com.file.management.service.state.MessageService;
import com.file.management.service.state.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;


/*
 我的工作
 */
@Controller
public class MyWorkIndex {

    //信息提示
    @Autowired
    private  MessageService messageService;
    @RequestMapping("/mywork/myworkindex")
    public String myWorkindex( Map<String,Object> map){
        List<Message> lm = messageService.findAll();
        int num = lm.size()-5;
        for(int i=1;i<=5;i++){
            Message m = messageService.findById(num+i);
            if(m.getMessage()!=null){
                String str = "mes0"+i;
                map.put(str,m.getMessage());
            }
        }
        return "mywork/myworkindex";
    }
    //即将到期档案
    @RequestMapping("/mywork/ExpiredFile")
    public  String ExpiredFile(){
        return "mywork/ExpiredFile";
    }
    //档案清点
    @RequestMapping("/mywork/FileCount")
    public  String FileCount(){
        return "mywork/FileCount";
    }
    //已到期档案
    @Autowired
    private FileResposity fileResposity;
    @RequestMapping(value = "/mywork/MatureFile")
    public String FileManagement(Model model){
        List<ExpiredFile> list = fileResposity.findAll();
        model.addAttribute("filelist",list);
        return "mywork/MatureFile";
    }
    //代收档案
    @Autowired
    private NewFileResposity newFileResposity;
    @RequestMapping(value = "/mywork/NewFile")
    public String NewFileManagement(Model model){
        List<NewFile> list = newFileResposity.findAllByFinish("true");
        model.addAttribute("newfilelist",list);
        return "mywork/NewFile";
    }
    //异常档案
    @RequestMapping(value = "/mywork/ErrorFile")
    public String ErrorFileManagement(Model model){
        List<NewFile> list = newFileResposity.findAllByFinish("false");
        model.addAttribute("errorfilelist",list);
        return "mywork/ErrorFile";
    }
    //环境监控
    @Autowired
    private StateService stateService;
    @RequestMapping(value = "/mywork/Environment")
    public  String environment(Map<String,Object> map){
        State s1 = stateService.findStateByName("温度");
        State s2 = stateService.findStateByName("湿度");
        map.put("temperature",s1.getNum()+"摄氏度");
        map.put("humidity",s2.getNum()+"%RH");
        map.put("mes01","无化学污染");
        map.put("mes02","尘埃颗粒正常");
        map.put("mes03","无蛀虫等生物污染");
        map.put("mes04","设备状态正常");
        return  "/mywork/Environment";
    }

    //视频监控
    @Autowired
    private VideoDao videoDao;
    @RequestMapping("/mywork/Video")
    public  String video(){
        return "/mywork/Video";
    }

    @RequestMapping("/mywork/chaxun")
    public String chanxun(Model model){
        List<Video> videos = videoDao.findAll();
        model.addAttribute("videolist", videos);
        return "/mywork/Video";
    }

    private String  url;

    @RequestMapping(value="/mywork/uploadFile",produces="application/json;charset=UTF-8")
    @ResponseBody
    public String uploadFile(@RequestParam("fileName") MultipartFile file) {

        if (file.isEmpty()) {
            return "上传文件不可为空";
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        //加个时间戳，尽量避免文件名称重复
        String path = "D:/Video/" +fileName;
        System.out.println(path);
        //创建文件路径
        File dest = new File(path);

        //判断文件是否已经存在
        if (dest.exists()) {
            //判断文件父目录是否存在
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdir();
            }
            return "文件已经存在";
        }

        try {
            //上传文件
            file.transferTo(dest); //保存文件
            url="http://localhost:8080/images/"+fileName;//本地运行项目
            Video v = new Video();
            v.setName(fileName);
            v.setUrl(url);
            v.setLocalpath(path);
            videoDao.save(v);

        } catch (IOException e) {
            return "上传失败";
        }

        return "上传成功,文件url=="+url;
    }


    //人员进出
    @RequestMapping("/mywork/UserManage")
    public String manage(){
        return "/mywork/UserManage";
    }

    @RequestMapping("/mywork/borrow")
    public String borrow(){
        return "/mywork/borrow";
    }

    @Autowired
    RegistrationFormService registrationFormService;

    //借阅申请查询
    @ResponseBody
    @RequestMapping("/getBorrow2")
    public String getBorrow(){
        //System.out.println("1");
        List<RegistrationForm> examines=registrationFormService.findByStatus("待审批");
        int total=examines.size();
        JSONObject result= new JSONObject();
        result.put("rows",examines);
        result.put("total",total);

        return result.toJSONString();
    }
}
