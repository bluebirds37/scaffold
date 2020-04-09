package top.bluebirds37.scaffold.config.authority;

import top.bluebirds37.scaffold.config.exception.AuthenticationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


public class LoginUtils {


    public static Integer getLoginUserId() {
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            throw new AuthenticationException("请登录");
        }
        HttpServletRequest request =
                requestAttributes.getRequest();
        String authentication = request.getHeader("Authorization") == null ? (String) request.getServletContext().getAttribute("Authorization") : request.getHeader("Authorization");
        if (StringUtils.isBlank(authentication)) {
            throw new AuthenticationException("请登录");
        }
        return JWTUtil.getUserId(authentication);

    }

    public static String getToken() {
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            throw new AuthenticationException("请登录");
        }
        HttpServletRequest request =
                requestAttributes.getRequest();
        String authentication = request.getHeader("Authorization") == null ? (String) request.getServletContext().getAttribute("Authorization") : request.getHeader("Authorization");
        if (StringUtils.isBlank(authentication)) {
            throw new AuthenticationException("请登录");
        }
        JWTUtil.getUserId(authentication);
        return authentication;

    }
}
