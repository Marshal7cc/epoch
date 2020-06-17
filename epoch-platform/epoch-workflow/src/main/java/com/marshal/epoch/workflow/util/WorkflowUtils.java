package com.marshal.epoch.workflow.util;


import java.util.*;

import com.marshal.epoch.hr.domain.entity.HrEmployee;
import com.marshal.epoch.hr.domain.entity.HrPosition;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.FormProperty;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.GroupEntityImpl;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 工作流相关工具类
 *
 * @author: Marshal
 * @date: 2019/2/5
 */
@Slf4j
public class WorkflowUtils {

    /**
     * 将employee转换至工作流用户
     *
     * @param emp HR employee
     * @return
     */
    public static UserEntity toWorkflowUser(HrEmployee emp) {
        UserEntityImpl entity = new UserEntityImpl();
        if (emp == null) {
            return entity;
        }
        entity.setId(emp.getEmployeeCode());
        String empName = emp.getName();
        entity.setFirstName(StringUtils.defaultIfEmpty(empName, "UNKNOWN"));
        entity.setLastName("");
        entity.setEmail(emp.getEmail());
        entity.setRevision(1);
        return entity;
    }

    /**
     * 将position转换至工作流组
     *
     * @param position 职位
     * @return
     */
    public static GroupEntity toWorkflowGroup(HrPosition position) {
        GroupEntityImpl groupEntity = new GroupEntityImpl();
        if (position == null) {
            return groupEntity;
        }
        groupEntity.setRevision(1);
        groupEntity.setId(position.getPositionCode());
        groupEntity.setName(position.getName());
        groupEntity.setType("assignment");
        return groupEntity;
    }

    /**
     * 返回第二个日期与第一个日期之间相差的秒数
     *
     * @return 可能为负数，表明第二个日期在第一个日期之前
     */
    public static Long secondsBetweenDate(Date firstDate, Date secondDate) {
        Long interval = (Long) ((secondDate.getTime() - firstDate.getTime()) / 1000);
        return interval;
    }

    /**
     * 通过ID获取表单属性
     *
     * @param properties
     * @param id
     * @return
     */
    public static FormProperty getFormPropertyById(List<FormProperty> properties, String id) {
        FormProperty formProperty = null;
        if (CollectionUtils.isNotEmpty(properties)) {
            for (FormProperty item : properties) {
                if (id.equals(item.getId())) {
                    formProperty = item;
                    break;
                }
            }
        }

        return formProperty;
    }

}
