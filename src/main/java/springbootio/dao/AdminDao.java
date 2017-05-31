package springbootio.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import springbootio.entity.Admin;

/**
 * Created by muyi on 17-4-18.
 */
@Mapper
@Repository
public interface AdminDao {

    public int addAdmin(Admin admin);

    public Admin queryByName(String username);

}
