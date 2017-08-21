package springbootio.service.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import springbootio.dao.TeacherDao;
import springbootio.entity.persistence.GradeDetail;
import springbootio.entity.persistence.TeacherDetail;
import springbootio.entity.persistence.TeacherInfo;
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
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    public TeacherDetail queryTeacherDetailById(int id) throws TeacherException{
        try {
            return teacherDao.queryTeacherById(id);
        }catch (Exception e){
            throw  new TeacherException("数据库异常！");
        }

    }

    @Override
    public TeacherDetail queryTeacherDetailByName(String username) throws TeacherException{
        try {
            return teacherDao.queryTeacherByName(username);
        }catch(Exception e){
            throw new TeacherException("数据库异常！");
        }

    }

    @Override
    public int addTeacherDetail(TeacherDetail teacherDetail) throws TeacherException {
        try {
            return teacherDao.addTeacher(teacherDetail);
        }catch(Exception e){
            throw new TeacherException("数据库异常！");
        }
    }


    @Override
    public int updateTeacherDetail(TeacherDetail teacherDetail) throws TeacherException{
        try {
            return teacherDao.updateTeacher(teacherDetail);
        }catch(Exception e){
            throw new TeacherException("数据库异常！");
        }

    }

    //老师添加注册信息
    @Override
    public int registerTeacher(TeacherInfo teacherInfo) throws TeacherException{
        try {

            BCryptPasswordEncoder encoder  = new BCryptPasswordEncoder();
            final String rawPassword = teacherInfo.getPassword();
            teacherInfo.setPassword(encoder.encode(rawPassword));
            return teacherDao.addTeacherInfo(teacherInfo);
        }catch(Exception e){
            throw new TeacherException("数据库异常！");
        }
    }

    @Override
    public TeacherInfo queryTeacherInfoByName(String username)throws TeacherException{
        try {
            return teacherDao.selectTeacherInfoByName(username);
        }catch (Exception e){
            throw new TeacherException("数据库异常！");
        }

    }


    @Override
    public TeacherInfo queryTeacherInfo(int id)throws TeacherException{
        try {
            return teacherDao.queryTeacherInfoById(id);
        }catch (Exception e){
            throw new TeacherException("数据库异常！");
        }

    }

    @Override
    public String teacherLogin(String username, String password) throws TeacherException{
        try{
            UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username,password);
            final Authentication authentication = authenticationManager.authenticate(upToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final UserDetails userDetails = JwtUserFactory.createTeacher(teacherDao.selectTeacherInfoByName(username));
            final String token = jwtTokenUtil.generateToken(userDetails);
            return token;
        }catch (Exception e){
            throw new TeacherException("获取token异常！");
        }

    }
}
