package org.epoch.iam.api.controller.v1;


import io.swagger.annotations.Api;
import org.epoch.iam.api.SysFunctionApi;
import org.epoch.iam.api.dto.FunctionDTO;
import org.epoch.iam.api.query.FunctionQuery;
import org.epoch.iam.api.vo.FunctionVO;
import org.epoch.iam.config.SwaggerTags;
import org.epoch.iam.domain.entity.SysFunction;
import org.epoch.iam.domain.repository.SysFunctionRepository;
import org.epoch.web.facade.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marshal
 */
@Api(tags = SwaggerTags.FUNCTION)
@RequestMapping("/functions")
@RestController("sysFunctionController.v1")
public class SysFunctionController extends BaseController<SysFunctionRepository, FunctionDTO, FunctionVO, FunctionQuery, SysFunction, Long> implements SysFunctionApi {

}
