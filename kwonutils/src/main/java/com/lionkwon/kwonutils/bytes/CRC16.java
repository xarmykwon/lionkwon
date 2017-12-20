package com.lionkwon.kwonutils.bytes;

/**
 * @class CRC16
 * @brief byte 통신에서 오류 검사 코드 CRC 16byte를 생성하는 객체.\n
 */
public class CRC16 {

	/**
	 * byte 배열의 crc 16bit code를 만드는 함수.
	 * @param buffer crc 16bit를 만들 byte 배열.
	 * @return CRC 16bit(2byte) 반환.
	 */
    public static byte[] make(byte[] buffer){
        return make(buffer, buffer.length);	
    }
    /**
	 * byte 배열의 crc short 데이타를 만드는 함수.
	 * @param buffer crc short 데이타를 만들 byte 배열.
	 * @return CRC short 반환.
	 */
    public static short makes(byte[] buffer) {
    	return makes(buffer, buffer.length);
    }
    /**
     * byte 배열의 crc short 데이타를 만드는 함수.
     * @param buffer crc short 데이타를 만들 byte 배열.
     * @param length byte 배열에서 CRC 데이터를 만드는 사용되는 범위 :: 시작은 0 부터.
     * @return CRC short 반환.
     */
    public static short makes(byte[] buffer, int length) {
    	short crc=0, temp =0, quick=0;
    	int i=0;
    	
    	while(i<length){
    		 temp = (short)( ((crc >>> 8) & 0xFF) ^ (buffer[i++] & 0xFF) );
             crc <<= 8;
             quick = (short) ( temp ^ (temp >>> 4) );
             crc ^= quick;
             quick <<= 5;
             crc ^= quick;
             quick <<=7;
             crc ^= quick;
         }
    	
    	return crc;
    }
    
    /**
     * byte 배열의 crc short 데이타를 만드는 함수.
     * @param buffer crc short 데이타를 만들 byte 배열.
     * @param length byte 배열에서 CRC 데이터를 만드는 사용되는 범위 :: 시작은 0 부터.
     * @return CRC 16bit(2byte) 반환.
     */
    public static byte[] make(byte[] buffer, int length){
        short crc = 0, temp = 0, quick = 0;
        int i = 0;
        
        while(i < length){
            temp = (short)( ((crc >>> 8) & 0xFF) ^ (buffer[i++] & 0xFF) );
            crc <<= 8;
            quick = (short) ( temp ^ (temp >>> 4) );
            crc ^= quick;
            quick <<= 5;
            crc ^= quick;
            quick <<=7;
            crc ^= quick;
        }
        
        byte[] temp2 = new byte[2];
        
        temp2[0] = (byte)(crc>>8 & 0xFF);
        temp2[1] = (byte)(crc & 0xFF);
        
        return temp2;
    }
    
    /**
     * byte 배열에서 CRC 16bit를 검사하는 함수
     * @param packet CRC 16bit를 검사할 함수.
     * @return 오류가 없으면 true 반환, 오류가 있으면 fale 반환.
     */
    public static boolean checker(byte[] packet){
    	byte[] crc1 = make(packet, packet.length-3);
    	byte[] crc2 = ArrayUtils.subarray(packet, packet.length-3, packet.length-1);
    	
    	return ArrayUtils.isEquals(crc1, crc2);
    }
}
