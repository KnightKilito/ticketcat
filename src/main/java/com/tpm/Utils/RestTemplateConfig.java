package com.tpm.Utils;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 注入restTemplate用于http请求
 */
@Configuration
public class RestTemplateConfig {

    @Resource
    private RestTemplateBuilder templateBuilder;

    @Bean
    public RestTemplate restTemplate(){

        return templateBuilder.build();
    }

}
