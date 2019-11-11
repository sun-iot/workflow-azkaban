package com.ci123.workflow.azkaban.operate.bean.response.base;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.Objects;

/**
 * Copyright (c) 2018-2028 Corp-ci All Rights Reserved
 * <p>
 * Project: workflow
 * Package: com.ci123.workflow.azkaban.bean.response.azkaban
 * Version: 1.0
 * <p>
 * Created by SunYang on 2019/10/21 17:15
 */
@Data
public class BaseResponse {
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";

    /**
     * 响应状态
     */
    private String status;
    /**
     * 错误类型(单纯为了映射Azkaban)
     */
    private String error;
    /**
     * 详细信息
     */
    private String message;

    /**
     * 更正信息
     */
    public void correction() {
        if (!ERROR.equals(this.status) && Objects.isNull(this.error)) {
            this.status = SUCCESS;
        } else {
            this.status = ERROR;
            if (Objects.isNull(this.error)) {
                this.error = this.message;
            } else if (Objects.isNull(this.message)) {
                this.message = this.error;
            }
        }
    }

    public String toString(){
        return JSON.toJSONString(this);
    }

}
