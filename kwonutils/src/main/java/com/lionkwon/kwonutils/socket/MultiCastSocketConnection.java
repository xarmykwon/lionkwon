package com.lionkwon.kwonutils.socket;

import com.lionkwon.kwonutils.log.Logger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 * @class MultiCastSocketConnection
 * @brief 멀티캐스트 소켓 객체
 * @author lionkwon
 *
 */
public class MultiCastSocketConnection implements Runnable{

	private InetAddress mAddr;
	private MulticastSocket ms = null; 
	private String address = "239.255.255.250";
	private int port = 6001;
	private boolean isRunnable;
	private MultiCastSocketListener listener;
	
	public MultiCastSocketConnection(MultiCastSocketListener listener){
		this.listener = listener;
	}
	
	/**
	 * 멀티캐스트 소켓을 여는 함수
	 * @param address 멀티캐스트 그룹 주소
	 * @param port 멀티캐스트 포트
	 * @return 성공=true; 실패=false
	 */
	public boolean open(String address, int port) {
		this.address = address;
		this.port = port;
		
		boolean flag = true;
		try{
            mAddr = InetAddress.getByName(address);
            ms = new MulticastSocket(port);
            ms.joinGroup(mAddr);
            Logger.debug("MultiCaseSocket OPEN :: "+address+":"+port);
        }catch(UnknownHostException e){
        	Logger.error(e); flag = false;
        }catch (IOException e) {
			// TODO Auto-generated catch block
        	Logger.error(e); flag = false;
		}
        
        return flag;
		
	}
	/**
	 * 멀티캐스트 소켓을 닫는다.
	 */
	public void close() {
		try {
			if(ms!=null){
				ms.leaveGroup(mAddr);
				ms.close();
				ms = null;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			 Logger.error(e);
		}
		Logger.debug("MultiCaseSocket Close");
	}
	
	/**
	 * 멀티캐스트 소켓으로 메시지를 전송하는 함수
	 * @param message 전송할 메시지
	 * @param addr 전송할 주소
	 * @param port 전송할 포트
	 */
	public void sendMessage(String message, String addr, int port) {
		try {
			DatagramPacket dgmPacket = new DatagramPacket(message.getBytes(), message.length(), InetAddress.getByName(addr), port);
			DatagramSocket ssdpUniSock = new DatagramSocket();
			ssdpUniSock.send(dgmPacket);
		}
		catch (Exception e) {
			Logger.error(e);
		}
	}
	
	/**
	 * 멀티캐스트 소켓 그룹으로 설정한 IP 주소를 가져오는 함수
	 * @return IP 주소를 반환
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 멀티캐스트 소켓 그룹으로 설정한 IP 주소를 설정하는 함수
	 * @param address IP 주소
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 멀티캐스트 소켓 채널에 해당하는 PORT를 가져오는 함수
	 * @return PORT를 반환
	 */
	public int getPort() {
		return port;
	}

	/**
	 * 멀티캐스트 소켓 채널에 해당하는 PORT를 설정하는 함수
	 * @param port 멀티캐스트 소켓 포트
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * 멀티캐스트 소켓 스레드가 돌아가고 있는 지 여부를 확인하는 함수
	 * @return 스레드 종료 여부
	 */
	public boolean isRunnable() {
		return isRunnable;
	}

	/**
	 * 멀티케스트 소켓 스레드를 유지할 지를 설정하는 함수
	 * @param isRunnable 스레드 유지=true, 스레드 종료=false
	 */
	public void setRunnable(boolean isRunnable) {
		this.isRunnable = isRunnable;
	}

	/**
	 * 멀티캐스트 소켓으로 들어오는 메시지를 쓰레드를 돌면서 받는 함수
	 */
	public void run() {
        
        byte[] buffer = new byte[1024];
        try {
        	DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
            while(isRunnable) {
            	ms.receive(dp);
                String message = new String(dp.getData(), 0, dp.getLength());
                if(listener != null) listener.multicastSocketReceive(message);
            }
        }catch(IOException ie){
        }finally{
        	isRunnable = false;
        	try {
				if(ms!=null){
					ms.leaveGroup(mAddr);
					ms.close();
					ms = null;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				 Logger.error(e);
			}
			Logger.debug("MultiCaseSocket Close");
        }
    }
}
