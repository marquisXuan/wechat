package org.yyx.wx.message.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;
import org.yyx.wx.commons.util.InterfaceUtil;
import org.yyx.wx.message.proxy.message.TextMessageHandlerProxy;

import java.io.IOException;
import java.util.List;

/**
 * 自定义文本业务实现类 - DEMO
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/10-13:39
 */
@Component
public class TextMessageServiceCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String servicePackageName = context.getEnvironment().getProperty("service.package");
        try {
            List<Class<?>> interfaceSubClass = InterfaceUtil.getInterfaceSubClass(servicePackageName);
            for (int i = 0; i < interfaceSubClass.size(); i++) {
                Class<?> aClass = interfaceSubClass.get(i);
                Object o = aClass.newInstance();
                // 存在另外一个文本消息处理器代理实现类
                if (o instanceof TextMessageHandlerProxy) {
                    return false;
                }
            }
        } catch (IOException e) {

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return true;
    }
}