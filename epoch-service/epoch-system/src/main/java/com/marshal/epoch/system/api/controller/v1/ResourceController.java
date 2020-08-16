package com.marshal.epoch.system.api.controller.v1;

import com.marshal.epoch.mybatis.base.BaseController;
import com.marshal.epoch.system.api.ResourceApi;
import com.marshal.epoch.system.domain.entity.Resource;
import com.marshal.epoch.system.domain.repository.ResourceRepository;
import org.springframework.web.bind.annotation.*;

/**
 * @author Marshal
 */
@RequestMapping("/tests")
@RestController("ResourceController.v1")
public class ResourceController extends BaseController<Resource, ResourceRepository> implements ResourceApi{

}
