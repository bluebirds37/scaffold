package top.bluebirds37.scaffold.config.mvc;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.bluebirds37.scaffold.config.authority.AuthenticationInterceptor;
import top.bluebirds37.scaffold.config.authority.AuthenticationProperties;
import top.bluebirds37.scaffold.config.authority.AuthorityInterceptor;
import top.bluebirds37.scaffold.config.constant.SystemConstant;
import top.bluebirds37.scaffold.config.properties.SystemProperties;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Configuration
@Slf4j
public class WebMvcConfig implements WebMvcConfigurer {


    @Autowired
    private AuthenticationInterceptor jwtInterceptor;
    @Autowired
    private AuthorityInterceptor authorityInterceptor;
    @Autowired
    private AuthenticationProperties authenticationProperties;
    @Resource
    private SystemProperties systemProperties;

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

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        File file = new File("");
        String canonicalPath = "";
        try {
            canonicalPath = file.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("init download dir:{}", canonicalPath);
        registry
                .addResourceHandler(
                        StringUtils.join(
                                SystemConstant.URI_CONCAT_SYMBOL,
                                systemProperties.getFileStoreDir(),
                                SystemConstant.URI_CONCAT_SYMBOL,
                                SystemConstant.URI_FULL_PATTERN_SYMBOL
                        )
                ).addResourceLocations(
                StringUtils.join(
                        SystemConstant.WEB_MVC_STATIC_RESOURCE_MAPPING_PREFIX,
                        canonicalPath,
                        SystemConstant.URI_CONCAT_SYMBOL,
                        systemProperties.getFileStoreDir(),
                        SystemConstant.URI_CONCAT_SYMBOL
                ));
    }

}
