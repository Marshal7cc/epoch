package com.marshal.epoch.system.api.controller.v1;


import com.marshal.epoch.core.rest.ResponseEntity;
import com.marshal.epoch.core.rest.Response;

import com.marshal.epoch.mybatis.base.BaseController;
import com.marshal.epoch.system.config.SwaggerTags;
import com.marshal.epoch.system.domain.entity.SysMenu;
import com.marshal.epoch.system.api.SysMenuApi;
import com.marshal.epoch.system.domain.repository.SysMenuRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Marshal
 */
@Api(tags = SwaggerTags.MENU)
@RequestMapping("/menus")
@RestController("sysMenuController.v1")
public class SysMenuController extends BaseController<SysMenu, SysMenuRepository> implements SysMenuApi {

    @Override
    public ResponseEntity queryMenuTree(SysMenu dto) {
        return Response.success(repository.queryMenuTree(dto));
    }

    @Override
    public ResponseEntity getUserMenu() {
        return Response.success(repository.getUserMenu());
    }
}
