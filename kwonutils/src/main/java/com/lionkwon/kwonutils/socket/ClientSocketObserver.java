package com.lionkwon.kwonutils.socket;

import com.lionkwon.kwonutils.log.Logger;

/**
 * @class ClientSocketObserver
 * @brief 클라이언트 소켓의 연결을 관리하기 위한 객체
 * @author kittenjun
 *
 */
public class ClientSocketObserver extends Thread{

	private ClientSocketListener response = null; ///< 클라이언트 소켓으로 들어오는 데이터를 받는 함수를 가진 인터페이스
	private String address = null; ///< 서버 주소.
	private int port = 0; ///< 서버 포트.
	private boolean flag = false; ///< 서버와 연결 유지 여부.
	
	private ClientSocketConnection clientSocket = null; ///< 클라이언트 소켓.
	
	/**
	 * 클라이언트 소켓을 생성하고 서버의 연결을 관리하는 ClientSocketObserver 객체 생성자 함수.
	 * @param listnener 클라이언트 소켓으로 들어오는 데이터를 받는 함수를 가진 인터페이스
	 * @param address 서버 주소.
	 * @param port 서버 포트.
	 */
	public ClientSocketObserver(ClientSocketListener listnener, String address, int port){
    	this.response = listnener;
    	this.address = address;
    	this.port = port;
    	flag = false;
    }
    
	/**
	 * 서버와 연결을 유지를 지속할지 여부를 설정하는 함수.
	 * @param flag true:서버연결 끊음, false:서버연결 유지.
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
    public void run() {
        while(true){
            try{
                if(clientSocket == null){
                	clientSocket = new ClientSocketConnection(response,address,port);
                	clientSocket.start();
                }else{
                   if(!clientSocket.isAlive()){
                	   clientSocket = null;
                	   Logger.debug("disconnection.....");
                   }
                }
            }catch(Exception e){
            	Logger.debug("disconnection.....");
            }finally{
                try {
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					Logger.error(e);
				}
            }
            if(flag) {
            	if(clientSocket != null){
                	clientSocket.close();
	                clientSocket = null;
	            }
            	break;
            }
        }
        Logger.debug("ClientSocketObserver Thread Stop......");
    }
	
    /**
     * 클라이언트 소켓 객체를 가져오는 함수.
     * @return 클라이언트 소켓 인스턴스 반환.
     */
    public ClientSocketConnection getConnector(){
    	return clientSocket;
    }
}
