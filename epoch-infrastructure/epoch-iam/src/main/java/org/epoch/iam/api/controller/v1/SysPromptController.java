package org.epoch.iam.api.controller.v1;


import io.swagger.annotations.Api;
import org.epoch.iam.api.dto.PromptDTO;
import org.epoch.iam.api.vo.PromptVO;
import org.epoch.iam.config.SwaggerTags;
import org.epoch.iam.domain.entity.SysPrompt;
import org.epoch.iam.domain.service.PromptService;
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
public class SysPromptController extends BaseController<PromptService, PromptDTO, PromptVO, SysPrompt, Long> {

    public ResponseEntity queryForI18n(String langCode) {
        return Response.success();
    }
}
