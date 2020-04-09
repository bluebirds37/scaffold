package top.bluebirds37.scaffold.config.listener;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import top.bluebirds37.scaffold.config.properties.SystemProperties;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;


@Configuration
@Slf4j
public class CustomerOldFileListener implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    private SystemProperties systemProperties;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.debug("系统配置:{}", JSONObject.toJSONString(systemProperties));
    }
}
