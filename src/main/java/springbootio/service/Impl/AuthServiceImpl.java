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
import org.springframework.util.StringUtils;
import springbootio.dao.StudentDao;
import springbootio.dao.TeacherDao;
import springbootio.entity.persistence.StudentInfo;
import springbootio.entity.persistence.TeacherInfo;
import springbootio.entity.view.*;
import springbootio.exception.StudentException;
import springbootio.exception.TeacherException;
import springbootio.service.AuthService;
import springbootio.service.MailService;
import springbootio.util.JwtTokenUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by miaomiao on 17-9-2.
 */
@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    MailService mailService;

    @Override
    public Map<String,Object> getVerifyCodeForRegister(String username, String mail) throws Exception {
        Map<String,Object> map = new HashMap<String,Object>();
        if(StringUtils.isEmpty(username)){
            map.put("msgname","用户名不能为空");
            return map;
        }
        if(StringUtils.isEmpty(mail)){
            map.put("msgpwd","邮箱不能为空");
            return map;
        }
        if(studentDao.selectStudentInfoByName(username) != null || teacherDao.selectTeacherInfoByName(username) != null){
            map.put("msgname","该用户名已被注册");
            return map;
        }
        if(studentDao.selectStudentInfoByEmail(mail) != null || teacherDao.selectTeacherInfoByEmail(mail) != null){
            map.put("msgmail","该邮箱已被注册");
            return map;
        }
        String verifyCode = mailService.sendRegisterMail(username,mail);
        return map;
    }

    @Override
    public Map<String, Object> getVerifyCodeForUpdatePwd(String username,String mail) throws Exception {
        Map<String,Object> map = new HashMap<String,Object>();
        if(studentDao.selectStudentInfoByName(username) != null){
            if (!mail.equals(studentDao.selectStudentInfoByName(username).getEmail())){
                map.put("msgmail","用户名与邮箱不匹配");
                return map;
            }
            String verifyCode = mailService.sendFindPwdMail(mail);
            return map;
        }else if (teacherDao.selectTeacherInfoByName(username) != null) {
            if (!mail.equals(teacherDao.selectTeacherInfoByName(username))) {
                map.put("msgmail", "用户名与邮箱不匹配");
                return map;
            }
            String verifyCode = mailService.sendFindPwdMail(mail);
            return map;
        }else {
            map.put("msgusername", "用户名不存在");
            return map;
        }
    }

    @Override
    public int FindPassword(FindPassword findPassword) throws Exception {
        Map<String,Object> map = new HashMap<String,Object>();
        String username = findPassword.getUsername();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode(findPassword.getNewPassword());
        if(studentDao.selectStudentInfoByName(username) != null){
            return studentDao.updatePassword(password,username);
        }else{
            return teacherDao.updatePassword(password,username);
        }
    }


    @Override
    public Map<String,Object> registerStudent(StudentInfo studentInfo) throws StudentException {
        Map<String,Object> map = new HashMap<String,Object>();
        final String password = studentInfo.getPassword();
        if(StringUtils.isEmpty(password)){
            map.put("msgpwd","密码不能为空");
            return map;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        studentInfo.setPassword(encoder.encode(password));
        int num = studentDao.addStudentInfo(studentInfo);
        if(num !=1) {
            map.put("msg","注册失败");
        }
        return map;
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


    //老师添加注册信息
    @Override
    public void registerTeacher(TeacherInfo teacherInfo) throws TeacherException {
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
}
