package com.cjwy.wxframework.validate.domain.factory;

import com.cjwy.wxframework.validate.domain.exception.ValidateException;
import com.cjwy.wxframework.validate.domain.exception.WxValidateException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;

/**
 * 异常工厂
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/25 7:10 上午
 */
@Slf4j
public class ExceptionFactory<T> {

    private ExceptionFactory() {
    }

    /**
     * 验证异常
     *
     * @param clazz 异常的类型
     * @param msg   异常信息
     * @param <T>   泛型
     * @return 异常
     */
    public static <T> ValidateException createValidateException(Class<T> clazz, String msg) {
        try {
            T t = clazz.getDeclaredConstructor(String.class).newInstance(msg);
            if (t instanceof WxValidateException) {
                return (WxValidateException) t;
            } else {
                return (ValidateException) t;
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return new ValidateException();
        }
    }
}
