package org.epoch.iam.domain.service;

import org.epoch.data.service.impl.BaseServiceImpl;
import org.epoch.iam.api.dto.FunctionDTO;
import org.epoch.iam.domain.entity.SysFunction;
import org.epoch.iam.domain.repository.SysFunctionRepository;
import org.springframework.stereotype.Service;

/**
 * @author Marshal
 * @since 2022/7/21
 */
@Service
public class FunctionService extends BaseServiceImpl<SysFunctionRepository, FunctionDTO, SysFunction, Long> {
}
