package com.marshal.epoch.workflow;

import com.marshal.epoch.web.annotation.EpochApplication;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * epoch工作流服务
 *
 * @author Marshal Yuan
 * @since 2020/6/4
 */
@MapperScan("com.marshal.epoch.**.mapper")
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class WorkflowApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkflowApplication.class, args);
    }

}
