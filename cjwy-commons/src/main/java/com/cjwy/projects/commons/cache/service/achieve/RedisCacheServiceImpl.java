package com.cjwy.projects.commons.cache.service.achieve;

import com.cjwy.projects.commons.cache.service.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis缓存工具类
 *
 * @param <K> key
 * @param <V> value
 * @author 叶云轩 contact by tdg_yyx@foxmail.com
 * @date 2018/9/8 - 14:55
 */
public class RedisCacheServiceImpl<K, V> implements CacheService<K, V> {

    /**
     * RedisCacheService日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisCacheServiceImpl.class);


    @Resource
    private RedisTemplate<K, V> redisTemplate;

    /**
     * 缓存list
     *
     * @param k 储存的数据的键
     * @param v 储存的数据的值
     * @return 缓存一个集合
     */
    @Override
    public boolean cacheList(K k, V v) {
        return cacheList(k, v, -1);
    }

    /**
     * 向一个list集合中添加新项
     *
     * @param k    储存的数据的键
     * @param v    储存的数据的值
     * @param time 缓存的时间
     * @return 缓存的状态
     */
    @Override
    public boolean cacheList(K k, V v, long time) {
        try {
            ListOperations<K, V> listOps = redisTemplate.opsForList();
            listOps.rightPush(k, v);
            if (time > 0) {
                redisTemplate.expire(k, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable t) {
            LOGGER.error("缓存[" + k + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存list
     *
     * @param k 储存的数据的键
     * @param v 储存的数据的值
     * @return 缓存状态
     */
    public boolean cacheList(K k, List<V> v) {
        return cacheList(k, v, -1);
    }

    /**
     * 缓存一个list
     *
     * @param k    储存的数据的键
     * @param v    储存的数据的值
     * @param time 缓存的时间
     * @return 缓存的状态
     */
    public boolean cacheList(K k, List<V> v, long time) {
        try {
            ListOperations<K, V> listOps = redisTemplate.opsForList();
            long l = listOps.rightPushAll(k, v);
            if (time > 0) {
                redisTemplate.expire(k, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable t) {
            LOGGER.error("缓存[" + k + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存set
     *
     * @param k 储存的数据的键
     * @param v 储存的数据的值
     * @return 缓存的结果
     */
    @Override
    public boolean cacheSet(K k, V v) {
        return cacheSet(k, v, -1);
    }

    /**
     * 缓存set操作
     *
     * @param k    储存的数据的键
     * @param v    储存的数据的值
     * @param time 数据的生命时长
     * @return set状态
     */
    @Override
    public boolean cacheSet(K k, V v, long time) {
        try {
            SetOperations<K, V> valueOps = redisTemplate.opsForSet();
            valueOps.add(k, v);
            if (time > 0) {
                redisTemplate.expire(k, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable t) {
            LOGGER.error("缓存[" + k + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存set
     *
     * @param k 储存的数据的键
     * @param v 储存的数据的值
     * @return 存入的状态
     */
    public boolean cacheSet(K k, Set<V> v) {
        return cacheSet(k, v, -1);
    }

    /**
     * 缓存set
     *
     * @param k    储存的数据的键
     * @param v    储存的数据的值
     * @param time 存入的时间
     * @return 存入的转台
     */
    public boolean cacheSet(K k, Set<V> v, long time) {
        try {
            SetOperations<K, V> setOps = redisTemplate.opsForSet();
            setOps.add(k, (V) v.toArray());
            if (time > 0) {
                redisTemplate.expire(k, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable t) {
            LOGGER.error("缓存[" + k + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 存储单个K/V
     *
     * @param k 储存的数据的键
     * @param v 储存的数据的值
     * @return 数据存储状态
     */
    @Override
    public boolean cacheValue(K k, V v) {
        return cacheValue(k, v, -1);
    }

    /**
     * 存储单个K/V
     *
     * @param k    储存的数据的键 redis缓存的键
     * @param v    储存的数据的值 redis缓存的值
     * @param time 记录的生命持续时间
     * @return 存储状态
     */
    @Override
    public boolean cacheValue(K k, V v, long time) {
        try {
            ValueOperations<K, V> valueOps = redisTemplate.opsForValue();
            valueOps.set(k, v);
            if (time > 0) {
                redisTemplate.expire(k, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable t) {
            LOGGER.error("缓存[" + k + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 判断key是否存在
     *
     * @param key 储存的数据的键
     * @return 是否包含这个key
     */
    @Override
    public boolean containsKey(K key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Throwable t) {
            LOGGER.error("判断缓存存在失败key[" + key + ", error[" + t + "]");
        }
        return false;
    }

    /**
     * 判断key是否存在
     *
     * @param k 储存的数据的键
     * @return 是否包含这个key
     */
    @Override
    public boolean containsListKey(K k) {
        return containsKey(k);
    }

    /**
     * 判断key是否存在
     *
     * @param k 储存的数据的键
     * @return 是否包含这个key
     */
    @Override
    public boolean containsSetKey(K k) {
        return containsKey(k);
    }

    /**
     * 判断key是否存在
     *
     * @param k 储存的数据的键
     * @return 是否包含这个key
     */
    @Override
    public boolean containsValueKey(K k) {
        return containsKey(k);
    }

    /**
     * 获取list缓存
     *
     * @param k     储存的数据的键
     * @param start 开始获取的位置
     * @param end   结束获取的位置
     * @return 集合
     */
    @Override
    public List<V> getList(K k, long start, long end) {
        try {
            ListOperations<K, V> listOps = redisTemplate.opsForList();
            return listOps.range(k, start, end);
        } catch (Throwable t) {
            LOGGER.error("获取list缓存失败key[" + k + ", error[" + t + "]");
        }
        return null;
    }

    /**
     * 获取总条数, 可用于分页
     *
     * @param k 储存的数据的键
     * @return 集合的长度
     */
    @Override
    public long getListSize(K k) {
        try {
            ListOperations<K, V> listOps = redisTemplate.opsForList();
            return listOps.size(k);
        } catch (Throwable t) {
            LOGGER.error("获取list长度失败key[" + k + "], error[" + t + "]");
        }
        return 0;
    }

    /**
     * 获取总条数, 可用于分页
     *
     * @param listOps 获取集合的size
     * @param k       储存的数据的键
     * @return 集合的长度
     */
    public long getListSize(ListOperations<String, String> listOps, String k) {
        try {
            return listOps.size(k);
        } catch (Throwable t) {
            LOGGER.error("获取list长度失败key[" + k + "], error[" + t + "]");
        }
        return 0;
    }

    /**
     * 获取缓存set数据
     *
     * @param k 储存的数据的键
     * @return 缓存的值
     */
    @Override
    public Set<V> getSet(K k) {
        try {
            SetOperations<K, V> setOps = redisTemplate.opsForSet();
            return setOps.members(k);
        } catch (Throwable t) {
            LOGGER.error("获取set缓存失败key[" + k + ", error[" + t + "]");
        }
        return null;
    }

    /**
     * 获取单个KEY缓存
     *
     * @param k 储存的数据的键
     * @return 获取缓存的value
     */
    @Override
    public V getValue(K k) {
        try {
            ValueOperations<K, V> valueOps = redisTemplate.opsForValue();
            return valueOps.get(k);
        } catch (Throwable t) {
            LOGGER.error("获取缓存失败key[" + k + ", error[" + t + "]");
        }
        return null;
    }

    @Override
    public Set<K> likeKeys(K key) {
        return redisTemplate.keys(key);
    }

    @Override
    public void print() {

    }

    /**
     * 移除某个KEY
     *
     * @param key 移除的数据的键
     * @return 是否移除成功
     */
    @Override
    public boolean remove(K key) {
        try {
            redisTemplate.delete(key);
            return true;
        } catch (Throwable t) {
            LOGGER.error("获取缓存失败key[" + key + ", error[" + t + "]");
        }
        return false;
    }

    /**
     * 移除并list中的首项
     *
     * @param k 储存的数据的键
     * @return 移除结果
     */
    @Override
    public boolean removeOneOfList(K k) {
        try {
            ListOperations<K, V> listOps = redisTemplate.opsForList();
            listOps.rightPop(k);
            return true;
        } catch (Throwable t) {
            LOGGER.error("移除list缓存失败key[" + k + ", error[" + t + "]");
        }
        return false;
    }

    /**
     * 移除某个KEY
     *
     * @param k 移除的数据的键
     * @return 是否移除成功
     */
    @Override
    public boolean removeValue(K k) {
        return remove(k);
    }

}