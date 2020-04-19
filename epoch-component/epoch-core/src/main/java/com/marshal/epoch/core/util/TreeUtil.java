package com.marshal.epoch.core.util;


import com.marshal.epoch.core.dto.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * TreeUtil
 */
public class TreeUtil {

    private TreeUtil() {
    }

    public static List<TreeNode> build(List<TreeNode> nodes) {
        if (nodes == null) {
            return null;
        }
        List<TreeNode> topNodes = new ArrayList<>();
        nodes.forEach(node -> {
            String pid = node.getParentId();
            if (pid == null) {
                topNodes.add(node);
                return;
            }
            for (TreeNode n : nodes) {
                String id = n.getId();
                if (id != null && id.equals(pid)) {
                    if (n.getChildren() == null)
                        n.initChildren();
                    n.getChildren().add(node);
                    node.setHasParent(true);
                    n.setHasChildren(true);
                    n.setHasParent(true);
                    return;
                }
            }
            if (topNodes.isEmpty())
                topNodes.add(node);
        });
        return topNodes;
    }
}
