package com.marshal.epoch.system.api.controller.v1;


import com.marshal.epoch.mybatis.base.BaseController;
import com.marshal.epoch.system.config.SwaggerTags;
import com.marshal.epoch.system.domain.entity.SysFunction;
import com.marshal.epoch.system.api.SysFunctionApi;
import com.marshal.epoch.system.domain.repository.SysFunctionRepository;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @author Marshal
 */
@Api(tags = SwaggerTags.FUNCTION)
@RequestMapping("/functions")
@RestController("sysFunctionController.v1")
public class SysFunctionController extends BaseController<SysFunction, SysFunctionRepository> implements SysFunctionApi {

}
