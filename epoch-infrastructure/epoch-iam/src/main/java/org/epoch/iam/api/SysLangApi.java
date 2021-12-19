package org.epoch.iam.api;


import org.epoch.iam.domain.entity.SysLang;
import org.epoch.starter.core.rest.ResponseEntity;
import org.epoch.starter.mybatis.common.CommonApi;
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
