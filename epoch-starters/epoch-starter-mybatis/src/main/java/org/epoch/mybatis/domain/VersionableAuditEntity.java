package org.epoch.mybatis.domain;

import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.epoch.data.domain.Versionable;

/**
 * @author Marshal
 * @since 2022/7/12
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class VersionableAuditEntity<T extends Model<?>, ID> extends SimpleAuditEntity<T, ID> implements Versionable {
    @Version
    private Integer objectVersion;
}
