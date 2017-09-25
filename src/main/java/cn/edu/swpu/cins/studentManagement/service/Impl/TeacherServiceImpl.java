package cn.edu.swpu.cins.studentManagement.service.Impl;


import cn.edu.swpu.cins.studentManagement.dao.GradeDao;
import cn.edu.swpu.cins.studentManagement.dao.StudentDao;
import cn.edu.swpu.cins.studentManagement.dao.TeacherDao;
import cn.edu.swpu.cins.studentManagement.entity.persistence.StudentGrade;
import cn.edu.swpu.cins.studentManagement.entity.persistence.TeacherDetail;
import cn.edu.swpu.cins.studentManagement.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import cn.edu.swpu.cins.studentManagement.dao.GradeDao;
import cn.edu.swpu.cins.studentManagement.dao.StudentDao;
import cn.edu.swpu.cins.studentManagement.dao.TeacherDao;
import cn.edu.swpu.cins.studentManagement.entity.persistence.StudentGrade;
import cn.edu.swpu.cins.studentManagement.entity.persistence.TeacherDetail;
import cn.edu.swpu.cins.studentManagement.entity.persistence.TeacherInfo;
import cn.edu.swpu.cins.studentManagement.entity.view.JwtAuthenticationRequest;
import cn.edu.swpu.cins.studentManagement.entity.view.JwtAuthenticationResponse;
import cn.edu.swpu.cins.studentManagement.entity.view.JwtUserFactory;
import cn.edu.swpu.cins.studentManagement.exception.TeacherException;
import cn.edu.swpu.cins.studentManagement.service.TeacherService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import cn.edu.swpu.cins.studentManagement.util.JwtTokenUtil;

/**
 * Created by miaomiao on 17-7-8.
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    GradeDao gradeDao;


    @Override
    public TeacherDetail queryTeacherDetailById(int teacherNumber) throws TeacherException{
        try {
            TeacherDetail teacherDetail = teacherDao.queryTeacherByNumber(teacherNumber);
            if(teacherDao.selectTeacherInfoById(teacherNumber) == null)
                throw new TeacherException("对不起！没有该帐号的老师！");
            if(teacherDetail == null)
                throw new TeacherException("该老师还没有添加个人信息！");
            return teacherDetail;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public TeacherDetail queryTeacherDetailByName(String username) throws TeacherException{
        try {
            TeacherDetail teacherDetail = teacherDao.queryTeacherByName(username);
            if(teacherDao.selectTeacherInfoByName(username) == null)
                throw new TeacherException("对不起！没有该用户名的老师!");
            if(teacherDetail == null)
                throw new TeacherException("该老师没有添加个人信息！");
            return teacherDetail;
        }catch(Exception e){
            throw e;
        }

    }

    @Override
    public String teacherDetail(TeacherDetail teacherDetail,String username) throws TeacherException {
        try {
            teacherDetail.setUsername(username);
            if (teacherDao.queryTeacherByNumber(teacherDetail.getTeacherNumber()) == null) {
                int num = teacherDao.addTeacher(teacherDetail);
                if (num != 1)
                    throw new TeacherException("添加信息失败！");
                return "添加信息成功！";
            }else {
                int num = teacherDao.updateTeacher(teacherDetail);
                if (num != 1)
                    throw new TeacherException("更新信息失败！");
                return "更新信息成功！";
            }
        }catch(Exception e){
            throw e;
        }
    }

    @Override
    public void addStudentGrade(StudentGrade studentGrade) throws TeacherException{
        try {
            int studentNumber = studentGrade.getStudentNumber();
            if (studentDao.queryStudentByNumber(studentNumber) == null)
                throw new TeacherException("对不起！查询成绩的学生学号不存在！");
            if(gradeDao.queryGrade(studentNumber) != null)
                throw new TeacherException("该学生成绩已存在，不能重复添加，请更新成绩！");
            int math = studentGrade.getMath();
            int english = studentGrade.getEnglish();
            int allgrade = math + english;
            studentGrade.setAllgrade(allgrade);
            int num = gradeDao.addGrade(studentGrade);
            if(num != 1)
                throw new TeacherException("添加成绩失败！");
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public void updateStudentGrade(StudentGrade studentGrade) throws TeacherException{
        try {
            int studentNumber = studentGrade.getStudentNumber();
            if (studentDao.queryStudentByNumber(studentNumber) == null)
                throw new TeacherException("对不起！查询成绩的学生学号不存在！");
            if(gradeDao.queryGrade(studentNumber) == null)
                throw new TeacherException("学生原成绩不存在，不能更新，请添加成绩！");
            int math = studentGrade.getMath();
            int english = studentGrade.getEnglish();
            int allgrade = math + english;
            studentGrade.setAllgrade(allgrade);
            int num = gradeDao.updateGeade(studentGrade);
            if(num != 1)
                throw new TeacherException("更新成绩失败！");
        }catch (Exception e){
            throw e;
        }
    }


    @Override
    public StudentGrade queryStudentGrade(int studentNumber) throws TeacherException {
        try {
            StudentGrade studentGrade = gradeDao.queryGrade(studentNumber);
            if (studentDao.queryStudentByNumber(studentNumber) == null)
                throw new TeacherException("对不起！查询成绩的学生学号不存在！");
            if(studentGrade == null)
                throw new TeacherException("对不起！该学生学生成绩不存在！");
            return studentGrade;
        }catch (Exception e){
            throw e;
        }
    }


}
