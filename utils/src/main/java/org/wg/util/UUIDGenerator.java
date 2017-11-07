package org.wg.util;

import java.util.UUID;

/**
 * 生成Uuid
 *
 * @author wg
 * @version 1.0
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
