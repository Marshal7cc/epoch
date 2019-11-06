package [(${package})].mapper;

[# th:each="item:${import}"]
import [(${item})];
[/]
import [(${package})].entity.[(${dtoName})];

public interface [(${mapperName})] extends Mapper<[(${dtoName})]>{

}