package org.epoch.iam.api.controller.v1;


import io.swagger.annotations.Api;
import org.epoch.iam.api.SysFunctionApi;
import org.epoch.iam.config.SwaggerTags;
import org.epoch.iam.domain.entity.SysFunction;
import org.epoch.iam.domain.repository.SysFunctionRepository;
import org.epoch.mybatis.common.CommonController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marshal
 */
@Api(tags = SwaggerTags.FUNCTION)
@RequestMapping("/functions")
@RestController("sysFunctionController.v1")
public class SysFunctionController extends CommonController<SysFunction, SysFunctionRepository> implements SysFunctionApi {

}
