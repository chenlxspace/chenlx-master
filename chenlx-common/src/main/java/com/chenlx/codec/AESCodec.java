package com.chenlx.codec;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES 加密解密算法
 * 
 * @author Ricky
 * @date 2017年2月27日 下午4:23:45
 */
public abstract class AESCodec {

	/**
	 * 密钥算法
	 */
	private static final String KEY_ALGORITHM = "AES";

	/**
	 * 加密/解密算法 / 工作模式 / 填充方式 Java 7 支持 PKCS5Padding Bouncy Castle 支持PKCS7Padding
	 */
	public static final String CIPHER_ALGORITHM_JDK7 = "AES/ECB/PKCS5Padding";

	/**
	 * 根据字节数组生成生成AES私密密钥
	 * 
	 * @author Ricky
	 * @date 2017年2月27日 下午4:31:07
	 * @param key
	 * @return
	 * @throws Exception
	 */
	private static Key toKey(byte[] key) throws Exception {
		SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
		return secretKey;
	}

	/**
	 * 使用key对data进行AES 解密
	 * 
	 * @author Ricky
	 * @date 2017年2月27日 下午4:34:56
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		// 还原密钥
		Key k = toKey(key);

		// 实例化
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_JDK7);

		// 初始化, 设置为解密模式
		cipher.init(Cipher.DECRYPT_MODE, k);

		// 执行操作
		return cipher.doFinal(data);
	}

	/**
	 * 使用key对data进行AES 加密
	 * 
	 * @author Ricky
	 * @date 2017年2月27日 下午4:35:40
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		// 还原密钥
		Key k = toKey(key);

		// 实例化
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_JDK7);

		// 初始化, 设置为加密模式
		cipher.init(Cipher.ENCRYPT_MODE, k);
		// 执行操作
		return cipher.doFinal(data);
	}

	/**
	 * 生成128位的随机密钥
	 * 
	 * @author Ricky
	 * @date 2017年2月27日 下午4:36:07
	 * @return
	 * @throws Exception
	 */
	public static byte[] init128Key() throws Exception {
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		return initKey(kg, 128);
	}

	/**
	 * 获取二进制随机密钥
	 * 
	 * @author Ricky
	 * @date 2017年2月27日 下午4:39:07
	 * @param kg
	 * @param len
	 * @return
	 * @throws Exception
	 */
	private static byte[] initKey(KeyGenerator kg, int len) throws Exception {
		kg.init(len);
		SecretKey secretKey = kg.generateKey();
		return secretKey.getEncoded();
	}

}
