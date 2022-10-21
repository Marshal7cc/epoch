package org.epoch.iam.domain.repository;


import org.epoch.data.repository.BaseRepository;
import org.epoch.iam.infrastructure.repository.entity.SysUser;

/**
 * @author Marshal
 */
public interface SysUserRepository extends BaseRepository<SysUser, Long> {

    /**
     * 分布式事务测试方法
     */
    void txTest();

}
