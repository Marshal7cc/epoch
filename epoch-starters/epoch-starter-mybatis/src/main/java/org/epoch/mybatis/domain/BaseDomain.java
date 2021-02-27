package org.epoch.mybatis.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import tk.mybatis.mapper.annotation.Version;

/**
 * <pre>
 * 审计对象
 * </pre>
 *
 * @author Marshal
 * @date 2019/11/1
 */
@Data
public class BaseDomain implements Serializable {

    public static String FILED_CREATED_BY = "createdBy";
    public static String FILED_CREATED_DATE = "createdDate";
    public static String FILED_LAST_UPDATED_BY = "updatedBy";
    public static String FILED_LAST_UPDATE_DATE = "updatedDate";

    /**
     * 创建者
     */
    private Long createdBy;

    /**
     * 创建时间
     */
    private Date createdDate;

    /**
     * 最后一次更新者
     */
    private Long updatedBy;

    /**
     * 最后一次更新时间
     */
    private Date updatedDate;

    /**
     * 有效状态
     */
    private String status;

    /**
     * 乐观锁关键字
     */
    @Version
    private Long objectVersion;
}
