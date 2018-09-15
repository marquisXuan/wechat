package org.yyx.wx.message.condition;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;
import org.yyx.wx.commons.exception.config.ConfigException;
import org.yyx.wx.commons.util.InterfaceUtil;
import org.yyx.wx.message.proxy.message.VoiceMessageHandlerProxy;

import java.io.IOException;
import java.util.List;

import static org.yyx.wx.commons.bussinessenum.ResponseCodeFromWx.error_load_config;
import static org.yyx.wx.commons.constant.ConfigConstant.PACKAGE_INTERFACE;

/**
 * 语音业务实现类Condition
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/10-13:39
 */
@Component
public class VoiceMessageServiceCondition implements Condition {

    /**
     * VoiceMessageServiceCondition 日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(VoiceMessageServiceCondition.class);

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String servicePackageName = context.getEnvironment().getProperty(PACKAGE_INTERFACE);
        if (StrUtil.isEmpty(servicePackageName)) {
            // 配置加载出错
            LOGGER.error("[配置加载出错] {}", servicePackageName);
            throw new ConfigException(error_load_config);
        }
        try {
            List<Class<?>> interfaceSubClass = InterfaceUtil.getInterfaceSubClass(servicePackageName);
            for (int i = 0; i < interfaceSubClass.size(); i++) {
                Class<?> aClass = interfaceSubClass.get(i);
                Object o = aClass.newInstance();
                if (o instanceof VoiceMessageHandlerProxy) {
                    return false;
                }
            }
        } catch (IOException e) {
            LOGGER.error("[IO异常] {}", e.getMessage());
        } catch (ClassNotFoundException e) {
            LOGGER.error("[类没有找到异常] {}", e.getMessage());
        } catch (IllegalAccessException e) {
            LOGGER.error("[权限异常] {}", e.getMessage());
        } catch (InstantiationException e) {
            LOGGER.error("[实例化异常] {}", e.getMessage());
        }
        return true;
    }
}