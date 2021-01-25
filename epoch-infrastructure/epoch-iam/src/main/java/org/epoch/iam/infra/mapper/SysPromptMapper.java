package org.epoch.iam.infra.mapper;


import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import org.epoch.iam.domain.entity.SysPrompt;

import java.util.List;

/**
 * @author Marshal
 */
public interface SysPromptMapper extends Mapper<SysPrompt> {

    /**
     * 根据语言编码获取多语言提示
     *
     * @param langCode
     * @return
     */
    List<SysPrompt> selectByLangCode(@Param("langCode") String langCode);

}
