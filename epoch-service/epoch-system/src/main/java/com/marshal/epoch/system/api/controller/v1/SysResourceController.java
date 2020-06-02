package com.marshal.epoch.system.api.controller.v1;


import com.marshal.epoch.mybatis.base.BaseController;
import com.marshal.epoch.system.config.SwaggerTags;
import com.marshal.epoch.system.domain.entity.SysResource;
import com.marshal.epoch.system.api.SysResourceApi;
import com.marshal.epoch.system.domain.repository.SysResourceRepository;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;


/**
 * @author Marshal
 */
@Api(tags = SwaggerTags.RESOURCE)
@RequestMapping("/resources")
@RestController("sysResourceController.v1")
public class SysResourceController extends BaseController<SysResource, SysResourceRepository> implements SysResourceApi {

}
