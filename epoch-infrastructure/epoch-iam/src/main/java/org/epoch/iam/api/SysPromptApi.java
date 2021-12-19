package org.epoch.iam.api;


import org.epoch.iam.domain.entity.SysPrompt;
import org.epoch.starter.core.rest.ResponseEntity;
import org.epoch.starter.mybatis.common.CommonApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Marshal
 */
public interface SysPromptApi extends CommonApi<SysPrompt> {

    /**
     * 国际化查询
     *
     * @param langCode 语言代码
     * @return
     */
    @GetMapping(value = "/i18n")
    ResponseEntity queryForI18n(@RequestParam String langCode);

}
