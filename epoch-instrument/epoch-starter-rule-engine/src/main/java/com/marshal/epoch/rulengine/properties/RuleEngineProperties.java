package com.marshal.epoch.rulengine.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>name:RuleEngineProperties</p>
 * <pre>
 *      description:
 * </pre>
 *
 * @author Marshal Yuan
 * @date 2020/8/9
 */
@Data
@Component
@ConfigurationProperties(prefix = "epoch.rule-engine")
public class RuleEngineProperties {
    private boolean enabled = true;
    private String rulesPath = "rules/";
}
