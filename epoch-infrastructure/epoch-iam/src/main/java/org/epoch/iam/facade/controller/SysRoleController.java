package org.epoch.iam.facade.controller;


import io.swagger.annotations.Api;
import org.epoch.iam.domain.dto.RoleDTO;
import org.epoch.iam.domain.service.RoleService;
import org.epoch.iam.facade.query.RoleQuery;
import org.epoch.iam.facade.vo.RoleVO;
import org.epoch.iam.infrastructure.configuration.SwaggerTags;
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
