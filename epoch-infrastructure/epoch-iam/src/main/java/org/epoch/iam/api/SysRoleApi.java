package org.epoch.iam.api;


import org.epoch.iam.api.dto.RoleDTO;
import org.epoch.iam.api.query.RoleQuery;
import org.epoch.iam.api.vo.RoleVO;
import org.epoch.web.facade.BaseFacade;

/**
 * @author Marshal
 */
public interface SysRoleApi extends BaseFacade<RoleDTO, RoleVO, RoleQuery,Long> {
}
