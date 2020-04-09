package top.bluebirds37.scaffold.config.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.Duration;
import java.time.LocalDateTime;

@Configuration
@Slf4j
@Aspect
public class ExecuteTimeAspect {

    @Pointcut("execution(public * top.bluebirds37.scaffold.controller.*.*(..)))")
    public void executeTimeAspect() {

    }

    private final ThreadLocal<LocalDateTime> currentThread = new ThreadLocal<>();

    @Before("executeTimeAspect()")
    public void doBeforeGame() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert servletRequestAttributes != null;
        String uri = servletRequestAttributes.getRequest().getRequestURI();
        log.info("接收请求:{}", uri);
        currentThread.set(LocalDateTime.now());
    }

    @AfterReturning("executeTimeAspect()")
    public void doAfterReturningGame() {
        if (currentThread.get() != null) {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            assert servletRequestAttributes != null;
            String uri = servletRequestAttributes.getRequest().getRequestURI();
            log.info("请求地址:{},耗时:{}毫秒", uri, Duration.between(currentThread.get(), LocalDateTime.now()).toMillis());
            currentThread.remove();
        }
    }
}
