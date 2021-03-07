package org.epoch.workflow.entity;

import java.util.Date;
import javax.persistence.Table;

import org.epoch.mybatis.domain.entity.BaseAuditEntity;
import org.hibernate.validator.constraints.Length;

@Table(name = "act_cus_deliver")
public class ActCusDeliver extends BaseAuditEntity {

    @Length(max = 30)
    private String employeeCode; //员工代码

    @Length(max = 30)
    private String deliverCode; //转交人员工代码

    private Date deliverStartDate; //有效期从

    private Date deliverEndDate; //有效期至

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getDeliverCode() {
        return deliverCode;
    }

    public void setDeliverCode(String deliverCode) {
        this.deliverCode = deliverCode;
    }

    public Date getDeliverStartDate() {
        return deliverStartDate;
    }

    public void setDeliverStartDate(Date deliverStartDate) {
        this.deliverStartDate = deliverStartDate;
    }

    public Date getDeliverEndDate() {
        return deliverEndDate;
    }

    public void setDeliverEndDate(Date deliverEndDate) {
        this.deliverEndDate = deliverEndDate;
    }

}
