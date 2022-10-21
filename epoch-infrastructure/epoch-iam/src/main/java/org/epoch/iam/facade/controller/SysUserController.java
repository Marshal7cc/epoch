package org.epoch.iam.facade.controller;


import io.swagger.annotations.Api;
import org.epoch.iam.domain.dto.UserDTO;
import org.epoch.iam.domain.service.UserService;
import org.epoch.iam.facade.query.RoleQuery;
import org.epoch.iam.facade.vo.UserVO;
import org.epoch.iam.infrastructure.configuration.SwaggerTags;
import org.epoch.web.facade.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Marshal
 */
@Api(tags = SwaggerTags.USER)
@RequestMapping("/users")
@RestController("sysUserController.v1")
public class SysUserController extends BaseController<UserService, UserVO, RoleQuery, UserDTO, Long> {

}
