package org.epoch.workflow.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;


import org.epoch.workflow.component.ActivitiBeanProvider;
import org.epoch.workflow.constant.ActivitiConstant;
import org.epoch.workflow.core.custom.behavior.CustomBehaviourFactory;
import org.epoch.workflow.core.custom.CustomUserTaskParseHandler;
import org.epoch.workflow.core.custom.db.CustomDbSqlSessionFactory;
import org.epoch.workflow.core.custom.user.CustomGroupDataManager;
import org.epoch.workflow.core.custom.user.CustomUserDataManager;
import org.epoch.workflow.interceptor.HalcyonCmdInterceptor;
import org.activiti.engine.impl.interceptor.CommandInterceptor;
import org.activiti.engine.parse.BpmnParseHandler;
import org.activiti.spring.SpringAsyncExecutor;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;


/**
 * @author Marshal
 * @date: 2019/2/5
 * @desc: 工作流配置文件
 */
@Configuration
@EnableConfigurationProperties(ActivitiProperties.class)
public class ActivitiConfiguration extends AbstractProcessEngineAutoConfiguration {

    @Autowired
    private ActivitiProperties activitiProperties;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private CustomBehaviourFactory customBehaviourFactory;

    @Autowired
    private ActivitiBeanProvider activitiBeanProvider;

    @Autowired
    private HalcyonCmdInterceptor halcyonCmdInterceptor;

    @Autowired
    private CustomUserTaskParseHandler customUserTaskParseHandler;

    @Bean
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

    @Bean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration(DataSourceTransactionManager transactionManager,
                                                                             SpringAsyncExecutor springAsyncExecutor) throws IOException {
        SpringProcessEngineConfiguration springProcessEngineConfiguration = baseSpringProcessEngineConfiguration(
                dataSource,
                transactionManager,
                springAsyncExecutor);

        //自定义行为工厂
        springProcessEngineConfiguration.setActivityBehaviorFactory(customBehaviourFactory);

        //设置字体，防止流程图中文乱码
        springProcessEngineConfiguration.setActivityFontName(ActivitiConstant.FONT_NAME);
        springProcessEngineConfiguration.setLabelFontName(ActivitiConstant.FONT_NAME);
        springProcessEngineConfiguration.setAnnotationFontName(ActivitiConstant.FONT_NAME);

        //配置beans,若不指定则spring容器中所有bean都用作代理表达式
        springProcessEngineConfiguration.setBeans(activitiBeanProvider);

        //配置cmd 拦截器
        List<CommandInterceptor> commandInterceptors = new ArrayList<>();
        commandInterceptors.add(halcyonCmdInterceptor);
        springProcessEngineConfiguration.setCustomPostCommandInterceptors(commandInterceptors);

        //设置自定义parseHandler
        List<BpmnParseHandler> bpmnParseHandlers = new ArrayList<>();
        bpmnParseHandlers.add(customUserTaskParseHandler);
        springProcessEngineConfiguration.setCustomDefaultBpmnParseHandlers(bpmnParseHandlers);

        return springProcessEngineConfiguration;
    }

    @Bean
    public CustomDbSqlSessionFactory customDbSqlSessionFactory() {
        return new CustomDbSqlSessionFactory(activitiProperties.getTableNameUpperCase());
    }

    @Bean
    public CustomGroupDataManager customGroupDataManager() {
        return new CustomGroupDataManager();
    }

    @Bean
    public CustomUserDataManager customUserDataManager() {
        return new CustomUserDataManager();
    }
}
