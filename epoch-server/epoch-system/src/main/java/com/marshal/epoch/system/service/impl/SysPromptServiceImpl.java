package com.marshal.epoch.system.service.impl;


import com.marshal.epoch.core.service.impl.BaseServiceImpl;
import com.marshal.epoch.system.mapper.SysPromptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marshal.epoch.system.entity.SysPrompt;
import com.marshal.epoch.system.service.SysPromptService;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysPromptServiceImpl extends BaseServiceImpl<SysPrompt> implements SysPromptService {

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
