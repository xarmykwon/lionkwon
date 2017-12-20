package com.lionkwon.kwonutils.socket;

/**
 * @class MultiCastSocketListener
 * @brief 멀티캐스트 소켓으로 들어온 데이타를 전달하는 인터페이스
 */
public interface MultiCastSocketListener {

	/**
	 * 멀티캐스트 소켓으로 들어온 데이타를 전달하는 함수
	 * @param message 멀티캐스트 소켓으로 들어온 데이타
	 */
	public void multicastSocketReceive(String message);
}
