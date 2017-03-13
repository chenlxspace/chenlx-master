package com.chenlx.codec;

import java.util.Locale;

import org.apache.commons.codec.digest.DigestUtils;


/**
 * @Description MD5 消息摘要
 * @ClassName MDCoder
 * @author Ricky
 * @date 2016年11月23日 上午10:27:19
 */
public abstract class MD5Coder {
	
	/**
	 * 
	 * @Description: MD5 返回byte[], 需要注意不能直接转为字符串, 必须转为16进制
	 * @Title: encodeMD5
	 * @param data
	 * @return
	 * @throws Exception byte[]
	 * @throws
	 */
	public static byte[] encodeMD5(byte[] data) throws Exception {
		return DigestUtils.md5(data);
	}
	
	/**
	 * 
	 * @Description:  MD5 返回 32位小写 16进制 字符串
	 * @Title: encodeMD532LowerCaseHex
	 * @param data
	 * @return
	 * @throws Exception String
	 * @throws
	 */
	public static String encodeMD532LowerCaseHex(byte[] data) throws Exception {
		return DigestUtils.md5Hex(data);
	}
	
	/**
	 * 
	 * @Description: TODO
	 * @Title: encodeMD532LowerCaseHex
	 * @param data
	 * @return
	 * @throws Exception String
	 * @throws
	 */
	public static String encodeMD532LowerCaseHex(String data) throws Exception {
		return DigestUtils.md5Hex(data);
	}
	
	/**
	 * 
	 * @Description: MD5 返回 16位小写 16进制 字符串
	 * @Title: encodeMD516LowerCaseHex
	 * @param data
	 * @return
	 * @throws Exception String
	 * @throws
	 */
	public static String encodeMD516LowerCaseHex(byte[] data) throws Exception {
		return DigestUtils.md5Hex(data).substring(8, 24);
	}
	
	/**
	 * 
	 * @Description: TODO
	 * @Title: encodeMD516LowerCaseHex
	 * @param data
	 * @return
	 * @throws Exception String
	 * @throws
	 */
	public static String encodeMD516LowerCaseHex(String data) throws Exception {
		return DigestUtils.md5Hex(data).substring(8, 24);
	}
	
	/**
	 * 
	 * @Description: MD5 返回 32位大写 16进制 字符串
	 * @Title: encodeMD532UpperCaseHex
	 * @param data
	 * @return
	 * @throws Exception String
	 * @throws
	 */
	public static String encodeMD532UpperCaseHex(byte[] data) throws Exception {
		return encodeMD532LowerCaseHex(data).toUpperCase(Locale.ENGLISH);
	}
	
	/**
	 * 
	 * @Description: TODO
	 * @Title: encodeMD532UpperCaseHex
	 * @param data
	 * @return
	 * @throws Exception String
	 * @throws
	 */
	public static String encodeMD532UpperCaseHex(String data) throws Exception {
		return encodeMD532LowerCaseHex(data).toUpperCase(Locale.ENGLISH);
	}
	
	/**
	 * 
	 * @Description: MD5 返回 16位大写 16进制 字符串
	 * @Title: encodeMD516UpperCaseHex
	 * @param data
	 * @return
	 * @throws Exception String
	 * @throws
	 */
	public static String encodeMD516UpperCaseHex(byte[] data) throws Exception {
		return encodeMD516LowerCaseHex(data).toUpperCase(Locale.ENGLISH);
	}
	
	/**
	 * 
	 * @Description: TODO
	 * @Title: encodeMD516UpperCaseHex
	 * @param data
	 * @return
	 * @throws Exception String
	 * @throws
	 */
	public static String encodeMD516UpperCaseHex(String data) throws Exception {
		return encodeMD516LowerCaseHex(data).toUpperCase(Locale.ENGLISH);
	}
}
