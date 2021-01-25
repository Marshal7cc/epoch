package org.epoch.search.standard.servcie.impl;

import org.epoch.search.common.annotation.SearchField;
import org.epoch.search.common.servcie.impl.BaseElasticsearchServiceImpl;
import org.epoch.search.standard.dto.FullTextRetrievalDto;
import org.epoch.search.standard.dto.SysUser;
import org.epoch.search.standard.repository.SysUserRepository;
import org.epoch.search.standard.servcie.SysUserSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Marshal
 * @date 2020/2/16
 */
@Slf4j
@Service
public class SysUserSearchServiceImpl extends BaseElasticsearchServiceImpl<SysUser> implements SysUserSearchService {

    private static final String INDEX_NAME = "epoch_standard";

    private static final String[] FIELD_NAMES;

    /**
     * 初始化fields
     */
    static {
        List<String> searchFields = new ArrayList<>();
        Class clazz = SysUser.class;
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(SearchField.class)) {
                searchFields.add(field.getName());
            }
        }

        FIELD_NAMES = searchFields.toArray(new String[searchFields.size()]);
    }

    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public Map<String, Object> query(FullTextRetrievalDto dto) {
        int page = dto.getPage();
        int pageSize = dto.getPageSize();
        String keyword = dto.getKeyword();
        String termWord = dto.getTermWord();

        Assert.notNull(page, "页码不能为空!");
        Assert.notNull(pageSize, "每页数量不能为空");
        Assert.hasText(keyword, "关键词不能为空");

        return queryHighlight(page, pageSize, keyword, INDEX_NAME, FIELD_NAMES);
    }

    @Override
    public void save(SysUser sysUser) {
        sysUserRepository.save(sysUser);
    }
}
