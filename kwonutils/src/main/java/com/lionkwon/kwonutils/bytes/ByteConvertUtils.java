package com.lionkwon.kwonutils.bytes;

/**
 * @class ByteConvertUtils
 * @brief byte[],integer,short 형 변환 클래스
 * @remarks \n
          Integer, Short 형을 ----> byte[]로 변환
          byte[] -----> Integer, Short 형으로 변환
          Integer ---> 4byte
          Short   ---> 2byte
 */
public class ByteConvertUtils {

	/**
	 * @brief Integer를 byte[]로 변환하는 메소
	 * @param type 1:: litte-endian else big-endian
	 * @param integer  변환하고자 하는 숫
	 * @return 변환된 byte[]
	 */
	public static byte[] byteToInteger(int type,int integer) {
		byte[] newByte = new byte[4];
		if(type == 1){
			newByte[0] = (byte)(integer&0xff);
			newByte[1] = (byte)((integer>>8)&0xff);
			newByte[2] = (byte)((integer>>16)&0xff);
			newByte[3] = (byte)((integer>>24)&0xff);
		}else{
			newByte[3] = (byte)(integer&0xff);
			newByte[2] = (byte)((integer>>8)&0xff);
			newByte[1] = (byte)((integer>>16)&0xff);
			newByte[0] = (byte)((integer>>24)&0xff);
		}

		return newByte;
	}


	/**
	 * @brief byte[]를 Integer로 변환하는 메소
	 * @param type 1:: litte-endian else big-endian
	 * @param newByte 변환할 byte[] 배열
	 * @return 변환된 Integer
	 */
	public static int integerToByte(int type,byte[] newByte) {
		int integer = 0;
		if(type == 1){
			integer |= ((int)newByte[3] << 24) & 0xFF000000;
			integer |= ((int)newByte[2] << 16) & 0xFF0000;
			integer |= ((int)newByte[1] << 8) & 0xFF00;
			integer |= ((int)newByte[0]) & 0xFF;
		}else{
			integer |= ((int)newByte[0] << 24) & 0xFF000000;
			integer |= ((int)newByte[1] << 16) & 0xFF0000;
			integer |= ((int)newByte[2] << 8) & 0xFF00;
			integer |= ((int)newByte[3]) & 0xFF;
		}
		return integer;
	}

	/**
	 * @brief short를 byte[]로 변환하는 메소드
	 * @param type 1:: litte-endian else big-endian
	 * @param integer 변환할 short형 데이타
	 * @return 변환된 byte[]
	 */
	public static byte[] byteToShort(int type, short integer) {
		byte[] newByte = new byte[2];
		if(type == 1){
			newByte[0] = (byte)(integer&0xff);
			newByte[1] = (byte)((integer>>8)&0xff);
		}else{
			newByte[1] = (byte)(integer&0xff);
			newByte[0] = (byte)((integer>>8)&0xff);
		}

		return newByte;
	}

	/**
	 * @brief byte[]를 short로 변환하는 메소드
	 * @param type 1:: litte-endian else big-endian
	 * @param newByte 변환할 byte[]
	 * @return 변환된 short
	 */
	public static short shortToByte(int type,byte[] newByte) {
		short integer = 0;
		if(type == 1){
			integer |= ((short)newByte[1] << 8) & 0xFF00;
			integer |= ((short)newByte[0]) & 0xFF;
		}else{
			integer |= ((short)newByte[0] << 8) & 0xFF00;
			integer |= ((short)newByte[1]) & 0xFF;
		}
		return integer;
	}
}
