package com.ci123.workflow.azkaban.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Copyright (c) 2018-2028 Corp-ci All Rights Reserved
 * <p>
 * Project: workflow
 * Package: com.ci123.workflow.conifg
 * Version: 1.0
 * <p>
 * Created by SunYang on 2019/10/21 16:36
 */
@Configuration
public class SwaggerConfig {
    // 接口版本号
    private final String version = "1.0";
    // 接口大标题
    private final String title = "任务调度框架API接口";
    // 具体的描述
    private final String description = "任务调度框架API接口调用测试，Azkaban/Oozie/AirFlow";
    // 服务说明url
    private final String termsOfServiceUrl = "http://www.kingeid.com";
    // licence
    private final String license = "MIT";
    // licnce url
    private final String licenseUrl = "https://mit-license.org/";
    // 接口作者联系方式
    private final Contact contact = new Contact("SunYang", "https://github.com/sun-iot/workflow", "sunyang.iot@gmail.com");

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ci123.workflow.azkaban.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("任务调度框架API接口")
                .description("简单优雅的restfun风格")
                .termsOfServiceUrl("https://github.com/sun-iot/workflow-azkaban")
                .version("1.0")
                .contact(contact)
                .build();
    }


}
