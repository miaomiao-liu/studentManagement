package springbootio.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import springbootio.dao.AdminDao;
import springbootio.dao.UserDao;
import springbootio.entity.Admin;
import springbootio.entity.User;
import springbootio.entity.auth.JwtUserFactory;

/**
 * Created by muyi on 17-4-18.
 */
@Service
public class JwtServiceDetailsImpl implements UserDetailsService {


    @Autowired
    private AdminDao adminDao;

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Admin admin = adminDao.queryByName(username);

        User user = userDao.queryByName(username);



        if (admin != null && admin.getRole().equalsIgnoreCase("ROLE_ADMIN"))
            return JwtUserFactory.createAdmin(admin);


        else if(user != null && user.getRole().equalsIgnoreCase("ROLE_USER"))
            return JwtUserFactory.createUser(user);

        else
            return null;



    }
}
