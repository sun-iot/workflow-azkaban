package com.ci123.workflow.azkaban.operate.bean.response;

import com.alibaba.fastjson.JSON;
import com.ci123.workflow.azkaban.operate.bean.response.base.BaseResponse;

/**
 * Created by shirukai on 2019-06-04 10:13
 * 查询定时任务响应
 */
public class FetchScheduleResponse extends BaseResponse {
    private Schedule schedule;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}

