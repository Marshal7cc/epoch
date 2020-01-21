package com.marshal.epoch.generator.util;


import com.marshal.epoch.core.dto.ResponseEntity;
import com.marshal.epoch.core.service.BaseService;
import com.marshal.epoch.core.service.impl.BaseServiceImpl;
import com.marshal.epoch.core.util.RequestHelper;
import com.marshal.epoch.core.util.ResponseUtil;
import com.marshal.epoch.generator.config.ThymeLeafConfig;
import com.marshal.epoch.generator.dto.*;
import com.marshal.epoch.generator.enums.FileType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class TemplateUtil {

    private TemplateUtil() {
    }

    public static String columnToCamel(String str) {
        Pattern linePattern = Pattern.compile("_(\\w)");
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
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

    public static byte[] createFtlInfoByType(FileType type, DBTable table, GeneratorConfig generatorConfig) throws IOException {
        String parentPackagePath = generatorConfig.getParentPackagePath();
        String packagePath = generatorConfig.getPackagePath();
        String pac = parentPackagePath + "/" + packagePath;
        FtlInfo info = new FtlInfo();
        List<String> importPackages = new ArrayList<>();
        if (type == FileType.Controller) {
            importPackages.add(ResponseEntity.class.getName());
            importPackages.add(ResponseUtil.class.getName());
        } else if (type == FileType.Api) {
            importPackages.add(ResponseEntity.class.getName());
        } else if (type == FileType.Mapper) {
            importPackages.add("tk.mybatis.mapper.common.Mapper");
        } else if (type == FileType.Service) {
            importPackages.add(BaseService.class.getName());
        } else if (type == FileType.Impl) {
            importPackages.add(BaseServiceImpl.class.getName());
            importPackages.add("org.springframework.stereotype.Service");
        }
        pac = pac.replaceAll("/", ".");
        info.setPackageName(pac);
        info.setImportName(importPackages);
        List<DBColumn> columns = table.getColumns();

        List<XmlColumnsInfo> columnsInfos = new ArrayList<>();

        for (DBColumn column : columns) {
            XmlColumnsInfo columnsInfo = new XmlColumnsInfo();
            columnsInfo.setTableColumnsName(columnToCamel(column.getName()));
            columnsInfo.setDBColumnsName(column.getName());
            columnsInfo.setJdbcType(column.getJdbcType());
            columnsInfos.add(columnsInfo);
        }
        info.setColumnsInfo(columnsInfos);
        return createFtl(info, type, generatorConfig);
    }

    public static byte[] createFtl(FtlInfo ftlInfo, FileType type, GeneratorConfig generatorConfig) throws IOException {
        TemplateEngine templateEngine = ThymeLeafConfig.getTemplateEngine();
        Context context = new Context();
        Map<String, Object> map = new HashMap<>();
        String templateName = null;
        if (type == FileType.Controller) {
            templateName = "controller.java";
        } else if (type == FileType.Mapper) {
            templateName = "mapper.java";
        } else if (type == FileType.Service) {
            templateName = "service.java";
        } else if (type == FileType.Impl) {
            templateName = "serviceImpl.java";
        } else if (type == FileType.Api) {
            templateName = "Api.java";
        }
        if (templateName != null) {
            String controllerName = generatorConfig.getTargetName() + FileType.Controller.getFileSuffix();
            String serviceName = generatorConfig.getTargetName() + FileType.Service.getFileSuffix();
            String serviceImplName = generatorConfig.getTargetName() + FileType.Impl.getFileSuffix();
            String apiName = generatorConfig.getTargetName() + FileType.Api.getFileSuffix();
            String mapperName = generatorConfig.getTargetName() + FileType.Mapper.getFileSuffix();
            String mapperXmlName = generatorConfig.getTargetName() + FileType.MapperXml.getFileSuffix();

            map.put("package", ftlInfo.getPackageName());
            map.put("import", ftlInfo.getImportName());
            map.put("name", ftlInfo.getFileName());
            map.put("dtoName", generatorConfig.getTargetName());
            map.put("apiName",
                    apiName.substring(0, apiName.indexOf('.')));
            map.put("controllerName",
                    controllerName.substring(0, controllerName.indexOf('.')));
            map.put("implName", serviceImplName.substring(0, serviceImplName.indexOf('.')));
            map.put("serviceName",
                    serviceName.substring(0, serviceName.indexOf('.')));
            map.put("mapperName", mapperName.substring(0, mapperName.indexOf('.')));
            map.put("xmlName",
                    mapperXmlName.substring(0, mapperXmlName.indexOf('.')));
            map.put("columnsInfo", ftlInfo.getColumnsInfo());
            String url = generatorConfig.getTargetName().toLowerCase();
            map.forEach((k, v) -> {
                context.setVariable(k, v);
            });
            return templateEngine.process(templateName, context).getBytes();
        }
        return null;
    }

}
