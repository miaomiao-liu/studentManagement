package cn.edu.swpu.cins.studentManagement.service.Impl;

import cn.edu.swpu.cins.studentManagement.entity.persistence.TeacherInfo;
import cn.edu.swpu.cins.studentManagement.entity.view.FindPassword;
import cn.edu.swpu.cins.studentManagement.entity.view.JwtAuthenticationRequest;
import cn.edu.swpu.cins.studentManagement.entity.view.JwtAuthenticationResponse;
import cn.edu.swpu.cins.studentManagement.entity.view.JwtUserFactory;
import cn.edu.swpu.cins.studentManagement.enums.RegisterEnum;
import cn.edu.swpu.cins.studentManagement.enums.VerifyEnum;
import cn.edu.swpu.cins.studentManagement.service.AuthService;
import cn.edu.swpu.cins.studentManagement.util.JwtTokenUtil;
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
import cn.edu.swpu.cins.studentManagement.dao.StudentDao;
import cn.edu.swpu.cins.studentManagement.dao.TeacherDao;
import cn.edu.swpu.cins.studentManagement.entity.persistence.StudentInfo;
import cn.edu.swpu.cins.studentManagement.entity.persistence.TeacherInfo;
import cn.edu.swpu.cins.studentManagement.enums.RegisterEnum;
import cn.edu.swpu.cins.studentManagement.enums.VerifyEnum;
import cn.edu.swpu.cins.studentManagement.exception.StudentException;
import cn.edu.swpu.cins.studentManagement.exception.TeacherException;
import cn.edu.swpu.cins.studentManagement.service.AuthService;
import cn.edu.swpu.cins.studentManagement.service.MailService;
import cn.edu.swpu.cins.studentManagement.util.JwtTokenUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by miaomiao on 17-9-2.
 */
@Service
public class AuthServiceImpl implements AuthService {

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
            map.put("msgname", VerifyEnum.EMPTY_USERNAME);
            return map;
        }
        if(StringUtils.isEmpty(mail)){
            map.put("msgmail",VerifyEnum.EMPTY_EMAIL);
            return map;
        }
        if(studentDao.selectStudentInfoByName(username) != null || teacherDao.selectTeacherInfoByName(username) != null){
            map.put("msgname",VerifyEnum.REPEAT_USERNAME);
            return map;
        }
        if(studentDao.selectStudentInfoByEmail(mail) != null || teacherDao.selectTeacherInfoByEmail(mail) != null){
            map.put("msgmail",VerifyEnum.REPEAT_EMAIL);
            return map;
        }
        String verifyCode = mailService.sendRegisterMail(username,mail);
        return map;
    }

    @Override
    public Map<String, Object> getVerifyCodeForUpdatePwd(String username,String mail) {
        Map<String,Object> map = new HashMap<String,Object>();
        if(StringUtils.isEmpty(username)){
            map.put("msgname",VerifyEnum.EMPTY_USERNAME);
            return map;
        }
        if(StringUtils.isEmpty(mail)){
            map.put("msgmail",VerifyEnum.EMPTY_EMAIL);
            return map;
        }
        if(studentDao.selectStudentInfoByName(username) != null){
            if (!mail.equals(studentDao.selectStudentInfoByName(username).getEmail())){
                map.put("msgmail",VerifyEnum.MATE_USERNAME_MAIL);
                return map;
            }
            String verifyCode = mailService.sendFindPwdMail(mail);
            return map;
        }else if (teacherDao.selectTeacherInfoByName(username) != null) {
            if (!mail.equals(teacherDao.selectTeacherInfoByName(username))) {
                map.put("msgmail", VerifyEnum.MATE_USERNAME_MAIL);
                return map;
            }
            String verifyCode = mailService.sendFindPwdMail(mail);
            return map;
        }else {
            map.put("msgusername", VerifyEnum.NO_USER);
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
            map.put("msgpwd", RegisterEnum.EMPTY_PWD.getMsg());
            return map;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        studentInfo.setPassword(encoder.encode(password));
        int num = studentDao.addStudentInfo(studentInfo);
        if(num !=1) {
            map.put("msg",RegisterEnum.REGISTER_FAIL.getMsg());
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
    public Map<String,Object> registerTeacher(TeacherInfo teacherInfo) throws TeacherException {
            Map<String,Object> map = new HashMap<String,Object>();
            final String password = teacherInfo.getPassword();
            if(StringUtils.isEmpty(password)){
                map.put("msgpwd",RegisterEnum.EMPTY_PWD.getMsg());
            }
            BCryptPasswordEncoder encoder  = new BCryptPasswordEncoder();
            teacherInfo.setPassword(encoder.encode(password));
            int num = teacherDao.addTeacherInfo(teacherInfo);
            if(num != 1) {
                map.put("msg",RegisterEnum.REGISTER_FAIL.getMsg());
            }
            return map;
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
