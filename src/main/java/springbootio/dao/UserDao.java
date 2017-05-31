package springbootio.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import springbootio.entity.User;

import java.util.List;

/**
 * Created by muyi on 17-4-4.
 */
@Repository
@Mapper
public interface UserDao {

    public List<User> queryAll();

    public User queryByName(String username);

    public int addUser(User user);



}
