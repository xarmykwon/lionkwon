package com.lionkwon.kwonutils.socket;

/**
* @class ClientSocketListener
* @brief 클라이언트 소켓으로 부터 수신된 데이타를 처리하는 메소드
*/
public interface ClientSocketListener {

	/**
	 * 클라이언트 소켓으로 수신된 데이터를 전달하는 함수.
	 * @param message 수신된 데이타
	 */
	public void clientSocketListener(byte[] message);
}
