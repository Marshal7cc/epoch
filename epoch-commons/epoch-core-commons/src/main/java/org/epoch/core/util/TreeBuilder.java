package org.epoch.core.util;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

/**
 * 树结构构造器
 *
 * @author Marshal
 * @date 2020/1/21
 */
public class TreeBuilder {
    private TreeBuilder() {
    }

    public static <T> List<TreeNode> build(List<TreeNode> nodes) {
        if (CollectionUtils.isEmpty(nodes)) {
            return nodes;
        }

        List<TreeNode> topNodes = new ArrayList<>();

        Map<String, TreeNode> dataMap = nodes.stream().collect(Collectors.toMap(TreeNode::getId, Function.identity()));
        for (TreeNode n : nodes) {
            String parentId = n.getParentId();
            if (StringUtils.isEmpty(parentId)) {
                topNodes.add(n);
                continue;
            }
            TreeNode parentNode = dataMap.get(parentId);
            if (Objects.isNull(parentNode)) {
                continue;
            }
            parentNode.addChild(n);
        }

        return topNodes;
    }
}
