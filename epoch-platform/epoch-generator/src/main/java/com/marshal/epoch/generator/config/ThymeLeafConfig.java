package com.marshal.epoch.generator.config;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.FileTemplateResolver;

/**
 * 枚举单例 * ThymeLeaf的Text模式,用于生成文件
 *
 * @author Marshal
 * @date 2019/10/31
 */
public enum ThymeLeafConfig {

    /**
     * 文本模式
     */
    TEXT("TEXT");

    private TemplateEngine templateEngine;

    ThymeLeafConfig(String templateMode) {
        FileTemplateResolver templateResolver = new FileTemplateResolver();
        templateResolver.setPrefix(getTemplatePath());
        templateResolver.setTemplateMode(templateMode);
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }

    private String getTemplatePath() {
        return ThymeLeafConfig.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "templates/";
    }

    public static TemplateEngine getTemplateEngine() {
        return TEXT.templateEngine;
    }
}
