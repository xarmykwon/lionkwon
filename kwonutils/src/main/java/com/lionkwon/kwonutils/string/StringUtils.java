package com.lionkwon.kwonutils.string;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @file StringUtils.java
 * @brief 문자열 관련하여 자주 사용되는 util을 담고있는 객체.
 */
public class StringUtils {

	/// @example StringUtilsExam.java

	public static final String[] EMPTY_STRING_ARRAY = new String[0];

	/**
	 * strTarget이 null 이거나 화이트스페이스 일 경우 strDest을 반환한다.
	 * 
	 * @param strTarget
	 *            대상 문자열
	 * @param strDest
	 *            대체 문자열
	 * @return strTarget이 null 이거나 화이트스페이스 일 경우 strDest 문자열로 반환
	 */
	public static String nvl(String strTarget, String strDest)
	{
		String retValue = null;

		if ( strTarget == null || strTarget.equals( "" ) )
			retValue = strDest;
		return retValue;
	}



	/**
	 * strTarget이 null 이거나 화이트스페이스 일 경우 화이트스페이스로 반환한다.
	 * 
	 * @param strTarget
	 *            대상문자열
	 * @return strTarget이 null 이거나 화이트스페이스 일 경우 화이트스페이스로 반환
	 */
	public static String nvl(String strTarget)
	{
		return nvl( strTarget, "" );
	}



	/**
	 * 대상문자열이 null 인지 여부 확인하기
	 * 
	 * @param strTarget 대상 문자열
	 * @return null 여부
	 */
	public static boolean isNull(String strTarget)
	{
		boolean retValue = false;

		if ( strTarget == null )
			retValue = true;
		else
			retValue = false;
		return retValue;
	}



	/**
	 * 대상 문자열이 지정한 길이보다 길 경우 지정한 길이만큼 잘라낸 문자열 반환하기
	 * 
	 * @param strTarget
	 *            대상 문자열
	 * @param nLimit
	 *            길이
	 * @param bDot
	 *            잘린 문자열이 존재할 경우 ... 표시 여부 
	 * @return 대상 문자열이 지정한 길이보다 길 경우 지정한 길이만큼 잘라낸 문자열
	 */
	public static String cutText(String strTarget, int nLimit, boolean bDot)
	{
		// System.out.println("strTarget :" + strTarget);
		if ( strTarget == null || strTarget.equals( "" ) )
		{
			return strTarget;
		}

		String retValue = null;

		int nLen = strTarget.length();
		int nTotal = 0;
		int nHex = 0;


		String strDot = "";

		if ( bDot )
			strDot = "...";

		for (int i = 0 ; i < nLen ; i++)
		{
			nHex = (int)strTarget.charAt( i );
			nTotal += Integer.toHexString( nHex ).length() / 2;
			// System.out.println("nTotal :" + nTotal + ", nLimit :" + nLimit);
			if ( nTotal > nLimit )
			{
				// System.out.println("1");
				retValue = strTarget.substring( 0, i ) + strDot;
				break;
			}
			else if ( nTotal == nLimit )
			{
				if ( i == (nLen - 1) )
				{
					// System.out.println("2");
					retValue = strTarget.substring( 0, i - 1 ) + strDot;
					break;
				}
				// System.out.println("3");
				retValue = strTarget.substring( 0, i + 1 ) + strDot;
				break;
			}
			else
			{
				// System.out.println("4");
				retValue = strTarget;
			}
		}
		return retValue;
	}



	/**
	 * 대상문자열에 지정한 문자가 위치한 위치 값을 반환하기(대소문자 무시)
	 * 
	 * @param strTarget
	 *            대상 문자열
	 * @param strDest
	 *            찾고자 하는 문자열
	 * @param nPos
	 *            시작 위치
	 * @return 대상문자열에 지정한 문자가 위치한 위치 값을 반환
	 */
	public static int indexOfIgnore(String strTarget, String strDest, int nPos)
	{
		if ( strTarget == null || strTarget.equals( "" ) )
			return -1;

		strTarget = strTarget.toLowerCase();
		strDest = strDest.toLowerCase();

		return strTarget.indexOf( strDest, nPos );
	}



	/**
	 * 대상문자열에 지정한 문자가 위치한 위치 값을 반환하기(대소문자 무시)
	 * 
	 * @param strTarget
	 *            대상 문자열
	 * @param strDest
	 *            찾고자 하는 문자열
	 * @return 대상문자열에 지정한 문자가 위치한 위치 값을 반환
	 */
	public static int indexOfIgnore(String strTarget, String strDest)
	{
		return indexOfIgnore( strTarget, strDest, 0 );
	}

	/**
	 * 대상 문자열 치환하기
	 * @param text 대상문자열
	 * @param repl 찾고자 하는 문자열
	 * @param with 치환할 문자열
	 * @return 치환된 문자열
	 */
	public static String replace(String text, String repl, String with) {
		return replace(text, repl, with, -1);
	}

	/**
	 * 
	 * 대상 문자열 치환하기
	 * @param text 대상문자열
	 * @param repl 찾고자 하는 문자열
	 * @param with 치환할 문자열
	 * @param max 치환 횟수 :: -1 or 1 이면 한번만 한다.
	 * @return 치환된 문자열
	 */
	public static String replace(String text, String repl, String with, int max) {
		if (isEmpty(text) || isEmpty(repl) || with == null || max == 0) {
			return text;
		}
		int start = 0;
		int end = text.indexOf(repl, start);
		if (end == -1) {
			return text;
		}
		int replLength = repl.length();
		int increase = with.length() - replLength;
		increase = (increase < 0 ? 0 : increase);
		increase *= (max < 0 ? 16 : (max > 64 ? 64 : max));
		StringBuffer buf = new StringBuffer(text.length() + increase);
		while (end != -1) {
			buf.append(text.substring(start, end)).append(with);
			start = end + replLength;
			if (--max == 0) {
				break;
			}
			end = text.indexOf(repl, start);
		}
		buf.append(text.substring(start));
		return buf.toString();
	}

	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	/**
	 * 대상 문자열 모두 치환하기
	 * @param str 대상문자열
	 * @param searchChars 찾고자 하는 문자열
	 * @param replaceChars 치환할 문자열
	 * @return 치환된 문자열
	 */
	public static String replaceAll(String str, String searchChars, String replaceChars) {
		if (isEmpty(str) || isEmpty(searchChars)) {
			return str;
		}
		if (replaceChars == null) {
			replaceChars = "";
		}
		boolean modified = false;
		int replaceCharsLength = replaceChars.length();
		int strLength = str.length();
		StringBuffer buf = new StringBuffer(strLength);
		for (int i = 0; i < strLength; i++) {
			char ch = str.charAt(i);
			int index = searchChars.indexOf(ch);
			if (index >= 0) {
				modified = true;
				if (index < replaceCharsLength) {
					buf.append(replaceChars.charAt(index));
				}
			} else {
				buf.append(ch);
			}
		}
		if (modified) {
			return buf.toString();
		} else {
			return str;
		}
	}
	/**
	 * 각종 구분자 제거하기
	 * 
	 * @param strTarget
	 *            대상문자열
	 * @return String 구분자가 제거된 문자열
	 */
	public static String removeFormat(String strTarget)
	{
		if ( strTarget == null || strTarget.equals( "" ) )
			return strTarget;

		return strTarget.replaceAll( "[$|^|*|+|?|/|:|\\-|,|.|\\s]", "" );
	}



	/**
	 * 콤마 제거하기
	 * 
	 * @param strTarget
	 *            대상 문자열
	 * @return String 콤마가 제거된 문자열
	 */
	public static String removeComma(String strTarget)
	{
		if ( strTarget == null || strTarget.equals( "" ) )
			return strTarget;

		return strTarget.replaceAll( "[,|\\s]", "" );
	}



	/**
	 * HTML 태그 제거하기
	 * @param strTarget 대상문자열
	 * @return 태그가 제거된 문자열
	 */
	public static String removeHTML(String strTarget)
	{
		if ( strTarget == null || strTarget.equals( "" ) )
			return strTarget;

		return strTarget.replaceAll( "<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "" );
	}



	/**
	 * 값 채우기
	 * 
	 * @param strTarget
	 *            대상 문자열
	 * @param strDest
	 *            채워질 문자열
	 * @param nCount 
	 *            채워질 문자열의 반복 횟수.
	 * @param bLeft
	 *            채워질 문자의 방향이 좌측인지 여부
	 * @return 지정한 길이만큼 채워진 문자열
	 */
	public static String padValue(String strTarget, String strDest, int nCount, boolean bLeft)
	{
		if ( strTarget == null )
			return strTarget;

		String retValue = null;

		StringBuffer objSB = new StringBuffer();

		for (int i = 0 ; i < nCount ; i++)
		{
			objSB.append( strDest );
		}

		if ( bLeft == true ) // 채워질 문자열의 방향이 좌측일 경우
			retValue = objSB.toString() + strTarget;
		else
			// 채워질 문자열의 방향이 우측일 경우
			retValue = strTarget + objSB.toString();

		return retValue;
	}



	/**
	 * 좌측으로 값 채우기
	 * 
	 * @param strTarget
	 *            대상 문자열
	 * @param strDest
	 *            채워질 문자열
	 * @param nSize 
	 *            총 문자열 길이
	 * @return 채워진 문자열
	 */
	public static String padLeft(String strTarget, String strDest, int nSize)
	{
		return padValue( strTarget, strDest, nSize, true );
	}



	/**
	 * 좌측에 공백 채우기
	 * 
	 * @param strTarget
	 *            대상 문자열
	 * @param nSize 
	 *            총 문자열 길이
	 * @return 채워진 문자열 길이
	 */
	public static String padLeft(String strTarget, int nSize)
	{
		return padValue( strTarget, " ", nSize, true );
	}



	/**
	 * 우측으로 값 채우기
	 * 
	 * @param strTarget
	 *            대상문자열
	 * @param strDest
	 *            채워질 문자열
	 * @param nSize 
	 *            총 문자열 길이
	 * @return 채워진 문자열 길이
	 */
	public static String padRight(String strTarget, String strDest, int nSize)
	{
		return padValue( strTarget, strDest, nSize, false );
	}



	/**
	 *우측으로 공백 채우기
	 * 
	 * @param strTarget
	 *            대상문자열
	 * @param nSize 
	 *            총 문자열 길이
	 * @return 채워진 문자열
	 */
	public static String padRight(String strTarget, int nSize)
	{
		return padValue( strTarget, " ", nSize, false );
	}



	/**
	 * HTML을 캐리지 리턴 값으로 변환하기
	 * 
	 * @param strTarget
	 *            대상 문자열
	 * @return HTML을 캐리지 리턴값으로 반환한 문자열
	 */
	public static String encodingHTML(String strTarget)
	{
		if ( strTarget == null || strTarget.equals( "" ) )
			return strTarget;

		strTarget = strTarget.replaceAll( "<br>", "\r\n" );
		strTarget = strTarget.replaceAll( "<q>", "'" );
		strTarget = strTarget.replaceAll( "&quot;", "\"" );

		strTarget = strTarget.replaceAll( "<BR>", "\r\n" );
		strTarget = strTarget.replaceAll( "<Q>", "'" );
		strTarget = strTarget.replaceAll( "&QUOT;", "\"" );
		return strTarget;
	}



	/**
	 * 캐리지리턴값을 HTML 태그로 변환하기
	 * 
	 * @param strTarget
	 *            대상 문자열
	 * @return 캐리지 리턴값을 HTML 태그로 변환한 문자열
	 */
	public static String decodingHTML(String strTarget)
	{
		if ( strTarget == null || strTarget.equals( "" ) )
			return strTarget;

		strTarget = strTarget.replaceAll( "\r\n", "<br>" );
		strTarget = strTarget.replaceAll( "'", "<q>" );
		strTarget = strTarget.replaceAll( "\"", "&quot;" );

		strTarget = strTarget.replaceAll( "\r\n", "<BR>" );
		strTarget = strTarget.replaceAll( "'", "<Q>" );
		strTarget = strTarget.replaceAll( "\"", "&QUOT;" );
		return strTarget;
	}



	/**
	 * 대상 문자열을 금액형 문자열로 변환하기
	 * 
	 * @param strTarget 대상 문자열
	 * @return 금액형 문자열
	 */
	public static String formatMoney(String strTarget)
	{
		if ( strTarget == null)
			return "0";

		strTarget = removeComma( strTarget );

		String strSign = strTarget.substring( 0, 1 );
		if ( strSign.equals( "+" ) || strSign.equals( "-" ) )
		{ // 부호가 존재할 경우
			strSign = strTarget.substring( 0, 1 );
			strTarget = strTarget.substring( 1 );
		}
		else
		{
			strSign = "";
		}

		String strDot = "";
		if ( strTarget.indexOf( "." ) != -1 )
		{ // 소숫점이 존재할 경우
			int nPosDot = strTarget.indexOf( "." );
			strDot = strTarget.substring( nPosDot, strTarget.length() );
			strTarget = strTarget.substring( 0, nPosDot );
		}

		StringBuffer objSB = new StringBuffer( strTarget );
		int nLen = strTarget.length();
		for (int i = nLen ; 0 < i ; i -= 3) // Comma 단위 
		{
			objSB.insert( i, "," );
		}
		return strSign + objSB.substring( 0, objSB.length() - 1 ) + strDot;
	}



	/**
	 * 대상문자열의 소숫점 설정하기
	 * 
	 * @param strTarget 대상문자열
	 * @param nDotSize 소숫점 길이
	 * @return
	 */
	public static String round(String strTarget, int nDotSize)
	{
		if ( strTarget == null || strTarget.trim().length() == 0 )
			return strTarget;

		String strDot = null;

		int nPosDot = strTarget.indexOf( "." );
		if ( nPosDot == -1 )
		{ // 소숫점이 존재하지 않을 경우
			strDot = (nDotSize == 0) ? padValue( "", "0", nDotSize, false ) : "." + padValue( "", "0", nDotSize, false );
		}
		else
		{ // 소숫점이 존재할 경우

			String strDotValue = strTarget.substring( nPosDot + 1 ); // 소숫점 이하 값
			strTarget = strTarget.substring( 0, nPosDot ); // 정수 값

			if ( strDotValue.length() >= nDotSize )
			{ // 실제 소숫점 길이가 지정한 길이보다 크다면 지정한 소숫점 길이 만큼 잘라내기
				strDot = "." + strDotValue.substring( 0, nDotSize );
			}
			else
			{ // 실제 소숫점길이가 지정한 길이보다 작다면 지정한 길이만큼 채우기 
				strDot = "." + padValue( strDotValue, "0", nDotSize, false );
			}
		}
		return strTarget + strDot;
	}

	/**
	 * 대상문자열에 공백으로 잘라 문자열 배열로 만들어 주는 함수.
	 * @param str 대상문자열
	 * @return 공백으로 자른 문자열배열 반환.
	 */
	public static String[] split(String str) {
		return split(str, null, -1);
	}

	/**
	 * 대상문자열을 지정한 구분자(문자)로 잘라 문자열 배열로 만들어 주는 함수.
	 * @param str 대상문자열
	 * @param separatorChar 문자열을 자를 구분자(문자).
	 * @return 구분자로 자른 문자열 배열 반환.
	 */
	public static String[] split(String str, char separatorChar) {
		return splitWorker(str, separatorChar, false);
	}

	/**
	 * 대상문자열을 지정한 구분자(문자열)로 잘라 문자열 배열로 만들어 주는 함수.
	 * @param str 대상문자열
	 * @param separatorChars 문자열을 자를 구분자(문자열)
	 * @return 구분자로 자른 문자열 배열 반환
	 */
	public static String[] split(String str, String separatorChars) {
		return splitWorker(str, separatorChars, -1, false);
	}

	/**
	 * 대상문자열을 지정한 구분자(문자열)로 잘라 문자열 배열로 만들어 주는 함수 
	 * @param str 대상문자열
	 * @param separatorChars 문자열을 자를 구분자(문자열)
	 * @param max 반환되는 문자열 배열 크기지정 :: -1이면 잘라진 전체 문자열 배열을 뜻함.
	 * @return 구분자로 자른 문자열 배열 반환
	 */
	public static String[] split(String str, String separatorChars, int max) {
		return splitWorker(str, separatorChars, max, false);
	}

	private static String[] splitWorker(String str, char separatorChar, boolean preserveAllTokens) {
		// Performance tuned for 2.0 (JDK1.4)

		if (str == null) {
			return null;
		}
		int len = str.length();
		if (len == 0) {
			return EMPTY_STRING_ARRAY;
		}
		List list = new ArrayList();
		int i = 0, start = 0;
		boolean match = false;
		boolean lastMatch = false;
		while (i < len) {
			if (str.charAt(i) == separatorChar) {
				if (match || preserveAllTokens) {
					list.add(str.substring(start, i));
					match = false;
					lastMatch = true;
				}
				start = ++i;
				continue;
			} else {
				lastMatch = false;
			}
			match = true;
			i++;
		}
		if (match || (preserveAllTokens && lastMatch)) {
			list.add(str.substring(start, i));
		}
		return (String[]) list.toArray(new String[list.size()]);
	}

	private static String[] splitWorker(String str, String separatorChars, int max, boolean preserveAllTokens) {
		// Performance tuned for 2.0 (JDK1.4)
		// Direct code is quicker than StringTokenizer.
		// Also, StringTokenizer uses isSpace() not isWhitespace()

		if (str == null) {
			return null;
		}
		int len = str.length();
		if (len == 0) {
			return EMPTY_STRING_ARRAY;
		}
		List list = new ArrayList();
		int sizePlus1 = 1;
		int i = 0, start = 0;
		boolean match = false;
		boolean lastMatch = false;
		if (separatorChars == null) {
			// Null separator means use whitespace
			while (i < len) {
				if (Character.isWhitespace(str.charAt(i))) {
					if (match || preserveAllTokens) {
						lastMatch = true;
						if (sizePlus1++ == max) {
							i = len;
							lastMatch = false;
						}
						list.add(str.substring(start, i));
						match = false;
					}
					start = ++i;
					continue;
				} else {
					lastMatch = false;
				}
				match = true;
				i++;
			}
		} else if (separatorChars.length() == 1) {
			// Optimise 1 character case
			char sep = separatorChars.charAt(0);
			while (i < len) {
				if (str.charAt(i) == sep) {
					if (match || preserveAllTokens) {
						lastMatch = true;
						if (sizePlus1++ == max) {
							i = len;
							lastMatch = false;
						}
						list.add(str.substring(start, i));
						match = false;
					}
					start = ++i;
					continue;
				} else {
					lastMatch = false;
				}
				match = true;
				i++;
			}
		} else {
			// standard case
			while (i < len) {
				if (separatorChars.indexOf(str.charAt(i)) >= 0) {
					if (match || preserveAllTokens) {
						lastMatch = true;
						if (sizePlus1++ == max) {
							i = len;
							lastMatch = false;
						}
						list.add(str.substring(start, i));
						match = false;
					}
					start = ++i;
					continue;
				} else {
					lastMatch = false;
				}
				match = true;
				i++;
			}
		}
		if (match || (preserveAllTokens && lastMatch)) {
			list.add(str.substring(start, i));
		}
		return (String[]) list.toArray(new String[list.size()]);
	}

	/**
	 * 한글을 유니코드로 변환하는 함수(URI한글이 포함되었을 경우 :: 웹브라우저 전송시 사용).
	 * @param src 한글이 표함된 문자열
	 * @return 유니코드 인코딩 문자열
	 */
	public static String escape(String src) {   
		int i;   
		char j;   
		StringBuffer tmp = new StringBuffer();   
		tmp.ensureCapacity(src.length() * 6);   
		for (i = 0; i < src.length(); i++) {   
			j = src.charAt(i);   
			if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j))   
				tmp.append(j);   
			else if (j < 256) {   
				tmp.append("%");   
				if (j < 16)   
					tmp.append("0");   
				tmp.append(Integer.toString(j, 16));   
			} else {   
				tmp.append("%u");   
				tmp.append(Integer.toString(j, 16));   
			}   
		}   
		return tmp.toString();   
	}   

	/**
	 * 유니코드를 한글로 변환하는 함수(URI한글이 포함되었을 경우 :: 웹브라우저 전송시 사용)
	 * @param src 유니코드 문자열
	 * @return 유니코드 디코딩된 문자열
	 */
	public static String unescape(String src) {   
		StringBuffer tmp = new StringBuffer();   
		tmp.ensureCapacity(src.length());   
		int lastPos = 0, pos = 0;   
		char ch;   
		while (lastPos < src.length()) {   
			pos = src.indexOf("%", lastPos);   
			if (pos == lastPos) {   
				if (src.charAt(pos + 1) == 'u') {   
					ch = (char) Integer.parseInt(src   
							.substring(pos + 2, pos + 6), 16);   
					tmp.append(ch);   
					lastPos = pos + 6;   
				} else {   
					ch = (char) Integer.parseInt(src   
							.substring(pos + 1, pos + 3), 16);   
					tmp.append(ch);   
					lastPos = pos + 3;   
				}   
			} else {   
				if (pos == -1) {   
					tmp.append(src.substring(lastPos));   
					lastPos = src.length();   
				} else {   
					tmp.append(src.substring(lastPos, pos));   
					lastPos = pos;   
				}   
			}   
		}   
		return tmp.toString();   
	} 


	/**
	 * 한글를 유니코드로 변환하는 함수(아스키값으로 변경)
	 * @param str 문자열
	 * @return 유니코드 인코딩된 문자열
	 */
	public static String StrtoUni(String str)
	{
		String uniString = "" ;

		for ( int i = 0 ; i < str.length() ; i++)
		{
			char chr = str.charAt(i) ;
			String hex = Integer.toHexString(chr) ;
			uniString += "\\u"+hex ;
		}

		return uniString ;
	}


	/**
	 * 유니코드를 한글로 변환하는 함수(아스키값으로 변경)
	 * @param uni 유니코드 문자열
	 * @return 유니코드 디코딩된 문자열
	 */
	public static String UnitoStr(String uni)
	{
		String strString = "" ;

		StringTokenizer str1 = new StringTokenizer(uni,"\\u") ;

		while(str1.hasMoreTokens())
		{
			String str2 = str1.nextToken() ;
			int i = Integer.parseInt(str2,16) ;
			strString += (char)i ;
		}
		return strString ;
	}
}
