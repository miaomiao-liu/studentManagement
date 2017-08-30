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
    private  AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    GradeDao gradeDao;

    @Autowired
    private UserDetailsService userDetailsService;

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
    public void registerStudent(StudentInfo studentInfo) throws StudentException{
        try{
            if(studentDao.selectStudentInfoByName(studentInfo.getUsername()) != null){
                throw new StudentException("对不起!该用户名已被注册！");
            }
            if(studentDao.selectStudentInfoByEmail(studentInfo.getEmail()) != null){
                throw new StudentException("对不起！该邮箱已被注册！");
            }

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            final String rawPassword = studentInfo.getPassword();
            studentInfo.setPassword(encoder.encode(rawPassword));

            int num = studentDao.addStudentInfo(studentInfo);;
            if(num !=1) {
                throw new StudentException("对不起！注册失败！");
            }
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * UsernamePasswordAuthenticationFilter的验证过程如下：

     1. 首先过滤器会调用自身的attemptAuthentication方法，从request中取出authentication,
     authentication是在org.springframework.security.web.context.SecurityContextPersistenceFilter
     过滤器中通过捕获用户提交的登录表单中的内容生成的一个org.springframework.security.core.Authentication接口实例.

     2. 拿到authentication对象后，过滤器会调用ProviderManager类的authenticate方法，并传入该对象

     3.ProviderManager类的authenticate方法再调用自身的doAuthentication方法，在doAuthentication方法中
     会调用类中的List<AuthenticationProvider> providers集合中的各个AuthenticationProvider接口实现类中的
     authenticate(Authentication authentication)方法进行验证，由此可见，
     真正的验证逻辑是由各个各个AuthenticationProvider接口实现类来完成的,DaoAuthenticationProvider类是默认情况
     下注入的一个AuthenticationProvider接口实现类

     4.AuthenticationProvider接口通过UserDetailsService来获取用户信息
     **/

    //生成token 登录
    @Override
    public JwtAuthenticationResponse studentLogin(JwtAuthenticationRequest authenticationRequest) throws StudentException{
        try {
            StudentInfo studentInfo = studentDao.selectStudentInfoByName(authenticationRequest.getUsername());
            if(studentInfo == null)
                throw new StudentException("该用户不存在！");
            String username = authenticationRequest.getUsername();
            String password = authenticationRequest.getPassword();
            UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username,password);
            final Authentication authentication = authenticationManager.authenticate(upToken);
            //存放authentication到SecurityContextHolder   自定义用户认证
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final UserDetails userDetails = JwtUserFactory.createStudent(studentDao.selectStudentInfoByName(username));
            final String token = jwtTokenUtil.generateToken(userDetails);
            JwtAuthenticationResponse response = new JwtAuthenticationResponse(token,username);
            return response;
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
}
