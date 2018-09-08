package org.yyx.wx.commons.condition.cache;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

/**
 * 判断是否有Map使用的Condition
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/8-11:16
 */
@Component
public class MapCondition implements Condition {

    /**
     * RedisCondition日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MapCondition.class);

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        String host = environment.getProperty("spring.redis.host");
        String port = environment.getProperty("spring.redis.port");
        // 如果端口号和主机名都为空，认为没有配置redis
        boolean allEmpty = StrUtil.isAllEmpty(host, port);
        LOGGER.info("MapCondition [是否配置了Redis] {}", allEmpty ? "没有配置" : "配置了");
        return allEmpty;
    }
}
