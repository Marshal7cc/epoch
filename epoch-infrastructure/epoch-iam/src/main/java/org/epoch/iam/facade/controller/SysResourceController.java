package org.epoch.iam.facade.controller;


import io.swagger.annotations.Api;
import org.epoch.iam.domain.dto.ResourceDTO;
import org.epoch.iam.domain.service.ResourceService;
import org.epoch.iam.facade.query.ResourceQuery;
import org.epoch.iam.facade.vo.ResourceVO;
import org.epoch.iam.infrastructure.configuration.SwaggerTags;
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
