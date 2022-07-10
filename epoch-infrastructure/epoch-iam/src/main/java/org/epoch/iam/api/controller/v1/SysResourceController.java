package org.epoch.iam.api.controller.v1;


import io.swagger.annotations.Api;
import org.epoch.iam.api.SysResourceApi;
import org.epoch.iam.api.dto.ResourceDTO;
import org.epoch.iam.api.query.ResourceQuery;
import org.epoch.iam.api.vo.ResourceVO;
import org.epoch.iam.config.SwaggerTags;
import org.epoch.iam.domain.entity.SysResource;
import org.epoch.iam.domain.repository.SysResourceRepository;
import org.epoch.web.facade.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Marshal
 */
@Api(tags = SwaggerTags.RESOURCE)
@RequestMapping("/resources")
@RestController("sysResourceController.v1")
public class SysResourceController extends BaseController<SysResourceRepository, ResourceDTO, ResourceVO, ResourceQuery, SysResource, Long> implements SysResourceApi {

}
