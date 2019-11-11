package com.ci123.workflow.azkaban.operate.bean.response;

import com.alibaba.fastjson.JSON;
import com.ci123.workflow.azkaban.operate.bean.response.base.BaseResponse;

import java.util.List;

/**
 * Created by shirukai on 2019-06-04 09:53
 * 查询所有项目的响应
 */
public class FetchAllProjectsResponse extends BaseResponse {
    private List<Project> projects;

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public String toString(){
        return JSON.toJSONString(this);
    }

}

