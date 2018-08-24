package org.wg.utils;

import java.util.UUID;

/**
 * 生成Uuid
 *
 * @author liujunjun
 * @version 1.0
 * @date 2016年1月19日 上午9:44:14
 */
public class UUIDGenerator {

    /**
     * 生成去除“-”的UUID
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
    }
}
