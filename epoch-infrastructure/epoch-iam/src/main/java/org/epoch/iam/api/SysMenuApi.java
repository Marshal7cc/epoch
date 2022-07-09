package org.epoch.iam.api;


import org.epoch.core.rest.ResponseEntity;
import org.epoch.iam.api.dto.MenuDTO;
import org.epoch.iam.api.query.MenuQuery;
import org.epoch.iam.api.vo.MenuVO;
import org.epoch.iam.domain.entity.SysMenu;
import org.epoch.web.common.BaseFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Marshal
 */
public interface SysMenuApi extends BaseFacade<MenuDTO, MenuVO, MenuQuery,Long> {

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
