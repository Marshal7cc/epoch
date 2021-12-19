package org.epoch.iam.api.controller.v1;


import io.swagger.annotations.Api;
import org.epoch.iam.api.SysUserApi;
import org.epoch.iam.config.SwaggerTags;
import org.epoch.iam.domain.entity.SysUser;
import org.epoch.iam.domain.repository.SysUserRepository;
import org.epoch.starter.mybatis.common.CommonController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Marshal
 */
@Api(tags = SwaggerTags.USER)
@RequestMapping("/users")
@RestController("sysUserController.v1")
public class SysUserController extends CommonController<SysUser, SysUserRepository> implements SysUserApi {

}
