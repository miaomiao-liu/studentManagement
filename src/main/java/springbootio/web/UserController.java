package springbootio.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springbootio.entity.User;
import springbootio.service.UserService;

import java.util.List;

/**
 * Created by muyi on 17-4-4.
 */
@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    public List<User> queryAll(){

        return userService.queryAll();
    }



}
