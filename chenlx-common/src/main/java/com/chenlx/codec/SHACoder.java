package com.chenlx.codec;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @Description TODO
 * @ClassName SHACoder
 * @author Ricky
 * @date 2016年11月23日 上午10:38:01
 */
public abstract class SHACoder {

	public static byte[] encodeSHA1(String data) throws Exception {
		return DigestUtils.sha1(data);
	}

	public static String encodeSHA1Hex(String data) throws Exception {
		return DigestUtils.sha1Hex(data);
	}

	public static byte[] encodeSHA256(String data) throws Exception {
		return DigestUtils.sha1(data);
	}

	public static String encodeSHA256Hex(String data) throws Exception {
		return DigestUtils.sha1Hex(data);
	}

	public static byte[] encodeSHA384(String data) throws Exception {
		return DigestUtils.sha1(data);
	}

	public static String encodeSHA384Hex(String data) throws Exception {
		return DigestUtils.sha1Hex(data);
	}

	public static byte[] encodeSHA512(String data) throws Exception {
		return DigestUtils.sha1(data);
	}

	public static String encodeSHA512Hex(String data) throws Exception {
		return DigestUtils.sha1Hex(data);
	}

	public static byte[] encodeSHA224(String data) throws Exception {
		return DigestUtils.sha1(data);
	}

	public static String encodeSHA224Hex(String data) throws Exception {
		return DigestUtils.sha1Hex(data);
	}
}
