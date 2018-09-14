package org.yyx.wx.commons.util;

import cn.hutool.core.util.ArrayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * 接口工具类
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/10-17:18
 */
public class InterfaceUtil {

    private static final InterfaceUtil INTERFACE_UTIL = new InterfaceUtil();
    /**
     * InterfaceUtil日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(InterfaceUtil.class);

    private InterfaceUtil() {
    }

    /**
     * 查找类集合
     *
     * @param directory   类所在的目录
     * @param packageName 包名
     * @return 目录下的所有类文件
     * @throws ClassNotFoundException 类没有找到
     */
    private static List<Class<?>> findClasses(File directory, String packageName)
            throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        boolean empty = ArrayUtil.isEmpty(files);
        if (empty) {
            throw new ClassNotFoundException("目录-[" + directory + "]下没有类文件");
        }
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + "." + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }

    /**
     * 根据包名查找接口的子类集合
     *
     * @param packageName 接口所在的包
     * @return 接口的子类集合
     * @throws IOException            ioException
     * @throws ClassNotFoundException classNotFoundException
     */
    public static List<Class<?>> getInterfaceSubClass(String packageName) throws IOException, ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace(".", "/");
        LOGGER.info("[接口所在的包路径] {}", path);
        Enumeration<URL> resources = classLoader.getResources(path);
        List<Class<?>> classList = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            LOGGER.info("[url] {} == {}", url.getProtocol(), url.getPath());
            String protocol = url.getProtocol();
            if ("file".equals(protocol)) {
                File file = new File(url.getFile());
                classList.addAll(findClasses(file, packageName));
            }
        }
        return classList;
    }

    public InterfaceUtil getInstance() {
        return INTERFACE_UTIL;
    }
}
