package com.marshal.epoch.rulengine.service;

import java.util.List;

/**
 * <p>name:RuleEngineService</p>
 * <pre>
 *      description:
 * </pre>
 *
 * @author Marshal Yuan
 * @date 2020/8/9
 */
public interface RuleEngineService {
    void match(List<Object> data);

    void match(String ruleEngineType, List<Object> data);
}
