package com.marshal.epoch.workflow.component;

import java.util.Arrays;
import java.util.List;

import io.micrometer.core.instrument.util.StringUtils;
import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;

/**
 * @auth: Marshal
 * @date: 2019/4/2
 * @desc:
 */
@Component
public class ApproveStrategy implements ActivitiBean {

//    @Autowired
//    HrEmployeeMapper employeeMapper;

    /**
     * 查找上级领导
     *
     * @param employeeCode
     * @return
     */
    public List<String> getDirector(String employeeCode) {
        return Arrays.asList("Marshal", "SunLei");
    }

    /**
     * 查找部门领导
     *
     * @param employeeCode
     * @return
     */
    public List<String> getDeptDirector(String employeeCode) {
        return Arrays.asList("Marshal", "SunLei");
    }

    /**
     * 查找指定人
     *
     * @param employeeCode
     * @return
     */
    public List<String> getEmployeeCode(String employeeCode) {
        if (StringUtils.isBlank(employeeCode)) {
            return Arrays.asList("ADMIN");
        }
        return Arrays.asList(employeeCode);
    }

    /**
     * 根据岗位code动态查找审批人
     *
     * @param positionCode
     * @return
     */
    public List<String> getPositionEmp(String positionCode) {
        return Arrays.asList("Marshal", "SunLei");
    }


    /**
     * Test
     *
     * @param execution
     * @return
     */
    public List<String> getNext(DelegateExecution execution) {
        return Arrays.asList("Marshal", "SunLei");
    }

}
