package org.epoch.iam.domain.repository;


import java.util.Map;

import org.epoch.iam.domain.entity.SysPrompt;
import org.epoch.data.repository.BaseRepository;

/**
 * @author Marshal
 */
public interface SysPromptRepository extends BaseRepository<SysPrompt,Long> {

    /**
     * 国际化查询
     *
     * @param langCode
     * @return
     */
    Map queryForI18n(String langCode);

}
