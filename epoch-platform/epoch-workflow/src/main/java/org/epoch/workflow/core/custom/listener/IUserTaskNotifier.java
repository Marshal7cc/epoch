package org.epoch.workflow.core.custom.listener;

import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;

/**
 * @author Marshal Yuan
 * @since 2020/6/6
 */
public interface IUserTaskNotifier {
    void onTaskCreate(TaskEntity task, UserEntity userEntity);

    void onTaskCreate(TaskEntity task, GroupEntity groupEntity);
}
