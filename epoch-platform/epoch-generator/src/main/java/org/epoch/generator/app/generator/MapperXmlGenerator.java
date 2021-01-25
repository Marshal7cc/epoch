package org.epoch.generator.app.generator;

import org.epoch.generator.api.dto.DbColumn;
import org.epoch.generator.api.dto.DbTable;
import org.epoch.generator.api.dto.GeneratorConfig;
import org.epoch.generator.infra.enums.FileType;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.epoch.generator.infra.util.TemplateUtil.columnToCamel;

/**
 * @author Marshal
 * @date 2020/1/20
 */
@Component
public class MapperXmlGenerator implements AbstractGenerator {

    @Override
    public FileType getFileType() {
        return FileType.MapperXml;
    }

    @Override
    public byte[] generate(DbTable table, GeneratorConfig generatorConfig) throws Exception {
        String parentPackagePath = generatorConfig.getParentPackagePath();
        String packagePath = generatorConfig.getPackagePath();
        String entityName = generatorConfig.getEntityName();
        String packageName = parentPackagePath.replaceAll("/", ".") + "." + packagePath;
        String mapperNameSpace = generatorConfig.getEntityName() + "Mapper";

        List<DbColumn> columns = table.getColumns();

        // 生成属性
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
        sb.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\r\n");
        sb.append("<mapper namespace=\"" + packageName + ".mapper." + mapperNameSpace + "\">\r\n");
        sb.append("    <resultMap id=\"BaseResultMap\" type=\"" + packageName + ".domain.entity." + entityName + "\">\r\n");

        // 生成属性
        for (DbColumn cl : columns) {
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
