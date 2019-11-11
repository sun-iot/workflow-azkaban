package com.ci123.workflow.azkaban.operate.service.service;



import com.ci123.workflow.azkaban.operate.bean.response.*;
import com.ci123.workflow.azkaban.operate.bean.response.base.BaseResponse;
import com.ci123.workflow.azkaban.operate.config.Configuration;
import com.ci123.workflow.azkaban.operate.service.api.AzkabanAPI;
import com.ci123.workflow.azkaban.operate.service.http.GetHttpClientSSL;
import com.ci123.workflow.azkaban.operate.service.http.PostClientSSL;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (c) 2018-2028 Corp-ci All Rights Reserved
 * <p>
 * Project: workflow
 * Package: com.ci123.workflow.service.az.service
 * Version: 1.0
 * <p>
 * Created by SunYang on 2019/10/24 11:09
 */
public class AzkabanAPIImpl implements AzkabanAPI {
    private Logger logger = LoggerFactory.getLogger(AzkabanAPIImpl.class);
    private String username;
    private String password;
    private String uri;
    private String sessionId = "b1d4f665-f4b9-4e7d-b83a-b928b41cc323";
    private PostClientSSL postClientSSL;
    private GetHttpClientSSL getClientSSL;

    public AzkabanAPIImpl(String uri, String username, String password) {
        this.uri = uri;
        this.username = username;
        this.password = password;
        postClientSSL = new PostClientSSL();
        getClientSSL = new GetHttpClientSSL();

    }

    @Override
    public CreateResponse createProject(String name, String desc) {
        Map<String,String> body = new HashMap<>();
        body.put("session.id" ,sessionId);
        body.put("action","create");
        body.put("name",name);
        body.put("description" , desc);
        System.out.println("name:"+name + "\tdescription:" + desc);
        String result = postClientSSL.doPost(uri + "/manager", body ,"application/x-www-form-urlencoded");
        return ResponseHandler.handle(result, CreateResponse.class);
    }

    @Override
    public BaseResponse deleteProject(String name) {
        String result = getClientSSL.doGet(MessageFormat.format(Configuration.DELETE_PROJECT, uri, name, sessionId));
        result = "\"status\":\"success\",\"message\":\"delete project successfully\"";
        return ResponseHandler.handle(result);
    }

    @Override
    public ProjectZipResponse uploadProjectZip(String filePath, String projectName) {

        HttpEntity entity = MultipartEntityBuilder.create()
                .addBinaryBody("file" , new File(filePath) )
                .addTextBody("session.id", sessionId)
                .addTextBody("ajax", "upload")
                .addTextBody("project", projectName)
                .build();
        String result = postClientSSL.doPost(uri + "/manager", entity );
        System.out.println(result);

        return ResponseHandler.handle(result, ProjectZipResponse.class);
    }

    @Override
    public FetchFlowsResponse fetchProjectFlows(String projectName) {
        String result = getClientSSL.doGet(MessageFormat.format(Configuration.FETCH_PROJECT_FLOWS, uri, sessionId, projectName));
        return ResponseHandler.handle(result , FetchFlowsResponse.class);
    }

    @Override
    public FetchExecFlowResponse fetchJobFlows(String projectName, String flowName) {
        String result = getClientSSL.doGet(MessageFormat.format(Configuration.FETCH_JOBS_FLOWS, uri, sessionId, projectName, flowName));
        return ResponseHandler.handle(result , FetchExecFlowResponse.class);
    }

    @Override
    public FetchRunningExecutionFlowResponse fetchRunningExecutionFlow(String projectName, String flowName) {
        String result = getClientSSL.doGet(MessageFormat.format(Configuration.FETCH_RUNNING_EXECUTION_FLOW, uri, sessionId, projectName, flowName));
        return ResponseHandler.handle(result , FetchRunningExecutionFlowResponse.class);
    }

    @Override
    public ExecuteFlowResponse executeFlow(String projectName, String flowName) {
        String result = getClientSSL.doGet(MessageFormat.format(Configuration.EXECUTE_FLOW, uri, sessionId, projectName, flowName));
        return ResponseHandler.handle(result , ExecuteFlowResponse.class);
    }

    @Override
    public BaseResponse cancelFlow(String execId) {
        String result = getClientSSL.doGet(MessageFormat.format(Configuration.CANCEL_FLOW, uri, sessionId, execId));
        return ResponseHandler.handle(result);
    }

    @Override
    public FetchExecFlowResponse fetchExecFlow(String execId) {
        String result = getClientSSL.doGet(MessageFormat.format(Configuration.FETCH_EXEC_FLOW, uri, sessionId, execId));
        return ResponseHandler.handle(result , FetchExecFlowResponse.class);

    }

    @Override
    public FetchExecJobLogs fetchExecJobLogs(String execId, String jobId, int offset, int length) {
        String result = getClientSSL.doGet(MessageFormat.format(Configuration.FETCH_EXEC_JOB_LOGS, uri, sessionId, execId, jobId, offset, length));
        return ResponseHandler.handle(result , FetchExecJobLogs.class);
    }

    @Override
    public FetchFlowExecutionsResponse fetchFlowExecutions(String projectName, String flowName, int start, int length) {
        String result = getClientSSL.doGet(MessageFormat.format(Configuration.FETCH_FLOW_EXECUTIONS, uri, sessionId, projectName, flowName, start, length));
        return ResponseHandler.handle(result ,FetchFlowExecutionsResponse.class);
    }

    @Override
    public FetchAllProjectsResponse fetchAllProjects() {
        String result = getClientSSL.doGet(MessageFormat.format(Configuration.FETCH_ALL_PROJECTS, uri, sessionId));
        return ResponseHandler.handle(result , FetchAllProjectsResponse.class);
    }

    @Override
    public ScheduleCronFlowResponse scheduleCronFlow(String projectName, String flowName, String cronExpression) {
        HttpEntity entity = MultipartEntityBuilder.create()
                .addTextBody("session.id", sessionId)
                .addTextBody("project", projectName)
                .addTextBody("flow", flowName)
                .addTextBody("cronExpression", cronExpression)
                .build();
        String result = postClientSSL.doPost(uri + "/manager", entity);
        return ResponseHandler.handle(result , ScheduleCronFlowResponse.class);
    }

    @Override
    public FetchScheduleResponse fetchSchedule(Integer projectId, Integer flowId) {
        String result = getClientSSL.doGet(MessageFormat.format(Configuration.FETCH_SCHEDULE, uri, sessionId, projectId, flowId));
        return ResponseHandler.handle(result , FetchScheduleResponse.class);
    }

    @Override
    public BaseResponse removeSchedule(String scheduleId) {
        HttpEntity build = MultipartEntityBuilder.create()
                .addTextBody("session.id", sessionId)
                .addTextBody("action", "removeSched")
                .addTextBody("scheduleId", scheduleId)
                .build();
        String result = postClientSSL.doPost(uri + "/manager", build);
        return ResponseHandler.handle(result);
    }


    public LoginResponse login() {
        Response res = null;
        LoginResponse response = null;
        HttpEntity entity = null;
        try {
            Map<String, String> body = new HashMap<>();
            body.put("action", "login");
            body.put("username", username);
            body.put("password", password);
            String result = postClientSSL.doPost(uri, body, "application/x-www-form-urlencoded");
            String content = result.replace("session.id", "sessionId");
            response = ResponseHandler.handle(content, LoginResponse.class);
        } catch (Exception e) {
            System.out.println("loginException:message-> " + e.getMessage() + "\n" +
                    "loginException:cause" + e.getCause().getMessage());
            e.printStackTrace();
        }
        if (StringUtils.isNotEmpty(response.getSessionId())) {
            this.sessionId = response.getSessionId();
            System.out.println(this.sessionId);
        } else {
           logger.error("获取session 失败");
        }
        return response;
    }
}
