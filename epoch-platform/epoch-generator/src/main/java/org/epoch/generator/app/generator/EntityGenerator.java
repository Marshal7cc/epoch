package org.epoch.generator.app.generator;

import static org.epoch.generator.infra.util.TemplateUtil.columnToCamel;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.epoch.generator.api.dto.DbColumn;
import org.epoch.generator.api.dto.DbTable;
import org.epoch.generator.api.dto.GeneratorConfig;
import org.epoch.generator.infra.enums.FileType;
import org.epoch.generator.infra.util.TemplateUtil;
import org.epoch.mybatis.domain.entity.BaseAuditEntity;
import org.springframework.stereotype.Component;

/**
 * entity文件生成器
 *
 * @author Marshal
 * @date 2020/1/20
 */
@Component
public class EntityGenerator implements AbstractGenerator {

    @Override
    public FileType getFileType() {
        return FileType.Entity;
    }

    @Override
    public byte[] generate(DbTable table, GeneratorConfig generatorConfig) throws Exception {
        // 是否需要引入相对包
        boolean needTime = false;
        boolean needNotNull = false;

        List<DbColumn> columns = table.getColumns();
        String name = generatorConfig.getEntityName();
        String parentPackagePath = generatorConfig.getParentPackagePath();
        String packagePath = generatorConfig.getPackagePath();

        //对jdbcType和javaType做处理
        castType(columns);

        //判断是否需要引入包
        long notNullCount = columns.stream().filter(DbColumn::isNotNull).count();
        long dateColumnCount = columns.stream().filter(o -> "LocalDate".equalsIgnoreCase(o.getJavaType()) || "LocalDateTime".equals(o.getJavaType())).count();
        needNotNull = notNullCount == 0 ? false : true;
        needTime = dateColumnCount == 0 ? false : true;


        // 生成属性
        StringBuilder sb = new StringBuilder();
        String entityDir = parentPackagePath + "/" + packagePath + "/domain/entity";
        entityDir = entityDir.replaceAll("/", ".");
        //导入包
        importPackage(sb, entityDir, needTime, needNotNull);

        //附加作者注释
        TemplateUtil.appendAuthor(sb, generatorConfig.getAuthorName());

        sb.append("\r\n@Data\r\n");
        sb.append("@Table(name = " + "\"" + table.getName() + "\")\r\n");
        sb.append("public class " + name + " extends BaseDomain {\r\n\r\n");

        //生成静态字段
        generateStaticFields(columns, sb);
        // 生成属性
        generateFields(columns, sb);
        sb.append("}\r\n");
        return sb.toString().getBytes("utf-8");
    }

    /**
     * 对DbColumn jdbcType和javaType做处理
     *
     * @param columns
     */
    private void castType(List<DbColumn> columns) {
        //根据jdbc type 转换为 java type
        for (DbColumn s : columns) {
            switch (s.getJdbcType().toUpperCase()) {
                case "DATE":
                    s.setJavaType("LocalDate");
                    s.setJdbcType("DATE");
                    break;
                case "TIMESTAMP":
                case "DATETIME":
                case "TIME":
                    s.setJavaType("LocalDateTime");
                    s.setJdbcType("TIMESTAMP");
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
                case "NUMBER":
                case "FLOAT":
                    s.setJavaType("BigDecimal");
                    s.setJdbcType("DECIMAL");
                    break;
                default:
                    s.setJavaType("String");
                    s.setJdbcType("VARCHAR");
                    break;
            }
        }
    }

    /**
     * 导入包
     *
     * @param sb          文件字符
     * @param entityDir   实体文件目录
     * @param needTime    是否需要时间包
     * @param needNotNull 是否需要notNull
     */
    private void importPackage(StringBuilder sb, String entityDir, boolean needTime, boolean needNotNull) {

        sb.append("package " + entityDir + ";\r\n\r\n");
        String d = BaseAuditEntity.class.getName();
        sb.append("import " + d + ";\r\n");
        sb.append("import io.swagger.annotations.ApiModelProperty;\r\n");
        sb.append("import lombok.Data;\r\n");
        sb.append("\r\n");
        sb.append("import javax.persistence.Id;\r\n");
        sb.append("import javax.persistence.Table;\r\n");
        if (needTime) {
            sb.append("import java.time.*;\r\n");
        }
        if (needNotNull) {
            sb.append("import javax.validation.constraints.NotNull;\r\n");
        }
    }

    /**
     * 生成静态字段信息
     *
     * @param columns 列信息
     * @param sb      文件字符
     */
    private void generateStaticFields(List<DbColumn> columns, StringBuilder sb) {
        for (DbColumn column : columns) {
            sb.append("    private static final String ");
            sb.append(column.getName().toUpperCase());
            sb.append(" = \"");
            sb.append(column.getName());
            sb.append("\";\r\n");
        }
        sb.append("\r\n");
    }

    /**
     * 生成字段信息
     *
     * @param columns 列信息
     * @param sb      文件字符
     */
    private void generateFields(List<DbColumn> columns, StringBuilder sb) {
        for (DbColumn cl : columns) {
            if (cl.isId()) {
                sb.append("    @Id\r\n");
            } else {
                if (cl.isNotNull()) {
                    sb.append("    @NotNull\r\n");
                }
            }
            if (!StringUtils.isEmpty(cl.getRemarks())) {
                sb.append("    @ApiModelProperty(value=\"");
                sb.append(cl.getRemarks());
                sb.append("\")\r\n");
            }
            String str = "    private " + cl.getJavaType() + " " + columnToCamel(cl.getName()) + ";";
            str += "\r\n\r\n";
            sb.append(str);
        }
    }
}
