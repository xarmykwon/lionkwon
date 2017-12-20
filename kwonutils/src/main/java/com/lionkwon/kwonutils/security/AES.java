package com.lionkwon.kwonutils.security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @class AES
 * @brief AES(advanced encryption standard:고급 암호 표준)
 */
public class AES {
	/**
	 * @file AES.java
	 * @brief AES(advanced encryption standard:고급 암호 표준)
	 * AES는, 민감하지만 비밀로 분류되지는 않은 자료들에 대해 보안을 유지하기 위해 미국 정부기관들이 사용하는 암호화 알고리즘이며, 
	 * 그에 따라 민간 부문의 상업 거래용으로서 사실상의 암호화 표준으로 자리 잡을 것으로 보인다 (미군이나 그밖에 극비로 분류된 통신은 별도의 비밀 알고리즘에 의해 암호화된다).
	 * 1997년 1월에, 기존의 데이터 암호 표준, 즉 DES를 대체할 보다 강력한 알고리즘을 찾기 위한 공모 작업이 미국 상무부의 한 기관인 표준기술연구소(NIST)에 의해 시작되었다. 
	 * 새로운 알고리즘이 충족해야 할 규격 요건으로는, 최소 128 비트나 192 비트 또는 256 비트 크기의 키를 지원하는 128 비트 크기의 블록 암호화를 사용한 대칭형 
	 * (암호화나 복호화를 하는데 동일한 키가 사용되는) 알고리즘으로서, 전 세계적으로 로열티 없이 사용할 수 있어야 하며, 향후 20년~30년 동안 데이터를 보호하기 위해 
	 * 충분한 정도의 보안성을 제공할 것이 요구되었다. 
	 * 또한, 이 알고리즘은 스마트카드 등과 같은 제한된 환경을 포함하여 하드웨어나 소프트웨어로 구현하기 쉬워야 했으며, 다양한 공격 기술에 대해서도 잘 방어할 수 있어야 했다.
	 */

	public static String key = "75de8a33d3f18f1c29d86fa42b1894c7"; ///< AES Key 변수.
	public static String transformation = "AES"; ///< 운용모드
	public static int radix = 16; ///< 암호화 키 사이즈 변수

	/**
	 * hex to byte[]  : 16진수 문자열을 바이트 배열로 변환하는 함수.
	 * 
	 * @param hex 16진수 문자열
	 * @return 바이트 배열로 반환
	 */
	public static byte[] hexToByteArray(String hex) {
		if (hex == null || hex.length() == 0) {
			return null;
		}

		byte[] ba = new byte[hex.length() / 2];
		for (int i = 0; i < ba.length; i++) {
			ba[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return ba;
	}

	/**
	 * byte[] to hex : unsigned byte(바이트) 배열을 16진수 문자열로 변환하는 함수
	 * 
	 * @param ba 16진수 문자열로 바꿀 바이트 배열
	 * @return 16진수 문자열 반환
	 */
	public static String byteArrayToHex(byte[] ba) {
		if (ba == null || ba.length == 0) {
			return null;
		}

		StringBuffer sb = new StringBuffer(ba.length * 2);
		String hexNumber;
		for (int x = 0; x < ba.length; x++) {
			hexNumber = "0" + Integer.toHexString(0xff & ba[x]);

			sb.append(hexNumber.substring(hexNumber.length() - 2));
		}
		return sb.toString();
	} 

	/**
	 * 문자열 데이타를 AES 방식의 암호화를 하는 함수
	 * 
	 * @param message AES 방식의 암호화를 할 문자열 데이타
	 * @return AES 암호화를 적용한 문자열 데이타 반환.
	 * @throws Exception 키값이 맞지 않을 경우 예외 발생
	 */
	public static String encrypt(String message) throws Exception {
		// use key coss2
		SecretKeySpec skeySpec = new SecretKeySpec(toBytes(key,radix), "AES");

		// Instantiate the cipher
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encrypted = cipher.doFinal(message.getBytes());
		return byteArrayToHex(encrypted);
	}

	/**
	 * AES 방식의 암호화된 데이타를 복호화하는 함수.
	 * 
	 * @param message AES 방식의 암호화를 적용한 문자열 데이타
	 * @return AES 방식의 암호화된 데이타를 복호화한 데이타 반환.
	 * @throws Exception 키값이 맞지 않을 경우 예외 발생
	 */
	public static String decrypt(String encrypted) throws Exception {
		// use key coss2
		SecretKeySpec skeySpec = new SecretKeySpec(toBytes(key,16), "AES");

		Cipher cipher = Cipher.getInstance(transformation);
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);

		byte[] original = cipher.doFinal(hexToByteArray(encrypted));
		String originalString = new String(original);
		return originalString;
	}

	/**
	 * Key 문자열을 바이트 배열로 바꾼다.
	 * @param digits 문자열
	 * @param radix 진수
	 * @return 바이트 배열 반환.
	 * @throws IllegalArgumentException 진수 값이 16 or 10 or 8 아니면 예외 발생, 문자열의 길이를 2 or 3으로 나눴을 때 나머지가 있으면 예외 발생
	 * @throws NumberFormatException 문자열에 0~9 or a~f 이외의 값이 들어 갔을 경우 예외 발생
	 */
	public static byte[] toBytes(String digits, int radix) throws IllegalArgumentException, NumberFormatException {
		if (digits == null) {
			return null;
		}
		if (radix != 16 && radix != 10 && radix != 8) {
			throw new IllegalArgumentException("For input radix: \"" + radix + "\"");
		}
		int divLen = (radix == 16) ? 2 : 3;
		int length = digits.length();
		if (length % divLen == 1) {
			throw new IllegalArgumentException("For input string: \"" + digits + "\"");
		}

		length = length / divLen;
		byte[] bytes = new byte[length];
		for (int i = 0; i < length; i++) {
			int index = i * divLen;
			bytes[i] = (byte)(Short.parseShort(digits.substring(index, index+divLen), radix));
		}
		return bytes;
	}
}


