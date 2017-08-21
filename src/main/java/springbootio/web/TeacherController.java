package springbootio.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springbootio.dao.GradeDao;
import springbootio.dao.TeacherDao;
import springbootio.entity.persistence.GradeDetail;
import springbootio.entity.persistence.TeacherDetail;
import springbootio.entity.persistence.TeacherInfo;
import springbootio.entity.view.JwtAuthenticationRequest;
import springbootio.entity.view.JwtAuthenticationResponse;
import springbootio.entity.view.ResultData;
import springbootio.service.TeacherService;

/**
 * Created by miaomiao on 17-7-8.
 */

@RestController
@RequestMapping("/springbootio")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private GradeDao gradeDao;

    //老师查询个人信息
    @PostMapping(value = "/queryTeacher/id")
    public ResultData queryTeacher(@RequestParam int id) {
        try {
            if(teacherService.queryTeacherDetailById(id)==null)
                return new ResultData(false,"对不起！没有该帐号的老师！");
            return new ResultData(true,teacherService.queryTeacherDetailById(id));
        }catch(Exception e){
            return new ResultData(false,e.getMessage());
        }

    }

    @PostMapping(value = "/queryTeacher/name")
    public ResultData queryTeacher(@RequestParam String name) {
        try {
            if(teacherService.queryTeacherDetailByName(name)==null)
                return new ResultData(false,"对不起！没有为该名字的老师！");
            return new ResultData(true,teacherService.queryTeacherDetailByName(name));
        }catch(Exception e){
            return new ResultData(false,e.getMessage());
        }

    }


    //老师填写个人信息
    @PostMapping(value = "/addTeacher/detail")
    public ResultData registerTeachet(@RequestBody TeacherDetail teacherDetail) {
        try {
            if (teacherService.queryTeacherInfo(teacherDetail.getId()) != null) {
                int num = teacherDao.addTeacher(teacherDetail);
                if (num == 1)
                    return new ResultData(true,"添加信息成功！");
                return new ResultData(false,"添加信息失败！");
            }
            return new ResultData(false,"对不起！没有你要添加信息的帐号！请先注册！");
        }catch(Exception e){
            return new ResultData(false,e.getMessage());
        }

    }

    //老师更新个人信息
    @RequestMapping(value ="/updateTeacher",method = RequestMethod.POST)
    public ResultData updateTeacherDetail(@RequestBody TeacherDetail teacherDetail) {
            if (teacherDao.queryTeacherByName(teacherDetail.getUsername()) != null) {
                int num = teacherDao.updateTeacher(teacherDetail);
                if(num==1)
                    return new ResultData(true,"你的信息已更新成功！");
                return new ResultData(false,"你的信息更新失败！");
            }
            return new ResultData(false,"对不起！没有你所要更新的用户！");

    }

    //老师上传学生成绩
    @PostMapping(value = "/addGrade")
    public ResultData teacherAddStudentGrade(@RequestBody GradeDetail gradeDetail){
        if(gradeDao.queryGrade(gradeDetail.getStudentNumber()) == null){
            int num = gradeDao.addGrade(gradeDetail);
            if(num==1)
                return new ResultData(true,"添加学生成绩成功！");
            return new ResultData(false,"添加学生成绩失败！");
        }
        return new ResultData(false,"学生成绩已存在！不能重复添加！");
    }

    //老师更新学生成绩
    @PostMapping(value = "/teacherUpdateGrade")
    public ResultData teacherUpdateStudentGrade(@RequestBody GradeDetail gradeDetail){
        if(gradeDao.queryGrade(gradeDetail.getStudentNumber()) != null) {
            int num = gradeDao.updateGeade(gradeDetail);
            if(num==1)
                return new ResultData(true,"更新学生成绩成功！");
            return new ResultData(false,"更新学生成绩失败！");
        }
        return new ResultData(false,"更新成绩的学生不存在！");
    }

    //老师查询学生成绩
    @GetMapping(value = "/teacherQueryGrade")
    public ResultData teacherQueryStudentGrade(@RequestParam int studentNumber){
        if(gradeDao.queryGrade(studentNumber) !=null)
            return new ResultData(true,gradeDao.queryGrade(studentNumber));
        return new ResultData(false,"你所查询学生的学号不存在！");
    }

    //老师注册
    @PostMapping("/registerTeacher")
    public ResultData registerTeacherInfo(@RequestBody TeacherInfo teacherInfo){
        try {
            if(teacherService.queryTeacherDetailByName(teacherInfo.getUsername()) != null) {
                return new ResultData(false, "对不起！该用户名已经被注册！");
            }
            if(teacherDao.selectTeacherInfoByEmail(teacherInfo.getEmail()) != null){
                return new ResultData(false,"对不起！该邮箱已被注册！");
            }
            int num = teacherService.registerTeacher(teacherInfo);
            if(num == 1) {
                teacherInfo = teacherService.queryTeacherInfoByName(teacherInfo.getUsername());
                return new ResultData(true, teacherInfo.getId(),"注册成功！");
            }
            return new ResultData(true,"注册失败！");
        }catch(Exception e){
            return new ResultData(false,e.getMessage());
        }

    }


    //老师登录
    @PostMapping("/teacherLogin")
    public ResultData teacherLogin(@RequestBody JwtAuthenticationRequest authenticationRequest){
        try {
            TeacherInfo teacherInfo = teacherDao.selectTeacherInfoByName(authenticationRequest.getUsername());
            if(teacherInfo == null)
                return new ResultData(false,"该用户不存在!");
            String username = teacherInfo.getUsername();
            String token = teacherService.teacherLogin(authenticationRequest.getUsername(),authenticationRequest.getPassword());
            return new ResultData(true,new JwtAuthenticationRequest(token,username),"登录成功！");

        }catch(Exception e){
            return new ResultData(false,e.getMessage());
        }

    }

}
