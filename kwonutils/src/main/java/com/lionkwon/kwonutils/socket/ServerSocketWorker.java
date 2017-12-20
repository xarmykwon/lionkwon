package com.lionkwon.kwonutils.socket;

import com.lionkwon.kwonutils.bytes.ArrayUtils;
import com.lionkwon.kwonutils.log.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


/**
* @class ServerSocketWorker
* @brief 서버 소켓에 연결된 클라이언트 소켓 처리 클래스
* @remarks 사용예)\n
*          ServerSocketWorker connection = new ServerSocketWorker(clientSocket,serverSocket);\n
*          = 데이타보내기 =\r\n
*          connection.sendMessage(byte message[]);\n\n
*          = 데이타 받기 =\r\n
*          connection.start();\n\n
*          
*          객체가 생성되고 스레드 시작함수 start()를 호출하면\n
*          클라이언트 소켓으로 부터 데이터를 받아 SocketListener를 통해\n
*          데이터를 받을 수 있다.
*          
* @author lionkwon
* @date 2011/01/15
*/
public class ServerSocketWorker implements Runnable{
	
    private Socket socket;
    private InputStream is;
    private OutputStream out;
    private ServerSocketConnection cs;
	public boolean socketFlag = false;
  
	/**
	 * \brief
	 *  클라이언트 소켓 생성함수
	 * @param clientSocket 클라이언트 소켓
	 * @param cs 서버 소켓
	 */
    public ServerSocketWorker(Socket clientSocket, ServerSocketConnection cs) {
        this.socket = clientSocket;
        this.cs = cs;
        try {
            this.socket.setSoTimeout(0);
            is = this.socket.getInputStream();
	        out = this.socket.getOutputStream();
        } catch (IOException e) {
        	Logger.error(e);
        }
    }
    /**
     * \brief
     *  클라이언트 소켓 주소 반환 함수
     * @return 소켓 주소 반환
     */
    public String getInetAddress() {
    	return socket.getInetAddress().getHostAddress();
    }
    /**
     * 
     * \brief
     *  클라이언트 소켓 포트 반환 함수
     * @return 소켓 포트 반환
     */
    public int getPort() {
    	return socket.getPort();
    }
    /**
     * @ingroup interface_group
     * 소켓을 닫음
     * 
     * \brief
     *  클라이언트 소켓 닫기 함수
     */
    public void clientClose() {
    	try {
    		is.close();
			out.close();
			socket.close();
		} catch (IOException e) {

		}finally{
        	try {
				if(is != null) is.close();
				if(out != null) out.close();
				if(socket != null) socket.close();
				cs.delClient(this);
				
			} catch (Exception e) {
				Logger.debug("ServerSocketWorker clientClose Stream Close Error....");
			}
        	
        }  
    }
    /**
     * \brief
     *  클라이언트 소켓으로 데이타 전송 함수
     * @param message 전송 데이타
     */
    public void sendMessage(byte[] message) {
    	try {
			if(out != null) {
				out.write(message);
				
			}else{
				socketFlag = true;
			}
		} catch (IOException e) {
			socketFlag = false;
			Logger.debug("ServerSocketWorker sendMessage IOException Error....");
		}
	}
    
    public void run() {

    	try {
	        cs.addClient(this);
            while(true){
            	byte[] message = new byte[1024];
                int numBytes = is.read(message);
                if(numBytes == -1) break;
                message = ArrayUtils.subarray(message, 0, numBytes);
                cs.getListener().serverSocketListener(getInetAddress(),getPort(),message);
                Thread.sleep(100);   
            }
            
        } catch (IOException e) {
        	Logger.error(e);
        }catch (InterruptedException e) {
			// TODO Auto-generated catch block
        	Logger.error(e);
		}finally{
        	try {
        		if(is != null) is.close();
				if(out != null) out.close();
				if(socket != null)socket.close();

				cs.delClient(this);
			} catch (Exception e) {
				Logger.debug("ServerSocketWorker Thread Stop Stream Close Error....");
			}
        	
        }   
    
    }    
}
