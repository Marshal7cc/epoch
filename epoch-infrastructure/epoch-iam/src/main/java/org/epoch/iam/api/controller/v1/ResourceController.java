package org.epoch.iam.api.controller.v1;

import org.epoch.mybatis.common.CommonController;
import org.epoch.iam.api.ResourceApi;
import org.epoch.iam.domain.entity.Resource;
import org.epoch.iam.domain.repository.ResourceRepository;
import org.springframework.web.bind.annotation.*;

/**
 * @author Marshal
 */
@RequestMapping("/tests")
@RestController("ResourceController.v1")
public class ResourceController extends CommonController<Resource, ResourceRepository> implements ResourceApi {

}
