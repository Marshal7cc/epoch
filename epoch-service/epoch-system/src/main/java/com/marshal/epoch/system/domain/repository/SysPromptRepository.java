package com.marshal.epoch.system.domain.repository;


import com.marshal.epoch.mybatis.service.BaseRepository;

import com.marshal.epoch.system.domain.entity.SysPrompt;

import java.util.Map;

/**
 * @author Marshal
 */
public interface SysPromptRepository extends BaseRepository<SysPrompt> {

    /**
     * 国际化查询
     *
     * @param langCode
     * @return
     */
    Map queryForI18n(String langCode);

}
