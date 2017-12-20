package com.lionkwon.kwonutils.security;
/**
 * @file TripleDes.java
 * @brief DES를 3번 적용하는 방식
 * DES(Data Encryption Standard)는 블록 암호의 일종이다.
 * 메시지를 보내려는 사람과 받는 사람이 56비트의 동일한 열쇠를 가지고 있어야 한다.
 * 보내려는 사람은 열쇠를 이용하여 데이터를 암호화하여 안전하지 않은 채널로 전송하면, 
 * 받는 사람은 암호화된 데이터를 받아 보낸 사람의 열쇠와 동일한 열쇠를 이용하여 데이터를 복호화한다.
 * DES는 상당히 널리 쓰이고 있으나, 상당히 복잡하여 많은 부분이 알려져있지 않다.
 */

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import com.lionkwon.kwonutils.security.coder.BASE64Decoder;
import com.lionkwon.kwonutils.security.coder.BASE64Encoder;
/**
 * @class TripleDes
 * @author kittenjun
 * @brief DES를 3번 적용하는 방식
 * C#의 TripleDES의 페딩을 맞춰서 사용하고 있다.
 */
public class TripleDes {

	private static String vectorKey = "lionkwon"; ///< 벡터 키 변수
	private static String key = "softapplionkingman"; ///< 실제 키 변수
	private static String DES_PADDING = "DESede/CBC/PKCS5Padding"; 
	public final static String DES_TRANS = "DES/CBC/NoPadding"; 
	public final static String TRIPLEDES_TRANS = "DESede/CBC/PKCS5Padding";
	public final static String RC2_TRANS = "RC2/CBC/NoPadding";
	public final static String RIJNDAEL_TRANS = "AES/CBC/NoPadding";

	/**
	 * 운용모드 형태를 변경하는 함수
	 * @param type 변경할 운용모드
	 */
	public static void setPadding(String type) {
		DES_PADDING = type;
	}

	/**
	 * 벡터 및 실제 키를 설정하는 함수
	 * @param vectorKey 설정할 벡터 키
	 * @param key 설정할 실제 키
	 */
	public static void setKey(String vectorKey, String key) {
		TripleDes.vectorKey = vectorKey; //iv key
		TripleDes.key = key; //share key
	}

	/**
	 * 문자열 데이터를 TripleDes로 암호화 하는 함수 
	 * @param str TripleDes로 암호화를 적용할 문자열 데이타
	 * @return TripleDes로 암호화 적용이된 문자열 데이타 반환
	 * @throws Exception 운용 모드 및 키값이 틀렸을 경우 예외 발생.
	 */
	public static String encryption(String str) throws Exception {        
		String algorithm = "DESede";           

		byte[] keyValue =new byte[24];        
		if(key.getBytes().length == 16) {
			System.arraycopy(key.getBytes(), 0, keyValue, 0, 16);  
			System.arraycopy(key.getBytes(), 0, keyValue, 16, 8);  
		}else if(key.getBytes().length != 24) {
			return null;
		}else{
			keyValue = key.getBytes();
		}
		DESedeKeySpec keySpec = new DESedeKeySpec(keyValue);        /* Initialization Vector of 8 bytes set to zero. */       

		IvParameterSpec iv = new IvParameterSpec(vectorKey.getBytes());        

		SecretKey key = SecretKeyFactory.getInstance(algorithm).generateSecret(keySpec);        

		Cipher encrypter = Cipher.getInstance(DES_PADDING);       
		encrypter.init(Cipher.ENCRYPT_MODE, key, iv);        
		byte[] input = str.getBytes();      
		byte[] encrypted = encrypter.doFinal(input);        

		return new BASE64Encoder().encodeBuffer(encrypted);

	}

	/**
	 * TripleDes 암호화를 적용한 데이타를 복호화 하는 함수
	 * @param str TripleDes 암호화를 적용한 데이타
	 * @return TripleDes 암호화를 복호화한 문자열 데이타 반환.
	 * @throws Exception 운용 모드 및 키값이 틀렸을 경우 예외 발생.
	 */
	public static String decription(String str) throws Exception  {
		String algorithm = "DESede";           
		byte[] keyValue =new byte[24];        
		if(key.getBytes().length == 16) {
			System.arraycopy(key.getBytes(), 0, keyValue, 0, 16);  
			System.arraycopy(key.getBytes(), 0, keyValue, 16, 8);  
		}else if(key.getBytes().length != 24) {
			return null;
		}else{
			keyValue = key.getBytes();
		}
		DESedeKeySpec keySpec = new DESedeKeySpec(keyValue);        /* Initialization Vector of 8 bytes set to zero. */       

		IvParameterSpec iv = new IvParameterSpec(vectorKey.getBytes());        

		SecretKey key = SecretKeyFactory.getInstance(algorithm).generateSecret(keySpec);        

		Cipher decrypter = Cipher.getInstance(DES_PADDING);        

		decrypter.init(Cipher.DECRYPT_MODE, key, iv);        

		byte[] decrypted = decrypter.doFinal(new BASE64Decoder().decodeBuffer(str));        
		return new String(decrypted);
	}
}
