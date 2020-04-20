package com.marshal.epoch.system.service;


import com.marshal.epoch.database.service.BaseService;

import com.marshal.epoch.system.entity.SysPrompt;

import java.util.Map;

public interface SysPromptService extends BaseService<SysPrompt> {

    Map queryForI18n(String langCode);

}
