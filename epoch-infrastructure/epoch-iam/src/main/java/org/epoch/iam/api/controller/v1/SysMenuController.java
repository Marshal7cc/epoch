package org.epoch.iam.api.controller.v1;


import org.epoch.core.rest.ResponseEntity;
import org.epoch.core.rest.Response;

import org.epoch.mybatis.common.CommonController;
import org.epoch.iam.config.SwaggerTags;
import org.epoch.iam.domain.entity.SysMenu;
import org.epoch.iam.api.SysMenuApi;
import org.epoch.iam.domain.repository.SysMenuRepository;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @author Marshal
 */
@Api(tags = SwaggerTags.MENU)
@RequestMapping("/menus")
@RestController("sysMenuController.v1")
public class SysMenuController extends CommonController<SysMenu, SysMenuRepository> implements SysMenuApi {

    @Override
    public ResponseEntity queryMenuTree(SysMenu dto) {
        return Response.success(repository.queryMenuTree(dto));
    }

    @Override
    public ResponseEntity getUserMenu() {
        return Response.success(repository.getUserMenu());
    }
}
