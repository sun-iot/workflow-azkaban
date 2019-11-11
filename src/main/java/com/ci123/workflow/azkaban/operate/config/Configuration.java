package com.ci123.workflow.azkaban.operate.config;

/**
 * Copyright (c) 2018-2028 Corp-ci All Rights Reserved
 * <p>
 * Project: workflow
 * Package: com.ci123.workflow.conifg.az
 * Version: 1.0
 * <p>
 * Created by SunYang on 2019/10/22 15:07
 */
public class Configuration {
    public static final String DELETE_PROJECT = "{0}/manager?delete=true&project={1}&session.id={2}";
    public static final String FETCH_PROJECT_FLOWS = "{0}/manager?ajax=fetchprojectflows&session.id={1}&project={2}";
    public static final String FETCH_JOBS_FLOWS="{0}/manager?ajax=fetchflowgraph&session.id={1}&project={2}&flow={3}";
    public static final String EXECUTE_FLOW = "{0}/executor?ajax=executeFlow&session.id={1}&project={2}&flow={3}";
    public static final String FETCH_RUNNING_EXECUTION_FLOW = "{0}/executor?ajax=executeFlow&session.id={1}&project={2}&flow={3}";
    public static final String CANCEL_FLOW = "{0}/executor?ajax=cancelFlow&session.id={1}&execid={2}";
    public static final String FETCH_EXEC_FLOW = "{0}/executor?ajax=fetchexecflow&session.id={1}&execid={2}";
    public static final String FETCH_EXEC_JOB_LOGS = "{0}/executor?ajax=fetchExecJobLogs&session.id={1}&execid={2}" +
            "&jobId={3}&offset={4}&length={5}";
    public static final String FETCH_FLOW_EXECUTIONS = "{0}/manager?ajax=fetchFlowExecutions&session.id={1}" +
            "&project={2}&flow={3}&start={4}&length={5}";
    public static final String FETCH_ALL_PROJECTS = "{0}/index?ajax=fetchuserprojects&session.id={1}";
    public static final String SCHEDULE_CRON_FLOW = "{0}/schedule?ajax=scheduleCronFlow&session.id={1}&" +
            "projectName={2}&flow={3}&cronExpression={4}";
    public static final String FETCH_SCHEDULE = "{0}/schedule?ajax=fetchSchedule&session.id={1}&projectId={2}&flowId={3}";
}
