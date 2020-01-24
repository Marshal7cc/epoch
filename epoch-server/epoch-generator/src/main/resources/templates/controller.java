package [(${package})].controller;

[# th:each="item:${import}"]
import [(${item})];
[/]
import [(${package})].entity.[(${dtoName})];
import [(${package})].api.[(${apiName})];
import [(${package})].service.[(${serviceName})];
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class [(${controllerName})] implements [(${apiName})]{

    @Autowired
    private [(${serviceName})] service;

    public ResponseEntity query(int page,
                                int pageSize,
                                [(${dtoName})] dto) {
        return ResponseUtil.responseOk(service.select(dto,page,pageSize));
    }

    public ResponseEntity submit([(${dtoName})] dto){
        service.submit(dto);
        return ResponseUtil.responseOk();
    }

    public ResponseEntity remove(List<[(${dtoName})]> list) {
        service.batchDelete(list);
        return ResponseUtil.responseOk();
    }

    public ResponseEntity queryById(Long id) {
        return ResponseUtil.responseOk(service.selectByPrimaryKey(id));
    }

}
