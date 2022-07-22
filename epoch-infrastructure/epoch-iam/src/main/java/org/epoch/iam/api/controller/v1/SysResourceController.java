package org.epoch.iam.api.controller.v1;


import io.swagger.annotations.Api;
import org.epoch.iam.api.dto.ResourceDTO;
import org.epoch.iam.api.query.ResourceQuery;
import org.epoch.iam.api.vo.ResourceVO;
import org.epoch.iam.config.SwaggerTags;
import org.epoch.iam.domain.service.ResourceService;
import org.epoch.web.facade.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Marshal
 */
@Api(tags = SwaggerTags.RESOURCE)
@RequestMapping("/resources")
@RestController("sysResourceController.v1")
public class SysResourceController extends BaseController<ResourceService, ResourceVO, ResourceQuery, ResourceDTO, Long> {

}
