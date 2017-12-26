package org.wg.util;


import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;
import java.util.UUID;

public class CommonUtils {
    /**
     * 生成不重复的32位长的大写字符串
     *
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    /**
     * 把map转换成指定类型的javaBean对象
     *
     * @param map
     * @param clazz
     * @return
     */
    public static <T> T toBean(Map map, Class<T> clazz) {
        try {
            /*
             * 1. 创建指定类型的javabean对象
			 */
            T bean = clazz.newInstance();
            /*
			 * 2. 把数据封装到javabean中
			 */
            BeanUtils.populate(bean, map);
			/*
			 * 返回javabean对象
			 */
            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
