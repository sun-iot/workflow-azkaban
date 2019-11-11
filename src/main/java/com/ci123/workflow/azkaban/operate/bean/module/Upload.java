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
 * Created by SunYang on 2019/10/24 18:13
 */
@Data
@Api(value = "the zip information" , produces = "application/json")
public class Upload {
    @ApiModelProperty(value = "the project name")
    private String project ;
    @ApiModelProperty(value = "the path of the zip")
    private String file ;
}
