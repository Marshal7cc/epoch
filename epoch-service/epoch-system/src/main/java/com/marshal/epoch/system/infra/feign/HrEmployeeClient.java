package com.marshal.epoch.system.infra.feign;

import com.marshal.epoch.hr.api.HrEmployeeApi;
import com.marshal.epoch.web.feign.FeignRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Marshal
 */
@FeignClient(value = "epoch-hr", path = "/employees/", configuration = FeignRequestInterceptor.class)
public interface HrEmployeeClient extends HrEmployeeApi {
}
