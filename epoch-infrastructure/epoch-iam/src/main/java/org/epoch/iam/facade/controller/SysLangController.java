package org.epoch.iam.facade.controller;


import io.swagger.annotations.Api;
import org.epoch.iam.domain.dto.LangDTO;
import org.epoch.iam.domain.service.LangService;
import org.epoch.iam.facade.query.LangQuery;
import org.epoch.iam.facade.vo.LangVO;
import org.epoch.iam.infrastructure.configuration.SwaggerTags;
import org.epoch.web.facade.controller.BaseController;
import org.epoch.web.rest.Response;
import org.epoch.web.rest.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marshal
 */
@Api(tags = SwaggerTags.LANG)
@RequestMapping("/langs")
@RestController("sysLangController.v1")
public class SysLangController extends BaseController<LangService, LangVO, LangQuery, LangDTO, Long> {

    public ResponseEntity queryForOptions() {
        return Response.success();
    }
}
