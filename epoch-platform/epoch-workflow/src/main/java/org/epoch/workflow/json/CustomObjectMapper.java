package org.epoch.workflow.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.activiti.rest.common.api.DataResponse;
import org.springframework.stereotype.Component;

/**
 * @auth: Marshal
 * @Date: 2018/10/26
 * @desc: 自定义objectMapper
 */
@Component
public class CustomObjectMapper extends ObjectMapper {

    public CustomObjectMapper() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(DataResponse.class, new DataResponseSerializer());
        this.registerModule(module);
    }
}
