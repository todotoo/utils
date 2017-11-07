package org.wg.utils;

import org.apache.commons.codec.binary.Base64;

/**
 * 加密解密工具类
 * @author wg
 */
public class Base64Utils {

	/**
	 * 编码
	 * @param s
	 * @return
	 */
	public static String encode(String s) {
		return encode(s, "utf-8");
	}

	/**
	 * 解码
	 * @param s
	 * @return
	 */
	public static String decode(String s) {
		return decode(s, "utf-8");
	}
	
	public static String encode(String s, String charset) {
		try {
			byte[] bytes = s.getBytes(charset);
			bytes = Base64.encodeBase64(bytes);
			return new String(bytes, charset);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String decode(String s, String charset) {
		try {
			byte[] bytes = s.getBytes(charset);
			bytes = Base64.decodeBase64(bytes);
			return new String(bytes, charset);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
