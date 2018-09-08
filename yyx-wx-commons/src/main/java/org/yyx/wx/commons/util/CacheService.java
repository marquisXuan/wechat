package org.yyx.wx.commons.util;

import java.util.List;
import java.util.Set;

/**
 * 缓存接口
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/8-14:09
 */
public interface CacheService<K, V> {

    /**
     * 缓存list
     *
     * @param key   储存的数据的键
     * @param value 储存的数据的值
     * @return 缓存一个集合
     */
    boolean cacheList(K key, V value);

    /**
     * 向一个list集合中添加新项
     *
     * @param key   储存的数据的键
     * @param value 储存的数据的值
     * @param time  缓存的时间
     * @return 缓存的状态
     */
    boolean cacheList(K key, V value, long time);


    /**
     * 缓存set
     *
     * @param key   储存的数据的键
     * @param value 储存的数据的值
     * @return 缓存的结果
     */
    boolean cacheSet(K key, V value);

    /**
     * 缓存set操作
     *
     * @param key   储存的数据的键
     * @param value 储存的数据的值
     * @param time  数据的生命时长
     * @return set状态
     */
    boolean cacheSet(K key, V value, long time);

    /**
     * 存储单个K/V
     *
     * @param key   储存的数据的键
     * @param value 储存的数据的值
     * @return 数据存储状态
     */
    boolean cacheValue(K key, V value);

    /**
     * 存储单个K/V
     *
     * @param key   储存的数据的键 redis缓存的键
     * @param value 储存的数据的值 redis缓存的值
     * @param time  记录的生命持续时间
     * @return 存储状态
     */
    boolean cacheValue(K key, V value, long time);

    /**
     * 判断key是否存在
     *
     * @param key 储存的数据的键
     * @return 是否包含这个key
     */
    boolean containsKey(K key);

    /**
     * 判断key是否存在
     *
     * @param key 储存的数据的键
     * @return 是否包含这个key
     */
    boolean containsListKey(K key);

    /**
     * 判断key是否存在
     *
     * @param key 储存的数据的键
     * @return 是否包含这个key
     */
    boolean containsSetKey(K key);

    /**
     * 判断key是否存在
     *
     * @param key 储存的数据的键
     * @return 是否包含这个key
     */
    boolean containsValueKey(K key);

    /**
     * 获取list缓存
     *
     * @param key   储存的数据的键
     * @param start 开始获取的位置
     * @param end   结束获取的位置
     * @return 集合
     */
    List<V> getList(K key, long start, long end);

    /**
     * 获取总条数, 可用于分页
     *
     * @param key 储存的数据的键
     * @return 集合的长度
     */
    long getListSize(K key);

    /**
     * 获取缓存set数据
     *
     * @param key 储存的数据的键
     * @return 缓存的值
     */
    Set<V> getSet(K key);

    /**
     * 获取单个KEY缓存
     *
     * @param key 储存的数据的键
     * @return 获取缓存的value
     */
    V getValue(K key);

    /**
     * 移除某个KEY
     *
     * @param key 移除的数据的键
     * @return 是否移除成功
     */
    boolean remove(K key);

    /**
     * 移除并list中的首项
     *
     * @param key 储存的数据的键
     * @return 移除结果
     */
    boolean removeOneOfList(K key);

    /**
     * 移除某个KEY
     *
     * @param key 移除的数据的键
     * @return 是否移除成功
     */
    boolean removeValue(K key);
}
