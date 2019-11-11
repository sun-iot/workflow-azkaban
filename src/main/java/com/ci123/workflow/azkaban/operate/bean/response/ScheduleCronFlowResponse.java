package com.ci123.workflow.azkaban.operate.bean.response;

import com.alibaba.fastjson.JSON;
import com.ci123.workflow.azkaban.operate.bean.response.base.BaseResponse;

/**
 * Created by shirukai on 2019-06-04 10:09
 * 定时调度Flow响应
 */
public class ScheduleCronFlowResponse extends BaseResponse {
    private String scheduleId;

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String toString(){
        return JSON.toJSONString(this);
    }
}
