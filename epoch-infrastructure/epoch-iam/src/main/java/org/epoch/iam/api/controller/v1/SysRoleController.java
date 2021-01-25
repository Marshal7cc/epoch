package org.epoch.iam.api.controller.v1;


import org.epoch.mybatis.common.CommonController;
import org.epoch.iam.api.SysRoleApi;
import org.epoch.iam.config.SwaggerTags;
import org.epoch.iam.domain.entity.SysRole;
import org.epoch.iam.domain.repository.SysRoleRepository;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @author Marshal
 */
@Api(tags = SwaggerTags.ROLE)
@RequestMapping("/roles")
@RestController("sysRoleController.v1")
public class SysRoleController extends CommonController<SysRole, SysRoleRepository> implements SysRoleApi {

}
