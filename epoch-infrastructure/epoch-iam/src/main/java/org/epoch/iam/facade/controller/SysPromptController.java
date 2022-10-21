package org.epoch.iam.facade.controller;


import io.swagger.annotations.Api;
import org.epoch.iam.domain.dto.PromptDTO;
import org.epoch.iam.domain.service.PromptService;
import org.epoch.iam.facade.query.PromptQuery;
import org.epoch.iam.facade.vo.PromptVO;
import org.epoch.iam.infrastructure.configuration.SwaggerTags;
import org.epoch.web.facade.controller.BaseController;
import org.epoch.web.rest.Response;
import org.epoch.web.rest.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marshal
 */
@Api(tags = SwaggerTags.PROMPT)
@RequestMapping("/prompts")
@RestController("sysPromptController.v1")
public class SysPromptController extends BaseController<PromptService, PromptVO, PromptQuery, PromptDTO, Long> {

    public ResponseEntity queryForI18n(String langCode) {
        return Response.success();
    }
}
