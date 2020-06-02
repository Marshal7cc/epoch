package com.marshal.epoch.system.api.controller.v1;


import com.marshal.epoch.core.rest.ResponseEntity;
import com.marshal.epoch.core.rest.Response;
import com.marshal.epoch.mybatis.base.BaseController;
import com.marshal.epoch.system.api.SysLangApi;
import com.marshal.epoch.system.config.SwaggerTags;
import com.marshal.epoch.system.domain.entity.SysLang;
import com.marshal.epoch.system.domain.repository.SysLangRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Marshal
 */
@Api(tags = SwaggerTags.LANG)
@RequestMapping("/langs")
@RestController("sysLangController.v1")
public class SysLangController extends BaseController<SysLang, SysLangRepository> implements SysLangApi {

    @Override
    public ResponseEntity queryForOptions() {
        return Response.success(repository.selectAll());
    }
}
