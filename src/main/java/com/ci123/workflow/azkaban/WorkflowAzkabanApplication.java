package com.ci123.workflow.azkaban;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class WorkflowAzkabanApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkflowAzkabanApplication.class, args);
    }

}
