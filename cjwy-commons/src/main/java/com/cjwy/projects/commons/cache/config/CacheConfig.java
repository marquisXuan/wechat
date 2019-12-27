package com.cjwy.projects.commons.cache.config;

import com.cjwy.projects.commons.cache.condition.MapCondition;
import com.cjwy.projects.commons.cache.condition.RedisCondition;
import com.cjwy.projects.commons.cache.service.CacheService;
import com.cjwy.projects.commons.cache.service.achieve.MapCacheServiceImpl;
import com.cjwy.projects.commons.cache.service.achieve.RedisCacheServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * 缓存配置类
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/8-11:19
 */
@Configuration
public class CacheConfig {

    /**
     * CacheConfig日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CacheConfig.class);

    /**
     * 缓存工具
     *
     * @param <K> key
     * @param <V> value
     * @return 缓存工具对象
     */
    @Bean("cacheService")
    @Conditional(MapCondition.class)
    public <K, V> CacheService<K, V> mapCacheService() {
        LOGGER.info("[使用Map实现缓存] ");
        return new MapCacheServiceImpl<>();
    }

    /**
     * 缓存工具
     *
     * @param <K> key
     * @param <V> value
     * @return 缓存工具对象
     */
    @Bean("cacheService")
    @Conditional(RedisCondition.class)
    public <K, V> CacheService<K, V> redisCacheService() {
        LOGGER.info("[使用Redis实现缓存] ");
        return new RedisCacheServiceImpl<>();
    }
}
