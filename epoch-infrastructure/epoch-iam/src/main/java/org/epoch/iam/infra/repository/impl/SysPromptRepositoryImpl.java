package org.epoch.iam.infra.repository.impl;


import org.epoch.mybatis.repository.impl.BaseRepositoryImpl;
import org.epoch.iam.infra.mapper.SysPromptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.epoch.iam.domain.entity.SysPrompt;
import org.epoch.iam.domain.repository.SysPromptRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Marshal
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysPromptRepositoryImpl extends BaseRepositoryImpl<SysPrompt> implements SysPromptRepository {

    @Autowired
    private SysPromptMapper promptMapper;

    @Override
    public Map queryForI18n(String langCode) {
        Map<String, String> i18nMap = new LinkedHashMap<>();
        List<SysPrompt> list = promptMapper.selectByLangCode(langCode);
        for (SysPrompt item : list) {
            i18nMap.put(item.getPromptCode(), item.getDescription());
        }
        return i18nMap;
    }
}
