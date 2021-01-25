package org.epoch.hr.api.controller.v1;


import org.epoch.hr.domain.entity.HrCompany;
import org.epoch.hr.api.HrCompanyApi;
import org.epoch.hr.domain.repository.HrCompanyRepository;
import org.epoch.mybatis.common.CommonController;
import org.springframework.web.bind.annotation.*;

/**
 * @author Marshal
 */
@RequestMapping("/companies")
@RestController("companyController.v1")
public class HrCompanyController extends CommonController<HrCompany, HrCompanyRepository> implements HrCompanyApi {

}
