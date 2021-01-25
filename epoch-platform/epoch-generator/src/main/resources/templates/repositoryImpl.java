package [(${package})].service.impl;


import org.epoch.mybatis.repository.impl.BaseRepositoryImpl;
import [(${package})].domain.entity.[(${entityName})];
import [(${package})].domain.repository.[(${repositoryName})];
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author [(${authorName})]
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class [(${implName})] extends BaseRepositoryImpl<[(${entityName})]> implements [(${repositoryName})]{

}
