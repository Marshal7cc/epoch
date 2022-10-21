package org.epoch.iam.infrastructure.repository.mapper;


import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.epoch.iam.infrastructure.repository.entity.SysPrompt;

/**
 * @author Marshal
 */
public interface SysPromptMapper extends BaseMapper<SysPrompt> {

    /**
     * 根据语言编码获取多语言提示
     *
     * @param langCode
     * @return
     */
    List<SysPrompt> selectByLangCode(@Param("langCode") String langCode);

}
