package org.wg.utils;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author li.biao
 * @ClassName ArrayUtil
 * @Description 数组操作工具类
 * @date 2015-4-1
 */
public class ArrayUtil {

    /**
     * @param objs
     * @return
     * @Description 判断数组是否为空
     */
    public static boolean isEmpty(Object[] objs) {
        if (null == objs || objs.length < 1) {
            return true;
        }
        return false;
    }

    /**
     * @param c
     * @return
     * @Description 判断集合是否为空
     */
    public static boolean isEmpty(Collection c) {
        if (null == c || c.size() < 1)
            return true;
        return false;
    }

    /**
     * @param c          集合对象
     * @param fieldName  字段name
     * @param fieldValue 值
     * @return
     * @Description 移除 集合中字段名为name并且字段的值为value一个元素
     */
    public static Object remove(Collection c, String fieldName,
                                String fieldValue) {
        if (isEmpty(c)) {
            return null;
        }
        Iterator iter = c.iterator();
        Object itemObj = null;
        while (iter.hasNext()) {
            itemObj = iter.next();
            if (fieldValue.equals(ClassUtil.getFieldValue(itemObj, fieldName))) {
                iter.remove();
                return itemObj;
            }
        }
        return null;
    }

    /**
     * @param list
     * @param removeList
     * @param fieldName
     * @return
     * @Description
     */
    public static Collection removeAll(Collection list, Collection removeList,
                                       String fieldName) {
        if (isEmpty(list) || isEmpty(removeList)) {
            return list;
        }

        Iterator iter = removeList.iterator();
        Object itemObj = null;
        Object value = null;
        while (iter.hasNext()) {
            itemObj = iter.next();
            value = ClassUtil.getFieldValue(itemObj, fieldName);
            remove(list, fieldName, value + "");
            if (isEmpty(list))
                break;
        }
        return list;
    }
}
