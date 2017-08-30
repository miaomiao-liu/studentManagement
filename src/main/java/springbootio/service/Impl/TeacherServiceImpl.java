package springbootio.service.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import springbootio.dao.GradeDao;
import springbootio.dao.StudentDao;
import springbootio.dao.TeacherDao;
import springbootio.entity.persistence.StudentGrade;
import springbootio.entity.persistence.StudentInfo;
import springbootio.entity.persistence.TeacherDetail;
import springbootio.entity.persistence.TeacherInfo;
import springbootio.entity.view.JwtAuthenticationRequest;
import springbootio.entity.view.JwtAuthenticationResponse;
import springbootio.entity.view.JwtUserFactory;
import springbootio.exception.TeacherException;
import springbootio.service.TeacherService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springbootio.util.JwtTokenUtil;

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
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    GradeDao gradeDao;
    @Autowired
    private UserDetailsService userDetailsService;


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

    //老师添加注册信息
    @Override
    public void registerTeacher(TeacherInfo teacherInfo) throws TeacherException{
        try {
            if(teacherDao.selectTeacherInfoByName(teacherInfo.getUsername()) != null) {
                throw new TeacherException("对不起！该用户名已经被注册！");
            }
            if(teacherDao.selectTeacherInfoByEmail(teacherInfo.getEmail()) != null){
                throw new TeacherException("对不起！该邮箱已被注册！");
            }
            BCryptPasswordEncoder encoder  = new BCryptPasswordEncoder();
            final String rawPassword = teacherInfo.getPassword();
            teacherInfo.setPassword(encoder.encode(rawPassword));

            int num = teacherDao.addTeacherInfo(teacherInfo);
            if(num != 1) {
                throw new TeacherException("注册失败！");
            }
        }catch(Exception e){
            throw e;
        }
    }


    @Override
    public JwtAuthenticationResponse teacherLogin(JwtAuthenticationRequest authenticationRequest) throws TeacherException{
        try{
            TeacherInfo teacherInfo = teacherDao.selectTeacherInfoByName(authenticationRequest.getUsername());
            if(teacherInfo == null){
                throw new TeacherException("该用户不存在！");
            }
            String username = authenticationRequest.getUsername();
            String password = authenticationRequest.getPassword();
            UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username,password);
            final Authentication authentication = authenticationManager.authenticate(upToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final UserDetails userDetails = JwtUserFactory.createTeacher(teacherDao.selectTeacherInfoByName(username));
            final String token = jwtTokenUtil.generateToken(userDetails);
            JwtAuthenticationResponse response = new JwtAuthenticationResponse(token,username);
            return response;
        }catch (Exception e){
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
