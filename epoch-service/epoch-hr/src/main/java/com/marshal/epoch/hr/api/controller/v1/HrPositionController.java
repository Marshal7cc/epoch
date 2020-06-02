package com.marshal.epoch.hr.api.controller.v1;

import com.marshal.epoch.hr.domain.entity.HrPosition;
import com.marshal.epoch.hr.api.HrPositionApi;
import com.marshal.epoch.hr.domain.repository.HrPositionRepository;
import com.marshal.epoch.mybatis.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Marshal
 */
@RequestMapping("/positions")
@RestController("positionController.v1")
public class HrPositionController extends BaseController<HrPosition, HrPositionRepository> implements HrPositionApi {

}
