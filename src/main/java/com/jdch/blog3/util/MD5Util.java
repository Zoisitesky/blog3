package com.jdch.blog3.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * MD5加密
 * 
 * @author pibigstar
 *
 */
public class MD5Util {
	// 加盐
	private static final String slat = "ajjg*7^#jasldgj)*%@$dgsa";

	public static String encrypt(String dataStr) {
		try {
			dataStr = dataStr + slat;
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(dataStr.getBytes(StandardCharsets.UTF_8));
			byte[] s = m.digest();
			StringBuilder result = new StringBuilder();
			for (byte b : s) {
				result.append(Integer.toHexString((0x000000FF & b) | 0xFFFFFF00).substring(6));
			}
			return result.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}
}
