package com.marshal.epoch.system.feign;

import com.marshal.epoch.hr.api.HrEmployeeApi;
import com.marshal.epoch.system.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "epoch-hr", configuration = FeignConfig.class)
public interface HrEmployeeClient extends HrEmployeeApi {
}
