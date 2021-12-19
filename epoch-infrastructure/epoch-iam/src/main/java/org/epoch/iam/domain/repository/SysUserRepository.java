package org.epoch.iam.domain.repository;


import org.epoch.iam.domain.entity.SysUser;
import org.epoch.starter.mybatis.repository.BaseRepository;

/**
 * @author Marshal
 */
public interface SysUserRepository extends BaseRepository<SysUser> {

    /**
     * 分布式事务测试方法
     */
    void txTest();

}
