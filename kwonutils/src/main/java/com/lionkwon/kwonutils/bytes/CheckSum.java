package com.lionkwon.kwonutils.bytes;

/**
 * @class CheckSum
 * @brief byte 통신에서 오류 검사 코드 check sum을 생성하는 객체.\n
 *
 */
public class CheckSum {
	
	/**
	 * byte 배열의 check sum code를 만드는 함수.
	 * @param buffer check sum을 만들 byte 배열.
	 * @return check sum 반환.
	 * @remark 지정된 범위의 데이타를 연산자 + 를 이용하여 더한 값.
	 */
    public static byte makePlus( byte[] buffer ){
        return makePlus(buffer, buffer.length);
    }

    /**
     * byte 배열의 check sum code를 만드는 함수.
     * @param buffer check sum을 만들 byte 배열.
     * @param length byte 배열에서 check sum 데이터를 만드는데 사용되는 범위 :: 시작은 0 부터.
     * @return check sum 반환.
     * @remark 지정된 범위의 데이타를 연산자 + 를 이용하여 더한 값.
     */
    public static byte makePlus( byte[] buffer, int length ){
        return makePlus(buffer, 0, length);
    }
    
    /**
     * byte 배열의 check sum code를 만드는 함수.
     * @param buffer check sum을 만들 byte 배열.
     * @param start  check sum을 만들 시작 데이타 지정.
     * @param end check sum을 만들 끝 데이타 지정.
     * @return check sum 반환.
     * @remark start부터 end 까지 지정한 범위의 데이터를 이용하여\n
     *         비트 연산을 하여 check sum 데이타를 구한다.\n
     *         지정된 범위의 데이타를 연산자 + 를 이용하여 더한 값.
     */
    public static byte makePlus( byte[] buffer, int start, int end ){
    	int  i;
		byte btCS = 0;
		
		for( i = start; i < end; i++ )
		{
	        btCS += buffer[ i ] ;
		}
		return btCS;
    }
    
    /**
	 * byte 배열의 check sum code를 만드는 함수.
	 * @param buffer check sum을 만들 byte 배열.
	 * @return check sum 반환.
	 * @remark 지정된 범위의 데이타를 연산자 ^ 를 이용하여 더한 값.
	 */
    public static byte makeXOR( byte[] buffer ){
        return makeXOR(buffer, buffer.length);
    }

    /**
     * byte 배열의 check sum code를 만드는 함수.
     * @param buffer check sum을 만들 byte 배열.
     * @param length byte 배열에서 check sum 데이터를 만드는데 사용되는 범위 :: 시작은 0 부터.
     * @return check sum 반환.
     * @remark 지정된 범위의 데이타를 연산자 ^ 를 이용하여 더한 값.
     */
    public static byte makeXOR( byte[] buffer, int length ){
        return makeXOR(buffer, 0, length);
    }
    
    /**
     * byte 배열의 check sum code를 만드는 함수.
     * @param buffer check sum을 만들 byte 배열.
     * @param start  check sum을 만들 시작 데이타 지정.
     * @param end check sum을 만들 끝 데이타 지정.
     * @return check sum 반환.
     * @remark start부터 end 까지 지정한 범위의 데이터를 이용하여\n
     *         비트 연산을 하여 check sum 데이타를 구한다.\n
     *         지정된 범위의 데이타를 연산자 ^ 를 이용하여 더한 값.
     */
    public static byte makeXOR( byte[] buffer, int start, int end ){
    	int  i;
		byte btCS = 0;
		
		for( i = start; i < end; i++ )
		{
	        btCS ^= buffer[ i ] ;
		}
		return btCS;
    }
}
