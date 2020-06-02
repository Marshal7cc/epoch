package com.marshal.epoch.system.api;


import com.marshal.epoch.core.base.BaseApi;
import com.marshal.epoch.core.rest.ResponseEntity;
import com.marshal.epoch.system.domain.entity.SysLang;
import org.springframework.web.bind.annotation.*;

/**
 * @author Marshal
 */
public interface SysLangApi extends BaseApi<SysLang> {

    /**
     * 获取下拉框
     *
     * @return
     */
    @GetMapping(value = "/queryForOptions")
    ResponseEntity queryForOptions();

}
