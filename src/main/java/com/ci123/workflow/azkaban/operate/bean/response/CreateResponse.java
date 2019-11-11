package com.ci123.workflow.azkaban.operate.bean.response;

import com.alibaba.fastjson.JSON;
import com.ci123.workflow.azkaban.operate.bean.response.base.BaseResponse;


/**
 * Copyright (c) 2018-2028 Corp-ci All Rights Reserved
 * <p>
 * Project: workflow
 * Package: com.ci123.workflow.azkaban.bean.response.azkaban
 * Version: 1.0
 * <p>
 * Created by SunYang on 2019/10/24 16:52
 */

public class CreateResponse extends BaseResponse {

    private String path ;
    private String action ;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString(){
        return JSON.toJSONString(this) ;
    }
}
