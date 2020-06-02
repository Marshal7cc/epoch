package com.marshal.epoch.system.api.controller.v1;


import com.marshal.epoch.mybatis.base.BaseController;
import com.marshal.epoch.system.api.SysRoleApi;
import com.marshal.epoch.system.config.SwaggerTags;
import com.marshal.epoch.system.domain.entity.SysRole;
import com.marshal.epoch.system.domain.repository.SysRoleRepository;
import com.marshal.epoch.core.rest.ResponseEntity;
import com.marshal.epoch.core.rest.Response;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Marshal
 */
@Api(tags = SwaggerTags.ROLE)
@RequestMapping("/roles")
@RestController("sysRoleController.v1")
public class SysRoleController extends BaseController<SysRole, SysRoleRepository> implements SysRoleApi {

}
