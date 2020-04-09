package top.bluebirds37.scaffold.config.authority;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mwh
 * description    登录设置项
 * createDate     2019/4/11 10:26
 * updateRemark
 * version        1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "blue.auth")
public class AuthenticationProperties {
    private Long expireTime;

    private String headerName = "Authentication";
    /**
     * redis获取当前用户 key
     */
    private String loginUserKey = "LOGIN:USERS:";

    private String tokenKey;

    private String defaultNickname;

    List<String> permitUrlList = new ArrayList<>();
}
