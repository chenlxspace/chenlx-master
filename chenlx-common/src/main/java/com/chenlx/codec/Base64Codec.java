package com.chenlx.codec;

import org.apache.commons.codec.binary.Base64;

import com.chenlx.constants.CommonConstants;

/**
 * Base64 编码解码
 * 
 * @author Ricky
 * @date 2017年2月27日 下午5:02:59
 */
public abstract class Base64Codec {

	/**
	 * Base64 编码
	 * 
	 * @author Ricky
	 * @date 2017年2月27日 下午5:06:07
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String encode(String data) throws Exception {
		return encode(data, false);
	}

	/**
	 * Base64 编码
	 * @author Ricky
	 * @date 2017年2月27日 下午5:07:51
	 * @param data
	 * @param safe
	 * @return
	 * @throws Exception
	 */
	public static String encode(String data, boolean isChunked) throws Exception {
		byte[] b = Base64.encodeBase64(data.getBytes(CommonConstants.UTF8_ENCODING), isChunked);
		return new String(b, CommonConstants.UTF8_ENCODING);
	}

	/**
	 * Base64 解码
	 * 
	 * @author Ricky
	 * @date 2017年2月27日 下午5:07:46
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String decode(String data) throws Exception {
		byte[] b = Base64.decodeBase64(data.getBytes(CommonConstants.UTF8_ENCODING));
		return new String(b, CommonConstants.UTF8_ENCODING);
	}
}
