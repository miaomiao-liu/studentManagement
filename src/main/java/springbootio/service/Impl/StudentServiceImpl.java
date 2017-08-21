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
import springbootio.dao.StudentDao;
import springbootio.entity.persistence.GradeDetail;
import springbootio.entity.persistence.StudentDetail;
import springbootio.entity.persistence.StudentInfo;
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
    private UserDetailsService userDetailsService;

    @Override
    public StudentDetail queryStudentDetailById(int id) throws StudentException  {
        try {
            return studentDao.queryStudentById(id);
        }catch (Exception e){
            throw new StudentException("数据库异常！");
        }
    }

    @Override
    public StudentDetail queryStudentDetailByName(String username) throws StudentException{
        try {
            return studentDao.queryStudentByName(username);
        }catch (Exception e){
            throw new StudentException("数据库异常！");
        }

    }

    @Override
    public int addStudentDetail(StudentDetail studentDetail) throws StudentException{
        try {
            return studentDao.addStudent(studentDetail);
        }catch (Exception e){
            throw new StudentException("数据库异常！");
        }

    }

    @Override
    public int updateStudentDetail(StudentDetail studentDetail) throws StudentException{
        try {
            return studentDao.updateStudent(studentDetail);
        }catch (Exception e){
            throw new StudentException("数据库异常！");
        }

    }

    @Override
    public int registerStudent(StudentInfo studentInfo) throws StudentException{
        try{
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            final String rawPassword = studentInfo.getPassword();
            studentInfo.setPassword(encoder.encode(rawPassword));
            return studentDao.addStudentInfo(studentInfo);
        }catch (Exception e){
            throw new StudentException("数据库异常！");
        }

    }

    @Override
    public StudentInfo queryStudentInfo(String username) throws StudentException {
        try {
            return studentDao.selectStudentInfoByName(username);
        }catch (Exception e){
            throw new StudentException("数据库异常！");
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
    public String studentLogin(String username, String password) throws StudentException{
        try {
            UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);

            final Authentication authentication = authenticationManager.authenticate(upToken);
            //存放authentication到SecurityContextHolder   自定义用户认证
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final UserDetails userDetails = JwtUserFactory.createStudent(studentDao.selectStudentInfoByName(username));
            final String token = jwtTokenUtil.generateToken(userDetails);
            return token;
        }catch (Exception e){
            throw new StudentException("获取token异常！");
        }

    }
}
