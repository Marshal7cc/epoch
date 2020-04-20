package com.marshal.epoch.generator.component;

import com.marshal.epoch.common.dto.BaseDto;
import com.marshal.epoch.generator.dto.DBColumn;
import com.marshal.epoch.generator.dto.DBTable;
import com.marshal.epoch.generator.dto.GeneratorConfig;
import com.marshal.epoch.generator.enums.FileType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.marshal.epoch.generator.util.TemplateUtil.columnToCamel;

/**
 * @auth: Marshal
 * @date: 2020/1/20
 * @desc:
 */
@Component
public class DtoGenerator implements AbstractGenerator {

    @Override
    public FileType getFileType() {
        return FileType.Dto;
    }

    @Override
    public byte[] generate(DBTable table, GeneratorConfig generatorConfig) throws Exception {
        // key 是否需要引入相对包
        boolean needUtil = false;
        boolean needNotNull = false;
        boolean needNotEmpty = false;

        String name = generatorConfig.getTargetName();
        String parentPackagePath = generatorConfig.getParentPackagePath();
        String packagePath = generatorConfig.getPackagePath();

        List<DBColumn> columns = table.getColumns();
        // 判断是否需要引入包
        for (DBColumn s : columns) {
            switch (s.getJdbcType().toUpperCase()) {
                case "DATE":
                case "TIMESTAMP":
                case "DATETIME":
                case "TIME":
                    s.setJavaType("Date");
                    s.setJdbcType("DATE");
                    needUtil = true;
                    break;
                case "BIGINT":
                case "INT":
                case "INTEGER":
                case "DECIMAL":
                case "TINYINT":
                    s.setJavaType("Long");
                    s.setJdbcType("DECIMAL");
                    break;
                case "DOUBLE":
                    s.setJavaType("Double");
                    s.setJdbcType("DECIMAL");
                    break;
                case "NUMBER":
                case "FLOAT":
                    s.setJavaType("Float");
                    s.setJdbcType("DECIMAL");
                    break;
                default:
                    s.setJavaType("String");
                    s.setJdbcType("VARCHAR");
                    break;
            }
            if (s.isNotNull()) {
                needNotNull = true;
            } else if (s.isNotEmpty()) {
                needNotEmpty = true;
            }
        }
        // 生成属性
        StringBuilder sb = new StringBuilder();
        String dir1 = parentPackagePath + "/" + packagePath + "/entity";
        dir1 = dir1.replaceAll("/", ".");
        sb.append("package " + dir1 + ";\r\n\r\n");
        sb.append("import lombok.Data;;\r\n");
        sb.append("import javax.persistence.Id;\r\n");
        sb.append("import org.hibernate.validator.constraints.Length;\r\n");
        sb.append("import javax.persistence.Table;\r\n");
        String d = BaseDto.class.getName();
        sb.append("import " + d + ";\r\n");
        if (needUtil) {
            sb.append("import java.util.Date;\r\n");
        }
        if (needNotNull) {
            sb.append("import javax.validation.constraints.NotNull;\r\n");
        }
        if (needNotEmpty) {
            sb.append("import org.hibernate.validator.constraints.NotEmpty;\r\n");
        }
        sb.append("\r\n@Data\r\n");
        sb.append("@Table(name = " + "\"" + table.getName() + "\")\r\n");
        sb.append("public class " + name + " extends BaseDto {\r\n\r\n");

        // 生成属性
        for (DBColumn cl : columns) {
            if (!StringUtils.isEmpty(cl.getRemarks())) {
                sb.append("    /**\r\n");
                sb.append("     * " + cl.getRemarks() + "\r\n");
                sb.append("     */\r\n");
            }
            if (cl.isId()) {
                sb.append("    @Id\r\n");
            } else {
                if (cl.isNotEmpty()) {
                    sb.append("    @NotEmpty\r\n");
                } else if (cl.isNotNull()) {
                    sb.append("    @NotNull\r\n");
                }
                if (cl.getJavaType().equalsIgnoreCase("String")) {
                    sb.append("    @Length(max = ");
                    sb.append(cl.getColumnLength() + ")\r\n");
                }
            }
            String str = "    private " + cl.getJavaType() + " " + columnToCamel(cl.getName()) + ";";
            str += "\r\n\r\n";
            sb.append(str);
        }
        sb.append("}\r\n");
        return sb.toString().getBytes("utf-8");
    }
}
