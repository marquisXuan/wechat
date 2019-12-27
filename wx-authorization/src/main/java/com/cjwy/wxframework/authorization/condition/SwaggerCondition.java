package com.cjwy.wxframework.authorization.condition;

import org.springframework.beans.BeansException;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * SwaggerCondition
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/27 9:59 下午
 */
@Configuration
public class SwaggerCondition implements Condition {


    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        Object swagger2Config = null;
        try {
            swagger2Config = conditionContext.getBeanFactory().getBean("Swagger2Config");
        } catch (BeansException e) {
            return false;
        }
        return swagger2Config != null;
    }
}
