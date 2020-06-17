package com.marshal.epoch.workflow.core.custom.user;

import java.util.List;

import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.data.impl.MybatisGroupDataManager;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Marshal Yuan
 * @since 2020/6/6
 */
public class CustomGroupDataManager extends MybatisGroupDataManager implements InitializingBean {

    @Autowired
    private SpringProcessEngineConfiguration springProcessEngineConfiguration;

    public CustomGroupDataManager() {
        super(null);
    }

    @Override
    public List<Group> findGroupsByUser(String userId) {
        return super.findGroupsByUser(userId);
    }

    @Override
    public GroupEntity findById(String entityId) {
        return super.findById(entityId);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.processEngineConfiguration = springProcessEngineConfiguration;
    }
}
