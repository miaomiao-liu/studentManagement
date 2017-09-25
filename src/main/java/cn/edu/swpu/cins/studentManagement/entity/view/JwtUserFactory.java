package cn.edu.swpu.cins.studentManagement.entity.view;


import cn.edu.swpu.cins.studentManagement.entity.persistence.TeacherInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import cn.edu.swpu.cins.studentManagement.entity.persistence.StudentInfo;
import cn.edu.swpu.cins.studentManagement.entity.persistence.TeacherInfo;

import java.util.Collections;
import java.util.Date;

/**
 * 将Info类转换为Jwt类型
 * Created by muyi on 17-4-18.
 */
public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser createStudent(StudentInfo studentinfo) {
        return new JwtUser(
                studentinfo.getId(),
                studentinfo.getUsername(),
                studentinfo.getPassword(),
                studentinfo.getEmail()
        );
    }

    public static JwtUser createTeacher(TeacherInfo teacherinfo) {
        return new JwtUser(
                teacherinfo.getId(),
                teacherinfo.getUsername(),
                teacherinfo.getPassword(),
                teacherinfo.getEmail());
    }
}
