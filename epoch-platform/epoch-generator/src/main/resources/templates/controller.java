package [(${package})].controller;

import com.marshal.epoch.mybatis.base.BaseController;
import [(${package})].api.[(${apiName})];
import [(${package})].domain.entity.[(${entityName})];
import [(${package})].domain.repository.[(${repositoryName})];
import org.springframework.web.bind.annotation.*;

/**
 * @author [(${authorName})]
 */
@RestController("[(${controllerName})].v1")
public class [(${controllerName})] extends BaseController<[(${entityName})], [(${repositoryName})]> implements [(${apiName})]{

}
