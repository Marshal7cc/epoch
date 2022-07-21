package org.epoch.gateway.endpoints;

import org.epoch.web.rest.Response;
import org.epoch.web.rest.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author Marshal
 * @date 2020/2/11
 */
@RestController
public class GatewayEndpoint {

    @RequestMapping("fallback/{name}")
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public Mono<ResponseEntity> systemFallback(@PathVariable String name) {
        String response = String.format("访问%s超时或者服务不可用", name);
        return Mono.just(Response.error(response));
    }
}
