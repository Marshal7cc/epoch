package com.marshal.epoch.system.mapper;


import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import com.marshal.epoch.system.entity.SysPrompt;

import java.util.List;

public interface SysPromptMapper extends Mapper<SysPrompt> {

    List<SysPrompt> selectByLangCode(@Param("langCode") String langCode);

}
