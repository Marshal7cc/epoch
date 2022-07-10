package org.epoch.iam.api;


import org.epoch.iam.api.dto.ResourceDTO;
import org.epoch.iam.api.query.ResourceQuery;
import org.epoch.iam.api.vo.ResourceVO;
import org.epoch.web.facade.BaseFacade;


/**
 * @author Marshal
 */
public interface SysResourceApi extends BaseFacade<ResourceDTO, ResourceVO, ResourceQuery,Long> {
}
