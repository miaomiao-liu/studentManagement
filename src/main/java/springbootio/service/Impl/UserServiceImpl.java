package springbootio.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbootio.dao.UserDao;
import springbootio.entity.User;
import springbootio.service.UserService;

import java.util.List;

/**
 * Created by muyi on 17-4-4.
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDao userDao;

    @Override
    public List<User> queryAll() {

        return userDao.queryAll();
    }


}
