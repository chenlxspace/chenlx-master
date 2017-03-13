package com.chenlx.codec;

import org.apache.commons.codec.binary.Base64;

import com.chenlx.constants.CommonConstants;

/**
 * AES 默认编码解码 以Base64进行传递
 * 
 * @author Ricky
 * @date 2017年2月27日 下午4:30:11
 */
public class AESExtendCodec extends AESCodec {

	/**
	 * 
	 * @Description: AES/ECB/PKCS5Padding UTF-8 解密 Base64 字符串
	 * @Title: decryptString
	 * @param content
	 * @param key
	 * @return
	 * @throws Exception
	 *             String
	 * @throws
	 */
	public static String decryptBase64HexString(String content, String password) throws Exception {
		byte[] key = password.getBytes(CommonConstants.UTF8_ENCODING);
		byte[] data = Base64.decodeBase64(content);
		byte[] decryptByte = decrypt(data, key);
		return new String(decryptByte, CommonConstants.UTF8_ENCODING);
	}

	/**
	 * 
	 * @Description: AES/ECB/PKCS5Padding UTF-8 加密 返回Base64 字符串
	 * @Title: encryptBase64HexString
	 * @param content
	 * @param password
	 * @return
	 * @throws Exception
	 *             String
	 * @throws
	 */
	public static String encryptBase64HexString(String content, String key) throws Exception {
		return encryptBase64HexString(content.getBytes(CommonConstants.UTF8_ENCODING), key.getBytes(CommonConstants.UTF8_ENCODING));
	}

	/**
	 * 
	 * @Description: AES/ECB/PKCS5Padding UTF-8 加密 返回Base64 字符串
	 * @Title: encryptBase64HexString
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 *             String
	 * @throws
	 */
	public static String encryptBase64HexString(byte[] data, byte[] key) throws Exception {
		return Base64.encodeBase64String(encrypt(data, key));
	}
}
