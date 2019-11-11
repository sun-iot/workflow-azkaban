package com.ci123.workflow.azkaban.operate.bean.module;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Copyright (c) 2018-2028 Corp-ci All Rights Reserved
 * <p>
 * Project: workflow
 * Package: com.ci123.workflow.azkaban.bean.module.az
 * Version: 1.0
 * <p>
 * Created by SunYang on 2019/10/22 16:12
 */
@Data
@Api(value = "Azkaban Project Model" , produces = "application/json")
public class Project {
    @ApiModelProperty(value = "the project name which you operate" , required = true)
    public String name ;
    @ApiModelProperty(value = "the description of the project" , required = true)
    public String description ;
}
