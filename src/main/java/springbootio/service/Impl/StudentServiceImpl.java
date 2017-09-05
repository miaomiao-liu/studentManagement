package springbootio.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import springbootio.dao.GradeDao;
import springbootio.dao.StudentDao;
import springbootio.entity.persistence.StudentDetail;
import springbootio.entity.persistence.StudentGrade;
import springbootio.entity.persistence.StudentInfo;
import springbootio.entity.view.JwtAuthenticationRequest;
import springbootio.entity.view.JwtAuthenticationResponse;
import springbootio.entity.view.JwtUserFactory;
import springbootio.exception.StudentException;
import springbootio.service.StudentService;
import springbootio.util.JwtTokenUtil;

/**
 * Created by miaomiao on 17-7-8.
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;
    @Autowired
    GradeDao gradeDao;


    @Override
    public StudentDetail queryStudentDetailById(int studentNumber) throws StudentException  {
        try {
            StudentDetail studentDetail = studentDao.queryStudentByNumber(studentNumber);
            if( studentDao.selectStudentInfoById(studentNumber) == null)
                throw new StudentException("对不起！没有该学号的学生用户！");
            if(studentDetail == null)
                throw new StudentException("该学号学生没有添加学生信息！");
            return studentDetail;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public StudentDetail queryStudentDetailByName(String username) throws StudentException{
        try {
            StudentDetail studentDetail = studentDao.queryStudentByName(username);
            if( studentDao.selectStudentInfoByName(username) == null)
                throw new StudentException("对不起！没有该用户名的学生用户！");
            if(studentDetail == null)
                throw new StudentException("该学生没有添加学生信息！");
            return studentDetail;
        }catch (Exception e){
            throw e;
        }

    }

    @Override
    public String studentDetail(StudentDetail studentDetail,String username) throws StudentException{
        try {
            studentDetail.setUsername(username);
            if(studentDao.queryStudentByNumber(studentDetail.getStudentNumber()) == null) {
                int num = studentDao.addStudent(studentDetail);
                if (num != 1) {
                    throw new StudentException("添加信息失败！");
                }
                return "添加信息成功！";
            }else {
                int num = studentDao.updateStudent(studentDetail);
                if (num != 1) {
                    throw new StudentException("更新信息失败！");
                }
                return "更新信息成功！";
            }
        }catch (Exception e){
            throw e;
        }
    }


    @Override
    public StudentGrade queryGrade(int studentNumber) throws StudentException {
        try {
            StudentGrade studentGrade = gradeDao.queryGrade(studentNumber);
            if (studentDao.queryStudentByNumber(studentNumber) == null)
                throw new StudentException("对不起！查询成绩的学生学号不存在！");
            if(studentGrade == null)
                throw new StudentException("对不起！该学生学生成绩不存在！");
            return studentGrade;
        }catch (Exception e){
            throw e;
        }
    }

    //学生修改密码
//    @Override
//    public void updatePassword() throws StudentException {
//
//    }
}
