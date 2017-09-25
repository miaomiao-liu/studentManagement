package cn.edu.swpu.cins.studentManagement.async.handlerImpl;

import cn.edu.swpu.cins.studentManagement.async.EventHandler;
import cn.edu.swpu.cins.studentManagement.async.EventModel;
import cn.edu.swpu.cins.studentManagement.async.EventType;
import cn.edu.swpu.cins.studentManagement.dao.StudentDao;
import cn.edu.swpu.cins.studentManagement.dao.TeacherDao;
import cn.edu.swpu.cins.studentManagement.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created by miaomiao on 17-9-25.
 */
@Component
public class LikeHandler implements EventHandler {

    @Autowired
    StudentDao studentDao;
    @Autowired
    TeacherDao teacherDao;
    @Autowired
    MailService mailService;

    @Override
    public void doHandle(EventModel eventModel) {
        String username = eventModel.getActor();
        String isLikeName = eventModel.getAccept();
        String mail = studentDao.selectStudentInfoByName(isLikeName).getEmail();
        if(mail != null){
            mailService.sendLikeMail(username,mail);
        }else {
            mail = teacherDao.selectTeacherInfoByName(isLikeName).getEmail();
            mailService.sendLikeMail(username,mail);
        }
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.LIKE);
    }
}
