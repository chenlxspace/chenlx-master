package com.chenlx.codec;

import org.apache.commons.codec.binary.Base64;


/**
 * @Description 基于commons codec
 * @ClassName UrlBase64
 * @author Ricky
 * @date 2016年11月23日 上午10:23:57
 */
public abstract class URLBase64Coder {
	private static final String ENCODING = "UTF-8";
	
	public static String encode(String data) throws Exception {
		byte[] b = Base64.encodeBase64URLSafe(data.getBytes(ENCODING));
		return new String(b, ENCODING);
	}
	
	public static String decode(String data) throws Exception {
		byte[] b = Base64.decodeBase64(data.getBytes(ENCODING));
		return new String(b, ENCODING);
	}
}
