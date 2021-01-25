package org.epoch.workflow.core.custom.user;

import java.util.List;

import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.data.impl.MybatisUserDataManager;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Marshal Yuan
 * @since 2020/6/6
 */
public class CustomUserDataManager extends MybatisUserDataManager implements InitializingBean {

    @Autowired
    private SpringProcessEngineConfiguration springProcessEngineConfiguration;

    public CustomUserDataManager() {
        super(null);
    }

    @Override
    public UserEntity findById(String entityId) {
        return super.findById(entityId);
    }

    @Override
    public List<Group> findGroupsByUser(String userId) {
        return super.findGroupsByUser(userId);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.processEngineConfiguration = springProcessEngineConfiguration;
    }
}
