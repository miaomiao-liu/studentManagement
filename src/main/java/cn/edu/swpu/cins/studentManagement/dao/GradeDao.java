package cn.edu.swpu.cins.studentManagement.dao;

import cn.edu.swpu.cins.studentManagement.entity.persistence.StudentGrade;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import cn.edu.swpu.cins.studentManagement.entity.persistence.StudentGrade;

/**
 *
 */
@Repository
@Mapper
public interface GradeDao {
    StudentGrade queryGrade(int studentNumber);

    int addGrade(StudentGrade gradeDetail);

    int updateGeade(StudentGrade gradeDetail);
}
