package top.bluebirds37.scaffold.config.authority;


import com.alibaba.fastjson.JSONObject;
import top.bluebirds37.scaffold.config.exception.AuthenticationException;
import top.bluebirds37.scaffold.config.exception.AuthorityException;
import top.bluebirds37.scaffold.pojo.dto.system.UserDto;
import top.bluebirds37.scaffold.pojo.po.system.Role;
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
import java.util.Set;

/**
 * @author 鉴权拦截器
 * description
 * createDate     2019/4/15 14:54
 * updateRemark
 * version        1.0
 */

@Slf4j
@Component
public class AuthorityInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Resource
    private AuthenticationProperties authenticationProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (request.getMethod().equals(HttpMethod.OPTIONS.name())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        // 当前用户
        Integer loginUserId = LoginUtils.getLoginUserId();
        if (loginUserId == null) {
            throw new AuthenticationException("请登录");
        }
        String loginUserString = redisTemplate.opsForValue().get(StringUtils.join(authenticationProperties.getLoginUserKey() , loginUserId));
        if (StringUtils.isBlank(loginUserString)) {
            throw new AuthenticationException("请登录");
        }
        JSONObject jsonObject = JSONObject.parseObject(loginUserString);
        UserDto loginUserDto = JSONObject.toJavaObject(jsonObject, UserDto.class);
        Set<Role> roles = loginUserDto.getRoles();
        String requestUri = request.getRequestURI();
        boolean hasPermission = roles.stream().anyMatch(role -> role.getPermissions().stream().anyMatch(permission -> permission.getUrl().equals(requestUri)));
        // 权限列表
        if (!hasPermission) {
            throw new AuthorityException("权限不足");
        }
        return true;
    }
}
