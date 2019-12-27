package com.cjwy.projects.commons.cache.condition;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

/**
 * 判断是否有Redis使用的Condition
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/8-11:16
 */
@Slf4j
@Component
public class RedisCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        String host = environment.getProperty("spring.redis.host");
        String port = environment.getProperty("spring.redis.port");
        // 如果端口号和主机名都为空，认为没有配置redis
        boolean allEmpty = StrUtil.isAllEmpty(host, port);
        log.info("RedisCondition [是否配置了Redis] {}", allEmpty ? "没有配置" : "配置了");
        return !allEmpty;
    }
}
