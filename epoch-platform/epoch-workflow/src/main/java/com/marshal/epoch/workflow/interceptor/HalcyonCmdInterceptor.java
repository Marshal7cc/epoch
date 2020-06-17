package com.marshal.epoch.workflow.interceptor;

import org.activiti.engine.impl.cmd.CompleteTaskCmd;
import org.activiti.engine.impl.interceptor.AbstractCommandInterceptor;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandConfig;
import org.springframework.stereotype.Component;

/**
 * @auth: Marshal
 * @date: 2019/7/4
 * @desc: 自定义命令拦截器(仅用作测试)
 */
@Component
public class HalcyonCmdInterceptor extends AbstractCommandInterceptor {

    @Override
    public <T> T execute(CommandConfig commandConfig, Command<T> command) {

        //custom behaviour
        if (command instanceof CompleteTaskCmd) {
            System.out.println(command);
        }

        //continue execute
        return next.execute(commandConfig, command);

    }

}
