package com.marshal.epoch.hr.api.controller.v1;


import com.marshal.epoch.hr.domain.entity.HrCompany;
import com.marshal.epoch.hr.api.HrCompanyApi;
import com.marshal.epoch.hr.domain.repository.HrCompanyRepository;
import com.marshal.epoch.mybatis.base.BaseController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Marshal
 */
@RequestMapping("/companies")
@RestController("companyController.v1")
public class HrCompanyController extends BaseController<HrCompany, HrCompanyRepository> implements HrCompanyApi {

}
