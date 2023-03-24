package com.ruoyi.framework.config.properties;

import com.ruoyi.common.annotation.Anonymous;
import org.apache.commons.lang3.RegExUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;
import java.util.regex.Pattern;

/**
 * 设置Anonymous注解允许匿名访问的url
 *
 * @author ruoyi
 */
@Configuration
public class PermitAllUrlProperties implements InitializingBean, ApplicationContextAware {
    private static final Pattern PATTERN = Pattern.compile("\\{(.*?)\\}");

    private ApplicationContext applicationContext;

    private List<String> urls = new ArrayList<>();

    public String ASTERISK = "*";

    // https://www.jianshu.com/p/4500b61384f8  2.5.8 以及 2.6.x以上 有区别，路径匹配策略方式不一样
    // 2.5.8版本用的路径匹配策略是 AntPathMatcher  而 Spring Boot 2.6.x版本以上的默认匹配策略是 path-pattern-matcher
    // 2.5.8版本 info.getPatternsCondition() 有值， info.getPathPatternsCondition()为空，2.6.x则是相反
    // 升级版本2.6.x后，swagger路径有变也是这个原因 可参考这个文章解决 https://www.jb51.net/article/244317.htm
    @Override
    public void afterPropertiesSet() {
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();

        map.keySet().forEach(info -> {
            HandlerMethod handlerMethod = map.get(info);

            // 获取方法上边的注解 替代path variable 为 *
            Anonymous method = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), Anonymous.class);
            Optional.ofNullable(method).ifPresent(anonymous -> Objects.requireNonNull(info.getPathPatternsCondition().getPatterns())
                    .forEach(url -> urls.add(RegExUtils.replaceAll(url.getPatternString(), PATTERN, ASTERISK))));

            // 获取类上边的注解, 替代path variable 为 *
            Anonymous controller = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), Anonymous.class);
            Optional.ofNullable(controller).ifPresent(anonymous -> Objects.requireNonNull(info.getPathPatternsCondition().getPatterns())
                    .forEach(url -> urls.add(RegExUtils.replaceAll(url.getPatternString(), PATTERN, ASTERISK))));
        });
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.applicationContext = context;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
