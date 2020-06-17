package com.marshal.epoch.generator.infra.util;


import com.marshal.epoch.core.rest.Response;
import com.marshal.epoch.core.rest.ResponseEntity;
import com.marshal.epoch.generator.api.dto.*;
import com.marshal.epoch.mybatis.service.BaseRepository;
import com.marshal.epoch.mybatis.service.impl.BaseRepositoryImpl;
import com.marshal.epoch.generator.config.ThymeLeafConfig;
import com.marshal.epoch.generator.infra.enums.FileType;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Marshal
 */
@Component
public class TemplateUtil {

    private static final Pattern LINE_PATTERN = Pattern.compile("_(\\w)");

    private TemplateUtil() {
    }

    public static String columnToCamel(String str) {
        str = str.toLowerCase();
        Matcher matcher = LINE_PATTERN.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        String s = sb.toString();
        return s;
    }

    public static String camelToColumn(String str) {
        return str.replaceAll("[A-Z]", "_$0").toLowerCase();
    }

    public static byte[] createFtlInfoByType(FileType type, DbTable table, GeneratorConfig generatorConfig)
            throws IOException {
        String parentPackagePath = generatorConfig.getParentPackagePath();
        String packagePath = generatorConfig.getPackagePath();
        String pac = parentPackagePath + "/" + packagePath;
        FtlInfo info = new FtlInfo();
        List<String> importPackages = new ArrayList<>();
        if (type == FileType.Controller) {
            importPackages.add(ResponseEntity.class.getName());
            importPackages.add(Response.class.getName());
        } else if (type == FileType.Api) {
            importPackages.add(ResponseEntity.class.getName());
        } else if (type == FileType.Mapper) {
            importPackages.add("tk.mybatis.mapper.common.Mapper");
        } else if (type == FileType.Repository) {
            importPackages.add(BaseRepository.class.getName());
        } else if (type == FileType.Impl) {
            importPackages.add(BaseRepositoryImpl.class.getName());
            importPackages.add("org.springframework.stereotype.Service");
        }
        pac = pac.replaceAll("/", ".");
        info.setPackageName(pac);
        info.setImportName(importPackages);
        List<DbColumn> columns = table.getColumns();

        List<XmlColumnsInfo> columnsInfos = new ArrayList<>();

        for (DbColumn column : columns) {
            XmlColumnsInfo columnsInfo = new XmlColumnsInfo();
            columnsInfo.setTableColumnsName(columnToCamel(column.getName()));
            columnsInfo.setDbColumnsName(column.getName());
            columnsInfo.setJdbcType(column.getJdbcType());
            columnsInfos.add(columnsInfo);
        }
        info.setColumnsInfo(columnsInfos);
        return createFtl(info, type, generatorConfig);
    }

    public static byte[] createFtl(FtlInfo ftlInfo, FileType type, GeneratorConfig generatorConfig) throws IOException {
        TemplateEngine templateEngine = ThymeLeafConfig.getTemplateEngine();
        Context context = new Context();
        Map<String, Object> map = new HashMap<>(10);
        String templateName = null;
        if (type == FileType.Controller) {
            templateName = "controller.java";
        } else if (type == FileType.Mapper) {
            templateName = "mapper.java";
        } else if (type == FileType.Repository) {
            templateName = "repository.java";
        } else if (type == FileType.Impl) {
            templateName = "repositoryImpl.java";
        } else if (type == FileType.Api) {
            templateName = "Api.java";
        }
        if (templateName != null) {
            String controllerName = generatorConfig.getEntityName() + FileType.Controller.getFileSuffix();
            String repositoryName = generatorConfig.getEntityName() + FileType.Repository.getFileSuffix();
            String serviceImplName = generatorConfig.getEntityName() + FileType.Impl.getFileSuffix();
            String apiName = generatorConfig.getEntityName() + FileType.Api.getFileSuffix();
            String mapperName = generatorConfig.getEntityName() + FileType.Mapper.getFileSuffix();
            String mapperXmlName = generatorConfig.getEntityName() + FileType.MapperXml.getFileSuffix();

            map.put("authorName", generatorConfig.getAuthorName());
            map.put("package", ftlInfo.getPackageName());
            map.put("import", ftlInfo.getImportName());
            map.put("name", ftlInfo.getFileName());
            map.put("entityName", generatorConfig.getEntityName());
            map.put("apiName", apiName.substring(0, apiName.indexOf('.')));
            map.put("controllerName", controllerName.substring(0, controllerName.indexOf('.')));
            map.put("implName", serviceImplName.substring(0, serviceImplName.indexOf('.')));
            map.put("repositoryName", repositoryName.substring(0, repositoryName.indexOf('.')));
            map.put("mapperName", mapperName.substring(0, mapperName.indexOf('.')));
            map.put("xmlName", mapperXmlName.substring(0, mapperXmlName.indexOf('.')));
            map.put("columnsInfo", ftlInfo.getColumnsInfo());
            String url = generatorConfig.getEntityName().toLowerCase();
            map.forEach((k, v) -> {
                context.setVariable(k, v);
            });
            return templateEngine.process(templateName, context).getBytes();
        }
        return null;
    }


    /**
     * 添加文件作者注释
     *
     * @param sb         文件字符
     * @param authorName 作者名称
     */
    public static void appendAuthor(StringBuilder sb, String authorName) {
        sb.append("\r\n");
        sb.append("/**\r\n");
        sb.append(" * @author ");
        sb.append(authorName);
        sb.append("\r\n");
        sb.append(" */");
    }
}
