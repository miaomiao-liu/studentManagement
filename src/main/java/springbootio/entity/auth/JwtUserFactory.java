package springbootio.entity.auth;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import springbootio.entity.Admin;
import springbootio.entity.User;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;


public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser createUser(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(ToGrantedAuthorities(user.getRole())),
                new Date(user.getLastPasswordResetDate())
                );
    }
    public static JwtUser createAdmin(Admin user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(ToGrantedAuthorities(user.getRole())),
                new Date(user.getLastPasswordResetDate())
        );
    }


    private static GrantedAuthority ToGrantedAuthorities(String authorities) {

        return new SimpleGrantedAuthority(authorities);

    }
}
