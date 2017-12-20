package com.lionkwon.kwonutils.socket;

import com.lionkwon.kwonutils.log.Logger;
import com.lionkwon.kwonutils.string.StringUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;


/**
* @class ServerSocketConnection
* @brief 서버 소켓 인터페이스 제어하는 클래스
* @remarks 사용예)\n
*          ServerSocketConnection connection = new ServerSocketConnection(listener,"sensor",7679);\n
*          = 포트닫기 =\n
*          connection.close();\n\n
*          = 데이타보내기 =\n
*          connection.sendMessage(byte message[]);\n\n
*          = 데이타 받기 =\n
*          connection.start();\n\n
*          
*          객체가 생성되고 스레드 시작함수 start()를 호출하면\n
*          클라이언트 소켓으로 부터 데이터를 받아 SocketListener를 통해\n
*          데이터를 받을 수 있다.
*          
* @author lionkwon
* @date 2011/01/15
*/
public class ServerSocketConnection implements Runnable{

	/// @example SocketExam.java
	
    //   Declare server socket to listen for client connections
    private ServerSocket serverSocket;
    private Vector v = new Vector();
    private boolean clientFlag = false;
    private ServerSocketListener listener;
    private String description;
    private int serverPort;
    /**
     * \brief
     * ServerSocketConnection 생성자 함수
     * @param listener 서버소켓 데이타 리스너
     * @param description 서버소켓 설명
     * @param port 서버소켓 포트
     */
    public ServerSocketConnection(ServerSocketListener listener,String description, int port) {
        //   Set up the server first of all
        try {
        	this.listener = listener;
        	this.description = description;
        	this.serverPort = port;
        	serverSocket = new ServerSocket(port);
        	Logger.debug("server socket open = "+serverSocket.getLocalPort()+" "+description+" accept...");
            /**   Logger4j INFO   */
        } catch (IOException e) {
        	e.printStackTrace();
        } 
    }
    /**
     * \brief
     *  서버소켓 데이타 리스너 반환 함수
     * @return 데이타 리스너 반환 함수
     */
    public ServerSocketListener getListener() {
    	return listener;
    }
    /**
     * @ingroup interface_group
     * \brief
     *  서버소켓으로 연결된 모든 클라이언트 소켓에게 데이타 보내기
     * @param message 보낼 데이타
     */
    public synchronized void sendMessage(byte[] message) {
    	
    	StringBuffer str = new StringBuffer();
    	String[] index = null;
    	if(v.size() == 0) return;
    	
    	for(int i=0; i < v.size(); i++) {
    		ServerSocketWorker client = ((ServerSocketWorker)v.elementAt(i));
    		client.sendMessage(message);
    		
    		if(client.socketFlag) {
    			str.append(i);
    			str.append(";");
    			clientFlag = true;
    		}
    		
    	}
    	
    	if(clientFlag) {
    		String str1 = str.toString();
    		if(str1.lastIndexOf(";") != -1) str1 = str1.substring(0,str1.lastIndexOf(";")); 
    		index = StringUtils.split(str1, ";");
	    	
	    	for(int i=0; i < index.length -1; i++) {
	    		delClient(Integer.parseInt(index[i]));
	    	}
    	}
    }
    /**
     * @ingroup interface_group
     * \brief
     *  서버소켓으로 연결된 특정 클라이언트 소켓에게 데이타 보내기
     * @param address 클라이언트 소켓 주소
     * @param message 보낼 데이타
     */
    public synchronized void sendMessage(String address, byte[] message) {
    	StringBuffer str = new StringBuffer();
    	String[] index = null;
    	
    	if(v.size() == 0) {
    		return;
    	}
    	
    	for(int i=0; i < v.size(); i++) {
    		ServerSocketWorker client = ((ServerSocketWorker)v.elementAt(i));
    		if(client.getInetAddress().compareTo(address) == 0) {
	    		client.sendMessage(message);
	    		
	    		if(client.socketFlag) {
	    			str.append(i);
	    			str.append(";");
	    			clientFlag = true;
	    		}
	    		
	    	}
    	}
    	
    	if(clientFlag) {
    		String str1 = str.toString();
    		if(str1.lastIndexOf(";") != -1) str1 = str1.substring(0,str1.lastIndexOf(";")); 
    		index = StringUtils.split(str1, ";");
	    	
    		for(int i=0; i < index.length; i++) {
	    		delClient(Integer.parseInt(index[i]));
	    	}
    	}
    	
    }
    /**
     * \brief
     *  연결된 클라이언트 소켓의 수 반환하는 함수
     * @return Vector Size 반환
     */
    public int getVectorSize() {
    	return v.size();
    }
    /**
     * \brief
     *  연결된 특정 클라이언트 객체 반환하는 함수
     * @param address 클라이언트 주소
     * @return 클라이언트 소켓 반환
     */
    public ServerSocketWorker getClient(String address) {
    	for(int i=0; i < v.size(); i++) {
    		ServerSocketWorker client = ((ServerSocketWorker)v.elementAt(i));
    		if(client.getInetAddress().compareTo(address) == 0) {
    			return client;
    		}
    	}
    	
    	return null;
    }
    /**
     * \brief
     *  연결된 특정 클라이언트 객체 반환하는 함수
     * @param address 클라이언트 주소
     * @param port 클라이언트 포트
     * @return 클라이언트 소켓 반환
     */
    public ServerSocketWorker getClient(String address,int port) {
    	for(int i=0; i < v.size(); i++) {
    		ServerSocketWorker client = ((ServerSocketWorker)v.elementAt(i));
    		if(client.getInetAddress().compareTo(address) == 0 && client.getPort() == port) {
    			return client;
    		}
    	}
    	
    	return null;
    }
    /**
     * \brief
     *  클라이언트 소켓 저장 함수
     * @param client 클라이언트 소켓
     */
    public void addClient(ServerSocketWorker client) {
    	v.add(client);
    }
    /**
     * \brief
     *  클라이언트 소켓 삭제 함수
     * @param client 클라이언트 소켓
     */
    public void delClient(ServerSocketWorker client) {
    	Logger.debug("client socket disconnection :: "+client.getInetAddress()+":"+client.getPort());
    	v.removeElement(client);
    	
    }
    /**
     * \brief
     *  클라이언트 소켓 삭제 함수
     * @param count 클라이언트 소켓 저장 인덱스
     */
    public void delClient(int count) {
    	
    	v.remove(count);
    	
    }
    
    /**
     * \brief
     *  서버 소켓 닫기
     * @return boolean 반환
     * @throws IOException
     */
    public boolean close() {
    	boolean flag = true;
    	try {
			serverSocket.close();
		} catch (IOException e) {
			flag = false;
		}
		return flag;
    }
    
    public void run() {

    	try {
            while (true) {
                // listen for and accept the connection
                Socket clientSocket = serverSocket.accept ();
                String name = clientSocket.getInetAddress().getHostAddress();
                int port = clientSocket.getPort();
                Logger.debug("client socket connection : "+name+" : "+port);
                ServerSocketWorker server = new ServerSocketWorker(clientSocket, this);
                new Thread(server).start();
            }
            
        }
        catch (IOException e) {
        	Logger.debug("Server Socket Close : "+description+":"+serverPort);
        }
        
       
    }
}
