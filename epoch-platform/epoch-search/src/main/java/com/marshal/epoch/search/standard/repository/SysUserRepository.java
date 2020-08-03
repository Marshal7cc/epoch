package com.marshal.epoch.search.standard.repository;

import com.marshal.epoch.search.standard.dto.SysUser;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Marshal
 * @date 2020/2/16
 *
 */
public interface SysUserRepository extends ElasticsearchCrudRepository<SysUser, Long> {
}
