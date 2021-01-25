package org.epoch.workflow.core.custom.behavior;

import org.activiti.bpmn.model.Activity;
import org.activiti.engine.impl.bpmn.behavior.AbstractBpmnActivityBehavior;
import org.activiti.engine.impl.bpmn.behavior.ParallelMultiInstanceBehavior;
import org.activiti.engine.impl.bpmn.behavior.SequentialMultiInstanceBehavior;
import org.activiti.engine.impl.bpmn.parser.factory.DefaultActivityBehaviorFactory;
import org.springframework.stereotype.Component;

/**
 * 自定义行为工厂
 *
 * @author: Marshal
 * @date: 2019/7/7
 */
@Component
public class CustomBehaviourFactory extends DefaultActivityBehaviorFactory {

    @Override
    public SequentialMultiInstanceBehavior createSequentialMultiInstanceBehavior(Activity activity, AbstractBpmnActivityBehavior innerActivityBehavior) {
        return new CustomSequentialMultiInstanceBehavior(activity, innerActivityBehavior);
    }

    @Override
    public ParallelMultiInstanceBehavior createParallelMultiInstanceBehavior(Activity activity, AbstractBpmnActivityBehavior innerActivityBehavior) {
        return new CustomParallelMultiInstanceBehavior(activity, innerActivityBehavior);
    }

}
