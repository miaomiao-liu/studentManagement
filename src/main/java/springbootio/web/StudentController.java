package springbootio.web;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springbootio.dao.GradeDao;
import springbootio.dao.StudentDao;
import springbootio.entity.persistence.GradeDetail;
import springbootio.entity.persistence.StudentDetail;
import springbootio.entity.persistence.StudentInfo;
import springbootio.entity.view.JwtAuthenticationRequest;
import springbootio.entity.view.JwtAuthenticationResponse;
import springbootio.entity.view.ResultData;
import springbootio.exception.StudentException;
import springbootio.service.StudentService;

/**
 * Created by miaomiao on 17-7-8.
 */
@RestController
@RequestMapping("/springbootio")
public class StudentController {

    private StudentService studentService;
    private StudentDao studentDao;
    private GradeDao gradeDao;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public StudentController(StudentService studentService,
                             StudentDao studentDao,
                             GradeDao gradeDao){
        this.studentService=studentService;
        this.studentDao=studentDao;
        this.gradeDao=gradeDao;

    }

    //根据id查询学生信息
    @GetMapping("/queryStudent/id")
    public ResultData queryStudentById(@RequestParam int id) {
        try {
            if(studentService.queryStudentDetailById(id)==null)
                return new ResultData(false,"对不起！没有该帐号的学生用户！");
            return new ResultData(true,studentService.queryStudentDetailById(id));
        }catch(Exception e){
            return new ResultData(false,e.getMessage());
        }

}

//根据姓名查询学生信息
    @GetMapping("/queryStudent/name")
    public ResultData queryStudentByName(@RequestParam String name) {
        try{
            if(studentService.queryStudentDetailByName(name)==null)
                return new ResultData(false,"对不起！没有为该用户名的学生用户！");
            return new ResultData(true,studentService.queryStudentDetailByName(name));
        }catch(Exception e){
            return new ResultData(false,e.getMessage());
        }

    }



//注册之后
//添加学生信息
@PostMapping(value = "/addStudent/detail")
    public ResultData registerStudent(@RequestBody StudentDetail studentDetail) {
        try {
            if(studentService.queryStudentInfo(studentDetail.getUsername())!=null) {
                int num=studentService.addStudentDetail(studentDetail);
                if(num==1)
                    return new ResultData(true,"添加信息成功！");
                return new ResultData(false,"添加信息失败！") ;
            }
            return new ResultData(false,"对不起！没有你要添加信息的帐号！请先注册！");
        }catch(Exception e){
            return new ResultData(false,e.getMessage());
        }



}

//学生更新信息
@PostMapping(value = "/updateStudent")
    public  ResultData updateStudentDetail(@RequestBody StudentDetail studentDetail) {
        try {
            if (studentService.queryStudentDetailById(studentDetail.getId()) != null) {
                int num = studentService.updateStudentDetail(studentDetail);
                if (num == 1)
                    return new ResultData(true, "更新信息成功！");
                return new ResultData(false, "更新信息失败！");
            }
            return new ResultData(false, "对不起！没有你所要更新的用户！");
        }catch(Exception e){
            return new ResultData(false,e.getMessage());
        }

}


//学生查询成绩
@GetMapping(value = "/studentQueryGrade")
    public ResultData queryStudentGradeByStudentNumber(@RequestParam int  studentNumber){
        if(gradeDao.queryGrade(studentNumber) !=null){
                return new ResultData(true,gradeDao.queryGrade(studentNumber));
        }
    return new ResultData(false,"对不起！查询成绩的学生学号不存在！");
    }


//学生添加注册信息 注册
    @PostMapping("/registerStudent")
    public ResultData registerStudent(@RequestBody StudentInfo studentInfo){
        try{
            if(studentService.queryStudentDetailByName(studentInfo.getUsername()) != null){
                return new ResultData(false,"对不起!该用户名已被注册！");
            }
            if(studentDao.selectStudentInfoByEmail(studentInfo.getEmail()) != null){
                return new ResultData(false,"对不起！该邮箱已被注册！");
            }
            int num = studentService.registerStudent(studentInfo);
            if(num ==1) {
                studentInfo = studentService.queryStudentInfo(studentInfo.getUsername());
                return new ResultData(true, studentInfo.getId(),"注册成功！");
            }
            return new ResultData(false, "对不起！注册失败！");
        }catch(Exception e){
            return new ResultData(false,e.getMessage());
        }
    }


    //学生登录
    @RequestMapping(value = "/studentLogin", method = RequestMethod.POST)
    public ResultData loginStudent(@RequestBody JwtAuthenticationRequest authenticationRequest){
        try {
            StudentInfo studentInfo = studentDao.selectStudentInfoByName(authenticationRequest.getUsername());
            if(studentInfo == null)
                return new ResultData(false,"该用户不存在！");
            String username = studentInfo.getUsername();
            String token = studentService.studentLogin(authenticationRequest.getUsername(),authenticationRequest.getPassword());
            return new ResultData(true,new JwtAuthenticationResponse(token,username),"登录成功！");
        }catch(Exception e){
            return new ResultData(false,e.getMessage());
        }

    }



}
