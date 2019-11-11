package com.ci123.workflow.azkaban.operate.config;

import com.ci123.workflow.azkaban.operate.service.api.AzkabanAPI;
import com.ci123.workflow.azkaban.operate.service.proxy.AzkabanApiProxyBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright (c) 2018-2028 Corp-ci All Rights Reserved
 * <p>
 * Project: workflow
 * Package: com.ci123.workflow.conifg.az
 * Version: 1.0
 * <p>
 * Created by SunYang on 2019/10/22 16:00
 */
@Configuration
public class AzkabanConfig {
    @Value("${azkaban.url}")
    private String uri;

    @Value("${azkaban.username}")
    private String username;

    @Value("${azkaban.password}")
    private String password;

    @Bean
    public AzkabanAPI azkabanApi() {
        return AzkabanApiProxyBuilder.create()
                .setUri(uri)
                .setUsername(username)
                .setPassword(password)
                .build();
    }
}
