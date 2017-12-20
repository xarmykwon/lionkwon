package com.lionkwon.kwonutils.bytes;

/**
 * @class Unsigned
 * @brief JAVA에는 unsigned 형이 없으므로 인위적으로 unsigned 형으로 변환하는 객체.
 */
public class Unsigned {

	/**
	 * 데이타가 음수면 unsigned 형으로 변환하는 메소드.
	 * @param a unsigned 형으로 변환할 데이타.
	 * @return unsigned(부호없는=>+) char 데이타 반환.
	 */
	public static char a2i(char a) {
		
		if(a <='9')
			return (char) (a - '0');
		else if(a <= 'F')
			return (char) (a - 'A' + 10);
		else
			return (char) (a - 'a' + 10);  
	}
	
}
