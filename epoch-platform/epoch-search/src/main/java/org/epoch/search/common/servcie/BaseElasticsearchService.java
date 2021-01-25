package org.epoch.search.common.servcie;

import java.util.List;
import java.util.Map;

/**
 * @author Marshal
 * @date 2019/9/24
 * @desc 全文检索通用服务
 */
public interface BaseElasticsearchService<T> {

    /**
     * 创建索引
     *
     * @param clazz
     */
    void createIndex(Class<T> clazz);

    /**
     * 删除索引
     *
     * @param clazz
     */
    void deleteIndex(Class<T> clazz);

    /**
     * 创建mapping
     *
     * @param clazz
     */
    void putMapping(Class<T> clazz);

    /**
     * 查询
     *
     * @param keyword
     * @param clazz
     * @return
     */
    List<T> query(String keyword, Class<T> clazz);

    /**
     * 高亮查询
     *
     * @param keyword
     * @param indexName
     * @param fieldNames
     * @return
     */
    Map<String, Object> queryHighlight(String keyword, String indexName, String... fieldNames);

    /**
     * 高亮查询
     *
     * @param pageNo
     * @param pageSize
     * @param keyword
     * @param indexName
     * @param fieldNames
     * @return
     */
    Map<String, Object> queryHighlight(int pageNo, int pageSize, String keyword, String indexName,
                                       String... fieldNames);

    /**
     * 分页高亮查询
     *
     * @param pageNo
     * @param pageSize
     * @param termWord
     * @param keyword
     * @param indexName
     * @param fieldNames
     * @return
     */
    Map<String, Object> queryHighlight(int pageNo, int pageSize, String termWord, String keyword, String indexName,
                                       String... fieldNames);


    /**
     * 是否覆盖
     *
     * @return
     */
    default boolean isOverride() {
        return true;
    }

}
