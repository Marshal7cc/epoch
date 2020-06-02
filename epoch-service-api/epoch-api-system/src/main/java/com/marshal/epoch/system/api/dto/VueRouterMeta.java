package com.marshal.epoch.system.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Vue路由 Meta
 * @author Marshal
 */
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VueRouterMeta implements Serializable {

    private String title;
    private String icon;
    private Boolean breadcrumb = true;

}
