package org.epoch.iam.facade.controller;


import io.swagger.annotations.Api;
import org.epoch.iam.domain.dto.MenuDTO;
import org.epoch.iam.domain.service.MenuService;
import org.epoch.iam.facade.query.MenuQuery;
import org.epoch.iam.facade.vo.MenuVO;
import org.epoch.iam.infrastructure.configuration.SwaggerTags;
import org.epoch.iam.infrastructure.repository.entity.SysMenu;
import org.epoch.web.facade.controller.BaseController;
import org.epoch.web.rest.Response;
import org.epoch.web.rest.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marshal
 */
@Api(tags = SwaggerTags.MENU)
@RequestMapping("/menus")
@RestController("sysMenuController.v1")
public class SysMenuController extends BaseController<MenuService, MenuVO, MenuQuery, MenuDTO, Long> {

    public ResponseEntity queryMenuTree(SysMenu dto) {
        return Response.success();
    }

    public ResponseEntity getUserMenu() {
        return Response.success();
    }
}
