package org.epoch.iam.facade.controller;


import io.swagger.annotations.Api;
import org.epoch.iam.domain.dto.FunctionDTO;
import org.epoch.iam.domain.service.FunctionService;
import org.epoch.iam.facade.query.FunctionQuery;
import org.epoch.iam.facade.vo.FunctionVO;
import org.epoch.iam.infrastructure.configuration.SwaggerTags;
import org.epoch.web.facade.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marshal
 */
@Api(tags = SwaggerTags.FUNCTION)
@RequestMapping("/functions")
@RestController("sysFunctionController.v1")
public class SysFunctionController extends BaseController<FunctionService, FunctionVO, FunctionQuery, FunctionDTO, Long> {

}
