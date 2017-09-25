package cn.edu.swpu.cins.studentManagement.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 *用户访问资源时，发生授权异常（AuthenticationException）或认证异AccessDeniedException），
 * ExceptionTranslationFilter通过调用AuthenticationEntryPoint的commence方法发起认证过程。
 * 如果ExceptionTranslationFilter接收到的是授权异常，
 * 并且当前认证过的票据不是匿名票据（AnonymousAuthenticationToken），
 * 将不会发起认证过程，而是交给AccessDeniedHandler处理（一般会直接提示用户拒绝访问）。
 */

//AuthenticationEntryPoint是用户提供凭证的入口，真正的认证是由过滤器来完成。

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        // This is invoked when user tries to access a secured REST resource without supplying any credentials
        // We should just send a 401 Unauthorized response because there is no 'login page' to redirect to
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "您没有通过验证，请重新登陆,并验证您的用户名和密码");
    }

//    public ResultData commence(HttpServletRequest request,
//                         HttpServletResponse response,
//                         AuthenticationException authException) throws IOException {
//        // This is invoked when user tries to access a secured REST resource without supplying any credentials
//        // We should just send a 401 Unauthorized response because there is no 'login page' to redirect to
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "您没有通过验证，请重新登陆的并验证您的用户名和密码");
//        return new ResultData(false,"您没有通过验证，请重新登陆的并验证您的用户名和密码");
//    }

}
