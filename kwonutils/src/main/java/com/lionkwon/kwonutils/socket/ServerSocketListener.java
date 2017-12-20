package com.lionkwon.kwonutils.socket;

public interface ServerSocketListener {

	/**
	 * 서버 소켓으로 수신된 데이터를 전달하는 함수
	 * @param description 서버소켓 설명
	 * @param port 서버소켓 포트
	 * @param message 소켓 인터페이스로 부터 받은 데이타
	 */
	public void serverSocketListener(String address, int port, byte[] message);
}
