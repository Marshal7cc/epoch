package org.epoch.iam.api;


import org.epoch.iam.api.dto.UserDTO;
import org.epoch.iam.api.query.UserQuery;
import org.epoch.iam.api.vo.UserVO;
import org.epoch.web.common.BaseFacade;

/**
 * @author Marshal
 */
public interface SysUserApi extends BaseFacade<UserDTO, UserVO, UserQuery, Long> {
}
