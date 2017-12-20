package com.lionkwon.kwonutils.etc;

import java.util.UUID;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.lionkwon.kwonutils.log.Logger;

public class UUID_Create {
	
	
	/**
	 * 디바이스 고유키 생성
	 * @Method Name  : getDeviceId
	 * @작성일   : 2014. 3. 28. 
	 * @작성자   : lionkwon
	 * @변경이력  :
	 * @Method 설명 :
	 * 게임이나 유틸리티를 만들때 어플간의 데이터 충돌을 방지하기 위해 ID를 생성할 필요가 있는데

사용자에게 홈페이지 가입모듈이란 귀찮은 작업을 시키기는 싫을때 사용하는 정보입니다.




고유한 ID를 만들때 ip주소를 참고해 볼까도 하지만 ip주소는 결국 최 상단의 대표 ip와 내부 ip만 나올뿐 제대로 된 정보가 아닙니다.

 1개의 방에서 wifi를 통해서 여러개의 휴대폰이 접속하거나 dhcp(공유기에서 사용하는 내부 ip 자동할당)기능을 꺼놓고 다른 기기에다가

 이전에 사용했던 내부 ip를 할당하게 된다면 ip주소는 고유한 ID를 할당하기에는 적합하지 않습니다.




MacAddress도 네트워크 카드마다 유일하다고 하지만 wifi를 거치게 되면 wifi기기의 맥어드레스로 교체되고

 만약 공유기를 사용하지 않고 3G로 접속했을때는 휴대폰에 따라서 맥어드래스를 제공하거나 제공하지 않는 휴대폰이 존재합니다.




기본 안드로이드에서 제공하는 디바이스 생성 ID함수인 'TelephonyManager.getDefault.getDevideId()'의 경우는 USIM이 존재해야 하며

 USIM이 존재해도 값이 안넘어오는 경우가 발생합니다. 갤럭시S 초기 펌웨어 버전에서 간혹 발생됩니다.

 그리고 USIM을 탑재못하는 갤럭시 탭 등 플레이어 전용 안드로이드 기기에서 빈 값이 넘어오는 오류가 발생됩니다.




그래서 아래와 같이 여러개의 값을 조합하는 방법으로 초? 유니크한 UUID를 만듭니다.

===UUID

 UUID는 36글자의 char형 데이터가 반환되게 됩니다.

 해당 함수를 통해서 구한 UUID는 휴대폰을 초기화 시켜야만 변경하실 수 있습니다.

 어플을 아무리 삭제하여도 UUID는 변경되지 않습니다. (매우 많은 테스트를 해보진 않았습니다.)
	 * @param context
	 * @return
	 */
	public static String getDeviceId(Context context){
		 final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

		 final String tmDevice, tmSerial, androidId;
		 tmDevice = "" + tm.getDeviceId();
		 tmSerial = "" + tm.getSimSerialNumber();
		 androidId = "" + android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

		 UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
		 String deviceId = deviceUuid.toString();
		 Logger.error("고유키  ? : "+deviceId);
		 return deviceId;
	}
	
	public String getDeviceId_old(Context context){
		 TelephonyManager mgr = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
		 return mgr.getDeviceId();

	}
	
	private static final String toUUID(int seed)
	{
		String id = Integer.toString((seed & 0xFFFF), 16);
		int idLen = id.length();
		String uuid = "";
		for (int n=0; n<(4-idLen); n++)
			uuid += "0";
		uuid += id;
		return uuid;
	}

	/**
	 * 시간에 따른 UUID 생성
	 * @Method Name  : createUUID
	 * @작성일   : 2014. 3. 28. 
	 * @작성자   : kimsungkwon
	 * @변경이력  :
	 * @Method 설명 :
	 * @return
	 */
	public static final String createUUID()
	{
		long time1 = System.currentTimeMillis();
		long time2 = (long)(System.currentTimeMillis() * Math.random());
		return
			toUUID((int)(time1 & 0xFFFF)) + "-" +
			toUUID((int)((time1 >> 32) | 0xA000) & 0xFFFF) + "-" +
			toUUID((int)(time2 & 0xFFFF)) + "-" +
			toUUID((int)((time2 >> 32) | 0xE000) & 0xFFFF);
	}
	
}
