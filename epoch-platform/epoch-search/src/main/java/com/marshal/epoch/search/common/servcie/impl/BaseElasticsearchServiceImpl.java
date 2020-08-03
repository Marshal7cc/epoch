package com.marshal.epoch.search.common.servcie.impl;


import com.marshal.epoch.search.common.servcie.BaseElasticsearchService;
import com.marshal.epoch.search.common.util.ElasticSearchUtil;
import com.marshal.epoch.search.standard.dto.SysUser;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Marshal
 * @date 2019/9/24
 * @desc
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BaseElasticsearchServiceImpl<T> implements BaseElasticsearchService<T> {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;

    @Override
    public void createIndex(Class clazz) {
        elasticsearchTemplate.createIndex(clazz);
    }

    @Override
    public void deleteIndex(Class clazz) {
        elasticsearchTemplate.deleteIndex(clazz);
    }

    @Override
    public void putMapping(Class clazz) {
        elasticsearchTemplate.putMapping(clazz);
    }

    @Override
    public List<T> query(String keyword, Class<T> clazz) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(new QueryStringQueryBuilder(keyword))
                .withSort(SortBuilders.scoreSort().order(SortOrder.ASC))
                .build();
        return elasticsearchTemplate.queryForList(searchQuery, clazz);
    }

    @Override
    public Map<String, Object> queryHighlight(String keyword, String indexName, String... fieldNames) {
        // 构造查询条件,使用标准分词器.
        QueryBuilder matchQuery = ElasticSearchUtil.getQueryBuilder(keyword, fieldNames);

        // 设置高亮,使用默认的highlighter高亮器
        HighlightBuilder highlightBuilder = ElasticSearchUtil.getHighlightBuilder(fieldNames);


        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQuery)
                .withHighlightBuilder(highlightBuilder).build();


        // 设置查询字段
        AggregatedPage<SysUser> list = elasticsearchTemplate.queryForPage(searchQuery, SysUser.class);

        Aggregations aggregations = list.getAggregations();

        // 返回搜索结果
//        SearchHits hits = response.getHits();
//
//        Map<String, Object> result = new HashMap<>(2);
//        result.put("totalCount", hits.getTotalHits());
//        result.put("rows", ElasticSearchUtil.getHitList(hits));
//        return result;
        return null;
    }

    @Override
    public Map<String, Object> queryHighlight(int pageNo, int pageSize, String keyword, String indexName, String... fieldNames) {
        return queryHighlight(pageNo, pageSize, null, keyword, indexName, fieldNames);
    }

    @Override
    public Map<String, Object> queryHighlight(int pageNo, int pageSize, String termWord, String keyword, String indexName, String... fieldNames) {
        // 构造查询条件,使用标准分词器
        QueryBuilder matchQuery;
        if (!ObjectUtils.isEmpty(termWord)) {
            matchQuery = ElasticSearchUtil.getQueryBuilder(termWord, keyword, fieldNames);
        } else {
            matchQuery = ElasticSearchUtil.getQueryBuilder(keyword, fieldNames);
        }

        // 设置高亮,使用默认的highlighter高亮器
        HighlightBuilder highlightBuilder = ElasticSearchUtil.getHighlightBuilder(fieldNames);

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQuery)
                .withHighlightBuilder(highlightBuilder).build();


        // 设置查询字段
        AggregatedPage<SysUser> list = elasticsearchTemplate.queryForPage(searchQuery, SysUser.class);

        Aggregations aggregations = list.getAggregations();

        // 设置查询字段
//        SearchResponse response = elasticsearchTemplate.getClient().prepareSearch(indexName)
//                .setQuery(matchQuery)
//                .highlighter(highlightBuilder)
//                .setFrom((pageNo - 1) * pageSize)
//                .setSize(pageNo * pageSize)
//                .get();
//
//        // 返回搜索结果
//        SearchHits hits = response.getHits();
//
//        Map<String, Object> result = new HashMap<>(2);
//        result.put("totalCount", hits.getTotalHits());
//        result.put("rows", ElasticSearchUtil.getHitList(hits));
//        return result;
        return null;
    }
}

