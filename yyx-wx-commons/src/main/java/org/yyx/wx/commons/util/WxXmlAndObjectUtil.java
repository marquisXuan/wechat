package org.yyx.wx.commons.util;

import org.dom4j.Element;

import java.lang.reflect.Field;
import java.util.Iterator;

import static org.yyx.wx.commons.constant.ClassConstant.JAVA_LANG_OBJECT;

/**
 * 微信XML转对象工具类
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/25-08:47
 */
public class WxXmlAndObjectUtil {
    /**
     * 私有构造
     */
    private WxXmlAndObjectUtil() {
    }

    /**
     * 对象设值
     *
     * @param clazz 类对象
     * @param name  标识要给哪个字段赋值
     * @param value 实际值
     * @param t     对象
     * @throws IllegalAccessException 一般是权限异常 权限修饰符
     */
    private static void setValue(final Class clazz, final String name, final String value, final Object t)
            throws IllegalAccessException {
        // 获取当前类对象的父类对象
        Class superclass = clazz.getSuperclass();
        // 获取父类对象名
        String superclassName = superclass.getName();
        // 判断是不是Object类
        if (!JAVA_LANG_OBJECT.equals(superclassName)) {
            // 递归
            setValue(superclass, name, value, t);
        }
        // 获取字段
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            // 打破封装
            declaredField.setAccessible(true);
            // 字段名大写
            String s = declaredField.getName().toUpperCase();
            // 比较是否是该字段
            if (s.equals(name.toUpperCase())) {
                // 获取字段属性
                Class<?> type = declaredField.getType();
                if ("long".equals(type.getName())) {
                    // long类型数据
                    declaredField.setLong(t, Long.parseLong(value));
                } else {
                    // String类型数据
                    declaredField.set(t, value);
                }
                break;
            }
        }
    }

    /**
     * xml对象转Object方法
     *
     * @param element       xml对象根节点信息
     * @param parentElement 父节点信息
     * @param clazz         待转类对象
     * @param obj           泛型对象
     * @param <T>           对象
     * @return 对象
     * @throws IllegalAccessException 一般是权限异常 权限修饰符
     */
    private static <T> T xmlToObject(final Element element,
                                     final Element parentElement,
                                     final Class<T> clazz,
                                     final T obj) throws IllegalAccessException {
        if (element.isRootElement()) {
            // 当前节点是根节点,遍历当前节点
            Iterator iterator = element.elementIterator();
            if (iterator.hasNext()) {
                // 根节点下是否有元素
                while (iterator.hasNext()) {
                    Element next = (Element) iterator.next();
                    xmlToObject(next, element, clazz, obj);
                }
            }
        } else {
            // 不是根节点
            Iterator iterator = element.elementIterator();
            // 判断是否有子节点
            if (iterator.hasNext()) {
                //有子节点
                Element next = (Element) iterator.next();
                xmlToObject(next, element, clazz, obj);
            } else {
                //没有子节点
                String name = element.getName();
                String value = parentElement.elementText(element.getQName());
                setValue(clazz, name, value, obj);
            }
        }
        return obj;
    }

    /**
     * xml 转 对象的方法
     * <b>要求：
     * 实体对象的属性名与xml节点标签名格式一致
     * 如,xml中有一个<FromToUser>的标签，则对象中要有一个属性名为fromToUser的属性，大小写不限
     * </b>
     *
     * @param element 根元素
     * @param clazz   要转化的类对象
     * @param <T>     泛型
     * @return 对象
     */
    public static <T> T xmlToObject(final Element element, final Class<T> clazz)
            throws IllegalAccessException, InstantiationException {
        T t = clazz.newInstance();
        return xmlToObject(element, null, clazz, t);
    }
}
