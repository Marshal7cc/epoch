package org.epoch.mybatis.domain;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.epoch.core.constant.Status;
import org.epoch.data.domain.Stateful;

/**
 * @author Marshal
 * @since 2022/7/12
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class StatefulAuditEntity<T extends Model<?>, ID> extends SimpleAuditEntity<T, ID> implements Stateful {
    @TableLogic(value = Status.ENABLE, delval = Status.DISABLE)
    private String status;
}
