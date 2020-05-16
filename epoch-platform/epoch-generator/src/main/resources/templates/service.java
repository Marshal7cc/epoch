package [(${package})].service;

[# th:each="item:${import}"]
import [(${item})];
[/]
import [(${package})].entity.[(${dtoName})];

public interface [(${serviceName})] extends BaseService<[(${dtoName})]>{

}