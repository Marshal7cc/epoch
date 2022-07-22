package org.epoch.iam.api.controller.v1;


import io.swagger.annotations.Api;
import org.epoch.iam.api.dto.RoleDTO;
import org.epoch.iam.api.query.RoleQuery;
import org.epoch.iam.api.vo.RoleVO;
import org.epoch.iam.config.SwaggerTags;
import org.epoch.iam.domain.service.RoleService;
import org.epoch.web.facade.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marshal
 */
@Api(tags = SwaggerTags.ROLE)
@RequestMapping("/roles")
@RestController("sysRoleController.v1")
public class SysRoleController extends BaseController<RoleService, RoleVO, RoleQuery, RoleDTO, Long> {

}
