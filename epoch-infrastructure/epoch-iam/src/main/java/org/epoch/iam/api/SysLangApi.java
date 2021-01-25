package org.epoch.iam.api;


import org.epoch.mybatis.common.CommonApi;
import org.epoch.core.rest.ResponseEntity;
import org.epoch.iam.domain.entity.SysLang;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Marshal
 */
public interface SysLangApi extends CommonApi<SysLang> {

    /**
     * 获取下拉框
     *
     * @return
     */
    @GetMapping(value = "/queryForOptions")
    ResponseEntity queryForOptions();

}
