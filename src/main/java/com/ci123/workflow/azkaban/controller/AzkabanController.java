package com.ci123.workflow.azkaban.controller;

import com.ci123.workflow.azkaban.operate.bean.module.FetchSchedule;
import com.ci123.workflow.azkaban.operate.bean.module.RemoveSchedule;
import com.ci123.workflow.azkaban.operate.bean.module.ScheduleCronFlow;
import com.ci123.workflow.azkaban.operate.bean.module.Upload;
import com.ci123.workflow.azkaban.operate.bean.response.*;
import com.ci123.workflow.azkaban.operate.bean.response.base.BaseResponse;
import com.ci123.workflow.azkaban.operate.service.api.AzkabanAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright (c) 2018-2028 Corp-ci All Rights Reserved
 * <p>
 * Project: workflow
 * Package: com.ci123.workflow.controller
 * Version: 1.0
 * <p>
 * Created by SunYang on 2019/10/21 16:10
 */
@Controller
@RequestMapping(value = "/api/v1",
        produces = "application/json;charset=utf-8")
@Api(value = "AzkabanController",
        tags = {"azkaban 的接口 api 示例"})
public class AzkabanController {
    Logger logger = LoggerFactory.getLogger(AzkabanController.class);

    @Autowired
    private AzkabanAPI azkabanAPI;

    @RequestMapping(value = "/azkaban/manager/create", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "create a Azkaban Project ",
            httpMethod = "POST",
            produces = "application/json",
            consumes = "application/json")
    public String createProject(@RequestBody @ApiParam(value = "the name of the project and the description of the project ", required = true) com.ci123.workflow.azkaban.operate.bean.module.Project project) {
        CreateResponse response = azkabanAPI.createProject(project.getName(), project.getDescription());
        return response.toString();
    }


    @GetMapping("/azkaban/manager/delete")
    @ResponseBody
    @ApiOperation(value = "delete a Azkaban Project , and no response  ",
            httpMethod = "GET")
    public void deleteProject(@RequestParam("project") @ApiParam(value = "删除的 project ") String project) {
        azkabanAPI.deleteProject(project);
    }

    @PostMapping("/azkaban/manager/upload")
    @ResponseBody
    @ApiOperation(value = "upload the project zip file. The type should be set as application/zip or application/x-zip-compressed",
            httpMethod = "POST",
            produces = "application/json",
            consumes = "application/json")
    public String uploadProjectZip(@RequestBody @ApiParam(value = "project name and the zip of the project", required = true) Upload upload) {
        System.out.println(upload.getFile());
        ProjectZipResponse projectZipResponse = azkabanAPI.uploadProjectZip(upload.getFile(), upload.getProject());
        return projectZipResponse.toString();
    }

    @GetMapping("/azkaban/manager/fetch/flows/project")
    @ResponseBody
    @ApiOperation(value = "fetch follows of the project",
            httpMethod = "GET",
            produces = "application/json")
    public String fetchProjectFlows(@RequestParam("project") @ApiParam(value = "the project name", required = true) String project) {
        FetchFlowsResponse fetchFlowsResponse = azkabanAPI.fetchProjectFlows(project);
        return fetchFlowsResponse.toString();
    }

    @GetMapping("/azkaban/manager/fetch/flows/job")
    @ResponseBody
    @ApiOperation(value = "fetch jobs of a flow",
            httpMethod = "GET",
            produces = "application/json")
    public String fetchJobFlows(@RequestParam("project") @ApiParam(value = "the project name", required = true) String project,
                                @RequestParam("flow") @ApiParam(value = "the flow name", required = true) String flow) {
        FetchExecFlowResponse fetchExecFlowResponse = azkabanAPI.fetchJobFlows(project, flow);

        return fetchExecFlowResponse.toString();
    }

    @GetMapping("/azkaban/manager/fetch/execute/flows")
    @ResponseBody
    @ApiOperation(value = "fetch executions of a flow",
            httpMethod = "GET",
            produces = "application/json")
    public String fetchFlowExecutions(@RequestParam("project") @ApiParam(value = "The project name to be fetched", required = true) String project,
                                      @RequestParam("flow") @ApiParam(value = "The flow id to be fetched", required = true) String flow,
                                      @RequestParam("start") @ApiParam(value = "The start index(inclusive) of the returned list", required = true) Integer start,
                                      @RequestParam("length") @ApiParam(value = "The max length of the returned list", required = true) Integer length) {
        FetchFlowExecutionsResponse response = azkabanAPI.fetchFlowExecutions(project, flow, start, length);
        return response.toString();
    }

    @GetMapping("/azkaban/manager/fetch/execute/running")
    @ResponseBody
    @ApiOperation(value = "Fetch Running Executions of a Flow ",
            httpMethod = "GET",
            produces = "application/json")
    public String fetchRunningExecutionFlow(@RequestParam("project") @ApiParam(value = "The project name to be fetched", required = true) String project,
                                            @RequestParam("flow") @ApiParam(value = "The flow id to be fetched", required = true) String flow) {
        FetchRunningExecutionFlowResponse response = azkabanAPI.fetchRunningExecutionFlow(project, flow);
        return response.toString();
    }

    @GetMapping("/azkaban/manager/execute/flow")
    @ResponseBody
    @ApiOperation(value = "execute a flow",
            httpMethod = "GET",
            produces = "application/json")
    public String executeFlow(@RequestParam("project") @ApiParam(value = "the project name", required = true) String project,
                              @RequestParam("flow") @ApiParam(value = "the flow name", required = true) String flow) {
        ExecuteFlowResponse executeFlowResponse = azkabanAPI.executeFlow(project, flow);
        return executeFlowResponse.toString();
    }

    @GetMapping("/azkaban/manager/cancel/flow")
    @ResponseBody
    @ApiOperation(value = "cancel a flow",
            httpMethod = "GET",
            produces = "application/json")
    public String cancelFlow(@RequestParam("execid") @ApiParam(value = "The execution id. ", required = true) String execid){
        BaseResponse response = azkabanAPI.cancelFlow(execid);
        return response.toString();
    }

    @GetMapping("/azkaban/manager/fetch/execute/flow")
    @ResponseBody
    @ApiOperation(value = "fetches all the detailed information of that execution",
            httpMethod = "GET",
            produces = "application/json")
    public String fetchExecFlow(@RequestParam("execid") @ApiParam(value = "The execution id. ", required = true) String execid){
        FetchExecFlowResponse response = azkabanAPI.fetchExecFlow(execid);
        return response.toString();
    }

    @GetMapping("/azkaban/manager/fetch/execute/logs")
    @ResponseBody
    @ApiOperation(value = "",httpMethod = "GET",produces = "application/json")
    public String fetchExecJobLogs(@RequestParam("execid")@ApiParam(value = "The unique id for an execution",required = true) String execid ,
                                   @RequestParam("jobid")@ApiParam(value = "The unique id for the job to be fetched",required = true)String jobid,
                                   @RequestParam("offest")@ApiParam(value = "The offset for the log data",required = true)Integer offest,
                                   @RequestParam("length")@ApiParam(value = "The length of the log data",required = true)Integer length){
        FetchExecJobLogs response = azkabanAPI.fetchExecJobLogs(execid, jobid, offest, length);
        return response.toString();
    }

    @Deprecated
    @GetMapping("/azkaban/manager/fetch/project")
    public String fetchAllProjects(){
        FetchAllProjectsResponse response = azkabanAPI.fetchAllProjects();
        return response.toString();
    }

    @PostMapping("/az/manager/schedule/cron/flow")
    @ResponseBody
    @ApiOperation(value = "Flexible scheduling using Cron" , httpMethod = "POST" , produces = "application/json" , consumes = "application/json")
    @Deprecated
    public String scheduleCronFlow(@RequestBody @ApiParam(value = "" ,required = true) ScheduleCronFlow scheduleCronFlow){
        ScheduleCronFlowResponse response = azkabanAPI.scheduleCronFlow(scheduleCronFlow.getProject(), scheduleCronFlow.getFlow(), scheduleCronFlow.getCron());
        return response.toString();
    }

    @PostMapping("/az/manager/fetch/schedule")
    @ResponseBody
    @ApiOperation(value = "Fetch a Schedule",httpMethod = "POST",produces = "application/json",consumes = "application/json")
    public String fetchSchedule(@RequestBody @ApiParam(value = "",required = true) FetchSchedule fetchSchedule){
        FetchScheduleResponse response = azkabanAPI.fetchSchedule(fetchSchedule.getProjectId(), fetchSchedule.getFlowId());
        return response.toString();
    }

    @PostMapping("/az/manager/fetch/schedule/remove")
    @ResponseBody
    @ApiOperation(value = "remove a Schedule",httpMethod = "POST",produces = "application/json",consumes = "application/json")
    public String removeSchedule(@RequestBody @ApiParam(value = "",required = true) RemoveSchedule removeSchedule){
        BaseResponse response = azkabanAPI.removeSchedule(removeSchedule.getScheduleId());
        return response.toString();
    }


}

