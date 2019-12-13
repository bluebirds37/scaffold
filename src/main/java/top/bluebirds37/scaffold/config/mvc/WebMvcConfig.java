package top.bluebirds37.scaffold.config.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.bluebirds37.scaffold.config.authority.AuthenticationInterceptor;
import top.bluebirds37.scaffold.config.authority.AuthenticationProperties;
import top.bluebirds37.scaffold.config.authority.AuthorityInterceptor;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    @Autowired
    private AuthenticationInterceptor jwtInterceptor;
    @Autowired
    private AuthorityInterceptor authorityInterceptor;
    @Autowired
    private AuthenticationProperties authenticationProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
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
