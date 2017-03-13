package com.chenlx.codec;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * @Description TODO
 * @ClassName DESCoder
 * @author Ricky
 * @date 2016年11月23日 上午10:52:48
 */
public abstract class DESCoder {

	/**
	 * 密钥算法
	 * Java 7 只支持56位密钥
	 * Bouncy Castle 支持64位密钥
	 */
	public static final String KEY_ALGORITHM = "DES";

	/**
	 * 加密/解密算法 / 工作模式 / 填充方式
	 */
	public static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";

	/**
	 * 
	 * @Description: 转换密钥
	 * @Title: toKey
	 * @param key
	 * @return
	 * @throws Exception Key
	 * @throws
	 */
	private static Key toKey(byte[] key) throws Exception {
		// 实例化 DES 密钥材料
		DESKeySpec dks = new DESKeySpec(key);
		
		//实例化私密密钥工厂
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
		
		//生成私密密钥
		SecretKey secretKey = keyFactory.generateSecret(dks);
		
		return secretKey;
	}
	
	/**
	 * 
	 * @Description: 解密
	 * @Title: decrypt
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception byte[]
	 * @throws
	 */
	public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		//还原密钥
		Key k = toKey(key);
		
		//实例化
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		
		//初始化, 设置为解密模式
		cipher.init(Cipher.DECRYPT_MODE, k);
		
		//执行操作
		return cipher.doFinal(data);
	}
	
	/**
	 * 
	 * @Description: 加密
	 * @Title: encrypt
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception byte[]
	 * @throws
	 */
	public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		// 还原密钥
		Key k = toKey(key);
		
		//实例化
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		
		//初始化, 设置为加密模式
		cipher.init(Cipher.ENCRYPT_MODE, k);
		//执行操作
		return cipher.doFinal(data);
	}
	
	public static byte[] initKey() throws Exception {
		/**
		 * 若需要使用64位密钥
		 * KeyGenerator.getInstance(KEY_ALGORITHM, "BC");
		 */
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		/**
		 * 若需要使用64位密钥
		 * kg.init(64) 
		 */
		kg.init(56);
		
		SecretKey secretKey = kg.generateKey();
		
		//获得密钥的二进制编码形式
		return secretKey.getEncoded();
	}
}
