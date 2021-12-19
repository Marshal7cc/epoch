package org.epoch.starter.core.algorithm.tree;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 树节点
 *
 * @author Marshal
 * @date 2020/1/21
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreeNode<T> {
    private String id;

    private String label;

    private List<TreeNode<T>> children;

    private String parentId;

    private boolean hasParent = false;

    private boolean hasChildren = false;

    public void initChildren() {
        this.children = new ArrayList<>();
    }
}
