package org.epoch.iam.api.controller.v1;


import io.swagger.annotations.Api;
import org.epoch.iam.api.dto.LangDTO;
import org.epoch.iam.api.query.LangQuery;
import org.epoch.iam.api.vo.LangVO;
import org.epoch.iam.config.SwaggerTags;
import org.epoch.iam.domain.service.LangService;
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
