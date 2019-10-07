package top.bluebirds37.scaffold.config.authority;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private CrossInterceptor crossInterceptor;
    @Autowired
    private AuthenticationInterceptor jwtInterceptor;
    @Autowired
    private AuthorityInterceptor authorityInterceptor;
    @Autowired
    private AuthenticationProperties authenticationProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //跨域
        registry.addInterceptor(crossInterceptor)
                .addPathPatterns("/**")
                .order(0);
        //认证
        registry.addInterceptor(jwtInterceptor).order(1)
                .addPathPatterns("/**")
                .excludePathPatterns(authenticationProperties.getPermitUrlList());
        //鉴权
        registry.addInterceptor(authorityInterceptor).order(2)
                .addPathPatterns("/**")
                .excludePathPatterns(authenticationProperties.getPermitUrlList());
    }

}
