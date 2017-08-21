package springbootio.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import springbootio.dao.StudentDao;
import springbootio.dao.TeacherDao;
import springbootio.entity.persistence.StudentInfo;
import springbootio.entity.persistence.TeacherInfo;
import springbootio.entity.view.JwtUserFactory;

/**
 *  UserDetailService 加载用户特定数据的核心接口
 * 只有一个方法loadUserByUsername()，用于返回用户的信息
 * Created by muyi on 17-4-18.
 */
@Service
public class JwtUserServiceDetailImpl implements UserDetailsService {
    private TeacherDao teacherDao;
    private StudentDao studentDao;

    @Autowired
    public JwtUserServiceDetailImpl(TeacherDao teacherDao, StudentDao studentDao) {
        this.teacherDao = teacherDao;
        this.studentDao = studentDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        StudentInfo studentinfo = studentDao.selectStudentInfoByName(username);
        TeacherInfo teacherinfo = teacherDao.selectTeacherInfoByName(username);
        if (studentinfo != null) {
            return JwtUserFactory.createStudent(studentinfo);
        } else  {
            return JwtUserFactory.createTeacher(teacherinfo);
        }
    }
}
