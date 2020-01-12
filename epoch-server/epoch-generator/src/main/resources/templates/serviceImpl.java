package [(${package})].service.impl;

[# th:each="item:${import}"]
import [(${item})];
[/]
import [(${package})].entity.[(${dtoName})];
import [(${package})].service.[(${serviceName})];
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class [(${implName})] extends BaseServiceImpl<[(${dtoName})]> implements [(${serviceName})]{

}