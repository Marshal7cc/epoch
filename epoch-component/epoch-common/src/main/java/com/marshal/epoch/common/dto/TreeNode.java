package com.marshal.epoch.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @auth: Marshal
 * @date: 2020/1/21
 * @desc: 树节点
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreeNode {

    private String id;

    private String label;

    private List<TreeNode> children;

    private String parentId;

    private boolean hasParent = false;

    private boolean hasChildren = false;

    public void initChildren() {
        this.children = new ArrayList<>();
    }

}