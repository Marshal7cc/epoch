package com.marshal.epoch.search.common.util;

import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auth Marshal
 * @date 2019/9/27
 * @desc
 */
public class ElasticSearchUtil {

    private static final Logger logger = LoggerFactory.getLogger(ElasticSearchUtil.class);

    private static final String IK_SMART_ANALYZER = "ik_smart_pinyin";

    private static final String IK_MAX_WORD_ANALYZER = "ik_max_word";

    private static final String DEFAULT_SEARCH_FIELD = "filedOne";

    private ElasticSearchUtil() {
    }

    /**
     * 构造查询条件
     *
     * @param keyword
     * @param fieldNames
     * @return
     */
    public static QueryBuilder getQueryBuilder(String keyword, String... fieldNames) {
        // 构造查询条件,使用标准分词器
        return QueryBuilders.multiMatchQuery(keyword, fieldNames)
                .analyzer(IK_SMART_ANALYZER)
                .operator(Operator.OR);
    }

    /**
     * 根据分类号与关键词搜索
     *
     * @param termWord
     * @param keyword
     * @param fieldNames
     * @return
     */
    public static QueryBuilder getQueryBuilder(String termWord, String keyword, String... fieldNames) {

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        // 构造查询条件,使用标准分词器
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(keyword, fieldNames)
                .analyzer(IK_SMART_ANALYZER)
                .operator(Operator.OR);

        boolQueryBuilder.must(multiMatchQueryBuilder);

        if (!ObjectUtils.isEmpty(termWord)) {
            //精确匹配
            TermQueryBuilder termQuery = QueryBuilders.termQuery(DEFAULT_SEARCH_FIELD, termWord);
            boolQueryBuilder.must(termQuery);
        }

        return boolQueryBuilder;
    }

    /**
     * 高亮构造
     *
     * @param fieldNames
     * @return
     */
    public static HighlightBuilder getHighlightBuilder(String... fieldNames) {
        // 设置高亮,使用默认的highlighter高亮器
        HighlightBuilder highlightBuilder = new HighlightBuilder()
                .preTags("<span style='color:red'>")
                .postTags("</span>");
        // 设置高亮字段
        for (String fieldName : fieldNames) {
            highlightBuilder.field(fieldName);
        }
        return highlightBuilder;
    }

    /**
     * 处理数据
     *
     * @param hits
     * @return
     */
    public static List<Map<String, Object>> getHitList(SearchHits hits) {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> row;
        for (SearchHit searchHit : hits) {
            row = new HashMap<>(2);
            // 处理高亮数据
            Map<String, Object> hitMap = new HashMap<>();
            searchHit.getHighlightFields().forEach((k, v) -> {
                String highlight = "";
                for (Text text : v.getFragments()) {
                    highlight += text.string();
                }
                hitMap.put(v.getName(), highlight);
            });
            row.put("highlight", hitMap);
            // 处理源数据
            Map<String, Object> sourceMap = searchHit.getSourceAsMap();
            hitMap.forEach((k, v) -> sourceMap.put(k, v));
            row.put("source", sourceMap);
            list.add(row);
        }
        return list;
    }

}
