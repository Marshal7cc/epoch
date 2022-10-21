package org.epoch.iam.domain.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Vue路由
 * @author Marshal
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VueRouter implements Serializable {

    @JsonIgnore
    private Long id;
    @JsonIgnore
    private Long parentId;

    private String path;
    private String name;
    private String component;
    private String redirect;
    private VueRouterMeta meta;
    private Boolean hidden = false;
    private Boolean alwaysShow = false;
    private List<VueRouter> children;

    @JsonIgnore
    private Boolean hasParent = false;

    @JsonIgnore
    private Boolean hasChildren = false;

    public void initChildren(){
        this.children = new ArrayList<>();
    }

}
