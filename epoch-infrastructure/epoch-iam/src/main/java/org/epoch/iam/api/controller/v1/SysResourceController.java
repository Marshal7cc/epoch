package org.epoch.iam.api.controller.v1;


import org.epoch.mybatis.common.CommonController;
import org.epoch.iam.config.SwaggerTags;
import org.epoch.iam.domain.entity.SysResource;
import org.epoch.iam.api.SysResourceApi;
import org.epoch.iam.domain.repository.SysResourceRepository;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;


/**
 * @author Marshal
 */
@Api(tags = SwaggerTags.RESOURCE)
@RequestMapping("/resources")
@RestController("sysResourceController.v1")
public class SysResourceController extends CommonController<SysResource, SysResourceRepository> implements SysResourceApi {

}
