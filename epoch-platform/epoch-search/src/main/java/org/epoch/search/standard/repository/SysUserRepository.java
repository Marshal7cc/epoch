package org.epoch.search.standard.repository;

import org.epoch.search.standard.dto.SysUser;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

/**
 * @author Marshal
 * @date 2020/2/16
 */
public interface SysUserRepository extends ElasticsearchCrudRepository<SysUser, Long> {
}
