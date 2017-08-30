package springbootio.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import springbootio.entity.persistence.StudentGrade;

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
