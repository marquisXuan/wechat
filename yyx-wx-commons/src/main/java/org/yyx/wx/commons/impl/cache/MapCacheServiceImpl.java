package org.yyx.wx.commons.impl.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yyx.wx.commons.util.CacheService;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Map实现缓存机制
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/8-14:56
 */
@SuppressWarnings("unchecked")
public class MapCacheServiceImpl<K, V> implements CacheService<K, V> {
    /**
     * 唯一一个缓存MAP
     */
    private static final Map CACHE_MAP = new ConcurrentHashMap<>();
    /**
     * MapCacheServiceImpl日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MapCacheServiceImpl.class);
    /**
     * 定时后缀
     */
    private static final String TIME_SUFFIX = "_time_suffix";

    @Override
    public boolean cacheList(K key, V value) {
        return false;
    }

    @Override
    public boolean cacheList(K key, V value, long time) {
        return false;
    }

    @Override
    public boolean cacheSet(K key, V value) {
        return false;
    }

    @Override
    public boolean cacheSet(K key, V value, long time) {
        return false;
    }

    /**
     * 存储单个K/V
     *
     * @param key   储存的数据的键
     * @param value 储存的数据的值
     * @return 数据存储状态
     */
    @Override
    public boolean cacheValue(K key, V value) {
        return cacheValue(key, value, -1);
    }

    /**
     * 存储单个K/V
     *
     * @param key   储存的数据的键 redis缓存的键
     * @param value 储存的数据的值 redis缓存的值
     * @param time  记录的生命持续时间 单位 秒
     * @return 存储状态
     */
    @Override
    public boolean cacheValue(K key, V value, long time) {
        try {
            CACHE_MAP.put(key, value);
        } catch (Exception e) {
            LOGGER.error("[缓存{}失败] {}", key, e.getMessage());
        }
        if (time != -1) {
            long currentTimes = System.currentTimeMillis();
            long cacheTimes = currentTimes + time / 1000;
            try {
                CACHE_MAP.put(key + TIME_SUFFIX, cacheTimes);
            } catch (Exception e) {
                LOGGER.error("[缓存{}失败] {}", key, e.getMessage());
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }

    @Override
    public boolean containsListKey(K key) {
        return false;
    }

    @Override
    public boolean containsSetKey(K key) {
        return false;
    }

    @Override
    public boolean containsValueKey(K key) {
        return false;
    }

    @Override
    public List<V> getList(K key, long start, long end) {
        return null;
    }

    @Override
    public long getListSize(K key) {
        return 0;
    }

    @Override
    public Set<V> getSet(K key) {
        return null;
    }

    /**
     * 获取单个KEY缓存
     *
     * @param key 储存的数据的键
     * @return 获取缓存的value
     */
    @Override
    public V getValue(K key) {
        // 取值时间
        long currentTimeMillis = System.currentTimeMillis();
        // 缓存中的时间
        Long cacheTimeMillis = (Long) CACHE_MAP.get(key + TIME_SUFFIX);
        // 比较取值时间和缓存中的时间大小  如果大的是当前取值时间，说明缓存失效
        boolean b = Math.max(currentTimeMillis, cacheTimeMillis) == currentTimeMillis;
        if (b) {
            removeValue(key);
            return null;
        }
        return (V) CACHE_MAP.get(key);
    }

    @Override
    public boolean remove(K key) {
        return false;
    }

    @Override
    public boolean removeOneOfList(K key) {
        return false;
    }

    /**
     * 移除某个KEY
     *
     * @param key 移除的数据的键
     * @return 是否移除成功
     */
    @Override
    public boolean removeValue(K key) {
        CACHE_MAP.remove(key);
        CACHE_MAP.remove(key + TIME_SUFFIX);
        return getValue(key) == null;
    }
}
