package com.marshal.epoch.system.api;


import com.marshal.epoch.core.base.BaseApi;
import com.marshal.epoch.core.rest.ResponseEntity;
import com.marshal.epoch.system.domain.entity.SysPrompt;
import org.springframework.web.bind.annotation.*;

/**
 * @author Marshal
 */
public interface SysPromptApi extends BaseApi<SysPrompt> {

    /**
     * 国际化查询
     *
     * @param langCode 语言代码
     * @return
     */
    @GetMapping(value = "/i18n")
    ResponseEntity queryForI18n(@RequestParam String langCode);

}
