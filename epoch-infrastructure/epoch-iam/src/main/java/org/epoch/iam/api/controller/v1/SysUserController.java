package org.epoch.iam.api.controller.v1;


import io.swagger.annotations.Api;
import org.epoch.iam.api.dto.UserDTO;
import org.epoch.iam.api.query.RoleQuery;
import org.epoch.iam.api.vo.UserVO;
import org.epoch.iam.config.SwaggerTags;
import org.epoch.iam.domain.service.UserService;
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
