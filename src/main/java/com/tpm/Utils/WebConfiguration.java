package com.tpm.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * 跨域请求支持/token拦截
 * tip:只能写在一个配置类里
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private TokenInterceptor tokenInterceptor;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //构造方法
    public WebConfiguration(TokenInterceptor tokenInterceptor){
        this.tokenInterceptor = tokenInterceptor;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 允许跨域访问资源定义： /** 所有资源
        registry.addMapping("/**")
                // 允许发送Cookie
                .allowCredentials(true)
                .allowedHeaders("*")
                .maxAge(3600)
                .allowedMethods("GET","POST","DELETE","PUT")
                .allowedOriginPatterns("*");
        logger.info("====解决跨域问题成功！！！====");

    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer){
        configurer.setTaskExecutor(new ConcurrentTaskExecutor(Executors.newFixedThreadPool(3)));
        configurer.setDefaultTimeout(30000);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        List<String> excludePath = new ArrayList<>();
        // 排除拦截
//        excludePath.add("/**");//全部排除拦截，仅用于测试
//        excludePath.add("/tokenUser");
        excludePath.add("/user");  //登录
        excludePath.add("/user/login");  //忘记密码
//        excludePath.add("/user/register");  //用户注册
        excludePath.add("/cinema");//查看影院列表
        excludePath.add("/cinema/**");//查看影院列表
        excludePath.add("/movie");//查看电影列表
        excludePath.add("/movie/**");//查看电影列表
        excludePath.add("/session");
        excludePath.add("/session/**");
        excludePath.add("/sessionseat");
        excludePath.add("/sessionseat/**");
        excludePath.add("/moviecomment");
        excludePath.add("/moviecomment/**");
        excludePath.add("/collection");
        excludePath.add("/collection/**");
        excludePath.add("/hall");
        excludePath.add("/hall/**");
        excludePath.add("/wxUser");
        excludePath.add("/wxUser/**");
        excludePath.add("/ticket");
        excludePath.add("/ticket/**");
        excludePath.add("/alipay");
        excludePath.add("/alipay/**");
        excludePath.add("/webjars/**");
        excludePath.add("/static/**");  //静态资源
        excludePath.add("/assets/**");  //静态资源
        logger.info("====通过登录拦截器====");
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePath);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
