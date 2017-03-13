package com.chenlx.codec;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import com.chenlx.constants.CommonConstants;

public class AbstractAESCodecTest {

	@Test
	public void test() {
		String content = "chenlx";
		byte[] data = null;
		try {
			data = content.getBytes(CommonConstants.UTF8_ENCODING);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		try {
			byte[] key = AESCodec.init128Key();
			
			byte[] result = AESCodec.encrypt(data, key);
			
			byte[] decryptContent = AESCodec.decrypt(result, key);
			
			System.out.println(new String(decryptContent, CommonConstants.UTF8_ENCODING));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
