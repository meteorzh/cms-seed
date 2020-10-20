package com.github.wenzhencn.cmsseed.system.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * 系统接口注册器<br />
 * 应用启动的时候将所有接口注册到数据库
 * @author wenzhen
 * @since 2020/10/20 16:54
 */
@Slf4j
@Component
public class ApiRegister implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> methods = mapping.getHandlerMethods();
        log.info("register apis");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
