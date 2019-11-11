package com.ci123.workflow.azkaban.operate.bean.response;

import com.alibaba.fastjson.JSON;
import com.ci123.workflow.azkaban.operate.bean.response.base.BaseResponse;

import java.util.List;

/**
 * Copyright (c) 2018-2028 Corp-ci All Rights Reserved
 * <p>
 * Project: workflow
 * Package: com.ci123.workflow.azkaban.bean.response.azkaban
 * Version: 1.0
 * <p>
 * Created by SunYang on 2019/10/25 15:08
 */
public class FetchRunningExecutionFlowResponse extends BaseResponse {
    public List<Integer> execIds ;

    public List<Integer> getExecIds() {
        return execIds;
    }

    public void setExecIds(List<Integer> execIds) {
        this.execIds = execIds;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this) ;
    }
}
