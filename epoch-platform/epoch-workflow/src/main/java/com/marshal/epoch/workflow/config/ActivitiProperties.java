package com.marshal.epoch.workflow.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Marshal Yuan
 * @since 2020/6/5
 */
@ConfigurationProperties(prefix = "spring.activiti.epoch")
public class ActivitiProperties {

    private boolean tableNameUpperCase = true;

    public boolean getTableNameUpperCase() {
        return tableNameUpperCase;
    }

    public void setTableNameUpperCase(boolean tableNameUpperCase) {
        this.tableNameUpperCase = tableNameUpperCase;
    }
}
