package org.epoch.iam.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.Tag;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author Marshal Yuan
 * @date 2020/5/31
 */
@Configuration
public class SwaggerTags {

    public static final String USER = "user";
    public static final String ROLE = "role";
    public static final String RESOURCE = "resource";
    public static final String PROMPT = "prompt";
    public static final String MENU = "menu";
    public static final String LANG = "lang";
    public static final String FUNCTION = "function";

    @Autowired
    protected SwaggerTags(Docket docket) {
        docket.tags(new Tag(USER, "系统用户"),
                new Tag(ROLE, "系统角色"),
                new Tag(RESOURCE, "系统资源"),
                new Tag(PROMPT, "系统提示"),
                new Tag(MENU, "系统菜单"),
                new Tag(LANG, "系统语言"),
                new Tag(FUNCTION, "系统功能"));
    }
}
