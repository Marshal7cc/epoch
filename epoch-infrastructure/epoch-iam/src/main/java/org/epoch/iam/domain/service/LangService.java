package org.epoch.iam.domain.service;

import org.epoch.data.service.impl.BaseServiceImpl;
import org.epoch.iam.domain.dto.LangDTO;
import org.epoch.iam.domain.repository.SysLangRepository;
import org.epoch.iam.infrastructure.repository.entity.SysLang;
import org.springframework.stereotype.Service;

/**
 * @author Marshal
 * @since 2022/7/21
 */
@Service
public class LangService extends BaseServiceImpl<SysLangRepository, LangDTO, SysLang, Long> {
}
