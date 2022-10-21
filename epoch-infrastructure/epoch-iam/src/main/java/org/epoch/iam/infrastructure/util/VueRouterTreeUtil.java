package org.epoch.iam.infrastructure.util;

import java.util.ArrayList;
import java.util.List;

import org.epoch.iam.domain.dto.VueRouter;

/**
 * @author Marshal
 * @date 2020/1/22
 * 前端路由构建工具类
 */
public class VueRouterTreeUtil {

    private VueRouterTreeUtil() {
    }

    public static List<VueRouter> buildVueRouter(List<VueRouter> routes) {
        if (routes == null) {
            return null;
        }
        List<VueRouter> topRoutes = new ArrayList<>();
        routes.forEach(route -> {
            //父节点ID
            Long parentId = route.getParentId();
            //父节点ID为空则为顶级节点
            if (parentId == null) {
                topRoutes.add(route);
                return;
            }
            //遍历寻找route的parent节点并将之加入parent的children
            for (VueRouter parent : routes) {
                Long id = parent.getId();
                if (id != null && id.equals(parentId)) {
                    if (parent.getChildren() == null) {
                        parent.initChildren();
                    }
                    parent.getChildren().add(route);
                    parent.setAlwaysShow(true);
                    parent.setHasChildren(true);
                    route.setHasParent(true);
                    parent.setHasParent(true);
                    return;
                }
            }
        });
        VueRouter router404 = new VueRouter();
        router404.setName("404");
        router404.setComponent("error-page/404");
        router404.setPath("*");

        topRoutes.add(router404);
        return topRoutes;
    }
}
