package org.epoch.iam.api;


import org.epoch.iam.api.dto.FunctionDTO;
import org.epoch.iam.api.query.FunctionQuery;
import org.epoch.iam.api.vo.FunctionVO;
import org.epoch.web.facade.BaseFacade;

/**
 * @author Marshal
 */
public interface SysFunctionApi extends BaseFacade<FunctionDTO, FunctionVO, FunctionQuery, Long> {
}
