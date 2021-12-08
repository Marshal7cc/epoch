package org.epoch.starter.lock.autoconfigure;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.epoch.core.exception.CommonException;
import org.epoch.starter.lock.advisor.LockAspectHandler;
import org.epoch.starter.lock.constant.LockConstants;
import org.epoch.starter.lock.enums.ServerPattern;
import org.epoch.starter.lock.provider.LockInfoProvider;
import org.epoch.starter.lock.provider.LockServiceFactory;
import org.epoch.starter.lock.service.impl.*;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.*;
import org.redisson.connection.balancer.LoadBalancer;
import org.redisson.connection.balancer.RandomLoadBalancer;
import org.redisson.connection.balancer.RoundRobinLoadBalancer;
import org.redisson.connection.balancer.WeightedRoundRobinBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

/**
 * Lock自动装配配置
 *
 * @author Marshal
 * @date 2021/12/7
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
@EnableConfigurationProperties(LockConfigProperties.class)
public class LockAutoConfiguration {

    @Autowired
    private LockConfigProperties lockConfig;

    @Bean
    public LockAspectHandler lockAspectHandler() {
        return new LockAspectHandler();
    }

    @Bean(name = "lockRedissonClient", destroyMethod = "shutdown")
    @ConditionalOnMissingBean
    RedissonClient redisson() throws Exception {
        Config config = new Config();
        config.setThreads(lockConfig.getThreads());
        config.setNettyThreads(lockConfig.getNettyThreads());
        config.setLockWatchdogTimeout(lockConfig.getLockWatchdogTimeout());
        config.setKeepPubSubOrder(lockConfig.isKeepPubSubOrder());
        config.setUseScriptCache(lockConfig.isUseScriptCache());
        ServerPattern serverPattern = ServerPatternFactory.getServerPattern(lockConfig.getPattern());
        switch (serverPattern) {
            case SINGLE:
                SingleServerConfig singleServerConfig = config.useSingleServer();
                initSingleConfig(singleServerConfig);
                break;
            case CLUSTER:
                ClusterServersConfig clusterConfig = config.useClusterServers();
                initClusterConfig(clusterConfig);
                break;
            case MASTER_SLAVE:
                MasterSlaveServersConfig masterSlaveConfig = config.useMasterSlaveServers();
                initMasterSlaveConfig(masterSlaveConfig);
                break;
            case REPLICATED:
                ReplicatedServersConfig replicatedServersConfig = config.useReplicatedServers();
                initReplicatedServersConfig(replicatedServersConfig);
                break;
            case SENTINEL:
                SentinelServersConfig sentinelServersConfig = config.useSentinelServers();
                initSentinelServersConfig(sentinelServersConfig);
                break;
            default:
                break;
        }
        return Redisson.create(config);
    }

    @Bean
    public LockInfoProvider lockInfoProvider() {
        return new LockInfoProvider();
    }

    @Bean
    public LockServiceFactory lockFactory() {
        return new LockServiceFactory();
    }

    @Bean
    public ReentrantLockServiceImpl reentrantLockServiceImpl() {
        return new ReentrantLockServiceImpl();
    }

    @Bean
    public FairLockServiceImpl fairLockServiceImpl() {
        return new FairLockServiceImpl();
    }

    @Bean
    public ReadLockServiceImpl readLockServiceImpl() {
        return new ReadLockServiceImpl();
    }

    @Bean
    public WriteLockServiceImpl writeLockServiceImpl() {
        return new WriteLockServiceImpl();
    }

    @Bean
    public MultiLockServiceImpl multiLockService() {
        return new MultiLockServiceImpl();
    }

    @Bean
    public RedLockServiceImpl redLockService() {
        return new RedLockServiceImpl();
    }

    /**
     * 初始化单机模式参数
     *
     * @param singleServerConfig 单机模式配置
     */
    private void initSingleConfig(SingleServerConfig singleServerConfig) throws URISyntaxException {
        LockConfigProperties.SingleConfig singleConfig = lockConfig.getSingleServer();
        singleServerConfig.setAddress(String.format("%s%s%s%s", lockConfig.isSslEnableEndpointIdentification() ? LockConstants.REDIS_SSL_URL_PREFIX : LockConstants.REDIS_URL_PREFIX,
                singleConfig.getAddress(), LockConstants.COLON, singleConfig.getPort()));
        singleServerConfig.setClientName(lockConfig.getClientName());
        singleServerConfig.setConnectionMinimumIdleSize(singleConfig.getConnMinIdleSize());
        singleServerConfig.setConnectionPoolSize(singleConfig.getConnPoolSize());
        singleServerConfig.setConnectTimeout(singleConfig.getConnTimeout());
        singleServerConfig.setDatabase(singleConfig.getDatabase());
        singleServerConfig.setDnsMonitoringInterval(singleConfig.getDnsMonitoringInterval());
        singleServerConfig.setIdleConnectionTimeout(singleConfig.getIdleConnTimeout());
        singleServerConfig.setKeepAlive(singleConfig.isKeepAlive());
        if (StringUtils.isNotBlank(singleConfig.getPassword())) {
            singleServerConfig.setPassword(singleConfig.getPassword());
        }
        singleServerConfig.setRetryAttempts(singleConfig.getRetryAttempts());
        singleServerConfig.setRetryInterval(singleConfig.getRetryInterval());
        singleServerConfig.setSslEnableEndpointIdentification(lockConfig.isSslEnableEndpointIdentification());
        if (lockConfig.getSslKeystore() != null) {
            singleServerConfig.setSslKeystore(new URI(lockConfig.getSslKeystore()));
        }
        if (lockConfig.getSslKeystorePassword() != null) {
            singleServerConfig.setSslKeystorePassword(lockConfig.getSslKeystorePassword());
        }
        singleServerConfig
                .setSslProvider(LockConstants.JDK.equalsIgnoreCase(lockConfig.getSslProvider()) ? SslProvider.JDK
                        : SslProvider.OPENSSL);
    }

    /**
     * 初始化集群模式参数
     *
     * @param clusterServerConfig 集群模式配置
     */
    private void initClusterConfig(ClusterServersConfig clusterServerConfig) {
        LockConfigProperties.ClusterConfig clusterConfig = lockConfig.getClusterServer();
        String[] addressArr = clusterConfig.getNodeAddresses().split(LockConstants.COMMA);
        Arrays.asList(addressArr).forEach(address -> clusterServerConfig
                .addNodeAddress(String.format("%s%s", lockConfig.isSslEnableEndpointIdentification() ? LockConstants.REDIS_SSL_URL_PREFIX : LockConstants.REDIS_URL_PREFIX, address)));
        clusterServerConfig.setScanInterval(clusterConfig.getScanInterval());

        ReadMode readMode = getReadMode(clusterConfig.getReadMode());
        Assert.notNull(readMode, "Unknown load balancing mode type for read operations");
        clusterServerConfig.setReadMode(readMode);

        SubscriptionMode subscriptionMode = getSubscriptionMode(clusterConfig.getSubMode());
        Assert.notNull(subscriptionMode, "The type of load balancing pattern for an unknown subscription operation");
        clusterServerConfig.setSubscriptionMode(subscriptionMode);

        LoadBalancer loadBalancer = getLoadBalancer(clusterConfig.getLoadBalancer(), clusterConfig.getWeightMaps(),
                clusterConfig.getDefaultWeight());
        Assert.notNull(loadBalancer, "Unknown type of load balancing algorithm");
        clusterServerConfig.setLoadBalancer(loadBalancer);

        clusterServerConfig.setSubscriptionConnectionMinimumIdleSize(clusterConfig.getSubConnMinIdleSize());
        clusterServerConfig.setSubscriptionConnectionPoolSize(clusterConfig.getSubConnPoolSize());
        clusterServerConfig.setSlaveConnectionMinimumIdleSize(clusterConfig.getSlaveConnMinIdleSize());
        clusterServerConfig.setSlaveConnectionPoolSize(clusterConfig.getSlaveConnPoolSize());
        clusterServerConfig.setMasterConnectionMinimumIdleSize(clusterConfig.getMasterConnMinIdleSize());
        clusterServerConfig.setMasterConnectionPoolSize(clusterConfig.getMasterConnPoolSize());
        clusterServerConfig.setIdleConnectionTimeout(clusterConfig.getIdleConnTimeout());
        clusterServerConfig.setConnectTimeout(clusterConfig.getConnTimeout());
        clusterServerConfig.setTimeout(clusterConfig.getTimeout());
        clusterServerConfig.setRetryAttempts(clusterConfig.getRetryAttempts());
        clusterServerConfig.setRetryInterval(clusterConfig.getRetryInterval());
        if (StringUtils.isNotBlank(clusterConfig.getPassword())) {
            clusterServerConfig.setPassword(clusterConfig.getPassword());
        }
        clusterServerConfig.setSubscriptionsPerConnection(clusterConfig.getSubPerConn());
        clusterServerConfig.setClientName(lockConfig.getClientName());
    }

    /**
     * 初始化哨兵模式参数
     *
     * @param sentinelServersConfig 哨兵模式配置
     * @throws URISyntaxException URISyntaxException
     */
    private void initSentinelServersConfig(SentinelServersConfig sentinelServersConfig) throws URISyntaxException {
        LockConfigProperties.SentinelConfig sentinelConfig = lockConfig.getSentinelServer();
        String[] addressArr = sentinelConfig.getSentinelAddresses().split(LockConstants.COMMA);
        Arrays.asList(addressArr).forEach(address -> sentinelServersConfig
                .addSentinelAddress(String.format("%s%s", lockConfig.isSslEnableEndpointIdentification() ? LockConstants.REDIS_SSL_URL_PREFIX : LockConstants.REDIS_URL_PREFIX, address)));

        ReadMode readMode = getReadMode(sentinelConfig.getReadMode());
        Assert.notNull(readMode, "Unknown load balancing mode type for read operations");
        sentinelServersConfig.setReadMode(readMode);

        SubscriptionMode subscriptionMode = getSubscriptionMode(sentinelConfig.getSubMode());
        Assert.notNull(subscriptionMode, "The type of load balancing pattern for an unknown subscription operation");
        sentinelServersConfig.setSubscriptionMode(subscriptionMode);

        LoadBalancer loadBalancer = getLoadBalancer(sentinelConfig.getLoadBalancer(), sentinelConfig.getWeightMaps(),
                sentinelConfig.getDefaultWeight());
        Assert.notNull(loadBalancer, "Unknown type of load balancing algorithm");
        sentinelServersConfig.setLoadBalancer(loadBalancer);

        sentinelServersConfig.setMasterName(sentinelConfig.getMasterName());
        sentinelServersConfig.setDatabase(sentinelConfig.getDatabase());
        sentinelServersConfig.setSlaveConnectionPoolSize(sentinelConfig.getSlaveConnectionPoolSize());
        sentinelServersConfig.setMasterConnectionPoolSize(sentinelConfig.getMasterConnectionPoolSize());
        sentinelServersConfig.setSubscriptionConnectionPoolSize(sentinelConfig.getSubscriptionConnectionPoolSize());
        sentinelServersConfig.setSlaveConnectionMinimumIdleSize(sentinelConfig.getSlaveConnectionMinimumIdleSize());
        sentinelServersConfig.setMasterConnectionMinimumIdleSize(sentinelConfig.getMasterConnectionMinimumIdleSize());
        sentinelServersConfig.setSubscriptionConnectionMinimumIdleSize(sentinelConfig.getSubscriptionConnectionMinimumIdleSize());
        sentinelServersConfig.setDnsMonitoringInterval(sentinelConfig.getDnsMonitoringInterval());
        sentinelServersConfig.setSubscriptionsPerConnection(sentinelConfig.getSubscriptionsPerConnection());
        if (StringUtils.isNotBlank(sentinelConfig.getPassword())) {
            sentinelServersConfig.setPassword(sentinelConfig.getPassword());
        }
        sentinelServersConfig.setRetryAttempts(sentinelConfig.getRetryAttempts());
        sentinelServersConfig.setRetryInterval(sentinelConfig.getRetryInterval());
        sentinelServersConfig.setTimeout(sentinelConfig.getTimeout());
        sentinelServersConfig.setConnectTimeout(sentinelConfig.getConnectTimeout());
        sentinelServersConfig.setIdleConnectionTimeout(sentinelConfig.getIdleConnectionTimeout());
        setLockSslConfigAndClientName(sentinelServersConfig);
    }

    /**
     * 初始化云托管模式参数
     *
     * @param replicatedServersConfig 云托管模式配置
     * @throws URISyntaxException URISyntaxException
     */
    private void initReplicatedServersConfig(ReplicatedServersConfig replicatedServersConfig) throws URISyntaxException {
        LockConfigProperties.ReplicatedConfig replicatedConfig = lockConfig.getReplicatedServer();

        String[] addressArr = replicatedConfig.getNodeAddresses().split(LockConstants.COMMA);
        Arrays.asList(addressArr).forEach(address -> replicatedServersConfig
                .addNodeAddress(String.format("%s%s", lockConfig.isSslEnableEndpointIdentification() ? LockConstants.REDIS_SSL_URL_PREFIX : LockConstants.REDIS_URL_PREFIX, address)));
        ReadMode readMode = getReadMode(replicatedConfig.getReadMode());
        Assert.notNull(readMode, "Unknown load balancing mode type for read operations");
        replicatedServersConfig.setReadMode(readMode);

        SubscriptionMode subscriptionMode = getSubscriptionMode(replicatedConfig.getSubscriptionMode());
        Assert.notNull(subscriptionMode, "The type of load balancing pattern for an unknown subscription operation");
        replicatedServersConfig.setSubscriptionMode(subscriptionMode);

        LoadBalancer loadBalancer = getLoadBalancer(replicatedConfig.getLoadBalancer(),
                replicatedConfig.getWeightMaps(), replicatedConfig.getDefaultWeight());
        Assert.notNull(loadBalancer, "Unknown type of load balancing algorithm");
        replicatedServersConfig.setLoadBalancer(loadBalancer);

        replicatedServersConfig.setScanInterval(replicatedConfig.getScanInterval());
        replicatedServersConfig.setDatabase(replicatedConfig.getDatabase());
        replicatedServersConfig.setSlaveConnectionPoolSize(replicatedConfig.getSlaveConnectionPoolSize());
        replicatedServersConfig.setMasterConnectionPoolSize(replicatedConfig.getMasterConnectionPoolSize());
        replicatedServersConfig.setSubscriptionConnectionPoolSize(replicatedConfig.getSubscriptionConnectionPoolSize());
        replicatedServersConfig.setSlaveConnectionMinimumIdleSize(replicatedConfig.getSlaveConnectionMinimumIdleSize());
        replicatedServersConfig.setMasterConnectionMinimumIdleSize(replicatedConfig.getMasterConnectionMinimumIdleSize());
        replicatedServersConfig.setSubscriptionConnectionMinimumIdleSize(replicatedConfig.getSubscriptionConnectionMinimumIdleSize());
        replicatedServersConfig.setDnsMonitoringInterval(replicatedConfig.getDnsMonitoringInterval());
        replicatedServersConfig.setSubscriptionsPerConnection(replicatedConfig.getSubscriptionsPerConnection());
        if (StringUtils.isNotBlank(replicatedConfig.getPassword())) {
            replicatedServersConfig.setPassword(replicatedConfig.getPassword());
        }
        replicatedServersConfig.setRetryAttempts(replicatedConfig.getRetryAttempts());
        replicatedServersConfig.setRetryInterval(replicatedConfig.getRetryInterval());
        replicatedServersConfig.setTimeout(replicatedConfig.getTimeout());
        replicatedServersConfig.setConnectTimeout(replicatedConfig.getConnectTimeout());
        replicatedServersConfig.setIdleConnectionTimeout(replicatedConfig.getIdleConnectionTimeout());

        setLockSslConfigAndClientName(replicatedServersConfig);
    }

    /**
     * 初始化主从模式参数
     *
     * @param masterSlaveServersConfig 主从模式配置
     * @throws URISyntaxException URISyntaxException
     */
    private void initMasterSlaveConfig(MasterSlaveServersConfig masterSlaveServersConfig) throws URISyntaxException {
        LockConfigProperties.MasterSlaveConfig masterSlaveConfig = lockConfig.getMasterSlaveServer();
        masterSlaveServersConfig.setMasterAddress(
                String.format("%s%s", lockConfig.isSslEnableEndpointIdentification() ? LockConstants.REDIS_SSL_URL_PREFIX : LockConstants.REDIS_URL_PREFIX, masterSlaveConfig.getMasterAddress()));
        String[] addressArr = masterSlaveConfig.getSlaveAddresses().split(LockConstants.COMMA);

        Arrays.asList(addressArr).forEach(address ->
                masterSlaveServersConfig.addSlaveAddress(String.format("%s%s", lockConfig.isSslEnableEndpointIdentification() ? LockConstants.REDIS_SSL_URL_PREFIX : LockConstants.REDIS_URL_PREFIX, address)));

        ReadMode readMode = getReadMode(masterSlaveConfig.getReadMode());
        Assert.notNull(readMode, "Unknown load balancing mode type for read operations");
        masterSlaveServersConfig.setReadMode(readMode);

        SubscriptionMode subscriptionMode = getSubscriptionMode(masterSlaveConfig.getSubMode());
        Assert.notNull(subscriptionMode, "The type of load balancing pattern for an unknown subscription operation");
        masterSlaveServersConfig.setSubscriptionMode(subscriptionMode);

        LoadBalancer loadBalancer = getLoadBalancer(masterSlaveConfig.getLoadBalancer(),
                masterSlaveConfig.getWeightMaps(), masterSlaveConfig.getDefaultWeight());
        Assert.notNull(loadBalancer, "Unknown type of load balancing algorithm");
        masterSlaveServersConfig.setLoadBalancer(loadBalancer);

        masterSlaveServersConfig.setDatabase(masterSlaveConfig.getDatabase());
        masterSlaveServersConfig.setSlaveConnectionPoolSize(masterSlaveConfig.getSlaveConnectionPoolSize());
        masterSlaveServersConfig.setMasterConnectionPoolSize(masterSlaveConfig.getMasterConnectionPoolSize());
        masterSlaveServersConfig.setSubscriptionConnectionPoolSize(masterSlaveConfig.getSubscriptionConnectionPoolSize());
        masterSlaveServersConfig.setSlaveConnectionMinimumIdleSize(masterSlaveConfig.getSlaveConnectionMinimumIdleSize());
        masterSlaveServersConfig.setMasterConnectionMinimumIdleSize(masterSlaveConfig.getMasterConnectionMinimumIdleSize());
        masterSlaveServersConfig.setSubscriptionConnectionMinimumIdleSize(masterSlaveConfig.getSubscriptionConnectionMinimumIdleSize());
        masterSlaveServersConfig.setDnsMonitoringInterval(masterSlaveConfig.getDnsMonitoringInterval());
        masterSlaveServersConfig.setSubscriptionsPerConnection(masterSlaveConfig.getSubscriptionsPerConnection());
        if (StringUtils.isNotBlank(masterSlaveConfig.getPassword())) {
            masterSlaveServersConfig.setPassword(masterSlaveConfig.getPassword());
        }
        masterSlaveServersConfig.setRetryAttempts(masterSlaveConfig.getRetryAttempts());
        masterSlaveServersConfig.setRetryInterval(masterSlaveConfig.getRetryInterval());
        masterSlaveServersConfig.setTimeout(masterSlaveConfig.getTimeout());
        masterSlaveServersConfig.setConnectTimeout(masterSlaveConfig.getConnectTimeout());
        masterSlaveServersConfig.setIdleConnectionTimeout(masterSlaveConfig.getIdleConnectionTimeout());
        setLockSslConfigAndClientName(masterSlaveServersConfig);
    }

    /**
     * 根据用户的配置类型设置对应的LoadBalancer
     *
     * @param loadBalancerType   负载均衡算法类名
     * @param customerWeightMaps 权重值设置，当负载均衡算法是权重轮询调度算法时该属性有效
     * @param defaultWeight      默认权重值，当负载均衡算法是权重轮询调度算法时该属性有效
     * @return LoadBalancer OR NULL
     */
    private LoadBalancer getLoadBalancer(String loadBalancerType, String customerWeightMaps, int defaultWeight) {
        if (LockConstants.LoadBalancer.RANDOM_LOAD_BALANCER.equals(loadBalancerType)) {
            return new RandomLoadBalancer();
        }
        if (LockConstants.LoadBalancer.ROUND_ROBIN_LOAD_BALANCER.equals(loadBalancerType)) {
            return new RoundRobinLoadBalancer();
        }
        if (LockConstants.LoadBalancer.WEIGHTED_ROUND_ROBIN_BALANCER.equals(loadBalancerType)) {
            Map<String, Integer> weights = new HashMap<>(16);
            String[] weightMaps = customerWeightMaps.split(LockConstants.SEMICOLON);
            Arrays.asList(weightMaps)
                    .forEach(weightMap -> weights.put(
                            (lockConfig.isSslEnableEndpointIdentification() ? LockConstants.REDIS_SSL_URL_PREFIX : LockConstants.REDIS_URL_PREFIX) + weightMap.split(LockConstants.COMMA)[0],
                            Integer.parseInt(weightMap.split(LockConstants.COMMA)[1])));
            return new WeightedRoundRobinBalancer(weights, defaultWeight);
        }
        return null;
    }

    /**
     * 根据readModeType返回ReadMode
     *
     * @param readModeType 读取操作的负载均衡模式类型
     * @return ReadMode OR NULL
     */
    private ReadMode getReadMode(String readModeType) {
        if (LockConstants.SubReadMode.SLAVE.equals(readModeType)) {
            return ReadMode.SLAVE;
        }
        if (LockConstants.SubReadMode.MASTER.equals(readModeType)) {
            return ReadMode.MASTER;
        }
        if (LockConstants.SubReadMode.MASTER_SLAVE.equals(readModeType)) {
            return ReadMode.MASTER_SLAVE;
        }
        return null;
    }

    /**
     * 根据subscriptionModeType返回SubscriptionMode
     *
     * @param subscriptionModeType 订阅操作的负载均衡模式类型
     * @return SubscriptionMode OR NULL
     */
    private SubscriptionMode getSubscriptionMode(String subscriptionModeType) {
        if (LockConstants.SubReadMode.SLAVE.equals(subscriptionModeType)) {
            return SubscriptionMode.SLAVE;
        }
        if (LockConstants.SubReadMode.MASTER.equals(subscriptionModeType)) {
            return SubscriptionMode.MASTER;
        }
        return null;
    }

    /**
     * 设置SSL配置
     *
     * @param lockAutoConfig lockAutoConfig
     * @param <T>            lockAutoConfig
     * @throws URISyntaxException URISyntaxException
     */
    @SuppressWarnings("rawtypes")
    private <T extends BaseMasterSlaveServersConfig> void setLockSslConfigAndClientName(T lockAutoConfig)
            throws URISyntaxException {
        if (lockConfig.isSslEnableEndpointIdentification()) {
            lockAutoConfig.setClientName(lockConfig.getClientName());
            lockAutoConfig.setSslEnableEndpointIdentification(lockConfig.isSslEnableEndpointIdentification());
            if (lockConfig.getSslKeystore() != null) {
                lockAutoConfig.setSslKeystore(new URI(lockConfig.getSslKeystore()));
            }
            if (lockConfig.getSslKeystorePassword() != null) {
                lockAutoConfig.setSslKeystorePassword(lockConfig.getSslKeystorePassword());
            }
            if (lockConfig.getSslTruststore() != null) {
                lockAutoConfig.setSslTruststore(new URI(lockConfig.getSslTruststore()));
            }
            if (lockConfig.getSslTruststorePassword() != null) {
                lockAutoConfig.setSslTruststorePassword(lockConfig.getSslTruststorePassword());
            }
            lockAutoConfig.setSslProvider(LockConstants.JDK.equalsIgnoreCase(lockConfig.getSslProvider()) ? SslProvider.JDK
                    : SslProvider.OPENSSL);
        }
    }

    public static class ServerPatternFactory {

        private static Map<String, ServerPattern> serverPatternMap = new HashMap<>();

        static {
            serverPatternMap.put(ServerPattern.SINGLE.getPattern(), ServerPattern.SINGLE);
            serverPatternMap.put(ServerPattern.CLUSTER.getPattern(), ServerPattern.CLUSTER);
            serverPatternMap.put(ServerPattern.MASTER_SLAVE.getPattern(), ServerPattern.MASTER_SLAVE);
            serverPatternMap.put(ServerPattern.REPLICATED.getPattern(), ServerPattern.REPLICATED);
            serverPatternMap.put(ServerPattern.SENTINEL.getPattern(), ServerPattern.SENTINEL);
        }

        /**
         * 根据字符串模式标识返回服务器模式枚举类
         *
         * @param pattern
         * @return
         */
        public static ServerPattern getServerPattern(String pattern) throws CommonException {
            ServerPattern serverPattern = serverPatternMap.get(pattern);
            if (serverPattern == null) {
                throw new CommonException("没有找到相应的服务器模式,请检测参数是否正常,pattern的值为:" + pattern);
            }
            return serverPattern;
        }
    }
}
