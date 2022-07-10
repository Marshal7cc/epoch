package org.epoch.mybatis.domain;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.epoch.core.constants.BaseConstants.Status;
import org.epoch.data.domain.Statusable;
import org.epoch.data.domain.Versionable;

/**
 * @author Marshal
 * @since 2022/7/10
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseAuditEntity<T extends Model<?>, ID> extends SimpleAuditEntity<T, ID> implements Statusable, Versionable {
    @Version
    private String objectVersion;
    @TableLogic(value = Status.YES, delval = Status.NO)
    private String status;
}
