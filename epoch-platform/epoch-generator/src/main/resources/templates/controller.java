package [(${package})].controller;

import org.epoch.mybatis.common.CommonController;
import [(${package})].api.[(${apiName})];
import [(${package})].domain.entity.[(${entityName})];
import [(${package})].domain.repository.[(${repositoryName})];
import org.springframework.web.bind.annotation.*;

/**
 * @author [(${authorName})]
 */
@RestController("[(${controllerName})].v1")
public class [(${controllerName})] extends CommonController<[(${entityName})], [(${repositoryName})]> implements [(${apiName})]{

}
