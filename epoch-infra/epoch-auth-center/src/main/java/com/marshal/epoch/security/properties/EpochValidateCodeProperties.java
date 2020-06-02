package com.marshal.epoch.security.properties;

import com.marshal.epoch.core.base.BaseConstants;
import lombok.Data;

/**
 * @author Marshal
 * @date 2020/1/15
 */
@Data
public class EpochValidateCodeProperties implements BaseConstants {

    /**
     * 验证码有效时间，单位秒
     */
    private Long time = 120L;
    /**
     * 验证码类型，可选值 png和 gif
     */
    private String type = FileSuffix.PNG;
    /**
     * 图片宽度，px
     */
    private Integer width = 130;
    /**
     * 图片高度，px
     */
    private Integer height = 48;
    /**
     * 验证码位数
     */
    private Integer length = 4;
    /**
     * 验证码值的类型 1. 数字加字母 2. 纯数字 3. 纯字母
     */
    private Integer charType = 2;
}
