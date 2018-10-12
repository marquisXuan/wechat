package org.yyx.wx.message.condition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.yyx.wx.message.proxy.event.ModelMessagePushEventHandlerProxy;

import java.util.List;

import static org.yyx.wx.commons.constant.ConfigConstant.PACKAGE_INTERFACE;
import static org.yyx.wx.message.util.InterfaceSubClassUtil.getInterfaceSubClass;

/**
 * 模板消息事件推送处理器代理实现类
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/15-01:22
 */
public class ModelMessagePushEventServiceCondition implements Condition {
    /**
     * ModelMessagePushEventServiceCondition 日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ModelMessagePushEventServiceCondition.class);

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String servicePackageName = context.getEnvironment().getProperty(PACKAGE_INTERFACE);
        List<Class<?>> interfaceSubClass = getInterfaceSubClass(servicePackageName);
        // 遍历实现类数组
        for (int i = 0; i < interfaceSubClass.size(); i++) {
            // 接口实例
            Class<?> interfaceInstance = interfaceSubClass.get(i);
            Object o = null;
            try {
                o = interfaceInstance.newInstance();
            } catch (InstantiationException e) {
                LOGGER.error("[实例化异常] {}", e.getMessage());
            } catch (IllegalAccessException e) {
                LOGGER.error("[权限异常] {}", e.getMessage());
            }
            // 存在另外一个模板消息事件推送处理器代理实现类
            if (o instanceof ModelMessagePushEventHandlerProxy) {
                return false;
            }
        }
        return true;
    }
}
