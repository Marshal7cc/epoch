package org.epoch.iam.domain.service;

import org.epoch.data.service.impl.BaseServiceImpl;
import org.epoch.iam.domain.dto.PromptDTO;
import org.epoch.iam.domain.repository.SysPromptRepository;
import org.epoch.iam.infrastructure.repository.entity.SysPrompt;
import org.springframework.stereotype.Service;

/**
 * @author Marshal
 * @since 2022/7/21
 */
@Service
public class PromptService extends BaseServiceImpl<SysPromptRepository, PromptDTO, SysPrompt, Long> {
}
