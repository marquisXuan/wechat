package org.yyx.wx.commons.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by muyuan on 2017/5/22.
 */
@Component
public class RedisUtil {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 存储单个K/V
     *
     * @param k 储存的数据的键
     * @param v 储存的数据的值
     *
     * @return 数据存储状态
     */
    public boolean cacheValue(String k, String v) {
        return cacheValue(k, v, -1);
    }

    /**
     * 存储单个K/V
     *
     * @param k    储存的数据的键 redis缓存的键
     * @param v    储存的数据的值 redis缓存的值
     * @param time 记录的生命持续时间
     *
     * @return 存储状态
     */
    public boolean cacheValue(String k, String v, long time) {
        try {
            ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
            valueOps.set(k, v);
            if (time > 0) {
                redisTemplate.expire(k, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + k + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 判断key是否存在
     *
     * @param k 储存的数据的键
     *
     * @return 是否包含这个key
     */
    public boolean containsValueKey(String k) {
        return containsKey(k);
    }

    /**
     * 判断key是否存在
     *
     * @param key 储存的数据的键
     *
     * @return 是否包含这个key
     */
    public boolean containsKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Throwable t) {
            logger.error("判断缓存存在失败key[" + key + ", error[" + t + "]");
        }
        return false;
    }

    /**
     * 判断key是否存在
     *
     * @param k 储存的数据的键
     *
     * @return 是否包含这个key
     */
    public boolean containsSetKey(String k) {
        return containsKey(k);
    }

    /**
     * 判断key是否存在
     *
     * @param k 储存的数据的键
     *
     * @return 是否包含这个key
     */
    public boolean containsListKey(String k) {
        return containsKey(k);
    }

    /**
     * 获取单个KEY缓存
     *
     * @param k 储存的数据的键
     *
     * @return 获取缓存的value
     */
    public String getValue(String k) {
        try {
            ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
            return valueOps.get(k);
        } catch (Throwable t) {
            logger.error("获取缓存失败key[" + k + ", error[" + t + "]");
        }
        return null;
    }

    /**
     * 移除某个KEY
     *
     * @param k 移除的数据的键
     *
     * @return 是否移除成功
     */
    public boolean removeValue(String k) {
        return remove(k);
    }

    /**
     * 移除某个KEY
     *
     * @param key 移除的数据的键
     *
     * @return 是否移除成功
     */
    public boolean remove(String key) {
        try {
            redisTemplate.delete(key);
            return true;
        } catch (Throwable t) {
            logger.error("获取缓存失败key[" + key + ", error[" + t + "]");
        }
        return false;
    }

    /**
     * 缓存set
     *
     * @param k 储存的数据的键
     * @param v 储存的数据的值
     *
     * @return 缓存的结果
     */
    public boolean cacheSet(String k, String v) {
        return cacheSet(k, v, -1);
    }

    /**
     * 缓存set操作
     *
     * @param k    储存的数据的键
     * @param v    储存的数据的值
     * @param time 数据的生命时长
     *
     * @return set状态
     */
    public boolean cacheSet(String k, String v, long time) {
        String key = k;
        try {
            SetOperations<String, String> valueOps = redisTemplate.opsForSet();
            valueOps.add(key, v);
            if (time > 0) redisTemplate.expire(key, time, TimeUnit.SECONDS);
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存set
     *
     * @param k 储存的数据的键
     * @param v 储存的数据的值
     *
     * @return 存入的状态
     */
    public boolean cacheSet(String k, Set<String> v) {
        return cacheSet(k, v, -1);
    }

    /**
     * 缓存set
     *
     * @param k    储存的数据的键
     * @param v    储存的数据的值
     * @param time 存入的时间
     *
     * @return 存入的转台
     */
    public boolean cacheSet(String k, Set<String> v, long time) {
        String key = k;
        try {
            SetOperations<String, String> setOps = redisTemplate.opsForSet();
            setOps.add(key, v.toArray(new String[v.size()]));
            if (time > 0) redisTemplate.expire(key, time, TimeUnit.SECONDS);
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 获取缓存set数据
     *
     * @param k 储存的数据的键
     *
     * @return 缓存的值
     */
    public Set<String> getSet(String k) {
        try {
            SetOperations<String, String> setOps = redisTemplate.opsForSet();
            return setOps.members(k);
        } catch (Throwable t) {
            logger.error("获取set缓存失败key[" + k + ", error[" + t + "]");
        }
        return null;
    }

    /**
     * 缓存list
     *
     * @param k 储存的数据的键
     * @param v 储存的数据的值
     *
     * @return 缓存一个集合
     */
    public boolean cacheList(String k, String v) {
        return cacheList(k, v, -1);
    }

    /**
     * 向一个list集合中添加新项
     *
     * @param k    储存的数据的键
     * @param v    储存的数据的值
     * @param time 缓存的时间
     *
     * @return 缓存的状态
     */
    public boolean cacheList(String k, String v, long time) {
        try {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            listOps.rightPush(k, v);
            if (time > 0) redisTemplate.expire(k, time, TimeUnit.SECONDS);
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + k + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存list
     *
     * @param k 储存的数据的键
     * @param v 储存的数据的值
     *
     * @return 缓存状态
     */
    public boolean cacheList(String k, List<String> v) {
        return cacheList(k, v, -1);
    }

    /**
     * 缓存一个list
     *
     * @param k    储存的数据的键
     * @param v    储存的数据的值
     * @param time 缓存的时间
     *
     * @return 缓存的状态
     */
    public boolean cacheList(String k, List<String> v, long time) {
        String key = k;
        try {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            long l = listOps.rightPushAll(key, v);
            if (time > 0) redisTemplate.expire(key, time, TimeUnit.SECONDS);
            return true;
        } catch (Throwable t) {
            logger.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 获取list缓存
     *
     * @param k     储存的数据的键
     * @param start 开始获取的位置
     * @param end   结束获取的位置
     *
     * @return 集合
     */
    public List<String> getList(String k, long start, long end) {
        try {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            return listOps.range(k, start, end);
        } catch (Throwable t) {
            logger.error("获取list缓存失败key[" + k + ", error[" + t + "]");
        }
        return null;
    }

    /**
     * 获取总条数, 可用于分页
     *
     * @param k 储存的数据的键
     *
     * @return 集合的长度
     */
    public long getListSize(String k) {
        try {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            return listOps.size(k);
        } catch (Throwable t) {
            logger.error("获取list长度失败key[" + k + "], error[" + t + "]");
        }
        return 0;
    }

    /**
     * 获取总条数, 可用于分页
     *
     * @param listOps 获取集合的size
     * @param k       储存的数据的键
     *
     * @return 集合的长度
     */
    public long getListSize(ListOperations<String, String> listOps, String k) {
        try {
            return listOps.size(k);
        } catch (Throwable t) {
            logger.error("获取list长度失败key[" + k + "], error[" + t + "]");
        }
        return 0;
    }

    /**
     * 移除并list中的首项
     *
     * @param k 储存的数据的键
     *
     * @return 移除结果
     */
    public boolean removeOneOfList(String k) {
        try {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            listOps.rightPop(k);
            return true;
        } catch (Throwable t) {
            logger.error("移除list缓存失败key[" + k + ", error[" + t + "]");
        }
        return false;
    }
}