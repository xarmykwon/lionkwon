package com.lionkwon.kwonutils.socket;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

import com.lionkwon.kwonutils.bytes.ArrayUtils;
import com.lionkwon.kwonutils.log.Logger;

/**
* @class ClientSocketConnection
* @brief 클라이언트 소켓 인터페이스 제어하는 클래스
*/
public class ClientSocketConnection extends Thread{

	private Socket client = null; ///< 클라이언트 소켓 객체.
	private InputStream in = null; ///< 입력 스트림.
	private OutputStream out = null; ///< 출력 스트림.
	private boolean flag = false; ///< 서버와의 연결 유지 여부.
	private ClientSocketListener listener; ///< 클라이언트 소켓으로 들어오는 데이터를 받는 함수를 가진 인터페이스
	private boolean isConnection = false; ///< 서버 접속 성공 여부.
	
	/**
	 * 클라이언트 소켓 생성자 함수.
	 * @param listener 클라이언트 소켓으로 들어오는 데이터를 받는 함수를 가진 인터페이스
	 * @param host 서버 주소.
	 * @param port 서버 포트
	 * @throws UnknownHostException 서버주소가 잘 못된 포멧으로 되어있으면 오류 발생.
	 * @throws IOException 서버와 접속을 실패하면 오류 발생.
	 */
	public ClientSocketConnection(ClientSocketListener listener,String host, int port) throws UnknownHostException, IOException {
		this.listener = listener;
		Logger.info("ClientSocket Connection Address = "+host+":"+port);
		client = new Socket();
		client.setSoTimeout(0);
		SocketAddress address = new InetSocketAddress(host,port);
		client.connect(address);
		setConfig(client.isConnected());
		Logger.info("ClientSocket Connection ok ");
		
	}

	/**
	 * 서버와 접속 성공 여부를 가져오는 함수.
	 * @return true:성공,false:실패.
	 */
	public boolean getIsConnection(){
		return isConnection;
	}
	
	/**
	 * 서버와 접속 성공 여부를 설정하는 함수.
	 * @param flag true:성공,false:실패.
	 * @throws IOException 클라이언트 소켓이 NULL 인 경우 오류 발생.
	 */
	private void setConfig(boolean flag) throws IOException {
		isConnection = flag;
		if(isConnection) {
			in = client.getInputStream();
			out = client.getOutputStream();
		}
	}
	
	/**
	 * 서버로 데이터를 전송하는 함수.
	 * @param message 전송할 데이타.
	 * @throws IOException OutputStream이 Null인 경우 오류 발생.
	 */
	public void sendMessage(byte[] message) throws IOException {
		out.write(message);
		out.flush();
	}
	
	/**
	 * 서버와 접속을 끊을 경우 사용.
	 */
	public void close() {
		try {
			flag = true;
			if(out != null) out.close();
			if (in != null) in.close();
			if (client != null) client.close();
		} catch (IOException e) {
			Logger.error(e);
		}finally{
			try {
				flag = true;
				if(out != null) out.close();
				if (in != null) in.close();
				if (client != null) client.close();
			} catch (IOException e) {
			}
		}
	}
	
	public void run() {
		
		try{
			while(true) {
				byte[] message = new byte[1024];
				int numByte = in.read(message);
				if(numByte == -1 || flag) break;
				message = ArrayUtils.subarray(message, 0, numByte);
				//인퍼에스를 구현하고 있는 객체로 데이타 전달.
				listener.clientSocketListener(message);
			}
		}catch(IOException ex) {
			Logger.error(ex);
		}finally{
			try {
				if(out != null) out.close();
				if (in != null) in.close();
				if (client != null) client.close();
			} catch (IOException e) {
			}
		}    
	}
}
