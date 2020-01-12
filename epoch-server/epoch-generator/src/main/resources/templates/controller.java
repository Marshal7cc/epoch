package [(${package})].controller;

[# th:each="item:${import}"]
import [(${item})];
[/]
import [(${package})].entity.[(${dtoName})];
import [(${package})].service.[(${serviceName})];
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class [(${controllerName})] extends BaseController{

    @Autowired
    private [(${serviceName})] service;


    @RequestMapping(value = "[(${queryUrl})]")
    public ResponseData query([(${dtoName})] dto,
        @RequestParam int pageNum,
        @RequestParam int pageSize,
        HttpServletRequest request) {
        SessionContext sessionContext = RequestHelper.getSessionContext(request);
        return new ResponseData(service.select(dto,pageNum,pageSize));
    }

    @RequestMapping(value = "[(${submitUrl})]")
    public ResponseData update(@RequestBody [(${dtoName})] dto,
    HttpServletRequest request){
        if (getValidator().hasError(dto)) {
            return new ResponseData(false, getValidator().getErrors(dto));
        }
        service.save(dto);
        return new ResponseData();
    }

    @RequestMapping(value = "[(${removeUrl})]")
    public ResponseData delete(@RequestParam("selectedIds") Long[] selectedIds) {
        service.batchDelete(selectedIds);
        return new ResponseData(true, "删除成功");
    }
}