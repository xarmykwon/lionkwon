package com.lionkwon.kwonutils.bytes;

import com.lionkwon.kwonutils.string.StringUtils;


/**
 * @class ByteUtils
 * @brief byte 데이타를 16진수 String 데이타로 변환하는 함수를 가진 객체.
 * @author lionkwon
 *
 */
public class ByteUtils {
	
    /**
     * Byte 데이타를 16진수(Hex) 문자로 변화하는 함수
     * @param inByte 16진수 문자로 변환할 데이타.
     * @return 16진수 문자로 변환된 문자 데이타 반환.
     */
	public static String byteToHex(byte inByte)
    {
		byte[] inBytes = {inByte};
       return byteToHex(inBytes, 0, 1);
    }
	
	/**
     * Byte 배열을 16진수(Hex) 문자열로 변화하는 함수
     * @param inBytes 16진수 문자로 변환할 byte 배열.
     * @return 16진수 문자로 변환된 문자열 데이타 반환.
     */
	public static String byteToHex(byte[] inBytes)
    {
       return byteToHex(inBytes, 0, inBytes.length);
    }
    
	/**
	 * Byte 배열을 16진수(Hex) 문자열로 변화하는 함수
	 * @param inBytes 16진수 문자로 변환할 byte 배열.
	 * @param count 배열의 총 길이.
	 * @return 16진수 문자로 변환된 문자열 데이타 반환.
	 */
    public static String byteToHex(byte[] inBytes, int count)
    {
       return byteToHex(inBytes, 0, count);
    }
   
    /**
	 * Byte 배열을 16진수(Hex) 문자열로 변화하는 함수
	 * @param inBytes 16진수 문자로 변환할 byte 배열.
	 * @param startIndex 16진수 문자로 변환할 배열의 시작 위치.
	 * @param endIndex 16진수 문자로 변환할 배열의 끝 위치.
	 * @return 16진수 문자로 변환된 문자열 데이타 반환.
	 */
    public static String byteToHex(byte[] inBytes,int startIndex,int endIndex) 
    {
    	byte newByte = 0x00;
    	int i,hexIndex; 
    	String hexChars = "0123456789ABCDEF";
    	StringBuffer outBuffer = new StringBuffer(endIndex - startIndex);
    	if ( inBytes == null || endIndex <= startIndex ) return (String)null;
    	for ( i = startIndex; i < endIndex; i++) 
    	{
/*
 *       Each Hexadecimal character represents 4 bits and each element of
 *       the byte array represents 8 bits.  First strip off the left 4
 *       bits, shift to the least significant (right) portion of a new
 *       byte, then mask the upper portion to allow proper conversion to an 
 *       integer between 0 and 15.  This value can be used as the index into 
 *       the hexadecimal character string.
 */   
    		newByte = (byte)(inBytes[i] & 0xF0);  
    		newByte = (byte)(newByte >>> 4);     
    		newByte = (byte)(newByte & 0x0F); 
    		hexIndex = (int)newByte; 
    		outBuffer.append(hexChars.substring(hexIndex,hexIndex + 1));
/*
 *       Now strip off the right 4 bits, shift and convert to an integer
 *       between 0 and 15.
 */
    		newByte = (byte)(inBytes[i] & 0x0F);
    		hexIndex = (int)newByte;
    		outBuffer.append(hexChars.substring(hexIndex,hexIndex + 1));
         
    		outBuffer.append(" ");
    	}
    	return outBuffer.toString();
    }

   /**
     * byte 배열을 short 형 데이타로 변환하는 함수.
     * @param inBytes short 형으로 변환할 byte 배열.
     * @return short 데이타. 반환.
     * @remark 배열의 크기가 부호없는 상수보다 크다면 Short.MIN_VALUE를 반환.\n
     *         배열의 위치값을 잘못 지정했을 경우. Short.MIN_VALUE반환.
     */
    public static short byteToShort(byte[] inBytes)
    {
       return byteToShort(inBytes,0,inBytes.length);
    }
      
    /**
     * byte 배열을 short 형 데이타로 변환하는 함수.
     * @param inBytes short 형으로 변환할 byte 배열.
     * @param startIndex 16진수 문자로 변환할 배열의 시작 위치.
	 * @param endIndex 16진수 문자로 변환할 배열의 끝 위치.
	 * @return short 데이타. 반환.
     * @remark 배열의 크기가 부호없는 상수보다 크다면 Short.MIN_VALUE를 반환.\n
     *         배열의 위치값을 잘못 지정했을 경우. Short.MIN_VALUE반환.
     */
    public static short byteToShort(byte[] inBytes,int startIndex,int endIndex)
    {
      String hexString;
      short outputShort;
      hexString = byteToHex(inBytes,startIndex,endIndex);
      hexString = StringUtils.replaceAll(hexString, " ", "");
      try
      {
         outputShort = Short.parseShort(hexString,16);
      } catch (Exception ex ) {
         outputShort = Short.MIN_VALUE;
      }
      return outputShort;
    }
   /*
    * Convert a byte array to an int.  Integer.MIN_VALUE is returned 
    * if the startIndex is greater or equal to the endIndex, or if the
    * resultant unsigned integer is too large to store in an int.
    */
    /**
      * byte 배열을 int 형 데이타로 변환하는 함수.
     * @param inBytes int 형으로 변환할 byte 배열.
     * @return int 데이타. 반환.
     * @remark 배열의 크기가 부호없는 상수보다 크다면 Integer.MIN_VALUE를 반환.\n
     *         배열의 위치값을 잘못 지정했을 경우. Integer.MIN_VALUE반환.
     */
     public static int byteToInt(byte[] inBytes)
     {
        return byteToInt(inBytes,0,inBytes.length);
     }
      
     /**
      * byte 배열을 int 형 데이타로 변환하는 함수.
      * @param inBytes int 형으로 변환할 byte 배열.
      * @param startIndex 16진수 문자로 변환할 배열의 시작 위치.
	  * @param endIndex 16진수 문자로 변환할 배열의 끝 위치.
	  * @return int 데이타. 반환.
      * @remark 배열의 크기가 부호없는 상수보다 크다면 Integer.MIN_VALUE를 반환.\n
      *         배열의 위치값을 잘못 지정했을 경우. Integer.MIN_VALUE반환.
      */
     public static int byteToInt(byte[] inBytes,int startIndex,int endIndex)
     {
        String hexString;
        int outputInt;
        hexString = byteToHex(inBytes,startIndex,endIndex);
        hexString = StringUtils.replaceAll(hexString, " ", "");
        try
        {
           outputInt = Integer.parseInt(hexString,16);
        } catch (Exception ex ) {
           outputInt = Integer.MIN_VALUE;
        }
        return outputInt;
     }

     /**
      * byte 배열을 long 형 데이타로 변환하는 함수.
      * @param inBytes long 형으로 변환할 byte 배열.
      * @return long 데이타. 반환.
      * @remark 배열의 크기가 부호없는 상수보다 크다면 Long.MIN_VALUE를 반환.\n
      *         배열의 위치값을 잘못 지정했을 경우. Long.MIN_VALUE반환.
      */
     public static long byteToLong(byte[] inBytes)
     {
        return byteToLong(inBytes,0,inBytes.length);
     }
      
   /**
    * byte 배열을 long 형 데이타로 변환하는 함수.
    * @param inBytes long 형으로 변환할 byte 배열.
    * @param startIndex 16진수 문자로 변환할 배열의 시작 위치.
	* @param endIndex 16진수 문자로 변환할 배열의 끝 위치.
	* @return long 데이타. 반환.
    * @remark 배열의 크기가 부호없는 상수보다 크다면 Long.MIN_VALUE를 반환.\n
    *         배열의 위치값을 잘못 지정했을 경우. Long.MIN_VALUE반환.
    */
    public static long byteToLong(byte[] inBytes,int startIndex,int endIndex)
    {
      String hexString;
      long outputLong;
      hexString = byteToHex(inBytes,startIndex,endIndex);
      hexString = com.lionkwon.kwonutils.string.StringUtils.replaceAll(hexString, " ", "");
      try
      {
    	  outputLong = Long.parseLong(hexString,16);
      } catch (Exception ex ) {
    	  outputLong = Long.MIN_VALUE;
      }
      return outputLong;
    }
    
    /**
     * byte 데이타를 unsigned integer 데이타로 변환하는 함수.
     * @param b 변환할 byte 데이타.
     * @return unsigned integer 데이타 반환.
     */
    public static int byte2Int(byte b){
    	  int ii = 0;
    	  int temp = 0x01;
    	  
    	  if (b >= 0) return (int)b;
    	  
    	  for (int i = 0; i < 8; i++) {
    		  if( (b & (temp << i)) != 0){
    			  ii += Math.pow(2, i);   
    		  }
    	  }
    	  return ii;
    }
}

