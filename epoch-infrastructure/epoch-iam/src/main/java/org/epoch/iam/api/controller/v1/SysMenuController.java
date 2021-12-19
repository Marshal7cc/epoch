package org.epoch.iam.api.controller.v1;


import io.swagger.annotations.Api;
import org.epoch.iam.api.SysMenuApi;
import org.epoch.iam.config.SwaggerTags;
import org.epoch.iam.domain.entity.SysMenu;
import org.epoch.iam.domain.repository.SysMenuRepository;
import org.epoch.starter.core.rest.Response;
import org.epoch.starter.core.rest.ResponseEntity;
import org.epoch.starter.mybatis.common.CommonController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
