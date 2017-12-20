package com.lionkwon.kwonutils.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class KwonDate {


	private static class TIME_MAXIMUM{
		public static final int SEC = 60;
		public static final int MIN = 60;
		public static final int HOUR = 24;
		public static final int DAY = 30;
		public static final int MONTH = 12;
	}

	/**
	 * 현재 시간으로 부터 얼마전인지 반환
	 * @Method Name  : formatTimeString
	 * @작성일   : 2014. 1. 3. 
	 * @작성자   : kimsungkwon
	 * @변경이력  :
	 * @Method 설명 :
	 * @param tempDate
	 * @return
	 */
	public static String formatTimeString(Date tempDate) {

		long curTime = System.currentTimeMillis();
		long regTime = tempDate.getTime();
		long diffTime = (curTime - regTime) / 1000;

		String msg = null;
		if (diffTime < TIME_MAXIMUM.SEC) {
			// sec
			msg = "방금 전";
		} else if ((diffTime /= TIME_MAXIMUM.SEC) < TIME_MAXIMUM.MIN) {
			// min
			msg = diffTime + "분 전";
		} else if ((diffTime /= TIME_MAXIMUM.MIN) < TIME_MAXIMUM.HOUR) {
			// hour
			msg = (diffTime) + "시간 전";
		} else if ((diffTime /= TIME_MAXIMUM.HOUR) < TIME_MAXIMUM.DAY) {
			// day
			msg = (diffTime) + "일 전";
		} else if ((diffTime /= TIME_MAXIMUM.DAY) < TIME_MAXIMUM.MONTH) {
			// day
			msg = (diffTime) + "달 전";
		} else {
			msg = (diffTime) + "년 전";
		}

		return msg;
	}
	
	/**
	 * 이걸로 해야 일자 비교 가능
	 * @Method Name  : isToday
	 * @작성일   : 2014. 1. 3. 
	 * @작성자   : kimsungkwon
	 * @변경이력  :
	 * @Method 설명 :
	 * @param itDay
	 * @return
	 */
	public static String isToday(Long itDay){
		SimpleDateFormat format = new SimpleDateFormat( "yyyy/MM/dd" );
		SimpleDateFormat format2 = new SimpleDateFormat( "a hh:mm" );
		Date day1 = new Date();

		Date day2 = new Date();
		day2.setTime(itDay);

		Calendar aDate = Calendar.getInstance(); // 비교하고자 하는 임의의 날짜
		aDate.setTime(day1);

		Calendar bDate = Calendar.getInstance(); // 이것이 시스템의 날짜
		bDate.setTimeInMillis(itDay);

		// 여기에 시,분,초를 0으로 세팅해야 before, after를 제대로 비교함
		aDate.set( Calendar.HOUR_OF_DAY, 0 );
		aDate.set( Calendar.MINUTE, 0 );
		aDate.set( Calendar.SECOND, 0 );
		aDate.set( Calendar.MILLISECOND, 0 );

		bDate.set( Calendar.HOUR_OF_DAY, 0 );
		bDate.set( Calendar.MINUTE, 0 );
		bDate.set( Calendar.SECOND, 0 );
		bDate.set( Calendar.MILLISECOND, 0 );

		if (aDate.after(bDate)) // aDate가 bDate보다 클 경우 출력
			return format.format(day2); 
		else if (aDate.before(bDate)) // aDate가 bDate보다 작을 경우 출력
			return format.format(day2);
		else // aDate = bDate인 경우
			return format2.format(day2);
	}

	/**
	 * 오늘인지 확인
	 * @Method Name  : isToday
	 * @작성일   : 2014. 1. 3. 
	 * @작성자   : kimsungkwon
	 * @변경이력  :
	 * @Method 설명 :
	 * @param oneday
	 * @param itDay
	 * @return
	 */
	public static String isToday(Long oneday, Long itDay){
		SimpleDateFormat format = new SimpleDateFormat( "yyyy년 MM월 dd일 E요일" );
		Date day1 = new Date();
		day1.setTime(oneday);
		Date day2 = new Date();
		day2.setTime(itDay);

		int compare = day1.compareTo( day2 );
		if ( compare > 0 )
		{
			return format.format(day2);  // 다른날
		}
		else if ( compare < 0 )
		{
			return format.format(day2);  // 다른날
		}
		else
		{
			return null; // 오늘
		}
	}


	/**
	 * 날짜를 일로 비교함. 이걸로 해야 시간 단위가 아닌 일 단위 날짜 구분 가능
	 * @Method Name  : isToday2
	 * @작성일   : 2014. 1. 3. 
	 * @작성자   : kimsungkwon
	 * @변경이력  :
	 * @Method 설명 :
	 * @param oneday
	 * @param itDay
	 * @return
	 */
	public static boolean isToday2(Long oneday, Long itDay){
		Calendar aDate = Calendar.getInstance(); // 비교하고자 하는 임의의 날짜
		aDate.setTimeInMillis(oneday);

		Calendar bDate = Calendar.getInstance(); // 이것이 시스템의 날짜
		bDate.setTimeInMillis(itDay);

		// 여기에 시,분,초를 0으로 세팅해야 before, after를 제대로 비교함
		aDate.set( Calendar.HOUR_OF_DAY, 0 );
		aDate.set( Calendar.MINUTE, 0 );
		aDate.set( Calendar.SECOND, 0 );
		aDate.set( Calendar.MILLISECOND, 0 );

		bDate.set( Calendar.HOUR_OF_DAY, 0 );
		bDate.set( Calendar.MINUTE, 0 );
		bDate.set( Calendar.SECOND, 0 );
		bDate.set( Calendar.MILLISECOND, 0 );

		if (aDate.after(bDate)) // aDate가 bDate보다 클 경우 출력
			return false; 
		else if (aDate.before(bDate)) // aDate가 bDate보다 작을 경우 출력
			return false;
		else // aDate = bDate인 경우
			return true;
	}

	/**
	 * 날짜 비교 - 두개의 날짜중 하나 반환
	 * @Method Name  : systemDate
	 * @작성일   : 2014. 1. 3. 
	 * @작성자   : kimsungkwon
	 * @변경이력  :
	 * @Method 설명 :
	 * @param lastDate
	 * @param newDate
	 * @return
	 */
	public static String systemDate(Long lastDate, Long newDate){
		SimpleDateFormat format2 = new SimpleDateFormat( "yyyy년 MM월 dd일 E요일" );
		Date day2 = new Date();
		if(lastDate!=null){
			Date day1 = new Date();
			day1.setTime(lastDate);
			day2.setTime(newDate);

			int compare = day1.compareTo( day2 );
			if ( compare > 0 )
			{
				return null;
			}
			else if ( compare < 0 )
			{
				return null;
			}
			else
			{
				return format2.format(day2);  // 오늘날짜를 보내줌
			}

		}else{
			return format2.format(day2);  // 오늘날짜를 보내줌
		}
	}

	
	/**
	 * Long -> String 으로 변환
	 * @Method Name  : getString
	 * @작성일   : 2014. 1. 3. 
	 * @작성자   : kimsungkwon
	 * @변경이력  :
	 * @Method 설명 :
	 * @param Date
	 * @return
	 */
	public static String getString(Long Date){
		if(Date!=null){
			SimpleDateFormat format2 = new SimpleDateFormat( "yyyy년 MM월 dd일 E요일" );
			Date day = new Date();
			day.setTime(Date);
			return format2.format(day);  // 오늘날짜를 보내줌	
		}else{
			return null;
		}

	}

	/**
	 * 날짜차이 계산 - 시간 단위 기준
	 * @Method Name  : diffOfDate
	 * @작성일   : 2014. 1. 3. 
	 * @작성자   : kimsungkwon
	 * @변경이력  :
	 * @Method 설명 :
	 * @param begin
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public static long diffOfDate(String begin, String end) throws Exception
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

		Date beginDate = formatter.parse(begin);
		Date endDate = formatter.parse(end);

		long diff = endDate.getTime() - beginDate.getTime();
		long diffDays = diff / (24 * 60 * 60 * 1000);

		return diffDays;
	}

	/**
	 * 날짜차이 계산 - 시간 단위 기준
	 * @Method Name  : diffOfDate
	 * @작성일   : 2014. 1. 3. 
	 * @작성자   : kimsungkwon
	 * @변경이력  :
	 * @Method 설명 :
	 * @param begin
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public static long diffOfDate(Long begin, Long end) throws Exception
	{
		Date beginDate = new Date(begin);
		Date endDate = new Date(end);

		long diff = endDate.getTime() - beginDate.getTime();
		long diffDays = diff / (24 * 60 * 60 * 1000);

		return diffDays;
	}
}
