package org.epoch.iam.api;


import org.epoch.iam.api.dto.LangDTO;
import org.epoch.iam.api.query.LangQuery;
import org.epoch.iam.api.vo.LangVO;
import org.epoch.web.facade.BaseFacade;
import org.epoch.web.rest.ResponseEntity;
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
