package com.marshal.epoch.system.api.controller.v1;


import com.marshal.epoch.mybatis.base.BaseController;
import com.marshal.epoch.system.config.SwaggerTags;
import com.marshal.epoch.system.domain.entity.SysUser;
import com.marshal.epoch.system.api.SysUserApi;
import com.marshal.epoch.system.domain.repository.SysUserRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author Marshal
 */
@Api(tags = SwaggerTags.USER)
@RequestMapping("/users")
@RestController("sysUserController.v1")
public class SysUserController extends BaseController<SysUser, SysUserRepository> implements SysUserApi {

}
