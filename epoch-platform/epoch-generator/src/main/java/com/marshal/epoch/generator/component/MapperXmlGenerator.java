package com.marshal.epoch.generator.component;

import com.marshal.epoch.generator.dto.DBColumn;
import com.marshal.epoch.generator.dto.DBTable;
import com.marshal.epoch.generator.dto.GeneratorConfig;
import com.marshal.epoch.generator.enums.FileType;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.marshal.epoch.generator.util.TemplateUtil.columnToCamel;

/**
 * @auth: Marshal
 * @date: 2020/1/20
 * @desc:
 */
@Component
public class MapperXmlGenerator implements AbstractGenerator {

    @Override
    public FileType getFileType() {
        return FileType.MapperXml;
    }

    @Override
    public byte[] generate(DBTable table, GeneratorConfig generatorConfig) throws Exception {
        String parentPackagePath = generatorConfig.getParentPackagePath();
        String packagePath = generatorConfig.getPackagePath();
        String targetName = generatorConfig.getTargetName();
        String packageName = parentPackagePath.replaceAll("/", ".") + "." + packagePath;
        String mapperNameSpace = generatorConfig.getTargetName() + "Mapper";

        List<DBColumn> columns = table.getColumns();

        // 生成属性
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
        sb.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\r\n");
        sb.append("<mapper namespace=\"" + packageName + ".mapper." + mapperNameSpace + "\">\r\n");
        sb.append("<resultMap id=\"BaseResultMap\" type=\"" + packageName + ".entity." + targetName + "\">\r\n");

        // 生成属性
        for (DBColumn cl : columns) {
            String str = "        <result column=\"" + cl.getName() + "\" property=\"" + columnToCamel(cl.getName()) + "\" jdbcType=\"" + cl.getJdbcType() + "\" />\r\n";
            if (cl.isId()) {
                str = "        <id column=\"" + cl.getName() + "\" property=\"" + columnToCamel(cl.getName()) + "\" jdbcType=\"" + cl.getJdbcType() + "\" />\r\n";
            }
            sb.append(str);
        }
        sb.append("    </resultMap>\r\n");
        sb.append("</mapper>");

        return sb.toString().getBytes("utf-8");
    }
}
