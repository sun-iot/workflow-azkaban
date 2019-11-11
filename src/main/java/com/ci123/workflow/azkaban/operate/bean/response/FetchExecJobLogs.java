package com.ci123.workflow.azkaban.operate.bean.response;

import com.alibaba.fastjson.JSON;
import com.ci123.workflow.azkaban.operate.bean.response.base.BaseResponse;

/**
 * Created by shirukai on 2019-06-04 09:40
 * 查询执行Job的日志
 */
public class FetchExecJobLogs extends BaseResponse {
    private String data;
    private Integer offset;
    private Integer length;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
