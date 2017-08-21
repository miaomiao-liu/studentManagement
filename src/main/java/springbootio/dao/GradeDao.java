package springbootio.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import springbootio.entity.persistence.GradeDetail;

/**
 *
 */
@Repository
@Mapper
public interface GradeDao {
    public GradeDetail queryGrade(int studentNumber);

    public int addGrade(GradeDetail gradeDetail);

    public int updateGeade(GradeDetail gradeDetail);
}
