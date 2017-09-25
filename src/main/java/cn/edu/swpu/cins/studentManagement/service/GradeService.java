package cn.edu.swpu.cins.studentManagement.service;

import cn.edu.swpu.cins.studentManagement.entity.persistence.StudentGrade;
import cn.edu.swpu.cins.studentManagement.entity.persistence.StudentGrade;

/**
 * Created by miaomiao on 17-7-9.
 */
public interface GradeService {
    public StudentGrade queryStudentGrade(int studentNumber);

    public int addStudentGrade(StudentGrade gradeDetail);

    public int updateStudentGrade(StudentGrade gradeDetail);
}
