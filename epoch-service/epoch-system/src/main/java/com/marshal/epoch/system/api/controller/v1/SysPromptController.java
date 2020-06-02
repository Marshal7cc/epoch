package com.marshal.epoch.system.api.controller.v1;


import com.marshal.epoch.core.rest.ResponseEntity;
import com.marshal.epoch.core.rest.Response;

import com.marshal.epoch.mybatis.base.BaseController;
import com.marshal.epoch.system.config.SwaggerTags;
import com.marshal.epoch.system.domain.entity.SysPrompt;
import com.marshal.epoch.system.api.SysPromptApi;
import com.marshal.epoch.system.domain.repository.SysPromptRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Marshal
 */
@Api(tags = SwaggerTags.PROMPT)
@RequestMapping("/prompts")
@RestController("sysPromptController.v1")
public class SysPromptController extends BaseController<SysPrompt, SysPromptRepository> implements SysPromptApi {

    @Override
    public ResponseEntity queryForI18n(String langCode) {
        return Response.success(repository.queryForI18n(langCode));
    }
}
