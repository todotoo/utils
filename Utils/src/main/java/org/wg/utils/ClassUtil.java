package org.wg.utils;

import java.lang.reflect.Field;

/**
 * @author li.biao
 * @ClassName ClassUtil
 * @Description 类操作工具类
 * @date 2015-4-1
 */
public class ClassUtil {

    /**
     * @param cls       class
     * @param fieldName 字段名
     * @return
     * @Description 得到class的一个字段
     */
    public static Field getField(Class cls, String fieldName) {
        if (cls == Object.class)
            return null;
        Field f = null;
        try {
            f = cls.getDeclaredField(fieldName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null == f) {
            return getField(cls.getSuperclass(), fieldName);
        } else {
            f.setAccessible(true);
            return f;
        }
    }

    /**
     * @param obj       对象
     * @param fieldName 字段名
     * @return
     * @Description 获取字段fieldName的值
     */
    public static Object getFieldValue(Object obj, String fieldName) {
        Field f = getField(obj.getClass(), fieldName);
        if (null == f) {
            return null;
        }
        try {
            return f.get(obj);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param obj       对象
     * @param fieldName 字段
     * @param value     值
     * @Description 设置fieldName的值
     */
    public static void setFieldValue(Object obj, String fieldName, Object value) {
        Field f = getField(obj.getClass(), fieldName);
        if (null == f) {
            return;
        }
        try {
            f.set(obj, value);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param clsName 类
     * @return
     * @Description 加载一个类的实例
     */
    public static Object newInstance(String clsName) {
        try {
            Class cls = Class.forName(clsName);
            return cls.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @return
     * @Description 获取类路径
     */
    public static String getClassPath() {
        return ClassUtil.class.getClassLoader().getResource("/").getPath();
    }

}
