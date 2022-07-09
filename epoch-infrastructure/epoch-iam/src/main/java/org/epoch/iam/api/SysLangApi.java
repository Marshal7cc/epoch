package org.epoch.iam.api;


import org.epoch.core.rest.ResponseEntity;
import org.epoch.iam.api.dto.LangDTO;
import org.epoch.iam.api.query.LangQuery;
import org.epoch.iam.api.vo.LangVO;
import org.epoch.iam.domain.entity.SysLang;
import org.epoch.web.common.BaseFacade;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Marshal
 */
public interface SysLangApi extends BaseFacade<LangDTO, LangVO, LangQuery, Long> {

    /**
     * 获取下拉框
     *
     * @return
     */
    @GetMapping(value = "/queryForOptions")
    ResponseEntity queryForOptions();

}
