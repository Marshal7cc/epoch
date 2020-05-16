package com.marshal.epoch.search.standard.servcie;

import com.marshal.epoch.search.common.servcie.BaseElasticsearchService;
import com.marshal.epoch.search.standard.dto.FullTextRetrievalDto;
import com.marshal.epoch.search.standard.dto.SysUser;

import java.util.Map;

public interface SysUserSearchService extends BaseElasticsearchService<SysUser> {

    Map<String, Object> query(FullTextRetrievalDto dto);

    void save(SysUser sysUser);

}
