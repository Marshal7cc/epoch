package com.marshal.epoch.search.standard.dto;

/**
 * @auth Marshal
 * @date 2019/9/26
 * @desc 全文检索查询参数
 */
public class FullTextRetrievalDto {

    private int page;

    private int pageSize;

    private String termWord;

    private String keyword;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getTermWord() {
        return termWord;
    }

    public void setTermWord(String termWord) {
        this.termWord = termWord;
    }
}
