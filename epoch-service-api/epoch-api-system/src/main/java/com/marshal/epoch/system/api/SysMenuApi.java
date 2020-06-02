package com.marshal.epoch.system.api;


import com.marshal.epoch.core.base.BaseApi;
import com.marshal.epoch.core.rest.ResponseEntity;
import com.marshal.epoch.system.domain.entity.SysMenu;
import org.springframework.web.bind.annotation.*;

/**
 * @author Marshal
 */
public interface SysMenuApi extends BaseApi<SysMenu> {

    /**
     * 获取菜单树
     *
     * @param dto
     * @return
     */
    @PostMapping(value = "/queryMenuTree")
    ResponseEntity queryMenuTree(@RequestBody SysMenu dto);

    /**
     * 获取用户菜单
     *
     * @return
     */
    @GetMapping(value = "/getUserMenu")
    ResponseEntity getUserMenu();
}
