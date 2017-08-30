package springbootio.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by miaomiao on 17-8-29.
 */
@Component
public class GetUsrName {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.header}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    public String AllProjects(HttpServletRequest request) {
        String authHeader = request.getHeader(this.tokenHeader);//Bearer + token
        final String authToken = authHeader.substring(tokenHead.length());//token
        return jwtTokenUtil.getUsernameFromToken(authToken);
    }
}
