package org.epoch.search.standard.servcie;

import org.epoch.search.common.servcie.BaseElasticsearchService;
import org.epoch.search.standard.dto.FullTextRetrievalDto;
import org.epoch.search.standard.dto.SysUser;

import java.util.Map;

/**
 * @author Marshal
 */
public interface SysUserSearchService extends BaseElasticsearchService<SysUser> {

    /**
     * 查询
     *
     * @param dto
     * @return
     */
    Map<String, Object> query(FullTextRetrievalDto dto);

    /**
     * 保存
     *
     * @param sysUser
     */
    void save(SysUser sysUser);

}
