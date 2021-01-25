package org.epoch.iam.api.controller.v1;


import org.epoch.core.rest.ResponseEntity;
import org.epoch.core.rest.Response;

import org.epoch.mybatis.common.CommonController;
import org.epoch.iam.config.SwaggerTags;
import org.epoch.iam.domain.entity.SysPrompt;
import org.epoch.iam.api.SysPromptApi;
import org.epoch.iam.domain.repository.SysPromptRepository;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @author Marshal
 */
@Api(tags = SwaggerTags.PROMPT)
@RequestMapping("/prompts")
@RestController("sysPromptController.v1")
public class SysPromptController extends CommonController<SysPrompt, SysPromptRepository> implements SysPromptApi {

    @Override
    public ResponseEntity queryForI18n(String langCode) {
        return Response.success(repository.queryForI18n(langCode));
    }
}
