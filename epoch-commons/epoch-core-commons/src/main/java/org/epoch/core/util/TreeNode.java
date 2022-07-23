package org.epoch.core.util;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

/**
 * 树节点
 *
 * @author Marshal
 * @date 2020/1/21
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

    public void addChild(TreeNode child) {
        if (CollectionUtils.isEmpty(this.children)) {
            initChildren();
        }
        this.children.add(child);
    }
}
