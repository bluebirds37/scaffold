package top.bluebirds37.scaffold.config.authority;


import top.bluebirds37.scaffold.config.exception.AuthenticationException;
import top.bluebirds37.scaffold.pojo.po.system.User;
import top.bluebirds37.scaffold.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * @author 认证拦截器
 * description
 * createDate     2019/4/15 14:54
 * updateRemark
 * version        1.0
 */

@Slf4j
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Resource
    private AuthenticationProperties authenticationProperties;
    @Resource
    private UserRepository userRepository;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (request.getMethod().equals(HttpMethod.OPTIONS.name())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        String token = request.getHeader(authenticationProperties.getHeaderName());
        //判断token是否存在
        if (StringUtils.isBlank(token)) {
            token = request.getParameter(authenticationProperties.getHeaderName());
            if (StringUtils.isBlank(token)) {
                throw new AuthenticationException("请登录");
            }
        }
        //认证token是否合法
        Integer userId = JWTUtil.getUserId(token);
        if (userId == null) {
            throw new AuthenticationException("用户不存在");
        }
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            throw new AuthenticationException("用户不存在");
        }
        /*String redisToken = redisTemplate.opsForValue().get(authenticationProperties.getTokenKey() + userId);
        if (StringUtils.isBlank(redisToken)) {
            throw new AuthenticationException("登录超时");
        }
        if (!redisToken.equals(token)) {
            throw new AuthenticationException("已在其他地方登录");
        }*/
        if (!JWTUtil.verify(token)) {
            throw new AuthenticationException("请登录");
        }
        return true;
    }


}
