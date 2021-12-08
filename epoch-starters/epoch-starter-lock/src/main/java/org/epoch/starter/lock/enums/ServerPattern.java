package org.epoch.starter.lock.enums;

/**
 * @author Marshal
 * @date 2021/12/7
 */
public enum ServerPattern {

    /**
     * 单节点模式
     */
    SINGLE("single"),
    /**
     * 主从模式
     */
    MASTER_SLAVE("master_slave"),
    /**
     * 哨兵模式
     */
    SENTINEL("sentinel"),
    /**
     * 集群模式，除了适用于Redis集群环境，也适用于任何云计算服务商提供的集群模式
     */
    CLUSTER("cluster"),
    /**
     * 云托管模式，适用于任何由云计算运营商提供的Redis云服务，包括亚马逊云的AWS ElastiCache、微软云的Azure Redis 缓存和阿里云（Aliyun）的云数据库Redis版
     */
    REPLICATED("replicated");

    private String pattern;

    ServerPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return this.pattern;
    }

}
