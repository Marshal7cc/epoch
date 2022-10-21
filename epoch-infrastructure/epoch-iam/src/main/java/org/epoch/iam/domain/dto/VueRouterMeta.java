package org.epoch.iam.domain.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

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
