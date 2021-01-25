package org.epoch.iam.domain.repository;


import org.epoch.mybatis.repository.BaseRepository;

import org.epoch.iam.domain.entity.SysPrompt;

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
