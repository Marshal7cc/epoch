package org.epoch.iam.api;


import org.epoch.iam.api.dto.FunctionDTO;
import org.epoch.iam.api.query.FunctionQuery;
import org.epoch.iam.api.vo.FunctionVO;
import org.epoch.iam.domain.entity.SysFunction;
import org.epoch.web.common.BaseFacade;

/**
 * @author Marshal
 */
public interface SysFunctionApi extends BaseFacade<FunctionDTO, FunctionVO, FunctionQuery, Long> {
}
