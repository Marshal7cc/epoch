package org.epoch.iam.api;


import org.epoch.core.rest.ResponseEntity;
import org.epoch.iam.api.dto.PromptDTO;
import org.epoch.iam.api.query.PromptQuery;
import org.epoch.iam.api.vo.PromptVO;
import org.epoch.web.facade.BaseFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Marshal
 */
public interface SysPromptApi extends BaseFacade<PromptDTO, PromptVO, PromptQuery, Long> {

    /**
     * 国际化查询
     *
     * @param langCode 语言代码
     * @return
     */
    @GetMapping(value = "/i18n")
    ResponseEntity queryForI18n(@RequestParam String langCode);

}
