package org.epoch.iam.api.controller.v1;


import io.swagger.annotations.Api;
import org.epoch.core.rest.Response;
import org.epoch.core.rest.ResponseEntity;
import org.epoch.iam.api.SysLangApi;
import org.epoch.iam.config.SwaggerTags;
import org.epoch.iam.domain.entity.SysLang;
import org.epoch.iam.domain.repository.SysLangRepository;
import org.epoch.mybatis.common.CommonController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marshal
 */
@Api(tags = SwaggerTags.LANG)
@RequestMapping("/langs")
@RestController("sysLangController.v1")
public class SysLangController extends CommonController<SysLang, SysLangRepository> implements SysLangApi {

    @Override
    public ResponseEntity queryForOptions() {
        return Response.success(repository.selectAll());
    }
}
