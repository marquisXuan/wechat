package org.yyx.wx.demo.config.redis;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.yyx.wx.demo.domain.vo.wx.pubnum.reponse.AccessToken;

/**
 * Redis缓存配置类
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/24-14:52
 */
@Configuration
public class RedisConfig {


    @Bean
    @SuppressWarnings("unchecked")
    public RedisTemplate<String, AccessToken> redisTemplate(
            RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, AccessToken> redisTemplate = new RedisTemplate<>();
        FastJsonRedisSerializer fastJsonRedisSerializer =
                new FastJsonRedisSerializer(AccessToken.class);
        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        return redisTemplate;
    }
}
