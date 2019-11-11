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
 * Created by SunYang on 2019/10/25 16:11
 */
@Data
@Api(value = "fetchSchedule" , produces = "application/json")
public class FetchSchedule {
    @ApiModelProperty(value = "project name" , required = true)
    public Integer projectId;
    @ApiModelProperty(value = "flow name" , required = true)
    public Integer flowId ;
}
