package org.epoch.hr.api.controller.v1;

import org.epoch.hr.domain.entity.HrPosition;
import org.epoch.hr.api.HrPositionApi;
import org.epoch.hr.domain.repository.HrPositionRepository;
import org.epoch.mybatis.common.CommonController;
import org.springframework.web.bind.annotation.*;

/**
 * @author Marshal
 */
@RequestMapping("/positions")
@RestController("positionController.v1")
public class HrPositionController extends CommonController<HrPosition, HrPositionRepository> implements HrPositionApi {

}
